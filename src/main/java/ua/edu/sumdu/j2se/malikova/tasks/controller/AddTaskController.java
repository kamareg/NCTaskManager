package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;
import java.time.LocalDateTime;

/**
 * A class for adding task process.
 */
public class AddTaskController extends Controller {
    private String title;
    private int interval;
    private boolean isRepeated;
    private LocalDateTime time, start, end;
    private Task task;

    public AddTaskController(View view, int action) {
        super(view, action);
    }

    public int process(AbstractTaskList taskList) {
        title = new ValidationController().titleValidation();
        isRepeated = new ValidationController().repeatedValidation();
        if (isRepeated) {
            AddTaskView.startTime();
            start = new DateController().readyDate();
            AddTaskView.okTime("start time", start);

            AddTaskView.endTime();
            end = new DateController().readyDate();
            if (end.isBefore(start)) {
                AddTaskView.endBeforeStart();
                return Controller.MAIN_MENU_ACTION;
            }
            AddTaskView.okTime("end time", end);

            interval = new DateController().getInterval();
            AddTaskView.okTime("interval", interval);
            task = new Task(title, start, end, interval);
        } else {
            AddTaskView.time();
            time = new DateController().readyDate();
            AddTaskView.okTime("time", time);
            task = new Task(title, time);
        }
        task.setActive(true);
        taskList.add(task);
        AddTaskView.okAdd(task);
        return Controller.MAIN_MENU_ACTION;
    }
}
