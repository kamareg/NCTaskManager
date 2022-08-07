package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Main;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import ua.edu.sumdu.j2se.malikova.tasks.model.Tasks;
import ua.edu.sumdu.j2se.malikova.tasks.view.NotificatorView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A class for notification process.
 */
public class NotificatorController extends Thread {
    View view = new NotificatorView();
    private AbstractTaskList taskList;
    public final Logger logger = Logger.getLogger(NotificatorController.class);
    public static final int THREAD_SLEEP_TIME = 1000;

    public NotificatorController(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void run() {
        for (; ; ) {
            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, LocalDateTime.now(), LocalDateTime.now().plusSeconds(THREAD_SLEEP_TIME / 1000));
            for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
                LocalDateTime dateTime = entry.getKey();
                Date date = Timestamp.valueOf(dateTime);
                Set<Task> value = entry.getValue();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        for (Task val : value) {
                            System.out.println("The task \'" + val.getTitle() + "\' is started at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss")));
                        }
                    }
                };
                Main.timer.schedule(task, date);
            }
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (InterruptedException e) {
                logger.error("Tread exception: " + e);
            }
        }
    }
}

