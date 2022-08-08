package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Task;

import java.time.LocalDateTime;

/**
 * The main class responsible for the visual component of the application.
 */

public abstract class View {
    public void okTime(String string, LocalDateTime time){
    }
    public void okTime(String string, int time){
    }
    public void okAdd(Task task) {
    }
    public void start(){
    }
    public void end(){
    }
    public void time(){
    }
    public void endBeforeStart(){
    }
    public void noTasks(){
    }



    public String input() {
        return new Input().setInput();
    }

    public void text(String text) {
        System.out.println(text);
    }

    public void text(String text, LocalDateTime dateTime) {
        System.out.println(text + dateTime);
    }

    public void text(String text, int integer) {
        System.out.println(text + integer);
    }

    public void text(String firstText, String secondText) {
        System.out.println(firstText + secondText);
    }

    public void text(String one, String two, String three, String four) {
        System.out.println(one + two + three + four);
    }

    public void listToPrint(AbstractTaskList taskList) {
    }
}
