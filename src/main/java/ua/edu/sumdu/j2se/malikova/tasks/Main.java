package ua.edu.sumdu.j2se.malikova.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.malikova.tasks.view.MainView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.io.File;

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);
    public static File saver = new File("save.txt");
    public static void main(String[] args) {
        System.out.println("Task Manager is started");
        logger.info("Task Manager is started");
        AbstractTaskList taskList = new ArrayTaskList();
        TaskIO.readText(taskList, saver);
        logger.info("List read from file");
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        Notificator notificator = new Notificator(taskList);
        notificator.setDaemon(true);
        notificator.start();
        logger.info("Notificator is started");
        mainController.process(null);
        System.out.println("See you later");
        logger.info("Task Manager is closed");
    }
}

