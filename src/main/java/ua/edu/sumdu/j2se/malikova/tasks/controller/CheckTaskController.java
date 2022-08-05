package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;
/**
 * A class for checking the task list contains.
 */
public class CheckTaskController extends Controller {
    public final Logger logger = Logger.getLogger(CheckTaskController.class);

    public CheckTaskController(View view, int action) {
        super(view, action);
    }
    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.text(Messages.NO_TASKS_IN_LIST, Messages.ADD_TASKS_TO_LIST);
            logger.warn(Messages.NO_TASKS_IN_LIST);
            return Controller.MAIN_MENU_ACTION;
        } else {
            view.listToPrint(taskList);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
