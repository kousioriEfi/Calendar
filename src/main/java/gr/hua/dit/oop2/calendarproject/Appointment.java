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
    private String duration;

    public Appointment(String duration, String title, Date date, String time, String description) {
        super(title, date, time, description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
}

