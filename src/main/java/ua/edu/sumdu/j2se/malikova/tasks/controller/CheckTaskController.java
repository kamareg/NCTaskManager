package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;
/**
 * A class for checking the task list contains.
 */
public class CheckTaskController extends Controller {
    public CheckTaskController(View view, int action) {
        super(view, action);
    }
    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.noTasks();
            return Controller.MAIN_MENU_ACTION;
        } else {
            view.listToPrint(taskList);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
