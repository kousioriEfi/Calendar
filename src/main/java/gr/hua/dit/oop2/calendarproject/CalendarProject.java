/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gr.hua.dit.oop2.calendarproject;

/**
 *
 * @author leftp
 */
public class CalendarProject {

    public static void main(String[] args) {
           
        java.util.Scanner input = new java.util.Scanner(System.in);
        
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
            
            System.out.println("Event added: " + myEvent.getTitle() + " on " + myEvent.getDate());
        }
        
    }
}
