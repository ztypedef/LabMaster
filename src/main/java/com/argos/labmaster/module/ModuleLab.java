/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argos.labmaster.module;

import com.argos.labgui.gui.perspective.Perspective;
import java.util.ArrayList;

/**
 *
 * @author Zaparivanny Pavel
 */
public  class ModuleLab {
    private final ArrayList<DeviceBehavior> listDevice;
    private Perspective persp; 

    public ModuleLab() {
        listDevice = new ArrayList<>();
    }
    
    public void addDevice(DeviceBehavior db){
        listDevice.add(db);
    }
    
    public void setPerspective(Perspective p){
        persp = p;
    }
    
    public Perspective getPerspective(){
        return persp;
    }
    
    public ArrayList<DeviceBehavior> getListDevice(){
        return listDevice;
    }
    
    public abstract class DeviceModule{
        public abstract void okState();
        public abstract void timeoutState();
    }
}
