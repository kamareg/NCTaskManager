package ua.edu.sumdu.j2se.malikova.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.time.LocalDateTime;

public class Notificator extends Thread {
    private AbstractTaskList taskList;
    public final Logger logger = Logger.getLogger(Notificator.class);
    public static final int HOUR_MONITORING_TIME = 2;
    public static final int THREAD_SLEEP_TIME = 100000;
    public Notificator(AbstractTaskList taskList) {
        this.taskList = taskList;
    }
    @Override
    public void run() {
        for (; ; ) {
            for (Task task : taskList) {
                if (!(task.nextTimeAfter(LocalDateTime.now()) == null)) {
                    if ((task.nextTimeAfter(LocalDateTime.now())).isBefore(LocalDateTime.now().plusHours(HOUR_MONITORING_TIME))) {
                        System.out.print("Attention! Your task \"");
                        System.out.print(task.getTitle() + "\" will start at ");
                        System.out.println(task.nextTimeAfter(LocalDateTime.now()));
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
