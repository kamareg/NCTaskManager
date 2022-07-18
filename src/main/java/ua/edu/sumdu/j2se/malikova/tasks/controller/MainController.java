package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {
    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();



    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
      //  controllers.add(new CreateNewTaskListController(new CreateNewTaskListView(), Controller.EMPTY_LIST_ACTION));
        controllers.add(new AddTaskController(new AddTaskView(),Controller.ADD_TASK_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
        controllers.add(new CheckTaskController(new CheckTasksView(), Controller.TASK_LIST_ACTION));
        controllers.add(new EditTaskController(new EditTaskView(), Controller.EDIT_TASK_ACTION));

    }
    @Override
    public int process(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        for (;;) {
            for (Controller controller: controllers) {
                if(controller.canProcess(action)) {
                    action = controller.process(this.taskList);
                }
            }
            if (action == EXIT_ACTION) {
                break;
            }
        }
        return EXIT_ACTION;
    }
}
