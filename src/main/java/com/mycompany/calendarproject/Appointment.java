/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendarproject;

/**
 *
 * @author leftp
 */
public class Appointment extends Event{
    private String duration;

    public Appointment(String duration, String date, String title, String time, String description) {
        super(date, title, time, description);
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    
}

