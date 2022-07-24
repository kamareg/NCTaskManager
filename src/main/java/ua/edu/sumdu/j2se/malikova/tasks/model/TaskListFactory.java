package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;
/**
 * Factory for task list selection.
 */
public class TaskListFactory {
    public static final Logger logger = Logger.getLogger(TaskListFactory.class);
    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type.equals(ListTypes.types.ARRAY)) {
            return new ArrayTaskList();
        } else if (type.equals(ListTypes.types.LINKED)) {
            return new LinkedTaskList();
        } else {
            logger.error("Not the right option");
            return null;
        }
    }
}
