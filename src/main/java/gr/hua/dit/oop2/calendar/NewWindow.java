/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author stamatis
 */
public class NewWindow {
    
    JFrame winFrame = new JFrame();
    JPanel winBluePanel = new JPanel();
    
    NewWindow(){
    
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setSize(500,500);
        winFrame.setVisible(true);
        winFrame.add(winBluePanel);
    
    }
    
}
