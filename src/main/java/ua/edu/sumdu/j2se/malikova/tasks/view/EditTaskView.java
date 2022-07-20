package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.Date;
import ua.edu.sumdu.j2se.malikova.tasks.model.Input;
import ua.edu.sumdu.j2se.malikova.tasks.model.Validation;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditTaskView implements View {
    private Task task;
    private int interval, taskNumber;
    private LocalDateTime time, start, end;
    private String input;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");
    public final Logger logger = Logger.getLogger(EditTaskView.class);
    private static final int TASK_TITLE = 1;
    private static final int TASK_ACTIVITY = 2;
    private static final int TASK_TIME = 3;
    private static final int TASK_END_TIME = 4;
    private static final int TASK_REPEAT_INTERVAL = 5;

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list yet. Please add tasks to the list first.");
            logger.warn("There are no tasks in list.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println(taskList);
        System.out.println("What task do you want to edit? Please, enter a sequence number.");
        taskNumber = new Validation().getTask(taskList);
        task = taskList.getTask(taskNumber - 1);
        System.out.println("Your choice is: \n" + task);
        System.out.println("What do you want to edit?");
        System.out.println("Choose from the list below.\n " + TASK_TITLE + ". Task title. \n " + TASK_ACTIVITY + ". Task activity. ");
        if (task.isRepeated()) {
            System.out.println(" " + TASK_TIME + ". Task start time.\n " + TASK_END_TIME + ". Task end time.\n " + TASK_REPEAT_INTERVAL + ". Task repeated interval.");
        } else {
            System.out.println(" " + TASK_TIME + ". Task time.");
        }
        System.out.println("If you want to cancel this process please put " + Controller.MAIN_MENU_ACTION);

        for (; ; ) {
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("Please select an action number.");
                logger.error("Empty required field");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (task.isRepeated() && Integer.parseInt(input) > TASK_REPEAT_INTERVAL) {
                    System.out.println("Please select an action number.");
                    logger.error("Limit exceeded");
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > TASK_TIME) {
                    logger.error("Limit exceeded");
                    System.out.println("Please select an action number.");
                } else {
                    switch (Integer.parseInt(input)) {
                        case TASK_TITLE -> {
                            task.setTitle(new Validation().titleValidation());
                            logger.info("Task title was changed.");
                        }
                        case TASK_ACTIVITY -> {
                            task.setActive(new Validation().repeatedValidation());
                            logger.info("Task activity was changed.");
                        }
                        case TASK_TIME -> {
                            if (task.isRepeated()) {
                                System.out.println("You need to input the start time of the task.");
                                start = new Date().readyDate();
                                if ((task.getEndTime()).isBefore(start)) {
                                    System.out.println("Task end time is before start time. Please try again.");
                                    logger.warn("Task end time is before start time.");
                                    return Controller.MAIN_MENU_ACTION;
                                }
                                task.setStart(start);
                                System.out.println("Ok, now your task starts at " + start.format(dtf));
                                logger.info("Task start time was changed.");
                            } else {
                                System.out.println("You need to input the time of the task.");
                                time = new Date().readyDate();
                                task.setTime(time);
                                System.out.println("Ok, now your task time is " + time.format(dtf));
                                logger.info("Task time was changed.");
                            }
                        }
                        case TASK_END_TIME -> {
                            System.out.println("You need to input the end time of the task.");
                            end = new Date().readyDate();
                            if (end.isBefore(task.getStartTime())) {
                                System.out.println("Task end time is before start time. Please try again.");
                                logger.warn("Task end time is before start time.");
                                return Controller.MAIN_MENU_ACTION;
                            }
                            task.setEnd(end);
                            System.out.println("Ok, now your task ends at " + end.format(dtf));
                            logger.info("Task end time was changed.");
                        }
                        case TASK_REPEAT_INTERVAL -> {
                            interval = new Date().getInterval();
                            task.setInterval(interval);
                            System.out.println("Ok, your task interval is " + interval);
                            logger.info("Task interval was changed.");
                        }
                    }
                    break;
                }
            } else {
                System.out.println("Please select an action number.");
                logger.error("Required field not filled correctly");
            }
        }
        System.out.println("Task was edited.");
        return Controller.MAIN_MENU_ACTION;
    }
}
