package ua.edu.sumdu.j2se.malikova.tasks.view;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.Date;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarView implements View{
    private LocalDateTime first, second;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");
    public final Logger logger = Logger.getLogger(CalendarView.class);
    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list yet. Please add tasks to the list first.");
            logger.warn("There are no tasks in list.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println("You can view the task calendar in the required range from start to end.");
        System.out.println("Please enter the first point (start).");
        first = new Date().readyDate();
        System.out.println("Ok, your calendar range starts at " + first.format(dtf));
        System.out.println("Please enter the second point (end).");
        second = new Date().readyDate();
        if (second.isBefore(first)) {
            System.out.println("Second point is before first point. Please try again.");
            logger.warn("Second point is before first point.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println("Ok, your calendar range ends at " + second.format(dtf));
        System.out.println("Here is your calendar: \n");
        Tasks.calendar(taskList,first,second);
        return Controller.MAIN_MENU_ACTION;
    }
}
