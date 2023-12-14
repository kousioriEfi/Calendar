/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.hua.dit.oop2.calendar;

//Libraries used for the Event class
import java.time.LocalDateTime;
import java.util.Date;


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
