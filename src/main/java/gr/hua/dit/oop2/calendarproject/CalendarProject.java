/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;
import gr.hua.dit.oop2.calendar.TimeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import gr.hua.dit.oop2.calendar.TimeService;
import gr.hua.dit.oop2.calendar.TimeTeller;   
import java.time.LocalDateTime;
import java.util.InputMismatchException;
/**
 *
 * @author leftp
 */
public class CalendarProject implements TimeTeller{
    public static void main(String[] args) throws ParseException {
        Scanner input = new java.util.Scanner(System.in);
        ArrayList<Event> eventsList = new ArrayList<>();
        ArrayList<Event> printEventList = new ArrayList<>();
        BufferedReader reader = null;
        TimeTeller teller = TimeService.getTeller();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        int year=requestInt("Year");
        int month=requestChoice2(12,"Month (up to 12)");
        int day=requestChoice2(30,"Day (up to 30)" );
        int hours=requestChoice2(23,"Hours(up to 23)");
        int minutes=requestChoice(59,"Minutes(up to 59)");
        int seconds=requestChoice(59,"Seconds(up to 59)");
        LocalDateTime time = LocalDateTime.of(year, month, day, hours, minutes, seconds);
        LocalDateTime dt = teller.now();
        System.out.println(time.adjustInto(dt));
        System.out.println("Events Before Sorting");
        
        
                
        eventsList = readAppointmentsFromFile("output.ics", eventsList);
        eventsList = readProjectsFromFile("output.ics", eventsList);
        
        printEventList = readAppointmentsFromFileTest("output.ics", printEventList);
        
        Comparator<Event> eventComparator = Comparator.comparing(Event::getDate);
        eventsList.sort(eventComparator);
        
        System.out.println("Events After Sorting");
       
        
        writeSortedEventsToFile(eventsList);        
        for(int i=0;i<eventsList.size();i++){
           eventsList.get(i).printEvent();

        }

        System.out.println("Add event (Press 1)");
        System.out.println("Edit event (Press 2)");
        
        int opt = input.nextInt();
        input.nextLine();
        
        switch (opt){
        
            case 1 :{
                
                System.out.println("Add appointment (Press 1)");
                System.out.println("Add project (Press 2)");
                
                int ev_opt = requestChoice2(2,"1 or 2");
                input.nextLine();
                
                switch (ev_opt) { 
                    
                    case 1:{
                    
                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Date(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String dateString = input.nextLine();
                        


                        Date date = dateFormat.parse(dateString);
                        
                        
                        System.out.println("Date Ends (yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String durationString = input.nextLine();
                        
                        Date duration = dateFormat.parse(durationString);
                        
                        
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        Appointment myAppointment = new Appointment(title,description,date,duration);
                        writeAppointmentToFile(myAppointment,eventsList);
                    }
                    break;
                    case 2 : {
                        System.out.println("Add title");
                        String title = input.nextLine();
            
                        System.out.println("Current Date and Time(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String dateString = input.nextLine();
                        


                        Date date = dateFormat.parse(dateString);
                        
                        
                        System.out.println("Project due date and time(yyyyMMddTHHmmssZ) example 20231226T163000Z");
                        String dueDateTimeString = input.nextLine();
                        
                        Date dueDateTime = dateFormat.parse(dueDateTimeString);
                        
                        
                        System.out.println("Add description");
                        String description = input.nextLine();
                        
                        System.out.println("Give status of the project (true/false)");
                        boolean status = requestBoolean("true/false");
                        
                        Project myProject = new Project(title, description, dueDateTime,status);
                        writeProjectToFile(myProject,eventsList);
                        ////////////////eventsList.sort(Comparator.comparing(Event::getDate));///////////////////////////////////////////////////////////////////
                          
                    break;
                    } 
                }
                break;
            }
        } //////////////
        
    }
    
    
    
    //this method is used for the request of the minutes and seconds because it allows the user to input 0>=
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
        
    public static int requestInt(String inputType){
        Scanner input = new java.util.Scanner(System.in);
        int number=0;
        boolean valid=false;
        do{
            try{
                System.out.println("Please input " + inputType);
                number=input.nextInt();
                valid=true;
            }catch(InputMismatchException e){
                System.out.println("Invalid input.Please provide integer for the " + inputType);
                input.nextLine();
            }
        }while(!valid);
        
        return number; 
    }
    
    
        public static boolean requestBoolean(String inputType){
        Scanner input = new java.util.Scanner(System.in);
        String userInput;
        boolean bool=false;
        boolean valid=false;
        do{
                System.out.println("Please input " + inputType);
                userInput=input.nextLine().toLowerCase();
                if(userInput.equals("true")||userInput.equals("false")){
                    bool=Boolean.parseBoolean(userInput);
                    valid=true; 
                }else{
            
                    System.out.println("Invalid input.Please provide " + inputType);
            }
        }while(!valid);
        
            return bool; 
        }
        
    
    private static void readEventsFromFile(String fileName) throws ParseException{
        BufferedReader reader = null;

         try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            String title = null;
            String date = null;
            String dueDate = null;
            SimpleDateFormat iCalendarFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

            while ((line = reader.readLine()) != null) {
                if(line.equals("BEGIN:VEVENT")){
                    while((line = reader.readLine())!=null){
                        if (line.startsWith("SUMMARY:")) {
                            // Extracting the event title (SUMMARY)
                            title = line.substring("SUMMARY:".length());
                            System.out.println("Title: " + title);
                        } else if (line.startsWith("DTSTART:")) {
                            date = line.substring("DTSTART:".length());
                            Date formattedDate = iCalendarFormat.parse(date);
                            System.out.println("Date: " + formattedDate);
                        }else if(line.startsWith("DUE;VALUE=DATE:")){
                            dueDate = line.substring("DUE;VALUE=DATE:".length());
                            Date formattedDueDate = iCalendarFormat.parse(dueDate);
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
    
    private static ArrayList<Event> readAppointmentsFromFile(String fileName, ArrayList<Event> eventsList) {
        BufferedReader reader = null;

         try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            String title = null;
            String date = null;
            String duration = null;
            String description = null;
            

            SimpleDateFormat iCalendarFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

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
                            
                            try {
                                Date formattedDate = iCalendarFormat.parse(date);
                                Date formattedDateEnd = iCalendarFormat.parse(duration);
                                // Date date = dateFormat.parse(dateString);
                                Appointment appointment = new Appointment(title,description,formattedDate,formattedDateEnd);
                                eventsList.add(appointment);
                                // Print the event details
                                if (title != null) {
                                    System.out.println("Title: " + title);
                                    System.out.println("Date: " + formattedDate);
                                    System.out.println("Date End: "+formattedDateEnd);
                                    System.out.println("Description: "+ description);
                                    System.out.println();
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
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
    //MALAKIA PROS TO PARON
    private static ArrayList<Event> readProjectsFromFile(String fileName, ArrayList<Event> eventsList){
        BufferedReader reader2 = null;
        try  {
            
            reader2 = new BufferedReader(new FileReader(fileName));
            String line2;
            String title2 = null;
            String date2 = null;
            String description2 = null;
            boolean status = false;

            SimpleDateFormat iCalendarFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
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

                            try {
                                Date formattedDate = iCalendarFormat.parse(date2);

                                // Date date = dateFormat.parse(dateString);
                                Project project = new Project(title2,description2,formattedDate,status);
                                eventsList.add(project);
                                // Print the event details
                                if (title2 != null) {
                                    System.out.println("Title: " + title2);
                                    System.out.println("Due Date Time:" + formattedDate);
                                    System.out.println("Status: "+ status);
                                    System.out.println("Description: "+ description2);
                                    System.out.println();
                                }

                                // Reset variables for the next event
                                title2 = null;
                                date2 = null;
                                description2 = null;

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
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
    
    private static void writeAppointmentToFile(Appointment appointment, ArrayList<Event> eventsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");

            eventsList.add(appointment);
            
                    writer.write("BEGIN:VEVENT\n");
                    writer.write("SUMMARY:" + appointment.getTitle() + "\n");
                    writer.write("DTSTART:" + dateFormat.format(appointment.getDate()) + "\n");
                    writer.write("DTEND:" + dateFormat.format(appointment.getDuration()) + "\n");
                    writer.write("DESCRIPTION:" + appointment.getDescription() + "\n");
                    writer.write("END:VEVENT\n");
                    writer.write("\n");
                
            

            writer.write("END:VCALENDAR\n");

            System.out.println("iCalendar file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void writeProjectToFile(Project project, ArrayList<Event> eventsList){
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics",true))) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                            
                            eventsList.add(project);
                        
                            writer.write("BEGIN:VCALENDAR\n");
                            writer.write("VERSION:2.0\n");
                               
                            writer.write("BEGIN:VTODO\n");
                            writer.write("SUMMARY:"+ project.getTitle()+"\n");
                            writer.write("DUE;VALUE=DATE:"+ dateFormat.format(project.getDate())+"\n");
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
    private static void writeSortedEventsToFile(ArrayList<Event> eventsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.ics"))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

            writer.write("BEGIN:VCALENDAR\n");
            writer.write("VERSION:2.0\n");

            for (Event event : eventsList) {
                // Add an event
                if (event instanceof Appointment) {
                    Appointment appointment = (Appointment) event;
                    writer.write("BEGIN:VEVENT\n");
                    writer.write("SUMMARY:" + appointment.getTitle() + "\n");
                    writer.write("DTSTART:" + dateFormat.format(appointment.getDate()) + "\n");
                    writer.write("DTEND:" + dateFormat.format(appointment.getDuration()) + "\n");
                    writer.write("DESCRIPTION:" + appointment.getDescription() + "\n");
                    // End the event
                    writer.write("END:VEVENT\n");
                    writer.write("\n");
                } else if (event instanceof Project) {
                    Project project = (Project) event;
                    writer.write("BEGIN:VTODO\n");
                    writer.write("SUMMARY:" + project.getTitle() + "\n");
                    writer.write("DUE;VALUE=DATE:" + dateFormat.format(project.getDate()) + "\n");
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

    @Override
    public LocalDateTime now() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addTimeListener(TimeListener tl) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void removeTimeListener(TimeListener tl) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
    
    
        private static void printDay(){
            
        }    
    
        private static ArrayList<Event> readAppointmentsFromFileTest(String fileName, ArrayList<Event> printEventList) {
        BufferedReader reader = null;

         try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            String title = null;
            String date = null;
            String duration = null;
            String description = null;
            

            SimpleDateFormat iCalendarFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");

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
                            
                            try {
                                Date formattedDate = iCalendarFormat.parse(date);
                                Date formattedDateEnd = iCalendarFormat.parse(duration);
                                // Date date = dateFormat.parse(dateString);
                                Appointment appointment = new Appointment(title,description,formattedDate,formattedDateEnd);
    //if statement wste an time isAfter kanei add sto list kai meta kanw print to list                          
                                printEventList.add(appointment);
                                // Print the event details
                                if (title != null) {
                                    System.out.println("Title: " + title);
                                    System.out.println("Date: " + formattedDate);
                                    System.out.println("Date End: "+formattedDateEnd);
                                    System.out.println("Description: "+ description);
                                    System.out.println();
                                }

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
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
        return printEventList;
    }
}


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
// //                       printDay();
//                    }
//                    case "week"-> {
//  //                      printWeek();
//                    }
//                    case "month"->{
//  //                      printMonth();
//
//                    }
//                    case "pastday"->{
//   //                     printPastday();
//
//                    }
//                    case "pastweek"->{
//  //                      printPastweek();
//
//                    }
//                    case "pastmonth"->{
// //                       printPastmonth();
//                    }
//                    case "todo"->{
//  //                      printTodo();
//                    }
//                    case "due"->{
////                        printDue();
//                    }
//                    default->{
//                        break;
//                    }
//                }
//            }
//DEN XREIAZETAI NA EINAI CASE ALLA NA EINAI ARGS.LENGTH == 0 TOTE...
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

   