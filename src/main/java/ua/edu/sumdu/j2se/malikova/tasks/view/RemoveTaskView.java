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
        System.out.println(new StringBuilder().append(Messages.NO_TASKS_IN_LIST).append(Messages.ADD_TASKS_TO_LIST));
        logger.warn(Messages.NO_TASKS_IN_LIST);
    }
    @Override
    public void listToPrint(AbstractTaskList taskList) {
        System.out.println(taskList);
    }

    @Override
    public void start() {
        System.out.println(new StringBuilder().append("What task do you want to delete? ").append(Messages.WARN_SEQUENCE_NUMBER));
    }

    @Override
    public void okReady(Task task) {
        System.out.println(new StringBuilder().append("Task '").append(task.getTitle()).append("' was removed."));
        logger.info(new StringBuilder().append("Task '").append(task.getTitle()).append("' was removed."));
    }
}
