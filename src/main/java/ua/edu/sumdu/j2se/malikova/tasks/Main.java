package ua.edu.sumdu.j2se.malikova.tasks;


import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.model.Tasks;
import ua.edu.sumdu.j2se.malikova.tasks.view.MainView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task Manager is started");
        AbstractTaskList taskList = new ArrayTaskList();
        Task task = new Task("loih", LocalDateTime.of(2021,05,20, 20,32) );
        Task task1 = new Task("loih1", LocalDateTime.of(2022,05,20, 20,32) );
        Task task2 = new Task("loih2", LocalDateTime.of(2023,05,20, 20,32) );
        Task task3 = new Task("loih3", LocalDateTime.of(2024,05,20, 20,32) );
        task.setActive(true);
        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        System.out.println(Tasks.calendar(taskList, (LocalDateTime.of(2000,1,1,1,1)), (LocalDateTime.of(2050,1,1,1,1)) ));

        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);

               mainController.process(null);
        System.out.println("See you later");
    }
}

