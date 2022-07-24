package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
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
    public final Logger logger = Logger.getLogger(MainController.class);
    private String input;
    private int action;

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
        controllers.add(new CheckTaskController(new CheckTasksView(), Controller.TASK_LIST_ACTION));
        controllers.add(new EditTaskController(new EditTaskView(), Controller.EDIT_TASK_ACTION));

        view.text(Enum.TASK_MANAGER_STARTED);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        view.text(Enum.MENU_PRINT);
        view.text(String.valueOf(Controller.ADD_TASK_ACTION), ". Add new task.");
        view.text(String.valueOf(Controller.REMOVE_TASK_ACTION), ". Remove task.");
        view.text(String.valueOf(Controller.EDIT_TASK_ACTION), ". Edit task.");
        view.text(String.valueOf(Controller.TASK_LIST_ACTION), ". Check your tasks.");
        view.text(String.valueOf(Controller.CALENDAR_ACTION), ". Set necessary time range.");
        view.text(String.valueOf(Controller.EXIT_ACTION), ". Exit.");
        for (; ; ) {
            input = view.input();
            if (input.isEmpty()) {
                logger.error(Enum.EMPTY_FIELD);
                view.text(Enum.WARN_SEQUENCE_NUMBER);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) > Controller.EXIT_ACTION) {
                    view.text(Enum.WARN_SEQUENCE_NUMBER);
                    logger.error(Enum.LIMIT_EXCEEDED);
                } else {
                    action = Integer.parseInt(input);
                    break;
                }
            } else {
                view.text(Enum.WARN_SEQUENCE_NUMBER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
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