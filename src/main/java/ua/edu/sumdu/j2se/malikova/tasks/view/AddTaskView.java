package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.Date;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddTaskView implements View {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String title;
    Date dateConstructor;
    private int interval;
    private boolean isRepeated;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    String input;
    int year;
    int month;
    int date;
    int hour;
    int minute;
    Task task;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");


    @Override
    public int printInfo(AbstractTaskList taskList) {
        for (; ; ) {
            System.out.println("What is the title of the task?");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.isEmpty()) {
                System.out.println("Title cannot be empty!");
            } else {
                title = input;
                break;
            }
        }

        for (; ; ) {
            System.out.println("Will this task be repeated? If yes, put +, if no put -");
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.equals("-")) {
                isRepeated = false;
                break;
            } else if (input.equals("+")) {
                isRepeated = true;
                break;
            } else if (input.equals("0")) {
                return Controller.MAIN_MENU_ACTION;
            } else {
                System.out.println("Please make the right choice! If you want to cancel this process please put 0.");
            }
        }

        if (isRepeated) {
            dateConstructor = new Date(year,month,date,hour,minute);

            System.out.println("You need to input the start time of the task.");
            year = dateConstructor.getYear();
            if (year == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            month = dateConstructor.getMonth();
            if (month == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            date = dateConstructor.getDate();
            if (date == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            hour = dateConstructor.getHour();
            minute = dateConstructor.getMinute();

            start = LocalDateTime.of(year, month, date, hour, minute);
            System.out.println("Ok, your task starts at " + start.format(dtf));

            System.out.println("You need to input the end time of the task.");
            year = dateConstructor.getYear();
            if (year == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            month = dateConstructor.getMonth();
            if (month == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            date = dateConstructor.getDate();
            if (date == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            hour = dateConstructor.getHour();
            minute = dateConstructor.getMinute();

            end = LocalDateTime.of(year, month, date, hour, minute);
            if (end.isBefore(start)) {
                System.out.println("Task end time is before start time. Please try again.");
                return Controller.MAIN_MENU_ACTION;
            }
            System.out.println("Ok, your task ends at " + end.format(dtf));

            interval = dateConstructor.getInterval();
            if (interval == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            task = new Task(title, start, end, interval);

    } else {
            dateConstructor = new Date(year,month,date,hour,minute);
            System.out.println("You need to input the time of the task.");
            year = dateConstructor.getYear();
            if (year == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            month = dateConstructor.getMonth();
            if (month == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            date = dateConstructor.getDate();
            if (date == 0) {
                return Controller.MAIN_MENU_ACTION;
            }
            hour = dateConstructor.getHour();
            minute = dateConstructor.getMinute();

            time = LocalDateTime.of(year, month, date, hour, minute);
            System.out.println("Ok, your task time is " + time.format(dtf));

            task = new Task(title, time);
        }
        task.setActive(true);
        taskList.add(task);

        return Controller.MAIN_MENU_ACTION;
}
}


