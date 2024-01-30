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
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
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
    private static LocalDateTime chosenDate = LocalDateTime.now();
    private static ArrayList<Event> eventsList = new ArrayList<>();//A list created to add events(Appointments and projects)
    private static JLabel chosenDateLabel = new JLabel("Current Date: " + formatDate(chosenDate));
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
    private static String fileName = null;
    
    //BEGINNING OF MAIN
    public static void main(String[] args) throws ParseException {
        if(args.length == 1){
            fileName = getDirectoryPath()+"//"+ args[0]; //Ifits not running with 
            
        }else if (args.length == 0){
            String InputfileName = JOptionPane.showInputDialog("Enter the file name:");
        
            // Check if the user clicked OK or canceled the dialog
            if (InputfileName != null) {
                // User entered a file name
                System.out.println("File Name: " + InputfileName);
            } else {
                // User clicked Cancel or closed the dialog
                System.out.println("User canceled the operation.");
            }
        }
        

        eventsList = readAppointmentsFromFile(fileName, eventsList);//The ics file gets all the appointments
        eventsList = readProjectsFromFile(fileName, eventsList);//The ics file gets all the projects

        Comparator<Event> eventComparator = Comparator.comparing(Event::getDate);//A comparator created to compare the events besed on their day
        eventsList.sort(eventComparator);//EventList is getting sorted by their date with the comparator
        
         SwingUtilities.invokeLater(() -> {
            JFrame frame = createMainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });    
    }
    
    //Method used for checking whether the status for the project provided is valid or not
    public static boolean requestBoolean(String inputType){
        Scanner input = new java.util.Scanner(System.in);
        String userInput;
        boolean bool=false;
        boolean valid=false;
        do{
            try{
            userInput = JOptionPane.showInputDialog("Please input " + inputType);
            String toLowerCase = userInput.toLowerCase();
            if(toLowerCase.equals("true")||toLowerCase.equals("false")){
                bool=Boolean.parseBoolean(toLowerCase);
                valid=true; 
            
            }else{

                JOptionPane.showMessageDialog(null, "Invalid input. Please enter true/false");
            }
            }catch(InputMismatchException e){
               JOptionPane.showMessageDialog(null, "Invalid input.");
            }
        }while(!valid);
        
        return bool; 
    }
        
    public static int requestChoiceWin(int maxChoice,String inputType){
        boolean validInput = false;
        int timeChoice=0;
        while (!validInput) {
            try {
                String userInput = JOptionPane.showInputDialog("Enter the " + inputType +  "(0-"+ maxChoice + "):");
                if (userInput == null) {
                    // User clicked cancel or closed the dialog
                    System.exit(0);
                }

                timeChoice = Integer.parseInt(userInput);

                // Validate the input
                if (timeChoice >= 0 && timeChoice <= maxChoice) {
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 59.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            }
    
        }
        return timeChoice;
    }
    
    //Does the same as requestChoiceWin but this time it doesnt allow 0
    public static int requestChoiceWin2(int maxChoice,String inputType){
        boolean validInput = false;
        int timeChoice=0;
        while (!validInput) {
            try {
                String userInput = JOptionPane.showInputDialog("Enter the " + inputType +  "(0-"+ maxChoice + "):");
                if (userInput == null) {
                    // User clicked cancel or closed the dialog
                    System.exit(0); 
                }

                timeChoice = Integer.parseInt(userInput);

                // Validate the input
                if (timeChoice >= 1 && timeChoice <= maxChoice) {
                    validInput = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 0 and 59.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.");
            }
        }
        return timeChoice;
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
    
 
    
    private static void updateEventInList(ArrayList<Event> eventsList, String searchTitle) {

        for (Event event : eventsList) {
            if (event.getTitle().equals(searchTitle)) {
                // Ask the user for the updated information
                int year =requestChoiceWin2(9999,"updated year");
                int month = requestChoiceWin2(12,"updated month");
                int dayOfMonth = requestChoiceWin2(31,"updated day");
                int hours =requestChoiceWin(24,"updated hour");
                int minutes = requestChoiceWin(59,"updated minute");
                int seconds = requestChoiceWin(59,"updated second");
                // Calculate the day of the week for the 1st day of the entered month
                LocalDateTime updatedDate = LocalDateTime.of(year, month, dayOfMonth, hours, minutes,seconds);

                chosenDate = LocalDateTime.of(year, month, dayOfMonth, hours, minutes);
                String updatedDescription = JOptionPane.showInputDialog("Enter the updated description:");

                // Update the date and description in the event
                event.setDate(updatedDate);
                event.setDescription(updatedDescription);

                // Check if it's a project or an appointment and update accordingly
                if (event instanceof Appointment) {
                    // It's an appointment
                    int years =requestChoiceWin2(9999,"updated end year");
                    int months = requestChoiceWin2(12,"updated end month");
                    int dayOfMonths = requestChoiceWin2(31,"updated end day");
                    int hourss =requestChoiceWin(24,"updated end hour");
                    int minutess = requestChoiceWin(59,"updated end minute");
                    int secondss = requestChoiceWin(59,"updated end second");
                    // Calculate the day of the week for the 1st day of the entered month
                    LocalDateTime updatedEndDate = LocalDateTime.of(years, months, dayOfMonths, hourss, minutess,secondss);
                    ((Appointment) event).setDuration(updatedEndDate);
                } else if (event instanceof Project) {
                    // It's a project

                    boolean updatedStatus = requestBoolean("true/false");
                    ((Project) event).setStatus(updatedStatus);
                }

                System.out.println("Event updated successfully.");
                return; // Event found and updated, exit the method
            }
        }

        JOptionPane.showMessageDialog(null, "Invalid input. Please press edit button and try again!");
    }
    
    
    
    //Method used for writing the events sorted to a file
    private static void writeSortedEventsToFile(ArrayList<Event> eventsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {


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

        // chat gpt generated code to find file in system
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
        JButton clashowbutton4 = new JButton("Due/To Do");
        JButton clashowbutton5 = new JButton("edit");
        
      
        greenPanel.add(createButtonsPanel(Arrays.asList(calshowbutton1, calshowbutton2, calshowbutton3,clashowbutton4, clashowbutton5)));

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
        clashowbutton4.addActionListener(e -> handleDueToDoButton());
        clashowbutton5.addActionListener(e -> handleEditButton());
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
                
                LocalDateTime dayDate = currentDate.withDayOfMonth(dayNumber);

                // Check if the current button corresponds to today's date
                if (dayDate.toLocalDate().isEqual(currentDate.toLocalDate())) {
                    button.setBackground(Color.GREEN);
                }
                
                // Add an action listener to each day button for future actions
                button.addActionListener(dayButtonEvent -> {
                    // You can perform additional actions when a day button is clicked
                    JOptionPane.showMessageDialog(null, "Add event in day " + button.getText());

                    // Remove all components from bluePanel
                    bluePanel.removeAll();

                    ////////////
                    JPanel addEvent = createAddEventPanel(button);
                    
                    bluePanel.revalidate();
                    bluePanel.repaint();

                    bluePanel.add(addEvent, BorderLayout.CENTER);
                });
            }

            bluePanel.add(button);
        }
        
        displayWholeDayAndUpcoming(chosenDate);
        
        // Repaint bluePanel to reflect the changes
        bluePanel.revalidate();
        bluePanel.repaint();
        
    }
    public static void handleWeekButton() {
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

        // Get the start and end dates of the current week
        LocalDateTime startOfWeek = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        for (int i = 1; i <= totalCells; i++) {
            JButton button = new JButton();

            // Calculate the day number based on the starting day of the month
            int dayNumber = i - startingDayOfMonth + 1;

            if (dayNumber > 0 && dayNumber <= daysInMonth) {
                button.setText(Integer.toString(dayNumber));
                LocalDateTime dayDate = currentDate.withDayOfMonth(dayNumber);

                // Check if the day is in the current week
                if (dayDate.isEqual(startOfWeek) || (dayDate.isAfter(startOfWeek) && dayDate.isBefore(endOfWeek.plusDays(1)))) {
                    button.setBackground(Color.GREEN);
                }

                // Add an action listener to each day button for future actions
                button.addActionListener(dayButtonEvent -> {
                    // You can perform additional actions when a day button is clicked
                    JOptionPane.showMessageDialog(null, "Add event in day " + button.getText());

                    // Remove all components from bluePanel
                    bluePanel.removeAll();

                    ////////////
                    JPanel addEvent = createAddEventPanel(button);

                    bluePanel.revalidate();
                    bluePanel.repaint();

                    bluePanel.add(addEvent, BorderLayout.CENTER);
                });
            }

            bluePanel.add(button);
        }

        displayWholeWeekAndUpcoming(chosenDate);

        // Repaint bluePanel to reflect the changes
        bluePanel.revalidate();
        bluePanel.repaint();
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
                    JPanel addEvent = createAddEventPanel(button);
                    
                    bluePanel.revalidate();
                    bluePanel.repaint();

                    bluePanel.add(addEvent, BorderLayout.CENTER);
                });
            }

            bluePanel.add(button);
        }
        
        displayWholeMonthAndUpcoming(currentDate);
        
        // Repaint bluePanel to reflect the changes
        bluePanel.revalidate();
        bluePanel.repaint();
    }
    public static void handleDueToDoButton() {
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
                    JPanel addEvent = createAddEventPanel(button);
                    
                    bluePanel.revalidate();
                    bluePanel.repaint();

                    bluePanel.add(addEvent, BorderLayout.CENTER);
                });
            }

            bluePanel.add(button);
        }
        
        displayDueToDo(currentDate);
        
        // Repaint bluePanel to reflect the changes
        bluePanel.revalidate();
        bluePanel.repaint();
    }
    public static void handleChooseDateButton() {
        int year =requestChoiceWin2(9999,"year");
        int month = requestChoiceWin2(12,"month");
        int dayOfMonth = requestChoiceWin2(31,"day");
        int hours =requestChoiceWin(24,"hour");
        int minutes = requestChoiceWin(59,"minute");
        // Calculate the day of the week for the 1st day of the entered month
        LocalDateTime firstDayOfMonth = LocalDateTime.of(year, month, dayOfMonth, hours, minutes);
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();

        chosenDate = LocalDateTime.of(year, month, dayOfMonth, hours, minutes);
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
        
        handleMonthButton();// automatic press to see month 
        
    //        TimeTeller teller = TimeService.getTeller();
    //        
    //        teller.addTimeListener(new TimeListener() {
    //            @Override
    //            public void timeChanged(TimeEvent e) {
    //            System.out.println("Time has changed to " + e.getDateTime());
    //    }
    //});
    //        TimeService.waitUntilTimeEnds();

    }
    public static void handleEditButton(){ 
       String searchTitle=JOptionPane.showInputDialog("Enter the name of an Event:");
       updateEventInList(eventsList, searchTitle);

    // Write the updated events back to the file
        writeSortedEventsToFile(eventsList);
    }
    
    private static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // You can customize the pattern
        return date.format(formatter);
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
    private static JPanel createAddEventPanel(JButton button) {
        JPanel addEvent = createGradientPanel(new Color(200, 238, 255), new Color(0, 144, 0));
        addEvent.setPreferredSize(new Dimension(500, 500));

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

        JCheckBox checkBox2 = new JCheckBox("Appointment");

        checkBox1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                addEvent.setLayout(new GridLayout(6, 1, 10, 10));

                JLabel projdue = new JLabel("Project due time (format:yyyymmddThhmmssZ)");
                JTextField projdueField = new JTextField();
                projdueField.setPreferredSize(new Dimension(250, 40));

                JLabel projstatus = new JLabel("Status (True/false)");
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
                    
                    String inputTime = projdueField.getText();
                    int dayNumber = Integer.parseInt(button.getText());
                    
                    try {
                        boolean booleanValue = Boolean.parseBoolean(projstatusField.getText());
                        
                        // Parse the input time
                        LocalTime appointmentTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));
                       
                        
                        // Combine the date from the button with the user-provided time
                        LocalDateTime buttonDate = LocalDateTime.of(chosenDate.getYear(), chosenDate.getMonth(), dayNumber, 0, 0);
                        LocalDateTime dueDateTime = buttonDate.with(LocalTime.of(appointmentTime.getHour(), appointmentTime.getMinute()));
                        
                        // Create Project object and write to file
                        Project myProject = new Project(titleField.getText(),descriptionField.getText(),dueDateTime,booleanValue);
                        writeProjectToFile(myProject, eventsList,fileName);    
                    } catch (DateTimeParseException ex) {
                        // Handle parsing exception (invalid format)
                        JOptionPane.showMessageDialog(null, "Invalid time format. Please use HH:mm");
                    }                
                });
            }
        });

        checkBox2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Handle appointment checkbox selection
                addEvent.setLayout(new GridLayout(7, 2, 10, 10));

                JLabel startLabel = new JLabel("Appointment start time (format: format: HH:mm)");
                JTextField startTimeField = new JTextField();
                startTimeField.setPreferredSize(new Dimension(250, 40));
                
                JLabel endLabel = new JLabel("Appointment end time (format: HH:mm)");
                JTextField endTimeField = new JTextField();
                startTimeField.setPreferredSize(new Dimension(250, 40));

                JButton submit = new JButton("Submit");
                submit.setPreferredSize(new Dimension(500, 40));

                addEvent.add(startLabel);
                addEvent.add(startTimeField);
                addEvent.add(endLabel);
                addEvent.add(endTimeField);
                addEvent.add(submit);

                addEvent.revalidate();
                addEvent.repaint();

                submit.addActionListener(f -> {
                    addEvent.setVisible(false);
                    handleMonthButton();
                    
                    // Get the input time from the user
                    String inputTime = startTimeField.getText();
                    int dayNumber = Integer.parseInt(button.getText());
                    
                    try {
                        // Parse the input time
                        LocalTime appointmentTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime appointmentEndTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm"));
                        
                        // Combine the date from the button with the user-provided time
                        LocalDateTime buttonDate = LocalDateTime.of(chosenDate.getYear(), chosenDate.getMonth(), dayNumber, 0, 0);
                        LocalDateTime appointmentDateTime = buttonDate.with(LocalTime.of(appointmentTime.getHour(), appointmentTime.getMinute()));
                        LocalDateTime appointmentEndDateTime = buttonDate.with(LocalTime.of(appointmentEndTime.getHour(), appointmentEndTime.getMinute()));
                        // Create Appointment object and write to file
                        Appointment myAppointment = new Appointment(titleField.getText(), descriptionField.getText(), appointmentDateTime,appointmentEndDateTime);
                        writeAppointmentToFile(myAppointment, eventsList, fileName);
                    } catch (DateTimeParseException ex) {
                        // Handle parsing exception (invalid format)
                        JOptionPane.showMessageDialog(null, "Invalid time format. Please use HH:mm");
                    }
                });
            }
        });


        addEvent.add(title);
        addEvent.add(titleField);
        addEvent.add(description);
        addEvent.add(descriptionField);
        addEvent.add(checkBox1);
        addEvent.add(checkBox2);

        return addEvent;
    }
    
    private static void displayWholeDayAndUpcoming(LocalDateTime date) {
        
        ArrayList<Event> dayEvents = getEventsForDate(date);
        ArrayList<Event> upcomingEvents = getEventsForUpcomingDay(date);

        
        JPanel dayEventsPanel = createEventsPanel(dayEvents, " Today's Events");
        JPanel upcomingEventsPanel = createEventsPanel(upcomingEvents, "Upcoming Events for Today");
        // Clear existing components in the main events panel
        eventsPanel.removeAll();

        

        // Add month events panel to the main events panel
        eventsPanel.add(dayEventsPanel);
        
        // Add upcoming events panel to the main events panel
        eventsPanel.add(upcomingEventsPanel);


        // Repaint eventsPanel to reflect the changes
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }
    private static void displayWholeWeekAndUpcoming(LocalDateTime date) {
        ArrayList<Event> dayEvents = getEventsForDate(date);
        ArrayList<Event> weekEvents = getEventsForWeek(date);
        ArrayList<Event> upcomingEvents = getEventsForUpcomingWeek(date);

        JPanel dayEventsPanel = createEventsPanel(dayEvents, "Selected Day Events");
        JPanel weekEventsPanel = createEventsPanel(weekEvents, "Whole Week Events");
        JPanel upcomingEventsPanel = createEventsPanel(upcomingEvents, "Upcoming Week Events");

        // Clear existing components in the main events panel
        eventsPanel.removeAll();

        // Add day events panel to the main events panel
        eventsPanel.add(dayEventsPanel);

        // Add week events panel to the main events panel
        eventsPanel.add(weekEventsPanel);

        // Add upcoming events panel to the main events panel
        eventsPanel.add(upcomingEventsPanel);

        // Repaint eventsPanel to reflect the changes
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }
    private static void displayWholeMonthAndUpcoming(LocalDateTime date) {
        ArrayList<Event> dayEvents = getEventsForDate(date);
        ArrayList<Event> monthEvents = getEventsForMonth(date);
        ArrayList<Event> upcomingEvents = getEventsForUpcomingMonth(date);

        JPanel dayEventsPanel = createEventsPanel(dayEvents, "Selected Day Events");
        JPanel monthEventsPanel = createEventsPanel(monthEvents, "Whole Month Events");
        JPanel upcomingEventsPanel = createEventsPanel(upcomingEvents, "Upcoming Month Events");
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
    private static void displayDueToDo(LocalDateTime date) {
        ArrayList<Event> due = getEventsForDue(date);
        ArrayList<Event> toDo = getEventsForToDo(date);

        JPanel dueEventsPanel = createEventsPanel(due, "Incomplete projects after the deadline:");
        JPanel toDoEventsPanel = createEventsPanel(toDo, "Incomplete projects before the deadline:");
        // Clear existing components in the main events panel
        eventsPanel.removeAll();

        eventsPanel.add(dueEventsPanel);

        
        eventsPanel.add(toDoEventsPanel);

        // Repaint eventsPanel to reflect the changes
        eventsPanel.revalidate();
        eventsPanel.repaint();
    }

    private static ArrayList<Event> getEventsForDate(LocalDateTime date) {
        ArrayList<Event> eventsForDate = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().toLocalDate().equals(date.toLocalDate()) && event.getDate().getYear() == date.getYear()) {
                eventsForDate.add(event);
            }
        }

        return eventsForDate;
    }
    private static ArrayList<Event> getEventsForWeek(LocalDateTime date) {
        ArrayList<Event> eventsForWeek = new ArrayList<>();
        
        LocalDateTime startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        
        LocalDateTime endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        
        for (Event event : eventsList) {
            if (event.getDate().isAfter(startOfWeek) && event.getDate().isBefore(endOfWeek.plusDays(1)) && event.getDate().getYear() == date.getYear()) {
                eventsForWeek.add(event);
            }
        }

        return eventsForWeek;
    }
    private static ArrayList<Event> getEventsForMonth(LocalDateTime date) {
        ArrayList<Event> eventsForMonth = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().getMonth().equals(date.getMonth()) && event.getDate().getYear() == date.getYear()) {
                eventsForMonth.add(event);
            }

        }

        return eventsForMonth;
    }
    private static ArrayList<Event> getEventsForDue(LocalDateTime date) {
        ArrayList<Event> eventsForDue = new ArrayList<>();

        for (Event event : eventsList) {
            if (event instanceof Project) {
                Project project = (Project) event;

                // Check if it's a project, incomplete, and past the deadline
                if (project.getStatus()== false && project.getDate().isBefore(date)) {
                    eventsForDue.add(project);
                }
            }
        }
        return eventsForDue;
    }
    private static ArrayList<Event> getEventsForToDo(LocalDateTime date){
        ArrayList<Event> eventsForToDo = new ArrayList<>();

        for (Event event : eventsList) {
            if (event instanceof Project) {
                Project project = (Project) event;

                // Check if it's a project, incomplete, and past the deadline
                if (project.getStatus()== false && project.getDate().isAfter(date)) {
                    eventsForToDo.add(project);
                }
            }
        }
        
        return eventsForToDo;
    }
    
    private static ArrayList<Event> getEventsForUpcomingMonth(LocalDateTime date) {
        ArrayList<Event> eventsForUpcoming = new ArrayList<>();

        LocalDateTime endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        for (Event event : eventsList) {
            if (event.getDate().isAfter(date) && event.getDate().isBefore(endOfMonth.plusDays(1))) {
                eventsForUpcoming.add(event);
            }
        }

        return eventsForUpcoming;
    }
    private static ArrayList<Event> getEventsForUpcomingWeek(LocalDateTime date) {
        ArrayList<Event> eventsForUpcomingWeek = new ArrayList<>();

               LocalDateTime endOfWeek = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        for (Event event : eventsList) {
                if (event.getDate().isAfter(date) && event.getDate().isBefore(endOfWeek.plusDays(1))) {
                    eventsForUpcomingWeek.add(event);
                }
        }
        


        return eventsForUpcomingWeek;
    }
    private static ArrayList<Event> getEventsForUpcomingDay(LocalDateTime date) {
        ArrayList<Event> eventsForUpcomingDay = new ArrayList<>();

        for (Event event : eventsList) {
            LocalDateTime eventDateTime = event.getDate();

            // Check if the event is on the same day as the chosen date
            if (eventDateTime.toLocalDate().isEqual(date.toLocalDate())) {
                // Check if the event time is after the current time
                if (eventDateTime.toLocalTime().isAfter(date.toLocalTime())) {
                    eventsForUpcomingDay.add(event);
                }
            }
        }

        return eventsForUpcomingDay;
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

