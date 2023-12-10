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
public class Event {
    private String title;
    private String description;
    private Date date;





    public Event(String title , String description , Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

   

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
        public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }    
     
    public void printEvent(){
        System.out.println("Τίτλος: " + getTitle());
    }
}
