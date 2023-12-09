/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import gr.hua.dit.oop2.calendar.TimeService;
//import gr.hua.dit.oop2.calendar.TimeTeller;


/**
 *
 * @author leftp
 */
public class CalendarProject {
    public static void main(String[] args) throws ParseException {
         
        // Reading from an iCalendar (.ics) file
        try (BufferedReader reader = new BufferedReader(new FileReader("output.ics"))) {
            String line;
            String summary = null;
            String startDate = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SUMMARY:")) {
                    // Extracting the event title (SUMMARY)
                    summary = line.substring("SUMMARY:".length());
                } else if (line.startsWith("DTSTART:")) {
                    // Extracting the event start date (DTSTART)
                    startDate = line.substring("DTSTART:".length());
                }
//dateFormat.format(event.getDate())
                // Assuming the end of the event information is marked by "END:VEVENT"
                if (line.equals("END:VEVENT")) {
                    // Print the event details
                    if (summary != null && startDate != null) {
                        System.out.println("Title: " + summary);
                        System.out.println("Date: " + startDate);
                        System.out.println();
                    }

                    // Reset variables for the next event
                    summary = null;
                    startDate = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      
       
        
        Scanner input = new java.util.Scanner(System.in);
        ArrayList<Event> eventsList = new ArrayList<>();
        
        System.out.println("Add event (Press 1)");
        System.out.println("Edit event (Press 2)");
        
        int opt = input.nextInt();
        input.nextLine();
        
        switch (opt){
        
            case 1 :{
                
                System.out.println("Add appointment (Press 1)");
                System.out.println("Add project (Press 2)");
                
                int ev_opt = input.nextInt();
                input.nextLine();
                
                switch (ev_opt) { 
                    
                    case 1:{
                    
                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Date");
                        String dateString = input.nextLine();
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

                        Date date = dateFormat.parse(dateString);
                        
                        System.out.println("Duration");
                        String duration = input.nextLine();
            
                        System.out.println("Time");
                        String time = input.nextLine();
                        
                        
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        Appointment myAppointment = new Appointment(title, date, time, description, duration);
            
                        eventsList.add(myAppointment);
                        // Writing to an iCalendar (.ics) file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
                            
                            
                            writer.write("BEGIN:VCALENDAR\n");
                            writer.write("VERSION:2.0\n");

                            for (Event event : eventsList) {
                                // Add an event
                                writer.write("BEGIN:VEVENT\n");
                                writer.write("SUMMARY:"+ event.getTitle()+"\n");


                                writer.write("DTSTART:" + dateFormat.format(event.getDate()) + "\n");
                                //writer.write("DTEND:" + endDate + "\n");

                                // End the event
                                writer.write("END:VEVENT\n");
                            }
                            // End the calendar
                            writer.write("END:VCALENDAR\n");

                            System.out.println("iCalendar file created successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
       
                        System.out.println("Appointment added: " + myAppointment.getTitle() + " on " + myAppointment.getDate());
                    
                    }
                    
                    case 2 : {
                        
//                        System.out.println("Add dueDate");
//                        String dueDate = input.nextLine();
//            
//                        System.out.println("DueTime");
//                        String dueTime = input.nextLine();
//                        
//                        System.out.println("Enter true or false:");
//                        String userInput = input.nextLine();
//
//                        // Parse the string into a boolean
//                        boolean status = Boolean.parseBoolean(userInput);
//
//                        System.out.println("Add title");
//                        String title = input.nextLine();
//            
//                        System.out.println("Date");
//                        String date = input.nextLine();
//            
//                        System.out.println("Time");
//                        String time = input.nextLine();
//            
//                        System.out.println("Add description");
//                        String description = input.nextLine();
//                        
//                        Event myEvent = new Project(title ,date , time, description,dueDate, dueTime, status);
//            
//                        eventsList.add(myEvent);
//            
//                        System.out.println("Project added: " + myEvent.getTitle() + " on " + myEvent.getDate());
//                    
                    }
                    
                    
                }
             
                
            }
        }
        

//            Event myEvent = new Event("Meeting", "20231215", "15:30", "Discuss project progress"); 
//        
//        eventsList.add(testEvent);
//        eventsList.add(testProject);
        
        //System.out.println(testEvent.getDescription());
        //System.out.println(testProject.getDueDate());
        for(int i=0;i<eventsList.size();i++){
           eventsList.get(i).printEvent();
            
            }
    }
}