package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.Validation;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

public class RemoveTaskView implements View {
    public final Logger logger = Logger.getLogger(RemoveTaskView.class);
    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list yet. Please add tasks to the list first.");
            logger.warn("There are no tasks in list.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println(taskList);
        System.out.println("What task do you want to delete? Please, enter a sequence number.");
        taskList.remove(taskList.getTask((new Validation().getTask(taskList)) - 1));
        System.out.println("Task was removed.");
        logger.info("Task was removed.");
        return Controller.MAIN_MENU_ACTION;
    }
}
