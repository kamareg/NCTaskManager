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
    Task task;
    Date dateConstructor;
    private String title;
    private int interval;
    private boolean isRepeated;
    private boolean active;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    String input;
    int taskNumber;
    int year;
    int month;
    int date;
    int hour;
    int minute;
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
        System.out.println("Choose from the list below.\n 1. Task title. \n 2. Task activity. \n 3. Repeatability");
        if (task.isRepeated()){
            System.out.println(" 4. Task start time.\n 5. Task end time.\n 6. Task repeated interval.");
        } else {
            System.out.println(" 4. Task time.");
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
                } else if (task.isRepeated() && Integer.parseInt(input) > 6) {
                    System.out.println("Please select an action number.");
                } else if (!(task.isRepeated()) && Integer.parseInt(input) > 4) {
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
                                    System.out.println(task);
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
                            for (; ; ) {
                                System.out.println("Will this task be repeatable? If yes put +, if no put -");
                                try {
                                    input = reader.readLine();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                if (input.equals("-")) {
                                    task.setRepeated(false);
                                    break;
                                } else if (input.equals("+")) {
                                    task.setRepeated(true);
                                    break;
                                } else if (input.equals("0")) {
                                    return Controller.MAIN_MENU_ACTION;
                                } else {
                                    System.out.println("Please make the right choice! If you want to cancel this process please put 0.");
                                }
                            }
                        }
                        case 4 -> {
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
                                task.setTime(start);
                                System.out.println("Ok, your task starts at " + start.format(dtf));
                                System.out.println(task);
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
                                System.out.println("Ok, your task time is " + time.format(dtf));
                                System.out.println(task);
                            }
                        }
                        case 5 -> {

                        }
                        case 6 -> {

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
