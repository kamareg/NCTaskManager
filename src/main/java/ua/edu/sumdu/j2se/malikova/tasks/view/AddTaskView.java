package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.Date;
import ua.edu.sumdu.j2se.malikova.tasks.model.Validation;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTaskView implements View {
    private String title;
    private int interval;
    private boolean isRepeated;
    private LocalDateTime time, start, end;
    private Task task;
    public final Logger logger = Logger.getLogger(AddTaskView.class);
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");

    @Override
    public int printInfo(AbstractTaskList taskList) {
        title = new Validation().titleValidation();
        isRepeated = new Validation().repeatedValidation();
        if (isRepeated) {
            System.out.println("You need to input the start time of the task.");
            start = new Date().readyDate();
            System.out.println("Ok, your task starts at " + start.format(dtf));
            System.out.println("You need to input the end time of the task.");
            end = new Date().readyDate();
            if (end.isBefore(start)) {
                System.out.println("Task end time is before start time. Please try again.");
                logger.warn("Task end time is before start time.");
                return Controller.MAIN_MENU_ACTION;
            }
            System.out.println("Ok, your task ends at " + end.format(dtf));
            interval = new Date().getInterval();
            System.out.println("Ok, your task interval is " + interval);
            task = new Task(title, start, end, interval);
        } else {
            System.out.println("You need to input the time of the task.");
            time = new Date().readyDate();
            System.out.println("Ok, your task time is " + time.format(dtf));
            task = new Task(title, time);
        }
        task.setActive(true);
        taskList.add(task);
        System.out.println("Task was added to the list.");
        logger.info("Task " + task.getTitle() + " was added to the list.");
        return Controller.MAIN_MENU_ACTION;
    }
}


