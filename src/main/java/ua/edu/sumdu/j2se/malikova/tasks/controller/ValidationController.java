package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
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
            view.text(Enum.TITLE_OF_THE_TASK);
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Enum.EMPTY_FIELD);
                view.text(Enum.TITLE_CANT_BE_EMPTY);
            } else {
                title = input;
                break;
            }
        }
        return title;
    }

    public boolean repeatedValidation() {
        for (; ; ) {
            view.text(Enum.REPEATEDLY_TASK);
            input = view.input();
            if (input.equals("-")) {
                isRepeated = false;
                break;
            } else if (input.equals("+")) {
                isRepeated = true;
                break;
            } else {
                view.text(Enum.RIGHT_CHOICE);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return isRepeated;
    }

    public int getTask(AbstractTaskList taskList) {
        for (; ; ) {
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Enum.EMPTY_FIELD);
                view.text(Enum.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    view.text(Enum.VALUE_OUT_OF_LIST_BOUNDS);
                    logger.error(Enum.LIMIT_EXCEEDED);
                } else if (Integer.parseInt(input) > taskList.size()) {
                    view.text(Enum.VALUE_OUT_OF_LIST_BOUNDS);
                    logger.error(Enum.LIMIT_EXCEEDED);
                } else {
                    taskNumber = Integer.parseInt(input);
                    break;
                }
            } else {
                view.text(Enum.WARN_SEQUENCE_NUMBER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return taskNumber;
    }
}
