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
public class Project extends Event{
    private boolean status;

    public Project(String title,String description, LocalDateTime date, boolean status) {
        super(title,description,date);
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

