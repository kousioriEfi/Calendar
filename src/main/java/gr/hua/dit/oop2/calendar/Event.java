/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

//Libraries used for the Event class
import java.time.LocalDateTime;

/**
 *
 * @author leftp
 */
public class Event {
    private String title;
    private String description;
    private LocalDateTime date;





    public Event(String title , String description , LocalDateTime date) { //Constructor for the Event
        this.title = title;
        this.description = description;
        this.date = date;
    }

   
    //Setters/getters
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
    
        public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }    
     
    public void printEvent(){
        System.out.println("Τίτλος: " + getTitle() + "\tDate: " + getDate());
    }
}
