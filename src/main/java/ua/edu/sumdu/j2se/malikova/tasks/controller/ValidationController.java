package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.ValidationView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

/**
 * A class containing constructors and methods for working with some validation processes such as title validation, repeated validation and searching task.
 */
public class ValidationController extends Controller {
    private String input, title;
    private boolean isRepeated;
    private int taskNumber;
    public final Logger logger = Logger.getLogger(ValidationController.class);
    View view = new ValidationView();

    public ValidationController() {
    }

    public ValidationController(View view, int action) {
        super(view, action);
    }

    public String titleValidation() {
        for (; ; ) {
            view.text(Messages.TITLE_OF_THE_TASK);
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                view.text(Messages.TITLE_CANT_BE_EMPTY);
            } else {
                title = input;
                break;
            }
        }
        return title;
    }

    public boolean repeatedValidation() {
        for (; ; ) {
            view.text(Messages.REPEATEDLY_TASK);
            input = view.input();
            if (input.equals("-")) {
                isRepeated = false;
                break;
            } else if (input.equals("+")) {
                isRepeated = true;
                break;
            } else {
                view.text(Messages.RIGHT_CHOICE);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return isRepeated;
    }

    public int getTask(AbstractTaskList taskList) {
        for (; ; ) {
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Messages.EMPTY_FIELD);
                view.text(Messages.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    view.text(Messages.VALUE_OUT_OF_LIST_BOUNDS);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else if (Integer.parseInt(input) > taskList.size()) {
                    view.text(Messages.VALUE_OUT_OF_LIST_BOUNDS);
                    logger.error(Messages.LIMIT_EXCEEDED);
                } else {
                    taskNumber = Integer.parseInt(input);
                    break;
                }
            } else {
                view.text(Messages.WARN_SEQUENCE_NUMBER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return taskNumber;
    }
}
