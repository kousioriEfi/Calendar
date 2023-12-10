/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendarproject;

import java.util.Date;

/**
 *
 * @author leftp
 */
public class Appointment extends Event{
    private Date duration;

    public Appointment(String title,String description, Date date, Date duration) {
        super(title,description,date);
        this.duration = duration;
    }
  
    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }
    
}

