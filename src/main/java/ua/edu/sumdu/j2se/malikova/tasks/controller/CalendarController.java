package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.model.Tasks;
import ua.edu.sumdu.j2se.malikova.tasks.view.CalendarView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
/**
 * A class for displaying the calendar.
 */
public class CalendarController extends Controller {
    private LocalDateTime first, second;

    public final Logger logger = Logger.getLogger(CalendarController.class);
    public CalendarController(View view, int action) {
        super(view, action);
    }
    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.text(Enum.NO_TASKS_IN_LIST, Enum.ADD_TASKS_TO_LIST);
            logger.warn(Enum.NO_TASKS_IN_LIST);
            return Controller.MAIN_MENU_ACTION;
        }
        view.text(Enum.CALENDAR_BEGIN);
        first = new DateController().readyDate();
        view.text(Enum.CALENDAR_BEFORE_SECOND_POINT, first);
        view.text(Enum.ENTER_SECOND_POINT);
        second = new DateController().readyDate();
        if (second.isBefore(first)) {
            view.text(Enum.SECOND_BEFORE_FIRST, Enum.TRY_AGAIN);
            logger.warn(Enum.SECOND_BEFORE_FIRST);
            return Controller.MAIN_MENU_ACTION;
        }
        view.text(Enum.CALENDAR_AFTER_SECOND_POINT, second);
        view.text(Enum.READY_CALENDAR);
        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList,first,second);
        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
            LocalDateTime dateTime = entry.getKey();
            Set<Task> value = entry.getValue();
            CalendarView.calendarViewer(dateTime, value);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
