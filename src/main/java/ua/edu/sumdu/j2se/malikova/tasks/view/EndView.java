package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;

/**
 * Responsible for displaying Exit message.
 */
public class EndView extends View{
    public static final Logger logger = Logger.getLogger(EndView.class);
    public static void endProcess(){
        System.out.println("See you later");
        logger.info("Task Manager is closed");
    }
}
