package ua.edu.sumdu.j2se.malikova.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.controller.EndController;
import ua.edu.sumdu.j2se.malikova.tasks.controller.MainController;
import ua.edu.sumdu.j2se.malikova.tasks.controller.NotificatorController;
import ua.edu.sumdu.j2se.malikova.tasks.model.*;
import ua.edu.sumdu.j2se.malikova.tasks.view.MainView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.io.File;
import java.util.Timer;

/**
 * Main starting class
 */

public class Main {
    public static final Logger logger = Logger.getLogger(Main.class);
    public static File saver = new File("save.json");
    public static Timer timer = new Timer();

    public static void main(String[] args) {
        AbstractTaskList taskList = new LinkedTaskList();
        TaskIO.readText(taskList, saver);
        logger.info("List read from file");
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        logger.info(Messages.TASK_MANAGER_STARTED);
        NotificatorController notificator = new NotificatorController(taskList);
        notificator.setDaemon(true);
        notificator.start();
        logger.info("Notificator is started");
        mainController.process(null);
        EndController.endProcess();
        timer.cancel();
    }
}

