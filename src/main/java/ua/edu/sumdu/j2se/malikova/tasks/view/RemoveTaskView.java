package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTaskView implements View{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String input;

    @Override
    public int printInfo(AbstractTaskList taskList) {
        if(taskList.size()==0) {
            System.out.println("There are no tasks in your list yet. Please add tasks to the list first.");
            return Controller.MAIN_MENU_ACTION;
        }
        System.out.println(taskList);

        System.out.println("What task do you want to delete? Please, enter a sequence number. If you want to cancel this process please put 0.");

        for (; ; ) {
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (input.isEmpty()) {
                System.out.println("Please, enter a sequence number.");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    return Controller.MAIN_MENU_ACTION;
                } else if (Integer.parseInt(input) > taskList.size()) {
                    System.out.println("Please enter a value within the bounds of the list!");
                } else {
                    taskList.remove(taskList.getTask(Integer.parseInt(input)-1));
                    break;
                }
            } else {
                System.out.println("Please, enter a sequence number.");
            }
        }
        System.out.println("Task was removed.");
        return Controller.MAIN_MENU_ACTION;
    }
}
