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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("What do you want to do? Please, choose a number: \n")
                .append(Controller.ADD_TASK_ACTION).append(". Add new task.\n")
                .append(Controller.REMOVE_TASK_ACTION).append(". Remove task.\n")
                .append(Controller.EDIT_TASK_ACTION).append(". Edit task.\n")
                .append(Controller.TASK_LIST_ACTION).append(". Check your tasks.\n")
                .append(Controller.CALENDAR_ACTION).append(". Set necessary time range.\n")
                .append(Controller.EXIT_ACTION).append(". Exit.\n");
        System.out.println(stringBuilder);

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
