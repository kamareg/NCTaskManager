package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
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
            view.text(Enum.NO_TASKS_IN_LIST, Enum.ADD_TASKS_TO_LIST);
            logger.warn(Enum.NO_TASKS_IN_LIST);
            return Controller.MAIN_MENU_ACTION;
        }
        view.listToPrint(taskList);
        view.text(Enum.TASK_TO_DELETE, Enum.WARN_SEQUENCE_NUMBER);
        taskList.remove(taskList.getTask((new ValidationController().getTask(taskList)) - 1));
        view.text(Enum.OK_TASK_DELETED);
        logger.info(Enum.OK_TASK_DELETED);
        return Controller.MAIN_MENU_ACTION;
    }
}
