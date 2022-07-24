package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.view.NotificatorView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

/**
 * A class for notification process.
 */
public class NotificatorController extends Thread {
    View view = new NotificatorView();
    private AbstractTaskList taskList;
    public final Logger logger = Logger.getLogger(NotificatorController.class);
    public static final int HOUR_MONITORING_TIME = 2;
    public static final int THREAD_SLEEP_TIME = 100000;

    public NotificatorController(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        for (; ; ) {
            for (Task task : taskList) {
                if (!(task.nextTimeAfter(LocalDateTime.now()) == null)) {
                    if ((task.nextTimeAfter(LocalDateTime.now())).isBefore(LocalDateTime.now().plusHours(HOUR_MONITORING_TIME))) {
                        view.text("Attention! ");
                        view.text("Your task \"", task.getTitle(), "\" will start at ", String.valueOf(task.nextTimeAfter(LocalDateTime.now())));
                    }
                }
            }
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("Tread exception: " + e);
            }
        }

    }
}
