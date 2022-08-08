package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Responsible for displaying calendar.
 */
public class CalendarView extends View {
    public static final Logger logger = Logger.getLogger(CalendarView.class);

    @Override
    public void noTasks() {
        System.out.println(Messages.NO_TASKS_IN_LIST + Messages.ADD_TASKS_TO_LIST);
        logger.warn(Messages.NO_TASKS_IN_LIST);
    }

    @Override
    public void start() {
        System.out.println("You can view the task calendar in the required range from start to end. \nPlease enter the first point (start). ");
    }

    @Override
    public void end() {
        System.out.println("Please enter the second point (end).");
    }

    @Override
    public void okTime(String string, LocalDateTime time) {
        System.out.println("Ok, your calendar range " + string + " at " + time);
    }

    @Override
    public void endBeforeStart() {
        System.out.println(Messages.SECOND_BEFORE_FIRST + Messages.TRY_AGAIN);
        logger.warn(Messages.SECOND_BEFORE_FIRST);
    }

    @Override
    public void okReady(Task task) {
        System.out.println("Here is your calendar: \n");
    }

    public static void calendarViewer(LocalDateTime time, Set value) {
        System.out.print("|" + time + "| \t : \t");
        System.out.println(value);
    }
}
