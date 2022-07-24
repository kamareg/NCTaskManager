package ua.edu.sumdu.j2se.malikova.tasks.view;

import java.time.LocalDateTime;
import java.util.Set;
/**
 * Responsible for displaying calendar.
 */
public class CalendarView extends View {
    public static void calendarViewer(LocalDateTime time, Set value) {
        System.out.print("|" + time + "| \t : \t");
        System.out.println(value);
    }
}
