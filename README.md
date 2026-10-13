# 📅 Calendar App (Java)

A desktop calendar application built in **Java**, developed as a team project for the course *Object-Oriented Programming II* at Harokopio University of Athens.

## ✨ Features
- Create and edit calendar events
- Import and export events using the standard **iCalendar (.ics)** format, compatible with apps like Google Calendar and Outlook
- Graphical user interface (GUI)

## 🛠️ Tech Stack
- **Java**
- **Maven** for build and dependency management
- **Java Swing / AWT** for the user interface
- **iCalendar (.ics)** file handling

## 🚀 How to Run
1. Clone the repository
2. Open it in an IDE with Maven support (e.g. NetBeans or IntelliJ IDEA)
3. Build the project with Maven
4. Run the main class

> **Note:** The app uses a graphical interface, so it needs a desktop environment.
> Running it in a terminal without a display (e.g. a remote server) causes a `java.awt.HeadlessException`.

## 👥 Team
- [leftpap6](https://github.com/leftpap6)
- [kousioriEfi](https://github.com/kousioriEfi)
- [Masterburn3](https://github.com/Masterburn3)

----------OLD READ ME----------
*ΤΙ ΤΡΕΧΕΙ:

ΤΟ ΠΡΟΓΡΑΜΜΑ ΤΡΕΧΕΙ ΣΤΟ TERMINAL ΑΠΛΩΣ ΣΕ ΕΜΑΣ ΕΜΦΑΝΙΖΕΙ ΑΥΤΟ ΤΟ ERROR:

Exception in thread "AWT-EventQueue-0" java.awt.HeadlessException:
No X11 DISPLAY variable was set,
or no headful library support was found,
but this program performed an operation which requires it

                          
ΠΙΣΤΕΥΟΥΜΕ ΠΩΣ ΣΕ ΕΣΑΣ ΛΕΙΤΟΥΡΓΕΙ ΚΑΝΟΝΙΚΑ.
 
Χρησιμοποιήθηκε το chatgpt σε μερικές περιστάσεις για την αντιμετώπιση αδιεξόδων και errors.

*ΓΙΑ ΤΗΝ ΜΕΤΑΓΛΩΤΤΙΣΗ:

(1) Calendar/src/main/java/gr/hua/dit/oop2/calendar$ javac CalendarProject.java Event.java Project.java Appointment.java

(2) mvn clean install 

*ΓΙΑ ΤΟ ΤΡΕΞΙΜΟ:

-ΤΟ ΠΡΟΓΡΑΜΜΑ ΞΕΚΙΝΑΕΙ ΖΗΤΩΝΤΑΣ ΑΠΟ ΤΟ ΧΡΗΣΤΗ ΤΟ .ics FILE ΜΕ ΤΟ ΟΠΟΙΟ ΘΕΛΕΙ ΝΑ ΔΟΥΛΕΨΕΙ(ΕΜΕΙΣ ΓΙΑ ΤΕΣΤ ΕΧΟΥΜΕ output.ics ΚΑΙ calendar.ics).
(ΣΕ ΠΕΡΙΠΤΩΣΗ ΠΟΥ ΔΕΝ ΒΡΙΣΚΕΙ ΤΟ .ics file ΣΑΣ ΜΠΟΡΕΙΤΕ ΝΑ ΤΟ ΒΑΛΕΤΕ ΣΤΗ ΓΡΑΜΜΗ ΕΚΤΕΛΕΣΗΣ).
Π.Χ. java -jar CalendarProject-1.0-SNAPSHOT.jar output.ics


Η ΓΡΑΜΜΗ ΕΚΤΕΛΕΣΗΣ ΕΙΝΑΙ:
(3) java -jar CalendarProject-1.0-SNAPSHOT.jar



