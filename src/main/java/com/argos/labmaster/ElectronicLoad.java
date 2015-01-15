/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argos.labmaster;

import com.argos.labgui.gui.unit.Unit;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Zaparivanny Pavel
 */
public class ElectronicLoad extends Unit{

    
    private final JTextField uText;
    private final JTextField iText;
    private final JTextField tText;
    
    private JButton b_delete;
    private JButton p_res0;
    private JButton p_res1;

    
    public ElectronicLoad(int slaveAddress) {
        super(slaveAddress);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); 
        uText = new JTextField(3);
        iText = new JTextField(3);
        tText = new JTextField(3);
        
        initTextBox(uText);
        initTextBox(iText);
        initTextBox(tText);
        
        // For Voltage
        gbc.gridy = 0; 
        gbc.gridx = 0; 
        add(new JLabel("U ="), gbc); 
        gbc.gridx = 1; 
        add(uText, gbc);
        gbc.gridx = 2; 
        add(new JLabel("(V)"), gbc);
        
        // For current
        gbc.gridy = 1; 
        gbc.gridx = 0; 
        add(new JLabel("I  ="), gbc); 
        gbc.gridx = 1; 
        add(iText, gbc);
        gbc.gridx = 2; 
        add(new JLabel("(mA)"), gbc); 
        
        // For temperature
        gbc.gridy = 2; 
        gbc.gridx = 0; 
        add(new JLabel("T ="), gbc); 
        gbc.gridx = 1; 
        add(tText, gbc);
        gbc.gridx = 2; 
        add(new JLabel("(C)"), gbc);
        
        b_delete = new JButton("D");
        p_res0 = new JButton("S");
        p_res1 = new JButton("R");
        
        b_delete.setFocusable(false);
        p_res0.setFocusable(false);
        p_res1.setFocusable(false);
        
        gbc.gridy = 3; 
        gbc.gridx = 0; 
        add(p_res0, gbc); 
        gbc.gridx = 1; 
        add(p_res1, gbc);
        gbc.gridx = 2;
        add(b_delete, gbc);
    }
    
    private void initTextBox(JTextField jtf){
        jtf.setText("0");
        Font font1 = new Font("SansSerif", Font.BOLD, 16);
	jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.setFont(font1);
    }

    @Override
    public void setData(int[] pack) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
