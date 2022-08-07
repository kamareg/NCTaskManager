package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

/**
 * Responsible for displaying Validation messages.
 */
public class ValidationView extends View {
    public final Logger logger = Logger.getLogger(ValidationView.class);
    public String titleValidation() {
        for (; ; ) {
            System.out.println(Messages.TITLE_OF_THE_TASK);
            String input = new Input().setInput();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                System.out.println(Messages.TITLE_CANT_BE_EMPTY);
            } else {
                return input;
            }
        }
    }

    public boolean repeatedValidation() {
        for (; ; ) {
            System.out.println(Messages.REPEATEDLY_TASK);
            String input = new Input().setInput();
            if (input.equals("-")) {
                return false;
            } else if (input.equals("+")) {
                return true;
            } else {
                System.out.println(Messages.RIGHT_CHOICE);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }

    public int getTask(AbstractTaskList taskList) {
        for (; ; ) {
            String input = new Input().setInput();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                System.out.println(Messages.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    System.out.println(Messages.VALUE_OUT_OF_LIST_BOUNDS);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else if (Integer.parseInt(input) > taskList.size()) {
                    System.out.println(Messages.VALUE_OUT_OF_LIST_BOUNDS);
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

