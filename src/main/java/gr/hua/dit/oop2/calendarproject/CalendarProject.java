/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;
import java.util.ArrayList;

/**
 *
 * @author leftp
 */
public class CalendarProject {

    public static void main(String[] args) {
           
        java.util.Scanner input = new java.util.Scanner(System.in);
        ArrayList<Event> eventsList = new ArrayList<>();
        
        System.out.println("Add event (Press 1)");
        System.out.println("Edit event (Press 2)");
        
        int opt = input.nextInt();
        input.nextLine();
        
        if ( opt == 1){
             
            System.out.println("Add title");
            String title = input.nextLine();
            
            System.out.println("Date");
            String date = input.nextLine();
            
            System.out.println("Time");
            String time = input.nextLine();
            
            System.out.println("Add description");
            String description = input.nextLine();
        
            Event myEvent = new Event(title ,date , time, description);
            
            eventsList.add(myEvent);
            
            System.out.println("Event added: " + myEvent.getTitle() + " on " + myEvent.getDate());
        }
        
        Event testEvent = new Event("birthday","28/12/2003","11:30","oxi");
        Project testProject = new Project("15/12/2023","12:00",true,"ergasia","6/12/2023","4:49","nai");
        
        System.out.println(testEvent.getDescription());
        System.out.println(testProject.getDueDate());
    }
}
