package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.view.EndView;
/**
 * A class for closing the task manager.
 */
public class EndController extends Controller{
    public static final Logger logger = Logger.getLogger(EndController.class);
    public static void endProcess() {
        logger.info("Task Manager is closed");
        EndView.endProcess();
    }
}
