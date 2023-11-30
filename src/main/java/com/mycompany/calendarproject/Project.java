/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendarproject;

/**
 *
 * @author leftp
 */
public class Project extends Event{
    private String dueDate;
    private String dueTime;
    private boolean status;

    public Project(String dueDate, String dueTime, boolean status, String date, String title, String time, String description) {
        super(date, title, time, description);
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

