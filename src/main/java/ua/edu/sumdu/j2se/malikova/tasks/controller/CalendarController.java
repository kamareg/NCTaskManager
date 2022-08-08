package ua.edu.sumdu.j2se.malikova.tasks.controller;

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
    public CalendarController(View view, int action) {
        super(view, action);
    }
    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.noTasks();
            return Controller.MAIN_MENU_ACTION;
        }

        view.start();
        first = new DateController().readyDate();
        view.okTime("starts", first);

        view.end();
        second = new DateController().readyDate();
        if (second.isBefore(first)) {
            view.endBeforeStart();
            return Controller.MAIN_MENU_ACTION;
        }
        view.okTime("ends", second);
        view.okAdd(null);

        SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList,first,second);
        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
            LocalDateTime dateTime = entry.getKey();
            Set<Task> value = entry.getValue();
            CalendarView.calendarViewer(dateTime, value);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
