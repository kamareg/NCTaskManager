package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;

public class Validation {
    private String input;
    private String title;
    private boolean isRepeated;
    private int taskNumber;
    public final Logger logger = Logger.getLogger(Validation.class);

    public String titleValidation() {
        for (; ; ) {
            System.out.println("What is the title of the task?");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("Title cannot be empty!");
                logger.error("Empty required field");
            } else {
                title = input;
                break;
            }
        }
        return title;
    }

    public boolean repeatedValidation() {
        for (; ; ) {
            System.out.println("Will this task be repeated? If yes, put +, if no put -");
            input = new Input().setInput();
            if (input.equals("-")) {
                isRepeated = false;
                break;
            } else if (input.equals("+")) {
                isRepeated = true;
                break;
            } else {
                System.out.println("Please make the right choice! ");
                logger.error("Required field not filled correctly");
            }
        }
        return isRepeated;
    }

    public int getTask(AbstractTaskList taskList) {
        for (; ; ) {
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("Please, enter a sequence number.");
                logger.error("Empty required field");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    System.out.println("Please enter a value within the bounds of the list!");
                    logger.error("Limit exceeded");
                } else if (Integer.parseInt(input) > taskList.size()) {
                    System.out.println("Please enter a value within the bounds of the list!");
                    logger.error("Limit exceeded");
                } else {
                    taskNumber = Integer.parseInt(input);
                    break;
                }
            } else {
                System.out.println("Please, enter a sequence number.");
                logger.error("Required field not filled correctly");
            }
        }
        return taskNumber;
    }
}
