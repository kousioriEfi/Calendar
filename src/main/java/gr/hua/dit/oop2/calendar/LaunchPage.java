/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author stamatis
 */
public class LaunchPage implements ActionListener {
    
    JFrame winFrame = new JFrame();
    JButton myButton = new JButton();
    JPanel winBluePanel = new JPanel();
    
   
                    
                
    
    LaunchPage(){
        
        myButton.setBounds(100, 160, 200, 40);
        myButton.addActionListener(this);
    
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setSize(500,500);
        winFrame.setLayout(null);
        winFrame.setVisible(true);
        winFrame.add(myButton);
        winFrame.add(winBluePanel);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
