/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argos.labmaster;

import com.argos.examplelabmodule.ExampleModule;
import com.argos.labmaster.module.ModuleLoad;
import com.z.statemachine.FiniteStateMachine;

/**
 *
 * @author Zaparivanny Pavel
 */
public class App {
    public static void main(String[] args) {
        FiniteStateMachine f = new FiniteStateMachine();
        ModuleLoad mload = new ModuleLoad(f);
        int id = mload.addModule(new ExampleModule());
        mload.loadModule(id);  
        mload.start();
    }
}
