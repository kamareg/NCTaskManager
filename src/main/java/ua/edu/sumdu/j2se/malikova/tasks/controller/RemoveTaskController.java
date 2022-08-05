package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

/**
 * A class for removing task from the list process.
 */
public class RemoveTaskController extends Controller {
    public final Logger logger = Logger.getLogger(RemoveTaskController.class);

    public RemoveTaskController(View view, int action) {
        super(view, action);
    }

    public int process(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            view.text(Messages.NO_TASKS_IN_LIST, Messages.ADD_TASKS_TO_LIST);
            logger.warn(Messages.NO_TASKS_IN_LIST);
            return Controller.MAIN_MENU_ACTION;
        }
        view.listToPrint(taskList);
        view.text(Messages.TASK_TO_DELETE, Messages.WARN_SEQUENCE_NUMBER);
        taskList.remove(taskList.getTask((new ValidationController().getTask(taskList)) - 1));
        view.text(Messages.OK_TASK_DELETED);
        logger.info(Messages.OK_TASK_DELETED);
        return Controller.MAIN_MENU_ACTION;
    }
}
