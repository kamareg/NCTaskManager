package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.Input;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

public class MainView implements View {
    String input;
    int choice = 0;
    public final Logger logger = Logger.getLogger(EditTaskView.class);

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("What do you want to do? Please, choose a number:");
        System.out.println("1. Add new task.");
        System.out.println("2. Remove task.");
        System.out.println("3. Edit task.");
        System.out.println("4. Check your tasks.");
        System.out.println("5. Set necessary time range.");
        System.out.println("6. Exit.");

        input = new Input().setInput();
        if (input.isEmpty()) {
            logger.error("Empty required field");
            System.out.println("Please, enter a sequence number.");
        } else if (input.matches("[0-9]*")) {
            if (Integer.parseInt(input) > 6) {
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
