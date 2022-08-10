package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for displaying Notificator.
 */
public class NotificatorView extends View{
    public static void notification(Task task){
        System.out.println(new StringBuilder().append("The task '")
                .append(task.getTitle()).append("' is started at ")
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss"))));
    }
}
