package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

/**
 * Responsible for displaying the task list.
 */
public class CheckTasksView extends View{
    public final Logger logger = Logger.getLogger(CheckTasksView.class);

    @Override
    public void noTasks() {
        System.out.println(Messages.NO_TASKS_IN_LIST + Messages.ADD_TASKS_TO_LIST);
        logger.warn(Messages.NO_TASKS_IN_LIST);
    }

    @Override
    public void listToPrint(AbstractTaskList taskList) {
        System.out.println(taskList);
    }
}
