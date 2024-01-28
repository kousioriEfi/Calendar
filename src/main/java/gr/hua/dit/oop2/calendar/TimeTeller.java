/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

import java.time.LocalDateTime;

/**
 *
 * @author leftp
 */
public interface TimeTeller {

    public LocalDateTime now();

    public void addTimeListener(TimeListener timeListener);
    
    
}
