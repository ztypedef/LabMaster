/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argos.labmaster.module;

import com.argos.controlx.Modbus.MbEvent.MbStatus;
import com.z.statemachine.StateFMS;
import java.util.Arrays;


/**
 *
 * @author Zaparivanny Pavel
 */
public abstract class DeviceBehavior {
    private final int slaveAddress;
    private final int functionCode;
    private StateFMS[] sfms;
    private MbStatus[] ms;

    public DeviceBehavior(int slaveAddress, int functionCode) {
        this.slaveAddress = slaveAddress;
        this.functionCode = functionCode;
        sfms = new StateFMS[0];
        ms = new MbStatus[0];
        
    }
    
    public void addRule(StateFMS s, MbStatus m){
        sfms = Arrays.copyOf(sfms, sfms.length + 1);
        ms = Arrays.copyOf(ms, ms.length + 1);
        sfms[sfms.length - 1] = s;
        ms[ms.length - 1] = m;
    }
    
    
    public int getSlaveAddress(){
        return slaveAddress;
    }
    
    public int getFunctionCode(){
        return functionCode;
    }
    
    public StateFMS[] getStateFMS(){
        
        return sfms;
    }
    
    public MbStatus[] getMbStatus(){
        
        return ms;
    }

}
