package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.EditTaskView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

/**
 * A class for editing task process.
 */
public class EditTaskController extends Controller {
    private Task task;
    private int interval, taskNumber;
    private LocalDateTime time, start, end;
    public static final int TASK_TITLE = 1;
    public static final int TASK_ACTIVITY = 2;
    public static final int TASK_TIME = 3;
    public static final int TASK_END_TIME = 4;
    public static final int TASK_REPEAT_INTERVAL = 5;

    public EditTaskController(View view, int action) {
        super(view, action);
    }

    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.noTasks();
            return Controller.MAIN_MENU_ACTION;
        }
        view.listToPrint(taskList);
        taskNumber = new ValidationController().getTask(taskList);
        task = taskList.getTask(taskNumber - 1);
        EditTaskView.menu(task);
        int choice = EditTaskView.choice(task);
        switch (choice) {
            case Controller.MAIN_MENU_ACTION -> {
                return Controller.MAIN_MENU_ACTION;
            }
            case TASK_TITLE -> {
                task.setTitle(new ValidationController().titleValidation());
                EditTaskView.setTitleEdition();
            }
            case TASK_ACTIVITY -> {
                task.setActive(new ValidationController().repeatedValidation());
                EditTaskView.setTaskActivity();
            }
            case TASK_TIME -> {
                if (task.isRepeated()) {
                    view.start();
                    start = new DateController().readyDate();
                    if ((task.getEndTime()).isBefore(start)) {
                        view.endBeforeStart();
                        return Controller.MAIN_MENU_ACTION;
                    }
                    task.setStart(start);
                    view.okTime("start time", start);
                } else {
                    view.time();
                    time = new DateController().readyDate();
                    task.setTime(time);
                    view.okTime("time", time);
                }
            }
            case TASK_END_TIME -> {
                view.end();
                end = new DateController().readyDate();
                if (end.isBefore(task.getStartTime())) {
                   view.endBeforeStart();
                    return Controller.MAIN_MENU_ACTION;
                }
                task.setEnd(end);
                view.okTime("end time", end);
            }
            case TASK_REPEAT_INTERVAL -> {
                interval = new DateController().getInterval();
                task.setInterval(interval);
                view.okTime("interval", interval);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
