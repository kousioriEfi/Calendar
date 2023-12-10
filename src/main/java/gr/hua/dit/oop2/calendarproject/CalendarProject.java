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
         
        try (BufferedReader reader = new BufferedReader(new FileReader("output.ics"))) {
            String line;
            String summary = null;
            String startDate = null;

            SimpleDateFormat iCalendarFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("SUMMARY:")) {
                    // Extracting the event title (SUMMARY)
                    summary = line.substring("SUMMARY:".length());
                } else if (line.startsWith("DTSTART:")) {
                    // Extracting the event start date (DTSTART)
                    startDate = line.substring("DTSTART:".length());

                    // Parse the 'startDate' string using the iCalendar format
                    try {

                        // Format the parsed date using the desired output format
                        Date formattedDate = iCalendarFormat.parse(startDate);
                        // Date date = dateFormat.parse(dateString);

                        // Print the event details
                        if (summary != null) {
                            System.out.println("Title: " + summary);
                            System.out.println("Date: " + formattedDate);
                            System.out.println();
                        }

                        // Reset variables for the next event
                        summary = null;
                        startDate = null;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                // Assuming the end of the event information is marked by "END:VEVENT"
                if (line.equals("END:VEVENT")) {
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
                        
                        
                        System.out.println("Date Ends (yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String durationString = input.nextLine();
                        
                        Date duration = dateFormat.parse(durationString);
                        
                        
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        Appointment myAppointment = new Appointment(title, date, description, duration);
                        
                        eventsList.add(myAppointment);
                        // Writing to an iCalendar (.ics) file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
                            
                            
                            writer.write("BEGIN:VCALENDAR\n");
                            writer.write("VERSION:2.0\n");

                            for (Event event : eventsList) {
                                // Add an event
                                writer.write("BEGIN:VEVENT\n");
                                writer.write("SUMMARY:"+ event.getTitle()+"\n");

                            if (event instanceof Appointment) {
                                Appointment appointment = (Appointment) event;
                                writer.write("DTSTART:" + dateFormat.format(appointment.getDate()) + "\n");
//                                writer.write("DURATION:" + dateFormat.format(appointment.getDuration()) + "\n");
                                
                                    
                                    writer.write("DTEND:" + dateFormat.format(appointment.getDuration()) + "\n");
                                }
                                
                                writer.write("DESCRIPTION:"+ event.getDescription()+"\n");
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
                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Current Date and Time");
                        String dateString = input.nextLine();
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

                        Date date = dateFormat.parse(dateString);
                        
                        
                        System.out.println("Project due date and time(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String dueDateTimeString = input.nextLine();
                        
                        Date dueDateTime = dateFormat.parse(dueDateTimeString);
                        
                        
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        System.out.println("Give status of the project (true/false)");
                        boolean status = input.nextBoolean();
                        
                        Project myProject = new Project(title, description, dueDateTime,status);
                        
                        eventsList.add(myProject);
                        // Writing to an iCalendar (.ics) file
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
                            
                            
                            writer.write("BEGIN:VCALENDAR\n");
                            writer.write("VERSION:2.0\n");

                            for (Event event : eventsList) {
                                // Add an event
                                if (event instanceof Appointment) {
                                    Appointment appointment = (Appointment) event; 
                                    writer.write("BEGIN:VEVENT\n");
                                    writer.write("SUMMARY:"+ event.getTitle()+"\n");


                                    writer.write("DTSTART:" + dateFormat.format(appointment.getDate()) + "\n");                                
                                    writer.write("DTEND:" + dateFormat.format(appointment.getDuration()) + "\n");
                                    writer.write("DESCRIPTION:"+ event.getDescription()+"\n");
                                    writer.write("END:VEVENT\n");
                                }
                                else if (event instanceof Project){
                                    Project project =(Project) event;
                                    writer.write("BEGIN:VTODO\n");
                                    writer.write("DUE;VALUE=DATE:"+ dateFormat.format(project.getDueDateTime())+"\n");
                                    writer.write("SUMMARY:"+ event.getTitle()+"\n");
                                    if(project.getStatus()== true){
                                        writer.write("STATUS:NEEDS-ACTION\n");
                                    }else if(project.getStatus()== false){
                                        writer.write("STATUS:DOESNT-NEED-ACTION\n");
                                    }
                                    writer.write("DESCRIPTION:"+ event.getDescription()+"\n");
                                    writer.write("END:VTODO\n");
                                    
                                }
                                
                                System.out.println("Event added: " + event.getTitle());
                            }
                            // End the calendar
                            writer.write("END:VCALENDAR\n");

                            System.out.println("iCalendar file created successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
       
                        
                    
                    }
                    
                        
//                        System.out.println("Add dueDate");
//                        String dueDate = input.nextLine();
//            
//                        System.out.println("DueTime");
//                        String dueTime = inputSystem.nextLine();
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
//                        
//            
//                        System.out.println("Add description");
//                        String description = input.nextLine();
//                        
//                        Event myEvent = new Project(title ,date, description,dueDate, dueTime, status);
//            
//                        eventsList.add(myEvent);
//            
//                        System.out.println("Project added: " + myEvent.getTitle() + " on " + myEvent.getDate());
//                    
                    
                    
                    
                }
             
                
            }
        }
        

//            Event myEvent = new Event("Meeting", "20231215", "Discuss project progress"); 
//        
//        eventsList.add(testEvent);
//        eventsList.add(testProject);

        for(int i=0;i<eventsList.size();i++){
           eventsList.get(i).printEvent();
            
            }
    }
}



///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// */
//
//package gr.hua.dit.oop2.calendarproject;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import gr.hua.dit.oop2.calendar.TimeService;
//import gr.hua.dit.oop2.calendar.TimeTeller;
//
//
///**
// *
// * @author leftp
// */
//public class CalendarProject {
//    public static void main(String[] args) throws ParseException {
//         
//        Scanner input = new java.util.Scanner(System.in);
//        ArrayList<Event> eventsList = new ArrayList<>();
//        
//        if (args.length < 2) {
//            System.out.println("Usage: java -jar calendar.jar <calendarType> <filename.ics>");
//            return;
//        }
//        
//        
//        String calendarChoice = args[0].toLowerCase();
//        String fileName = args[1];
//        
//        switch (args.length){
//            case 1->{
//                switch(calendarChoice){
//                    case "day" -> {
// //                       calendarDay();
//                    }
//                    case "week"-> {
//  //                      calendarWeek();
//                    }
//                    case "month"->{
//  //                      calendarMonth();
//
//                    }
//                    case "pastday"->{
//   //                     calendarPastday();
//
//                    }
//                    case "pastweek"->{
//  //                      calendarPastweek();
//
//                    }
//                    case "pastmonth"->{
// //                       calendarPastmonth();
//                    }
//                    case "todo"->{
//  //                      calendarTodo();
//                    }
//                    case "due"->{
////                        calendarDue();
//                    }
//                    default->{
//                        break;
//                    }
//                }
//            }
//            case 2->{
//                System.out.println("Add appointment (Press 1)");
//                System.out.println("Add project (Press 2)");
//               
//                int ev_opt = requestChoice(2);              
//                
//                switch(ev_opt){
//                    case 1->{
//                        System.out.println("Add title");
//                        String title = input.nextLine();
//            
//                        System.out.println("Date");
//                        String dateString = input.nextLine();
//                        
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z's");
//
//                        Date date = dateFormat.parse(dateString);
//                        
//                        System.out.println("Duration");
//                        String duration = input.nextLine();
//                        
//                        
//                        System.out.println("Add description");
//                        String description = input.nextLine();
//                        
//                        Event myEvent = new Appointment(duration, title,Date date, description);
//            
//                        eventsList.add(myEvent);
//                        // Writing to an iCalendar (.ics) file
//                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
//                            
//                            
//                            writer.write("BEGIN:VCALENDAR\n");
//                            writer.write("VERSION:2.0\n");
//
//                            for (Event event : eventsList) {
//                                // Add an event
//                                writer.write("BEGIN:VEVENT\n");
//                                writer.write("SUMMARY:"+ event.getTitle()+"\n");
//
//
//                                writer.write("DTSTART:" + dateFormat.format(event.getDate()) + "\n");
//                                //writer.write("DTEND:" + endDate + "\n");
//
//                                // End the event
//                                writer.write("END:VEVENT\n");
//                            }
//                            // End the calendar
//                            writer.write("END:VCALENDAR\n");
//
//                            System.out.println("iCalendar file created successfully.");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//       
//                        System.out.println("Appointment added: " + myEvent.getTitle() + " on " + myEvent.getDate());
//                    
//                    }
//                    
//                    case 2->{
//                                                
//                        String dueDate;
//                        String dueTime;
//                        String userInput;
//                        boolean status;
//                        String title;   
//                        String date;
//                        String description;
//                       
//                        eventsList = new ArrayList<>();
//                        
//                        System.out.println("Add dueDate");
//                        dueDate = input.nextLine();
//            
//                        System.out.println("DueTime");
//                        dueTime = input.nextLine();
//                        
//                        System.out.println("Enter true or false:");
//                        userInput = input.nextLine();
//
//                        // Parse the string into a boolean
//                        status = Boolean.parseBoolean(userInput);
//
//                        System.out.println("Add title");
//                        title = input.nextLine();
//            
//                        System.out.println("Date");
//                        date = input.nextLine();
//            
//            
//                        System.out.println("Add description");
//                        description = input.nextLine();
//                      
//                        Event myEvent = new Project(dueDate, dueTime, status,title ,Date date , description);
//            
//                        eventsList.add(myEvent);
//            
//                        System.out.println("Project added: " + myEvent.getTitle() + " on " + myEvent.getDate());
//                    }
//                }
//            }
//        }    
//        // Reading from an iCalendar (.ics) file
////        try (BufferedReader reader = new BufferedReader(new FileReader("output.ics"))) {
////            String line;
////            String summary = null;
////            String startDate = null;
////
////            while ((line = reader.readLine()) != null) {
////                if (line.startsWith("SUMMARY:")) {
////                    // Extracting the event title (SUMMARY)
////                    summary = line.substring("SUMMARY:".length());
////                } else if (line.startsWith("DTSTART:")) {
////                    // Extracting the event start date (DTSTART)
////                    startDate = line.substring("DTSTART:".length());
////                }
//////dateFormat.format(event.getDate())
////                // Assuming the end of the event information is marked by "END:VEVENT"
////                if (line.equals("END:VEVENT")) {
////                    // Print the event details
////                    if (summary != null && startDate != null) {
////                        System.out.println("Title: " + summary);
////                        System.out.println("Date: " + startDate);
////                        System.out.println();
////                    }
////
////                    // Reset variables for the next event
////                    summary = null;
////                    startDate = null;
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////      
//
////            Event myEvent = new Event("Meeting", "20231215", "Discuss project progress"); 
////        
////        eventsList.add(testEvent);
////        eventsList.add(testProject);
//        
//        //System.out.println(testEvent.getDescription());
//        //System.out.println(testProject.getDueDate());
//
//    
//}}