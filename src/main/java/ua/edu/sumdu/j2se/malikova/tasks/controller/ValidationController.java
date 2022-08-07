package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.ValidationView;

/**
 * A class containing constructors and methods for working with some validation processes such as title validation, repeated validation and searching task.
 */
public class ValidationController extends Controller {
    public ValidationController() {
    }
    public String titleValidation() {
        return new ValidationView().titleValidation();
    }

    public boolean repeatedValidation() {
        return new ValidationView().repeatedValidation();
    }

    public int getTask(AbstractTaskList taskList) {
        return new ValidationView().getTask(taskList);
    }
}
