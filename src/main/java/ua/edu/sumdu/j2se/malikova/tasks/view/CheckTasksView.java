package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

public class CheckTasksView implements View{
    public final Logger logger = Logger.getLogger(CheckTasksView.class);
    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size()==0) {
            System.out.println("There are no tasks in your list yet.");
            logger.warn("There are no tasks in list.");
        } else {
            System.out.println(taskList);
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
