/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

//Libraries used for appointment class
import java.time.LocalDateTime;

/**
 *
 * @author leftp
 */
public class Appointment extends Event{ //Methods the subclass appointment inherit from the superclass Event
    private LocalDateTime duration;

    public Appointment(String title,String description, LocalDateTime date, LocalDateTime duration) { //Constructor for appointment
        super(title,description,date);
        this.duration = duration;
    }
    
    //Setters/getters
    public LocalDateTime getDuration() {
        return duration;
    }

    public void setDuration(LocalDateTime duration) {
        this.duration = duration;
    }
    
}

