package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.time.LocalDateTime;

/**
 * The main class responsible for the visual component of the application.
 */

public abstract class View {
    public void okTime(String string, LocalDateTime time) {
    }

    public void okTime(String string, int time) {
    }

    public void okReady(Task task) {
    }

    public void start() {
    }

    public void end() {
    }

    public void time() {
    }

    public void endBeforeStart() {
    }

    public void noTasks() {
    }

    public void listToPrint(AbstractTaskList taskList) {
    }
}
