package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.Input;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

public class MainView implements View {
    String input;
    int choice = Controller.MAIN_MENU_ACTION;
    public final Logger logger = Logger.getLogger(MainView.class);

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("What do you want to do? Please, choose a number:");
        System.out.println(Controller.ADD_TASK_ACTION + ". Add new task.");
        System.out.println(Controller.REMOVE_TASK_ACTION + ". Remove task.");
        System.out.println(Controller.EDIT_TASK_ACTION + ". Edit task.");
        System.out.println(Controller.TASK_LIST_ACTION + ". Check your tasks.");
        System.out.println(Controller.CALENDAR_ACTION + ". Set necessary time range.");
        System.out.println(Controller.EXIT_ACTION + ". Exit.");

        input = new Input().setInput();
        if (input.isEmpty()) {
            logger.error("Empty required field");
            System.out.println("Please, enter a sequence number.");
        } else if (input.matches("[0-9]*")) {
            if (Integer.parseInt(input) > Controller.EXIT_ACTION) {
                System.out.println("Please, enter a sequence number.");
                logger.error("Limit exceeded");
            } else {
                choice = Integer.parseInt(input);
            }
        } else {
            System.out.println("Please, enter a sequence number.");
            logger.error("Required field not filled correctly");
        }
        return choice;
    }
}
