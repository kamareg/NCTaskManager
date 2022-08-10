package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.malikova.tasks.controller.EditTaskController.*;

/**
 * Responsible for displaying Edit task messages.
 */
public class EditTaskView extends View {
    public static final Logger logger = Logger.getLogger(EditTaskView.class);

    @Override
    public void noTasks() {
        System.out.println(new StringBuilder(Messages.NO_TASKS_IN_LIST).append(Messages.ADD_TASKS_TO_LIST));
        logger.warn(Messages.NO_TASKS_IN_LIST);
    }

    @Override
    public void listToPrint(AbstractTaskList taskList) {
        System.out.println(taskList);
        System.out.println(new StringBuilder("What task do you want to edit? ").append(Messages.WARN_SEQUENCE_NUMBER));
    }

    @Override
    public void start() {
        System.out.println(Messages.WRITE_START_TIME);
    }

    @Override
    public void endBeforeStart() {
        System.out.println(new StringBuilder(Messages.END_BEFORE_START).append(Messages.TRY_AGAIN));
        logger.warn(Messages.END_BEFORE_START);
    }

    @Override
    public void okTime(String string, LocalDateTime time) {
        System.out.println(new StringBuilder("Ok, your task ").append(string).append(": ").append(time));
        logger.info("Task was changed.");
    }

    @Override
    public void okTime(String string, int time) {
        System.out.println(new StringBuilder("Ok, your task ").append(string).append(": ").append(time));
        logger.info("Task was changed.");
    }

    @Override
    public void end() {
        System.out.println(Messages.WRITE_END_TIME);
    }

    @Override
    public void time() {
        System.out.println(Messages.WRITE_TIME);
    }

    public static void menu(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your choice is: ").append(task.getTitle()).append("\nWhat do you want to edit?").append("\nChoose from the list below:\n").append(TASK_TITLE).append(". Task title.\n").append(TASK_ACTIVITY).append(". Task activity. \n");
        if (task.isRepeated()) {
            stringBuilder.append(TASK_TIME).append(". Task start time.\n").append(TASK_END_TIME).append(". Task end time.\n").append(TASK_REPEAT_INTERVAL).append(". Task repeated interval.\n");
        } else {
            stringBuilder.append(TASK_TIME).append(". Task time.\n");
        }
        stringBuilder.append("If you want to cancel this process please put ").append(Controller.MAIN_MENU_ACTION);
        System.out.println(stringBuilder);
    }

    public static int choice(Task task) {
        for (; ; ) {
            String input = new Input().setInput();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                System.out.println(Messages.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == Controller.MAIN_MENU_ACTION) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (task.isRepeated() && Integer.parseInt(input) > TASK_REPEAT_INTERVAL) {
                    System.out.println(Messages.WARN_SEQUENCE_NUMBER);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > TASK_TIME) {
                    System.out.println(Messages.WARN_SEQUENCE_NUMBER);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else {
                    return Integer.parseInt(input);
                }
            } else {
                System.out.println(Messages.WARN_SEQUENCE_NUMBER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }

    public static void setTitleEdition() {
        System.out.println("Task title was changed.");
        logger.info("Task title was changed.");
    }

    public static void setTaskActivity() {
        System.out.println("Task activity was changed.");
        logger.info("Task activity was changed.");
    }
}
