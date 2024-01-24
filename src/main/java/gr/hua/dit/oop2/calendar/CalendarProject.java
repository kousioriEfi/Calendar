/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    */

   //All libraries used throughout the code
   package gr.hua.dit.oop2.calendar;
   
    import java.awt.BorderLayout;
    import java.awt.Color;
import java.awt.Component;
    import java.awt.Dimension;
    import java.awt.GradientPaint;
    import java.awt.Graphics;
    import java.awt.Graphics2D;
    import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
   import java.util.InputMismatchException;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
   /**
    *
    * @author leftp
    */
   public class CalendarProject{
       
       //BEGINNING OF MAIN
    public static void main(String[] args) throws ParseException {
                SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame();
            frame.setTitle("Calendar");
            frame.setSize(500, 500);
            frame.setLayout(new BorderLayout());
            
            ImageIcon image= new ImageIcon("Resources/calendar-green-icon-png-17.png");
            frame.setIconImage(image.getImage());
            
            JPanel greenPanel = createGradientPanel(new Color(200, 238, 255), new Color(0, 144, 0));
            
            greenPanel.setPreferredSize(new Dimension(150, 500));
            frame.add(greenPanel, BorderLayout.WEST);
            
            JButton calshowbutton1 = new JButton("day");           
            JButton calshowbutton2 = new JButton("week");
            JButton calshowbutton3 = new JButton("month");
            
            
            greenPanel.setLayout(new  GridLayout(20,20,5,5));
            greenPanel.add(calshowbutton1);///
            greenPanel.add(calshowbutton2);
            greenPanel.add(calshowbutton3);


            JPanel mainPanel = new JPanel(new BorderLayout());
            frame.add(mainPanel, BorderLayout.CENTER);

            JPanel bluePanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
            bluePanel.setLayout(new GridLayout(5, 7, 5, 5)); 
            mainPanel.add(bluePanel, BorderLayout.CENTER);
            
            
            
//            JPanel labelPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
//            labelPanel.setLayout(new GridLayout(1, 7, 5, 5)); // 1 row, 7 columns
//            JLabel label = new JLabel("      M              T               W               T               F               S");
//            label.setForeground(Color.green);
//            labelPanel.add(label, BorderLayout.CENTER);

JPanel labelPanel = createGradientPanel(new Color(173, 216, 230), new Color(25, 25, 80));
            labelPanel.setLayout(new GridLayout(1, 7, 5, 5)); // 2 rows, 7 columns

            String[] dayLabels = {"M", "T", "W", "T", "F", "S", "S"};

            for (String dayLabel : dayLabels) {
                JLabel label = new JLabel(dayLabel);
                label.setForeground(Color.green);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                labelPanel.add(label);
            }

            mainPanel.add(labelPanel, BorderLayout.NORTH);

            // ... (same code as before)

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        
    

            mainPanel.add(labelPanel, BorderLayout.NORTH);
            
            calshowbutton1.addActionListener(e -> {
                Component[] components = bluePanel.getComponents();
                labelPanel.setVisible(false);
                bluePanel.remove(labelPanel);
                // Remove each component from bluePanel
                for (Component component : components) {
                    bluePanel.remove(component);
                }

                // Repaint bluePanel to reflect the changes
                bluePanel.revalidate();
                bluePanel.repaint();
                
                for (int i = 1; i <= 24; i++) {
                    bluePanel.setLayout(new GridLayout(24, 1, 5, 5)); 
                    JButton button = new JButton(Integer.toString(i));
                    bluePanel.add(button);
                }
                
             
            });
            
            calshowbutton2.addActionListener(e -> {
                Component[] components = bluePanel.getComponents();
                labelPanel.setVisible(false);
                // Remove each component from bluePanel
                for (Component component : components) {
                    bluePanel.remove(component);
                }

                // Repaint bluePanel to reflect the changes
                bluePanel.revalidate();
                bluePanel.repaint();
                
                for (int i = 1; i <= 7; i++) {
                    bluePanel.setLayout(new GridLayout(5, 7, 5, 5)); 
                    JButton button = new JButton(Integer.toString(i));
                    bluePanel.add(button);
                }
                
                
            });
            
            calshowbutton3.addActionListener(e -> {
                Component[] components = bluePanel.getComponents();
                labelPanel.setVisible(true);
                // Remove each component from bluePanel
                for (Component component : components) {
                    bluePanel.remove(component);
                }

                // Repaint bluePanel to reflect the changes
                bluePanel.revalidate();
                bluePanel.repaint();
                
                for (int i = 1; i <= 31; i++) {
                    bluePanel.setLayout(new GridLayout(5, 7, 5, 5)); 
                    JButton button = new JButton(Integer.toString(i));
                    bluePanel.add(button);
                }
                
                
            });
            
            
            
            

            for (int i = 1; i <= 31; i++) {
                JButton button = new JButton(Integer.toString(i));
                bluePanel.add(button);
            }
            


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });   
        
        
        
            
                
               //ΚΕΝΟ ΓΙΑ ΝΑ ΞΕΧΩΡΙΖΕΙ ΤΟ SWING ΑΠΟ ΤΟΝ ΥΠΟΛΟΙΠΠΟ ΚΩΔΙΚΑ 
                
                
                
                
                
        
        Scanner input = new java.util.Scanner(System.in);
           
            ArrayList<Event> eventsList = new ArrayList<>();//A list created to add events(Appointments and projects)
            TimeTeller teller = TimeService.getTeller();     
            String calendarChoice = args[0].toLowerCase();
            String fileName = null;
            if(args.length == 1){
                  fileName = args[0];
            }else{
                 fileName = args[1];
            }
            
            fileName=getDirectoryPath()+ "//" + fileName ;
            
            int year=requestInt("Year");
            int month=requestChoice2(12,"Month (up to 12)");
            int day=requestChoice2(30,"Day (up to 30)" );
            int hours=requestChoice(23,"Hours(up to 23)");
            int minutes=requestChoice(59,"Minutes(up to 59)");
            int seconds=requestChoice(59,"Seconds(up to 59)");
            
            LocalDateTime time = LocalDateTime.of(year, month, day, hours, minutes, seconds);//The info for a specific day is added to a LocalDateTime variable
            LocalDateTime dt = teller.now();
            
            System.out.println(time.adjustInto(dt));
            Temporal timeNow = time.adjustInto(dt);
            
            eventsList = readAppointmentsFromFile(fileName, eventsList);//The ics file gets all the appointments
            eventsList = readProjectsFromFile(fileName, eventsList);//The ics file gets all the projects

            Comparator<Event> eventComparator = Comparator.comparing(Event::getDate);//A comparator created to compare the events besed on their day
            eventsList.sort(eventComparator);//EventList is getting sorted by their date with the comparator

            switch (args.length){

                case 1 :{ //Case the option is add an event
                   //Message in order for the user to decide wether they want to add an appointment or project
                   System.out.println("Create Appointment /Project |Type(1/2)");
                    
                   int ev_opt = requestChoice2(2,"1/2");
                   input.nextLine();

                    switch (ev_opt) { 

                        case 1:{ //Case they chose to add appointment

                           System.out.println("Add title");
                           String title = input.nextLine();

                           System.out.println("Date(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                           String dateString = input.nextLine();

                           //Instant instant = dateString.toInstant;
                           //LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
                           LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                           System.out.println("Converted LocalDateTime: " + date);
                           //Date date = dateFormat.parse(dateString);

                           System.out.println("Date Ends (yyyyMMddTHHmmssZ) example 20231226T163000Z");
                           String durationString = input.nextLine();

                           LocalDateTime duration = LocalDateTime.parse(durationString, formatter);
                           System.out.println("Add description");
                           String description = input.nextLine();

                           Appointment myAppointment = new Appointment(title,description,date,duration);
                           writeAppointmentToFile(myAppointment,eventsList,fileName);
                          
                           break;
                           
                           
                        }case 2 : { //Case they want to add a project
                       
                            System.out.println("Add title");
                            String title = input.nextLine();
                            
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
                            System.out.println("Project due date and time(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                            String dueDateTimeString = input.nextLine();
                            LocalDateTime dueDateTime = LocalDateTime.parse(dueDateTimeString, formatter);
                            
                            System.out.println("Add description");
                            String description = input.nextLine();

                            System.out.println("Give status of the project (true/false)");
                            boolean status = requestBoolean("true/false");

                            Project myProject = new Project(title, description, dueDateTime,status);
                            writeProjectToFile(myProject,eventsList,fileName);
                            
                            break;
                        } 
                       
                    }
                }case 2:{
                
                    switch (calendarChoice){
                        case "day":{
                            printEventsForTheDay(eventsList,timeNow);
                            break;
                            }
                        case "week":{
                            printEventsForTheWeek(eventsList,timeNow);
                            break;
                        }
                        case "month":{
                            printEventsForTheMonth(eventsList,timeNow);
                            break;
                        }
                        case "pastday":{
                            printEventsForThePastDay(eventsList,timeNow);
                            break;
                        }
                        case "pastweek":{
                            printEventsForThePastWeek(eventsList,timeNow);
                            break;
                        }
                        case "pastmonth":{
                            printEventsForThePastMonth(eventsList,timeNow);
                            break;
                        }
                        case "todo":{
                            printEventsForTheToDo(eventsList,timeNow);
                            break;
                        }
                        case "due":{
                            printEventsForTheDue(eventsList,timeNow);
                            break;
                        }
                    }
                    break;
                }
            } 
           
           TimeService.stop();
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
    

    
    
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(e.getSource()==){
//            System.out.println("Button 1 clicked!");
//            // Add your code here for handling the button click
//            }
//        }
        
    
    
    
    
    
       private static JPanel createGradientPanel(Color startColor, Color endColor) {
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
   
   }



