package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;
import java.time.LocalDateTime;

/**
 * Responsible for displaying Add Tasks messages.
 */
public class AddTaskView extends View {
    public static final Logger logger = Logger.getLogger(AddTaskView.class);
    @Override
    public void start(){
        System.out.println(Messages.WRITE_START_TIME);
    }
    @Override
    public void end(){
        System.out.println(Messages.WRITE_END_TIME);
    }
    @Override
    public void time(){
        System.out.println(Messages.WRITE_TIME);
    }
    @Override
    public void endBeforeStart(){
        System.out.println(Messages.END_BEFORE_START + Messages.TRY_AGAIN);
        logger.warn(Messages.END_BEFORE_START);
    }
    @Override
    public void okTime(String string, LocalDateTime time){
        System.out.println("Ok, your task " + string + ": " + time);
    }
    @Override
    public void okTime(String string, int time){
        System.out.println("Ok, your task " + string + ": " + time);
    }
    @Override
    public void okReady(Task task){
        System.out.println(Messages.OK_TASK_ADDED);
        logger.info(task.getTitle() + "" + Messages.OK_TASK_ADDED);
    }
}



