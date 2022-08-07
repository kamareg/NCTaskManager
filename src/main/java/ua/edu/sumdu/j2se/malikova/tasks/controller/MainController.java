package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.malikova.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

import static ua.edu.sumdu.j2se.malikova.tasks.Main.saver;

/**
 * A class for main process of task manager.
 */
public class MainController extends Controller {
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
        controllers.add(new CheckTaskController(new CheckTasksView(), Controller.TASK_LIST_ACTION));
        controllers.add(new EditTaskController(new EditTaskView(), Controller.EDIT_TASK_ACTION));

        MainView.startPage();
    }

    @Override
    public int process(AbstractTaskList taskList) {
        MainView.printMainMenu();
        int action = MainView.actionMainMenu();
        for (; ; ) {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                    TaskIO.writeText(this.taskList, saver);
                }
            }
            if (action == EXIT_ACTION) {
                break;
            }
        }
        return EXIT_ACTION;
    }
}
