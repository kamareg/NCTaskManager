package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
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
    public final Logger logger = Logger.getLogger(AddTaskController.class);

    public AddTaskController(View view, int action) {
        super(view, action);
    }

    public int process(AbstractTaskList taskList) {
        title = new ValidationController().titleValidation();
        isRepeated = new ValidationController().repeatedValidation();
        if (isRepeated) {
            view.text(Enum.WRITE_START_TIME);
            start = new DateController().readyDate();
            view.text(Enum.OK_TASK_STARTS, start);
            view.text(Enum.WRITE_END_TIME);
            end = new DateController().readyDate();
            if (end.isBefore(start)) {
                view.text(Enum.END_BEFORE_START, Enum.TRY_AGAIN);
                logger.warn(Enum.END_BEFORE_START);
                return Controller.MAIN_MENU_ACTION;
            }
            view.text(Enum.OK_TASK_ENDS, end);
            interval = new DateController().getInterval();
            view.text(Enum.OK_TASK_INTERVAL, interval);
            task = new Task(title, start, end, interval);
        } else {
            view.text(Enum.WRITE_TIME);
            time = new DateController().readyDate();
            view.text(Enum.OK_TASK_TIME, time);
            task = new Task(title, time);
        }
        task.setActive(true);
        taskList.add(task);
        view.text(Enum.OK_TASK_ADDED);
        logger.info(task.getTitle() + "" + Enum.OK_TASK_ADDED);
        return Controller.MAIN_MENU_ACTION;
    }
}
