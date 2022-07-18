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

public class EditTaskView implements View{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Task task;
    private Date dateConstructor;
    private int interval;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private String input;
    private int taskNumber;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if(taskList.size()==0) {
            System.out.println("There are no tasks in your list yet. Please add tasks to the list first.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println(taskList);
        System.out.println("What task do you want to edit? Please, enter a sequence number. If you want to cancel this process please put 0.");
        getTask(taskList);

        if (taskNumber == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        task = taskList.getTask(taskNumber-1);
        System.out.println("Your choice is: \n" + task);

        System.out.println("What do you want to edit?");
        System.out.println("Choose from the list below.\n 1. Task title. \n 2. Task activity. ");
        if (task.isRepeated()){
            System.out.println(" 3. Task start time.\n 4. Task end time.\n 5. Task repeated interval.");
        } else {
            System.out.println(" 3. Task time.");
        }
        System.out.println("If you want to cancel this process please put 0.");

        for (; ; ) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.isEmpty()) {
                System.out.println("Please select an action number.");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (task.isRepeated() && Integer.parseInt(input) > 5) {
                    System.out.println("Please select an action number.");
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > 3) {
                    System.out.println("Please select an action number.");
                } else {
                    switch (Integer.parseInt(input)) {
                        case 1 -> {
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
                                    task.setTitle(input);
                                    break;
                                }
                            }
                        }
                        case 2 -> {
                            for (; ; ) {
                                System.out.println("What status do you want to set for the task? If active put +, if not active put -");
                                try {
                                    input = reader.readLine();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                if (input.equals("-")) {
                                    task.setActive(false);
                                    break;
                                } else if (input.equals("+")) {
                                    task.setActive(true);
                                    break;
                                } else if (input.equals("0")) {
                                    return Controller.MAIN_MENU_ACTION;
                                } else {
                                    System.out.println("Please make the right choice! If you want to cancel this process please put 0.");
                                }
                            }
                        }
                        case 3 -> {
                            if (task.isRepeated()) {
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
                                if ((task.getEndTime()).isBefore(start)) {
                                    System.out.println("Task end time is before start time. Please try again.");
                                    return Controller.MAIN_MENU_ACTION;
                                }
                                task.setStart(start);
                                System.out.println("Ok, now your task starts at " + start.format(dtf));
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
                                task.setTime(time);
                                System.out.println("Ok, now your task time is " + time.format(dtf));
                            }
                        }
                        case 4 -> {
                            dateConstructor = new Date(year,month,date,hour,minute);
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
                            if (end.isBefore(task.getStartTime())) {
                                System.out.println("Task end time is before start time. Please try again.");
                                return Controller.MAIN_MENU_ACTION;
                            }
                            task.setEnd(end);
                            System.out.println("Ok, now your task ends at " + end.format(dtf));
                        }
                        case 5 -> {
                            interval = dateConstructor.getInterval();
                            if (interval == 0) {
                                return Controller.MAIN_MENU_ACTION;
                            }
                            task.setInterval(interval);
                            System.out.println("Ok, your task interval is " + interval);
                        }
                    }
                    break;
                }
            } else {
                System.out.println("Please select an action number.");
            }
    }

        System.out.println("Task was edited.");
        return Controller.MAIN_MENU_ACTION;
    }

    private int getTask(AbstractTaskList taskList){
        for (; ; ) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.isEmpty()) {
                System.out.println("Please, enter a sequence number.");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (Integer.parseInt(input) > taskList.size()) {
                    System.out.println("Please enter a value within the bounds of the list!");
                } else {
                    taskNumber = Integer.parseInt(input);
                    break;
                }
            } else {
                System.out.println("Please, enter a sequence number.");
            }
        }
        return taskNumber;
    }
}
