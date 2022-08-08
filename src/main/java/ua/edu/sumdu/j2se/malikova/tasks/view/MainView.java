package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;

/**
 * Responsible for displaying Main Page.
 */
public class MainView extends View {
    public static final Logger logger = Logger.getLogger(MainView.class);

    @Override
    public void start() {
        System.out.println(Messages.TASK_MANAGER_STARTED);
    }

    public static int printMenu() {
        System.out.println("What do you want to do? Please, choose a number: ");
        System.out.println(Controller.ADD_TASK_ACTION + ". Add new task.");
        System.out.println(Controller.REMOVE_TASK_ACTION + ". Remove task.");
        System.out.println(Controller.EDIT_TASK_ACTION + ". Edit task.");
        System.out.println(Controller.TASK_LIST_ACTION + ". Check your tasks.");
        System.out.println(Controller.CALENDAR_ACTION + ". Set necessary time range.");
        System.out.println(Controller.EXIT_ACTION + ". Exit.");
        for (; ; ) {
            String input = new Input().setInput();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                System.out.println(Messages.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) > Controller.EXIT_ACTION) {
                    System.out.println(Messages.WARN_SEQUENCE_NUMBER);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else {
                    return Integer.parseInt(input);
                }
            } else {
                System.out.println(Messages.WARN_SEQUENCE_NUMBER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }
}
