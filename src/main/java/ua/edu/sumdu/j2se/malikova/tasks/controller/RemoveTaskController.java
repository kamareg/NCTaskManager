package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

/**
 * A class for removing task from the list process.
 */
public class RemoveTaskController extends Controller {
    public RemoveTaskController(View view, int action) {
        super(view, action);
    }
    public int process(AbstractTaskList taskList) {
        Task task;
        if (taskList.size() == 0) {
            view.noTasks();
            return Controller.MAIN_MENU_ACTION;
        }
        view.listToPrint(taskList);
        view.start();
        task = taskList.getTask((new ValidationController().getTask(taskList)) - 1);
        view.okReady(task);
        taskList.remove(task);
        return Controller.MAIN_MENU_ACTION;
    }
}
