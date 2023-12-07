/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author leftp
 */
public class CalendarProject {
    public static void main(String[] args) {
         // Writing to an iCalendar (.ics) file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics"))) {
            // Write iCalendar header
            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");
            writer.write("PRODID:-//Your Organization//Your Application//EN\n");

            // Add an event
            writer.write("BEGIN:VEVENT\n");
            writer.write("SUMMARY:Hello, File I/O!\n");

            // Set the event start and end time (here, it's set for the current date and time)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            String startDate = dateFormat.format(new Date());
            String endDate = dateFormat.format(new Date(System.currentTimeMillis() + 3600000)); // Adding 1 hour

            writer.write("DTSTART:" + startDate + "\n");
            writer.write("DTEND:" + endDate + "\n");

            // End the event
            writer.write("END:VEVENT\n");

            // End the calendar
            writer.write("END:VCALENDAR\n");

            System.out.println("iCalendar file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
       
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
                        
                        System.out.println("Add dueDate");
                        String dueDate = input.nextLine();
            
                        System.out.println("DueTime");
                        String dueTime = input.nextLine();
                        
                        System.out.println("Enter true or false:");
                        String userInput = input.nextLine();

                        // Parse the string into a boolean
                        boolean status = Boolean.parseBoolean(userInput);

                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Date");
                        String date = input.nextLine();
            
                        System.out.println("Time");
                        String time = input.nextLine();
            
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        Event myEvent = new Project(dueDate, dueTime, status,title ,date , time, description);
            
                        eventsList.add(myEvent);
            
                        System.out.println("Project added: " + myEvent.getTitle() + " on " + myEvent.getDate());
                    
                    }
                    
                    
                }
             
                
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
    }
}