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
    private int interval;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private String input;
    private int taskNumber;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");
    public final Logger logger = Logger.getLogger(EditTaskView.class);

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
        System.out.println("Choose from the list below.\n 1. Task title. \n 2. Task activity. ");
        if (task.isRepeated()) {
            System.out.println(" 3. Task start time.\n 4. Task end time.\n 5. Task repeated interval.");
        } else {
            System.out.println(" 3. Task time.");
        }
        System.out.println("If you want to cancel this process please put 0.");

        for (; ; ) {
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("Please select an action number.");
                logger.error("Empty required field");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (task.isRepeated() && Integer.parseInt(input) > 5) {
                    System.out.println("Please select an action number.");
                    logger.error("Limit exceeded");
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > 3) {
                    logger.error("Limit exceeded");
                    System.out.println("Please select an action number.");
                } else {
                    switch (Integer.parseInt(input)) {
                        case 1 -> {
                            task.setTitle(new Validation().titleValidation());
                            logger.info("Task title was changed.");
                        }
                        case 2 -> {
                            task.setActive(new Validation().repeatedValidation());
                            logger.info("Task activity was changed.");
                        }
                        case 3 -> {
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
                        case 4 -> {
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
                        case 5 -> {
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
