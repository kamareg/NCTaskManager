package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

/**
 * Responsible for displaying Remove Tasks messages.
 */
public class RemoveTaskView extends View {
    public final Logger logger = Logger.getLogger(RemoveTaskView.class);
    @Override
    public void noTasks() {
        System.out.println(Messages.NO_TASKS_IN_LIST + Messages.ADD_TASKS_TO_LIST);
        logger.warn(Messages.NO_TASKS_IN_LIST);
    }
    @Override
    public void listToPrint(AbstractTaskList taskList) {
        System.out.println(taskList);
    }

    @Override
    public void start() {
        System.out.println("What task do you want to delete? " + Messages.WARN_SEQUENCE_NUMBER);
    }

    @Override
    public void okReady(Task task) {
        System.out.println("Task '" + task.getTitle() + "' was removed.");
        logger.info("Task '" + task.getTitle() + "' was removed.");
    }
}
