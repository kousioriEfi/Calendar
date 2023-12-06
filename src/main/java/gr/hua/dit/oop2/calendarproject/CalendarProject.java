/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author leftp
 */
public class CalendarProject {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
           
        java.util.Scanner input = new java.util.Scanner(System.in);
        ArrayList<Event> eventsList = new ArrayList<>();
        
        System.out.println("Add event (Press 1)");
        System.out.println("Edit event (Press 2)");
        
        int opt = input.nextInt();
        input.nextLine();
        
        switch (opt){
        
            case 1 -> {
                
                System.out.println("Add appointment (Press 1)");
                System.out.println("Add project (Press 2)");
                
                int ev_opt = input.nextInt();
                input.nextLine();
                
                switch (ev_opt) { 
                    
                    case 1 -> {
                    
                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Date");
                        String date = input.nextLine();
                        
                        System.out.println("Duration");
                        String duration = input.nextLine();
            
                        System.out.println("Time");
                        String time = input.nextLine();
            
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        Event myEvent = new Appointment(duration, title, date, time, description);
            
                        eventsList.add(myEvent);
            
                        System.out.println("Appointment added: " + myEvent.getTitle() + " on " + myEvent.getDate());
                    
                    }
                    
                    case 2 -> {
                    
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
            
                        System.out.println("Project added: " + myEvent.getTitle() + " on " + myEvent.getDate());
                    
                    }
                }
             
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
        }
        
        Event testEvent = new Event("birthday","28/12/2003","11:30","oxi");
        Project testProject = new Project("15/12/2023","12:00",true,"ergasia","6/12/2023","4:49","nai"); 
        
        eventsList.add(testEvent);
        eventsList.add(testProject);
        
        //System.out.println(testEvent.getDescription());
        //System.out.println(testProject.getDueDate());
        for(int i=0;i<eventsList.size();i++){
           eventsList.get(i).printEvent();
            
            }
        //        FileOutputStream eventsFile = new FileOutputStream("events.txt");
//        ObjectOutputStream objectEvents = new ObjectOutputStream(eventsFile);
//        objectEvents.writeObject(eventsList);
        FileInputStream fis = new FileInputStream("events.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        eventsList = (ArrayList<Event>) ois.readObject();
        for(Event myEvent: eventsList){
            myEvent.printEvent();
            
            }
    }
}
