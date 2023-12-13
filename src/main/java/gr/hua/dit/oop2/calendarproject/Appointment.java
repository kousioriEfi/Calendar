/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendarproject;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author leftp
 */
public class Appointment extends Event{
    private LocalDateTime duration;

    public Appointment(String title,String description, LocalDateTime date, LocalDateTime duration) {
        super(title,description,date);
        this.duration = duration;
    }
  
    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }
    
}

