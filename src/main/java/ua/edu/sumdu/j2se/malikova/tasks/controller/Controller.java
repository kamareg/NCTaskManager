package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

/**
 * A main controller.
 */
public abstract class Controller {
    public static final int MAIN_MENU_ACTION = 0;
    public static final int ADD_TASK_ACTION = 1;
    public static final int REMOVE_TASK_ACTION = 2;
    public static final int EDIT_TASK_ACTION = 3;
    public static final int TASK_LIST_ACTION = 4;
    public static final int CALENDAR_ACTION = 5;
    public static final int EXIT_ACTION = 6;

    protected View view;
    protected int actionToDo = 0;
    protected AbstractTaskList taskList;

    public Controller() {
    }

    public Controller(View view, int action) {
        this.actionToDo = action;
        this.view = view;
    }

    public boolean canProcess(int action) {
        return action == actionToDo;
    }

    public int process(AbstractTaskList taskList) {
        return 0;
    }
}
