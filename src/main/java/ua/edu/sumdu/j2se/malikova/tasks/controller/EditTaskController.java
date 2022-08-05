package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

/**
 * A class for editing task process.
 */
public class EditTaskController extends Controller {
    public final Logger logger = Logger.getLogger(EditTaskController.class);
    private String input;
    private Task task;
    private int interval, taskNumber;
    private LocalDateTime time, start, end;
    private static final int TASK_TITLE = 1;
    private static final int TASK_ACTIVITY = 2;
    private static final int TASK_TIME = 3;
    private static final int TASK_END_TIME = 4;
    private static final int TASK_REPEAT_INTERVAL = 5;

    public EditTaskController(View view, int action) {
        super(view, action);
    }

    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.text(Messages.NO_TASKS_IN_LIST, Messages.ADD_TASKS_TO_LIST);
            logger.warn(Messages.NO_TASKS_IN_LIST);
            return Controller.MAIN_MENU_ACTION;
        }
        view.listToPrint(taskList);
        view.text(Messages.TASK_TO_EDIT, Messages.WARN_SEQUENCE_NUMBER);
        taskNumber = new ValidationController().getTask(taskList);
        task = taskList.getTask(taskNumber - 1);
        view.text("Your choice is: ", task.getTitle());
        view.text(Messages.PARAMETER_TO_EDIT);
        view.text("Choose from the list below:");
        view.text(String.valueOf(TASK_TITLE), ". Task title.");
        view.text(String.valueOf(TASK_ACTIVITY), ". Task activity. ");
        if (task.isRepeated()) {
            view.text(String.valueOf(TASK_TIME), ". Task start time.");
            view.text(String.valueOf(TASK_END_TIME), ". Task end time.");
            view.text(String.valueOf(TASK_REPEAT_INTERVAL), ". Task repeated interval.");
        } else {
            view.text(String.valueOf(TASK_TIME), ". Task time.");
        }
        view.text(Messages.CANCEL_OPERATION, String.valueOf(Controller.MAIN_MENU_ACTION));
        for (; ; ) {
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                view.text(Messages.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    break;
                } else if (task.isRepeated() && Integer.parseInt(input) > TASK_REPEAT_INTERVAL) {
                    view.text(Messages.WARN_SEQUENCE_NUMBER);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > TASK_TIME) {
                    view.text(Messages.WARN_SEQUENCE_NUMBER);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else {
                    switch (Integer.parseInt(input)) {
                        case TASK_TITLE -> {
                            task.setTitle(new ValidationController().titleValidation());
                            view.text(Messages.OK_TASK_EDITED);
                            logger.info("Task title was changed.");
                        }
                        case TASK_ACTIVITY -> {
                            task.setActive(new ValidationController().repeatedValidation());
                            view.text(Messages.OK_TASK_EDITED);
                            logger.info("Task activity was changed.");
                        }
                        case TASK_TIME -> {
                            if (task.isRepeated()) {
                                view.text(Messages.WRITE_START_TIME);
                                start = new DateController().readyDate();
                                if ((task.getEndTime()).isBefore(start)) {
                                    view.text(Messages.END_BEFORE_START, Messages.TRY_AGAIN);
                                    logger.warn(Messages.END_BEFORE_START);
                                    return Controller.MAIN_MENU_ACTION;
                                }
                                task.setStart(start);
                                view.text(Messages.OK_TASK_STARTS, start);
                                logger.info("Task start time was changed.");
                            } else {
                                view.text(Messages.WRITE_TIME);
                                time = new DateController().readyDate();
                                task.setTime(time);
                                view.text(Messages.OK_TASK_TIME, time);
                                logger.info("Task time was changed.");
                            }
                        }
                        case TASK_END_TIME -> {
                            view.text(Messages.WRITE_END_TIME);
                            end = new DateController().readyDate();
                            if (end.isBefore(task.getStartTime())) {
                                view.text(Messages.END_BEFORE_START, Messages.TRY_AGAIN);
                                logger.warn(Messages.END_BEFORE_START);
                                return Controller.MAIN_MENU_ACTION;
                            }
                            task.setEnd(end);
                            view.text(Messages.OK_TASK_ENDS, end);
                            logger.info("Task end time was changed.");
                        }
                        case TASK_REPEAT_INTERVAL -> {
                            interval = new DateController().getInterval();
                            task.setInterval(interval);
                            view.text(Messages.OK_TASK_INTERVAL, interval);
                            logger.info("Task interval was changed.");
                        }
                    }
                    break;
                }
            } else {
                view.text(Messages.WARN_SEQUENCE_NUMBER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}

