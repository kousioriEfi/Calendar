/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    */

   //All libraries used throughout the code
package gr.hua.dit.oop2.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
   /**
    *
    * @author leftp
    */
public class CalendarProject{
    private static JPanel bluePanel;
    private static JPanel labelPanel;
    private static JPanel eventsPanel;
    private static LocalDateTime chosenDate;
    private static ArrayList<Event> eventsList = new ArrayList<>();//A list created to add events(Appointments and projects)
    private static JLabel chosenDateLabel = new JLabel("Current Date: ");

    //BEGINNING OF MAIN
    public static void main(String[] args) throws ParseException {
        
        Scanner input = new java.util.Scanner(System.in);

        
        TimeTeller teller = TimeService.getTeller();     
//      String calendarChoice = args[0].toLowerCase();
        String fileName = null;
//        if(args.length == 1){
//              fileName = args[0];
//        }else{
//             fileName = args[1];
//        }

        fileName="calendar.ics";        //getDirectoryPath()+ "//" + fileName ;

//        int year=requestInt("Year");
//        int month=requestChoice2(12,"Month (up to 12)");
//        int day=requestChoice2(30,"Day (up to 30)" );
//        int hours=requestChoice(23,"Hours(up to 23)");
//        int minutes=requestChoice(59,"Minutes(up to 59)");
//        int seconds=requestChoice(59,"Seconds(up to 59)");
//
//        LocalDateTime time = LocalDateTime.of(year, month, day, hours, minutes, seconds);//The info for a specific day is added to a LocalDateTime variable
//        LocalDateTime dt = teller.now();
//
//        System.out.println(time.adjustInto(dt));
//        Temporal timeNow = time.adjustInto(dt);

        eventsList = readAppointmentsFromFile(fileName, eventsList);//The ics file gets all the appointments
        eventsList = readProjectsFromFile(fileName, eventsList);//The ics file gets all the projects

        Comparator<Event> eventComparator = Comparator.comparing(Event::getDate);//A comparator created to compare the events besed on their day
        eventsList.sort(eventComparator);//EventList is getting sorted by their date with the comparator
        
         SwingUtilities.invokeLater(() -> {
            JFrame frame = createMainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
         
//        switch (args.length){
//
//            case 1 :{ //Case the option is add an event
//               //Message in order for the user to decide wether they want to add an appointment or project
//               System.out.println("Create Appointment /Project |Type(1/2)");
//
//               int ev_opt = requestChoice2(2,"1/2");
//               input.nextLine();
//
//                switch (ev_opt) { 
//
//                    case 1:{ //Case they chose to add appointment
//
//                       System.out.println("Add title");
//                       String title = input.nextLine();
//
//                       System.out.println("Date(yyyyMMddTHHmmssZ) example 20231226T163000Z");
//                       String dateString = input.nextLine();
//
//                       //Instant instant = dateString.toInstant;
//                       //LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//                       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
//                       LocalDateTime date = LocalDateTime.parse(dateString, formatter);
//                       System.out.println("Converted LocalDateTime: " + date);
//                       //Date date = dateFormat.parse(dateString);
//
//                       System.out.println("Date Ends (yyyyMMddTHHmmssZ) example 20231226T163000Z");
//                       String durationString = input.nextLine();
//
//                       LocalDateTime duration = LocalDateTime.parse(durationString, formatter);
//                       System.out.println("Add description");
//                       String description = input.nextLine();
//
//                       Appointment myAppointment = new Appointment(title,description,date,duration);
//                       writeAppointmentToFile(myAppointment,eventsList,fileName);
//
//                       break;
//
//
//                    }case 2 : { //Case they want to add a project
//
//                        System.out.println("Add title");
//                        String title = input.nextLine();
//
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
//                        System.out.println("Project due date and time(yyyyMMddTHHmmssZ) example 20231226T163000Z");
//                        String dueDateTimeString = input.nextLine();
//                        LocalDateTime dueDateTime = LocalDateTime.parse(dueDateTimeString, formatter);
//
//                        System.out.println("Add description");
//                        String description = input.nextLine();
//
//                        System.out.println("Give status of the project (true/false)");
//                        boolean status = requestBoolean("true/false");
//
//                        Project myProject = new Project(title, description, dueDateTime,status);
//                        writeProjectToFile(myProject,eventsList,fileName);
//
//                        break;
//                    } 
//
//                }
//            }case 2:{
//
//                switch (calendarChoice){
//                    case "day":{
//                        printEventsForTheDay(eventsList,timeNow);
//                        break;
//                        }
//                    case "week":{
//                        printEventsForTheWeek(eventsList,timeNow);
//                        break;
//                    }
//                    case "month":{
//                        printEventsForTheMonth(eventsList,timeNow);
//                        break;
//                    }
//                    case "pastday":{
//                        printEventsForThePastDay(eventsList,timeNow);
//                        break;
//                    }
//                    case "pastweek":{
//                        printEventsForThePastWeek(eventsList,timeNow);
//                        break;
//                    }
//                    case "pastmonth":{
//                        printEventsForThePastMonth(eventsList,timeNow);
//                        break;
//                    }
//                    case "todo":{
//                        printEventsForTheToDo(eventsList,timeNow);
//                        break;
//                    }
//                    case "due":{
//                        printEventsForTheDue(eventsList,timeNow);
//                        break;
//                    }
//                }
//                break;
//            }
//        } 
//
//       TimeService.stop();
   }  
    //END OF MAIN
    
    //This method is used for the request of the minutes and seconds because it allows the user to input 0>=
    static private int requestChoice(int maxChoice , String inputType){
    Scanner input = new java.util.Scanner(System.in);
    boolean isValidChoice=false;
    int number=0;
    while(!isValidChoice)
    {
        System.out.println("Enter a valid choice of " + inputType);
        String choice = input.nextLine();
        try{
            number=Integer.parseInt(choice);
            if((number>=0)&&(number<=maxChoice)){
                isValidChoice=true;
            }else{
                System.out.println("Invalid choice. Please provide a number up to " + String.valueOf(maxChoice));
            }
        }catch(NumberFormatException e){
            System.out.println("The input was not an integer");
        }
    }
    return number;
}

    //This method is used for the request of the month day and hours because it allows the user to input 1>=
    static private int requestChoice2(int maxChoice , String inputType){
    Scanner input = new java.util.Scanner(System.in);
    boolean isValidChoice=false;
    int number=0;
    while(!isValidChoice)
    {
        System.out.println("Enter a valid choice of " + inputType);
        String choice = input.nextLine();
        try{
            number=Integer.parseInt(choice);
            if((number>=1)&&(number<=maxChoice)){
                isValidChoice=true;
            }else{
                System.out.println("Invalid choice. Please provide a number up to " + String.valueOf(maxChoice));
            }
        }catch(NumberFormatException e){
            System.out.println("The input was not an integer");
        }

    }
    return number;
}    
    //Method used for checking if the type the user is giving for a year is valid or not   
    public static int requestInt(String inputType){
        Scanner input = new java.util.Scanner(System.in);
        int number=0;
        boolean valid=false;
        do{
            try{
                System.out.println("Please input " + inputType);
                number=input.nextInt();
                input.nextLine();
                valid=true;
            }catch(InputMismatchException e){
                System.out.println("Invalid input.Please provide integer for the " + inputType);
                input.nextLine();
            }
        }while(!valid);
        
        return number; 
    }
    
    //Method used for checking whether the status for the project provided is valid or not
    public static boolean requestBoolean(String inputType){
        Scanner input = new java.util.Scanner(System.in);
        String userInput;
        boolean bool=false;
        boolean valid=false;
        do{
            try{
            System.out.println("Please input " + inputType);
            userInput=input.nextLine().toLowerCase();
            if(userInput.equals("true")||userInput.equals("false")){
                bool=Boolean.parseBoolean(userInput);
                valid=true; 
            
            }else{

                System.out.println("Invalid input.Please provide " + inputType);
                input.nextLine();
            }
            }catch(InputMismatchException e){
                System.out.println("This is not a valid input");
            }
        }while(!valid);
        
        return bool; 
    }
        
    //Method used for reading 
    private static void readEventsFromFile(String fileName) throws ParseException{
        BufferedReader reader = null;

         try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            String title = null;
            String date = null;
            String dueDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

            while ((line = reader.readLine()) != null) {
                if(line.equals("BEGIN:VEVENT")){
                    while((line = reader.readLine())!=null){
                        if (line.startsWith("SUMMARY:")) {
                            // Extracting the event title (SUMMARY)
                            title = line.substring("SUMMARY:".length());
                            System.out.println("Title: " + title);
                        } else if (line.startsWith("DTSTART:")) {
                            date = line.substring("DTSTART:".length());
                            LocalDateTime formattedDate = LocalDateTime.parse(date,formatter);
                            System.out.println("Date: " + formattedDate);
                        }else if(line.startsWith("DUE;VALUE=DATE:")){
                            dueDate = line.substring("DUE;VALUE=DATE:".length());
                            LocalDateTime formattedDueDate = LocalDateTime.parse(dueDate, formatter);
                            System.out.println("Due Date Time:" + formattedDueDate);
                        }
                        title = null;
                        date = null;
                    }
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         
    }
    
    //Method used for reading appointments from file
    private static ArrayList<Event> readAppointmentsFromFile(String fileName, ArrayList<Event> eventsList) {
        BufferedReader reader = null;

         try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            String title = null;
            String date = null;
            String duration = null;
            String description = null;
            

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

            while ((line = reader.readLine()) != null) {
                if(line.equals("BEGIN:VEVENT")){
                    while((line = reader.readLine())!=null){
                        if (line.startsWith("SUMMARY:")) {
                            // Extracting the event title (SUMMARY)
                            title = line.substring("SUMMARY:".length());
                        } else if (line.startsWith("DTSTART:")) {
                            // Extracting the event start date (DTSTART)
                            date = line.substring("DTSTART:".length());

                            // Parse the 'startDate' string using the iCalendar format
                            
                        }else if(line.startsWith("DTEND:")){
                            duration = line.substring("DTEND:".length());
                            
                        }else if(line.startsWith("DESCRIPTION:")){
                            description = line.substring("DESCRIPTION:".length());
                        
                        }else if (line.equals("END:VEVENT")) {
                            
                            LocalDateTime formattedDate = LocalDateTime.parse(date, formatter);
                            LocalDateTime formattedDateEnd = LocalDateTime.parse(duration, formatter);
                            // Date date = dateFormat.parse(dateString);
                            Appointment appointment = new Appointment(title,description,formattedDate,formattedDateEnd);
                            eventsList.add(appointment);
                            // Print the event details
//                            if (title != null) {
//                                System.out.println("Title: " + title);
//                                System.out.println("Date: " + formattedDate);
//                                System.out.println("Date End: "+formattedDateEnd);
//                                System.out.println("Description: "+ description);
//                                System.out.println();
//                            }
                            // Reset variables for the next event
                            title = null;
                            date = null;
                        }
                    }
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return eventsList;
    }
    
    //Method used for reading projects from file
    private static ArrayList<Event> readProjectsFromFile(String fileName, ArrayList<Event> eventsList){
        BufferedReader reader2 = null;
        try  {
            
            reader2 = new BufferedReader(new FileReader(fileName));
            String line2;
            String title2 = null;
            String date2 = null;
            String description2 = null;
            boolean status = false;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
            while ((line2 = reader2.readLine()) != null){
                if(line2.equals("BEGIN:VTODO")){
                    while((line2 = reader2.readLine())!=null){
                        if (line2.startsWith("SUMMARY:")) {
                            // Extracting the event title (SUMMARY)
                            title2 = line2.substring("SUMMARY:".length());
                        } else if (line2.startsWith("DUE;VALUE=DATE:")) {
                            // Extracting the event start date (DTSTART)
                            date2 = line2.substring("DUE;VALUE=DATE:".length());

                            // Parse the 'startDate' string using the iCalendar format

                        }else if(line2.equals("STATUS:DOESNT-NEED-ACTION")){
                            status = true;

                        }else if(line2.equals("STATUS:NEEDS-ACTION")){
                            status = false;
                        }else if(line2.startsWith("DESCRIPTION:")){
                            description2 = line2.substring("DESCRIPTION:".length());

                        }else if (line2.equals("END:VTODO")) {

                            LocalDateTime formattedDate = LocalDateTime.parse(date2,formatter);
                            // Date date = dateFormat.parse(dateString);
                            Project project = new Project(title2,description2,formattedDate,status);
                            eventsList.add(project);
                            // Print the event details
//                            if (title2 != null) {
//                                System.out.println("Title: " + title2);
//                                System.out.println("Due Date Time:" + formattedDate);
//                                System.out.println("Status: "+ status);
//                                System.out.println("Description: "+ description2);
//                                System.out.println();
//                            }
                            // Reset variables for the next event
                            title2 = null;
                            date2 = null;
                            description2 = null;
                            // Reset variables for the next event
                            title2 = null;
                            date2 = null;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return eventsList;
    }
    
    //Method for writting an appointment to the ical file
    private static void writeAppointmentToFile(Appointment appointment, ArrayList<Event> eventsList, String file) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
            
            
            if (eventsList.isEmpty()) {
            // Write calendar header if the list is empty
            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");
            }
            eventsList.add(appointment);
            writer.write("BEGIN:VEVENT\n");
            writer.write("SUMMARY:" + appointment.getTitle() + "\n");
            writer.write("DTSTART:" + formatter.format(appointment.getDate()) + "\n");
            writer.write("DTEND:" + formatter.format(appointment.getDuration()) + "\n");
            writer.write("DESCRIPTION:" + appointment.getDescription() + "\n");
            writer.write("END:VEVENT\n");
            //writer.write("\n");
                
            

            writer.write("END:VCALENDAR\n");

            System.out.println("iCalendar file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing appointment to file: " + e.getMessage());
        }
    }
    
    //Method for writting a project to the ical file 
    private static void writeProjectToFile(Project project, ArrayList<Event> eventsList, String file){
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
                            
                            eventsList.add(project);
                        
                            writer.write("BEGIN:VCALENDAR\n");
                            writer.write("VERSION:2.0\n");
                               
                            writer.write("BEGIN:VTODO\n");
                            writer.write("SUMMARY:"+ project.getTitle()+"\n");
                            writer.write("DUE;VALUE=DATE:"+ formatter.format(project.getDate())+"\n");
                            if(project.getStatus()== false){
                                writer.write("STATUS:NEEDS-ACTION\n");
                            }else if(project.getStatus()== true){
                                writer.write("STATUS:DOESNT-NEED-ACTION\n");
                            }
                            writer.write("DESCRIPTION:"+ project.getDescription()+"\n");
                            writer.write("END:VTODO\n");
                            writer.write("\n");
                                    
                                
                            System.out.println("Event added: " + project.getTitle());
                            // End the calendar
                            writer.write("END:VCALENDAR\n");

                            System.out.println("iCalendar file created successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
    }
    
    //Method used for writing the events sorted to a file
    private static void writeSortedEventsToFile(ArrayList<Event> eventsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics"))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");

            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");

            for (Event event : eventsList) {
                // Add an event
                if (event instanceof Appointment) { //Case which the event is an appointment
                    Appointment appointment = (Appointment) event;
                    writer.write("BEGIN:VEVENT\n");
                    writer.write("SUMMARY:" + appointment.getTitle() + "\n");
                    writer.write("DTSTART:" + formatter.format(appointment.getDate()) + "\n");
                    writer.write("DTEND:" + formatter.format(appointment.getDuration()) + "\n");
                    writer.write("DESCRIPTION:" + appointment.getDescription() + "\n");
                    // End the event
                    writer.write("END:VEVENT\n");
                    writer.write("\n");
                } else if (event instanceof Project) { //Case which the event is a project
                    Project project = (Project) event;
                    writer.write("BEGIN:VTODO\n");
                    writer.write("SUMMARY:" + project.getTitle() + "\n");
                    writer.write("DUE;VALUE=DATE:" + formatter.format(project.getDate()) + "\n");
                    if (project.getStatus() == false) { 
                        writer.write("STATUS:NEEDS-ACTION\n");
                    } else if (project.getStatus() == true) {
                        writer.write("STATUS:DOESNT-NEED-ACTION\n");
                    }
                    writer.write("DESCRIPTION:" + project.getDescription() + "\n");
                    writer.write("END:VTODO\n");
                    writer.write("\n");
                }
            }
            // End the calendar
            writer.write("END:VCALENDAR\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    
    //Method used for printing events for a day the user suggest
    public static void printEventsForTheDay(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        LocalTime time = date.toLocalTime();
        LocalDateTime endOfDay = LocalDateTime.of(date.toLocalDate(), time.MAX);

        System.out.println("Events for The Day " + date.toLocalDate() + ":");

        for (Event event : eventsList) {
            LocalDateTime eventDate = event.getDate();

            if (eventDate.isEqual(date) || (eventDate.isAfter(date) && eventDate.isBefore(endOfDay))) {
                event.printEvent();
            }
        }
    }
    public static void printEventsForTheWholeDay(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        LocalTime time = date.toLocalTime();
        
        LocalDateTime startOfDay = LocalDateTime.of(date.toLocalDate(),time.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(date.toLocalDate(), time.MAX);

        System.out.println("Events for The Day " + date.toLocalDate() + ":");

        for (Event event : eventsList) {
            LocalDateTime eventDate = event.getDate();

            if (eventDate.isEqual(startOfDay) || (eventDate.isAfter(startOfDay) && eventDate.isBefore(endOfDay))) {
                event.printEvent();
            }
        }
    }
    public static void printEventsForThePastDay(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        LocalTime time = date.toLocalTime();
        LocalDateTime startOfDay = LocalDateTime.of(date.toLocalDate(),time.MIN);
        

        System.out.println("Events for The Day" + date.toLocalDate() + ":");

        for (Event event : eventsList) {
            LocalDateTime eventDate = event.getDate();

            if (eventDate.isEqual(startOfDay) || (eventDate.isAfter(startOfDay) && eventDate.isBefore(date))) {
                event.printEvent();
            }
        }
    }
    
    public static void printEventsForTheWeek(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Events for this week:");

        // Get the current day of the week
        DayOfWeek currentDayOfWeek = date.getDayOfWeek();

        // Determine the number of days to add based on the current day of the week
        int daysToAdd = DayOfWeek.SATURDAY.getValue() - currentDayOfWeek.getValue();

        for (int i = 0; i <= daysToAdd; i++) {
            LocalDateTime currentDate = date.plusDays(i);

            // Print events for each day
            printEventsForTheDay(eventsList, currentDate);
        }
    }
    public static void printEventsForThePastWeek(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }
        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Events for the past week:");

        // Get the current day of the week
        DayOfWeek currentDayOfWeek = date.getDayOfWeek();

        // Determine the number of days to subtract based on the current day of the week
        int daysToSubtract = currentDayOfWeek.getValue();

        for (int i = daysToSubtract; i >= 0; i--) {
            LocalDateTime currentDate = date.minusDays(i);

            // Print events for each day
            printEventsForTheWholeDay(eventsList, currentDate);
        }
    }
    
    public static void printEventsForTheMonth(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Events for the rest of the month:");

        // Get the last day of the month
        LocalDateTime lastDayOfMonth = date.withDayOfMonth(date.getMonth().length(date.toLocalDate().isLeapYear()));

        // Print events for each day from today until the last day of the month
        while (date.isBefore(lastDayOfMonth) || date.isEqual(lastDayOfMonth)) {
            // Print events for each day
            printEventsForTheWholeDay(eventsList, date);

            // Move to the next day
            date = date.plusDays(1);
        }
    }
    public static void printEventsForThePastMonth(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Events from the beginning of the month until today:");

        // Get the first day of the month
        LocalDateTime firstDayOfMonth = date.withDayOfMonth(1);

        // Print events for each day from the first day of the month until today
        while (firstDayOfMonth.isBefore(date) || firstDayOfMonth.isEqual(date)) {
            // Print events for each day
            printEventsForTheDay(eventsList, firstDayOfMonth);

            // Move to the next day
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
    }
    
    public static void printEventsForTheToDo(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Incomplete projects before the deadline:");

        for (Event event : eventsList) {
            if (event instanceof Project) {
                Project project = (Project) event;

                // Check if it's a project, incomplete, and past the deadline
                if (project.getStatus()== false && project.getDate().isAfter(date)) {
                    project.printEvent();
                }
            }
        }
    }
    public static void printEventsForTheDue(ArrayList<Event> eventsList, Temporal timeNow) {
            if (!(timeNow instanceof LocalDateTime)) {
            // Handle the case where the provided Temporal is not a LocalDateTime
            System.out.println("Invalid Temporal type. Expected LocalDateTime.");
            return;
        }

        LocalDateTime date = (LocalDateTime) timeNow;
        
        System.out.println("Incomplete projects after the deadline:");

        for (Event event : eventsList) {
            if (event instanceof Project) {
                Project project = (Project) event;

                // Check if it's a project, incomplete, and past the deadline
                if (project.getStatus()== false && project.getDate().isBefore(date)) {
                    project.printEvent();
                }
            }
        }
    }

        // chat gpt generated code to find file in system, actually not used...
    public static String getDirectoryPath(){
       String currentDirectory = System.getProperty("user.dir");

       
        File currentDirFile = new File(currentDirectory);

        
        File parentDirFile = currentDirFile.getParentFile();

        if (parentDirFile != null) {
            
            String parentDirectoryPath = parentDirFile.getAbsolutePath();
            return parentDirectoryPath;
        } else {
            System.out.println("No parent directory (already at the root).");
            return "";
        }
        
    }
    
    ///////BUTTON METHODS/////////////////////////////////
    
    
    public static JFrame createMainFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        ImageIcon image = new ImageIcon("Resources/calendar-green-icon-png-17.png");
        frame.setIconImage(image.getImage());
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        bluePanel = createBluePanel();
        mainPanel.add(bluePanel, BorderLayout.CENTER);
        
        JPanel greenPanel = createGreenPanel();
        frame.add(greenPanel, BorderLayout.WEST);
        
        labelPanel = createLabelPanel();
        mainPanel.add(labelPanel, BorderLayout.NORTH);

        return frame;
    }

    public static JPanel createGreenPanel() {
        JPanel greenPanel = createGradientPanel(new Color(200, 238, 255), new Color(0, 144, 0));
        greenPanel.setLayout(new BoxLayout(greenPanel, BoxLayout.Y_AXIS));

        greenPanel.setPreferredSize(new Dimension(450, 500));
        
        greenPanel.add(chosenDateLabel);
        
        JButton calshowbutton1 = new JButton("day");
        JButton calshowbutton2 = new JButton("week");
        JButton calshowbutton3 = new JButton("month");

        greenPanel.add(createButtonsPanel(Arrays.asList(calshowbutton1, calshowbutton2, calshowbutton3)));

        List<String> eventLabels = Arrays.asList("Events for selected day", "Events for whole month", "Upcoming events");
        eventsPanel = new JPanel();
        greenPanel.add(createEventPanels(eventsPanel, eventLabels));

        JButton chooseDate = new JButton("CHOOSE DATE");
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(chooseDate, BorderLayout.CENTER);
        greenPanel.add(buttonPanel, BorderLayout.SOUTH);

        calshowbutton1.addActionListener(e -> handleDayButton());
        calshowbutton2.addActionListener(e -> handleWeekButton());
        calshowbutton3.addActionListener(e -> handleMonthButton());
        chooseDate.addActionListener(e -> handleChooseDateButton());
        
        return greenPanel;
    }
    public static JPanel createButtonsPanel(List<JButton> buttons) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(buttons.size(), 1, 3, 3));
        buttons.forEach(panel::add);
        return panel;
    }
    public static JPanel createEventPanels(JPanel targetPanel, List<String> eventLabels) {
        targetPanel.setLayout(new GridLayout(eventLabels.size(), 1, 3, 3));
        eventLabels.forEach(label -> {
            JPanel eventPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
            JLabel eventLabel = new JLabel(label);
            eventLabel.setForeground(Color.green);
            eventPanel.add(eventLabel);
            targetPanel.add(eventPanel);
        });
        return targetPanel;
    }
    public static void handleDayButton() {
        handleButton(24, 1, 5, 5,0);
    }

    public static void handleWeekButton() {
         handleButton(7, 5, 7, 5,0);
    }

    public static void handleMonthButton() {
        labelPanel.setVisible(true);

        // Use the chosenDate if it's set, otherwise use the current date
        LocalDateTime currentDate = (chosenDate != null) ? chosenDate : LocalDateTime.now();

        int daysInMonth = currentDate.getMonth().length(currentDate.toLocalDate().isLeapYear());
        int startingDayOfMonth = currentDate.withDayOfMonth(1).getDayOfWeek().getValue(); // 1 is Monday, 2 is Tuesday, ..., 7 is Sunday

        int totalCells = 5 * 7; // Total cells in a 5x7 grid

        // Remove all components from bluePanel
        bluePanel.removeAll();

        // Set the layout to the intended 5x7 grid layout for the month
        bluePanel.setLayout(new GridLayout(5, 7, 5, 5));

        for (int i = 1; i <= totalCells; i++) {
            JButton button = new JButton();

            // Calculate the day number based on the starting day of the month
            int dayNumber = i - startingDayOfMonth + 1;

            if (dayNumber > 0 && dayNumber <= daysInMonth) {
                button.setText(Integer.toString(dayNumber));

                // Add an action listener to each day button for future actions
                button.addActionListener(dayButtonEvent -> {
                    // You can perform additional actions when a day button is clicked
                    JOptionPane.showMessageDialog(null, "Add event in day " + button.getText());

                    // Remove all components from bluePanel
                    bluePanel.removeAll();

                    ////////////
                    JPanel addEvent = createGradientPanel(new Color(200, 238, 255), new Color(0, 144, 0));
                    addEvent.setPreferredSize(new Dimension(500, 500));
                    //bluePanel.setLayout(new GridLayout(1, 1, 5, 5));

                    JPanel titlePanel = new JPanel();
                    titlePanel.setForeground(Color.white);

                    addEvent.setLayout(new GridLayout(3, 1, 10, 10)); // 2 rows, 2 columns, horizontal and vertical gap of 10
                    
                    JLabel title = new JLabel("Title");
                    JTextField titleField = new JTextField();
                    titleField.setPreferredSize(new Dimension(250, 40));
                    
                    JLabel description = new JLabel("Description");
                    JTextField descriptionField = new JTextField();
                    descriptionField.setPreferredSize(new Dimension(250, 40));
                    
                    JCheckBox checkBox1 = new JCheckBox("Project");
                    
                    JCheckBox checkBox2 = new JCheckBox("Assigment");
                    
                    //JButton submitButton = new JButton("Submit");
                    
                    
                    checkBox1.addItemListener(e ->{
                       
                        if (e.getStateChange() == ItemEvent.SELECTED ){
                        addEvent.setLayout(new GridLayout(6, 1, 10, 10));
                        
                        
                        JLabel projdue = new JLabel("Project due");
                        JTextField projdueField = new JTextField();
                        projdueField.setPreferredSize(new Dimension(250, 40));
                        
                        JLabel projstatus = new JLabel("Status(True/false)");
                        JTextField projstatusField = new JTextField();
                        projstatusField.setPreferredSize(new Dimension(250, 40));
                        
                        JButton submit = new JButton("Submit");
                        submit.setPreferredSize(new Dimension(500, 40));
                    
                        addEvent.add(projdue, 0);
                        addEvent.add(projdueField, 1);
                        addEvent.add(projstatus, 0);
                        addEvent.add(projstatusField, 1);
                        addEvent.add(submit);
                        
                        
                        addEvent.revalidate();
                        addEvent.repaint();
                        
                         submit.addActionListener(f -> {
                            addEvent.setVisible(false);
                            handleMonthButton();
                            
                        });
                        
                        }
                      
                    });
                    
                    checkBox2.addItemListener(e ->{
                       
                        if (e.getStateChange() == ItemEvent.SELECTED ){
                        addEvent.setLayout(new GridLayout(6, 1, 10, 10));
                        
                        
                        JLabel start = new JLabel("Appointment start");
                        JTextField startField = new JTextField();
                        startField.setPreferredSize(new Dimension(250, 40));
                        
                        JLabel end = new JLabel("Appointment ends");
                        JTextField endField = new JTextField();
                        endField.setPreferredSize(new Dimension(250, 40));
                        
                        JButton submit = new JButton("Submit");
                        submit.setPreferredSize(new Dimension(500, 40));
                    
                        addEvent.add(end, 0);
                        addEvent.add(endField, 1);
                        addEvent.add(start, 0);
                        addEvent.add(startField, 1);
                        addEvent.add(submit);
                        
                        addEvent.revalidate();
                        addEvent.repaint();
                        
                        submit.addActionListener(f -> {
                            addEvent.setVisible(false);
                            handleMonthButton();
                            
                        });
                        
                        }
                      
                    });
                    
                    
                    
                    
                    addEvent.add(title);
                    addEvent.add(titleField);
                    addEvent.add(description);
                    addEvent.add(descriptionField);
                    addEvent.add(checkBox1);
                    addEvent.add(checkBox2);
                    
                    

                    bluePanel.revalidate();
                    bluePanel.repaint();

                    bluePanel.add(addEvent, BorderLayout.CENTER);
                });
            }

            bluePanel.add(button);
        }

        // Repaint bluePanel to reflect the changes
        bluePanel.revalidate();
        bluePanel.repaint();
    }
    
    public static void handleChooseDateButton() {
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter the year (e.g., 2024): "));
        int month = Integer.parseInt(JOptionPane.showInputDialog("Enter the month (1-12): "));
        int dayOfMonth = Integer.parseInt(JOptionPane.showInputDialog("Enter the day (1-31): "));

        // Calculate the day of the week for the 1st day of the entered month
        LocalDateTime firstDayOfMonth = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();

        chosenDate = LocalDateTime.of(year, month, dayOfMonth, 0, 0);
        chosenDateLabel.setText("Current Date: " + formatDate(chosenDate));


        // Use the dayOfWeek to determine the layout of your month view
        int startingDay = 0; // 0 corresponds to Sunday, 1 to Monday, ..., 6 to Saturday

        switch (dayOfWeek) {
            case MONDAY:
                startingDay = 1;
                break;
            case TUESDAY:
                startingDay = 2;
                break;
            case WEDNESDAY:
                startingDay = 3;
                break;
            case THURSDAY:
                startingDay = 4;
                break;
            case FRIDAY:
                startingDay = 5;
                break;
            case SATURDAY:
                startingDay = 6;
                break;
            case SUNDAY:
                startingDay = 0;
                break;
        }

        handleButton(31, 6, 7, 5, startingDay);
        handleMonthButton();// automatic press to see month 
        
    }
    private static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // You can customize the pattern
        return date.format(formatter);
    }

    // The handleButton method is modified to consider the starting day of the week
    public static void handleButton(int maxIterations, int rows, int cols, int gap, int startingDay) {
        Component[] components = bluePanel.getComponents();
        labelPanel.setVisible(false);
        bluePanel.removeAll();

        for (Component component : components) {
            bluePanel.remove(component);
        }

        bluePanel.revalidate();
        bluePanel.repaint();

        bluePanel.setLayout(new GridLayout(rows, cols, gap, gap));

        // Adjust the starting day of the week
        for (int i = 0; i < startingDay; i++) {
            JButton emptyButton = new JButton("");
            emptyButton.setEnabled(false);
            bluePanel.add(emptyButton);
        }

        for (int i = 1; i <= maxIterations; i++) {
            JButton button = new JButton(Integer.toString(i));
            bluePanel.add(button);
        }
        displayEventsForDate(chosenDate);
    }
    public static JPanel createBluePanel() {
        JPanel bluePanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
        bluePanel.setLayout(new GridLayout(5, 7, 5, 5));
        return bluePanel;
    }
    public static JPanel createLabelPanel() {
        JPanel labelPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
        labelPanel.setLayout(new GridLayout(1, 7, 5, 5));

        String[] dayLabels = {"M", "T", "W", "T", "F", "S", "S"};
        Arrays.stream(dayLabels)
                .map(label -> {
                    JLabel dayLabel = new JLabel(label);
                    dayLabel.setForeground(Color.green);
                    dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    return dayLabel;
                })
                .forEach(labelPanel::add);

        return labelPanel;
    }
    public static JPanel createGradientPanel(Color startColor, Color endColor) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            GradientPaint gradientPaint = new GradientPaint(-300, 0, startColor, width, height, endColor);
            g2d.setPaint(gradientPaint);
            g2d.fill(new Rectangle2D.Double(0, 0, width, height));
         }
     };
    }
    
    private static void displayEventsForDate(LocalDateTime date) {
        ArrayList<Event> dayEvents = getEventsForDate(date);
        ArrayList<Event> monthEvents = getEventsForMonth(date);
        ArrayList<Event> upcomingEvents = getEventsForUpcoming(date);

        JPanel dayEventsPanel = createEventsPanel(dayEvents, "Selected Day Events");
        JPanel monthEventsPanel = createEventsPanel(monthEvents, "Whole Month Events");
        JPanel upcomingEventsPanel = createEventsPanel(upcomingEvents, "Upcoming Events");
        // Clear existing components in the main events panel
        eventsPanel.removeAll();

        // Add day events panel to the main events panel
        eventsPanel.add(dayEventsPanel);

        // Add month events panel to the main events panel
        eventsPanel.add(monthEventsPanel);
        
        // Add upcoming events panel to the main events panel
        eventsPanel.add(upcomingEventsPanel);


        // Repaint eventsPanel to reflect the changes
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    // Example method to filter events for the specified date
    private static ArrayList<Event> getEventsForDate(LocalDateTime date) {
        ArrayList<Event> eventsForDate = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().toLocalDate().equals(date.toLocalDate())) {
                eventsForDate.add(event);
            }
        }

        return eventsForDate;
    }
    private static ArrayList<Event> getEventsForMonth(LocalDateTime date) {
        ArrayList<Event> eventsForMonth = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().getMonth().equals(date.getMonth())) {
                eventsForMonth.add(event);
            }
        }

        return eventsForMonth;
    }
    private static ArrayList<Event> getEventsForUpcoming(LocalDateTime date) {
        ArrayList<Event> eventsForUpcoming = new ArrayList<>();

        LocalDateTime endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        for (Event event : eventsList) {
            if (event.getDate().isAfter(date) && event.getDate().isBefore(endOfMonth.plusDays(1))) {
                eventsForUpcoming.add(event);
            }
        }

        return eventsForUpcoming;
    }


    private static JPanel createEventsPanel(ArrayList<Event> events, String title) {
        JPanel titleAndEventsPanel = new JPanel();
        titleAndEventsPanel.setLayout(new BoxLayout(titleAndEventsPanel, BoxLayout.Y_AXIS));

        // Add title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        titleAndEventsPanel.add(titleLabel);

        // Check if events list is empty
        if (events.isEmpty()) {
            JPanel noEventsPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
            JLabel noEventsLabel = new JLabel("No Events");
            noEventsLabel.setForeground(Color.green);
            noEventsPanel.add(noEventsLabel);
            titleAndEventsPanel.add(noEventsPanel);
        } else {
            // Add events to the same panel
            for (Event event : events) {
                JPanel eventPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String formattedDate = event.getDate().format(formatter);

                JLabel eventLabel = new JLabel(event.getTitle() + " - " + formattedDate);
                eventLabel.setForeground(Color.green);
                eventLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                eventPanel.add(eventLabel);

                titleAndEventsPanel.add(eventPanel);
            }
        }

        return titleAndEventsPanel;
    }



}

