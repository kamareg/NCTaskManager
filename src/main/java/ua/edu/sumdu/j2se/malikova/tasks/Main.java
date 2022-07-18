package ua.edu.sumdu.j2se.malikova.tasks;


import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.MainView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task Manager is started");
        AbstractTaskList taskList = new ArrayTaskList();
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);

        Task task = new Task("loih", LocalDateTime.of(2022,05,20, 20,32) );
        Task task1 = new Task("loih1", LocalDateTime.of(2022,05,20, 20,32) );
        Task task2 = new Task("loih2", LocalDateTime.of(2022,05,20, 20,32) );
        Task task3 = new Task("loih3", LocalDateTime.of(2022,05,20, 20,32) );

        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        mainController.process(null);
        System.out.println("See you later");
    }
}

