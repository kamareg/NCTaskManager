package ua.edu.sumdu.j2se.malikova.tasks;


import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.MainView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

public class Main {

    public static void main(String[] args) {
        System.out.println("Task Manager is started");
        AbstractTaskList taskList = new ArrayTaskList();
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        mainController.process(null);
        System.out.println("See you later");
    }
}

