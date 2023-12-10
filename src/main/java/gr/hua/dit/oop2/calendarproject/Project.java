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
public class Project extends Event{
    private Date dueDateTime;
    private boolean status;

    public Project(String title,String description, Date dueDateTime, boolean status) {
        super(title,description);
        this.dueDateTime = dueDateTime;
        this.status = status;
    }

    public Date getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(Date dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

