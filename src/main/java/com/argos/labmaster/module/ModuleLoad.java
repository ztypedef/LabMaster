/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argos.labmaster.module;

import com.argos.controlx.Modbus.MbEvent;
import com.argos.controlx.Modbus.Modbus;
import com.argos.labgui.gui.AFrame;
import com.argos.labgui.gui.perspective.Perspective;
import com.z.statemachine.ActionFMS;
import com.z.statemachine.FiniteStateMachine;
import com.z.statemachine.StateFMS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 *
 * @author Zaparivanny Pavel
 */
public class ModuleLoad {

    private final Map<Integer, ModuleLab> mapdb;
    private int countdb = 0;
    private FiniteStateMachine fms;
    private final AFrame frame;
    Modbus mb;
    
    public ModuleLoad(FiniteStateMachine f) {
        mapdb = new HashMap<>();
        fms = f;
        fms.addActionFMS(aFMS);
        frame = new AFrame();
        mb = new Modbus();
        try {
            mb.openPort("/dev/ttyUSB0");
        } catch (SerialPortException ex) {
            Logger.getLogger(ModuleLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        mb.addActionMb(f.mbAction);
    }

    public int addModule(ModuleLab m){
        mapdb.put(countdb++, m);
        return countdb - 1;
    }
    
    public void loadModule(int id){
        ModuleLab m = mapdb.get(id);
        frame.addPerspective(m.getPerspective());
        if(m == null) return;
        ArrayList<DeviceBehavior> listd = m.getListDevice();
        for(int i = 0; i < listd.size(); i++){
            DeviceBehavior d = listd.get(i);
            int idfms = fms.addCondition(d.getSlaveAddress(), d.getFunctionCode());
            StateFMS[] sfms = d.getStateFMS();
            MbEvent.MbStatus mb[] = d.getMbStatus();
            for(int j = 0; j < sfms.length; j++){
                fms.addExecution(idfms, mb[j], sfms[j]);
            }
        }
    }
    
    
    public void start(){
        
         javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                frame.addCommand("Start", Start, frame.RIGHT_BUTTTON | frame.TOOL_FUNCTION);
                frame.addCommand("Stop", Stop, frame.RIGHT_BUTTTON | frame.TOOL_FUNCTION);
       
                frame.addPerspective(new Perspective("view_2"));
            }
        });
        try {
            synchronized(fms){
                fms.wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ModuleLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] pack = new byte[3];
        pack[0] = 0x1;
        pack[1] = 0x0;
        pack[2] = 0x4;
        fms.start(pack);
        System.out.println("[Threads] exit");
        
    }
    
    
        
    private ActionListener Start = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Start");
            synchronized(fms){
                fms.notify();
            }
        }
    };
    
    private ActionListener Stop = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Stop");
            fms.stop();
        }
    };
    
    
    ActionFMS aFMS = new ActionFMS(){

        @Override
        public void actionPerfomed(byte[] pack) {
            mb.send(pack);
        }  
    };
}
