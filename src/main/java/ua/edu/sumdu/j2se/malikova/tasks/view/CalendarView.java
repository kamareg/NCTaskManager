package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.Date;
import ua.edu.sumdu.j2se.malikova.tasks.controller.Controller;
import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.malikova.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarView implements View{
    private Date dateConstructor;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private LocalDateTime first;
    private LocalDateTime second;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss");


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("You can view tasks in the range from start to end.");
        System.out.println("Please enter the first point (start).");
        dateConstructor = new Date(year,month,date,hour,minute);

        year = dateConstructor.getYear();
        if (year == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        month = dateConstructor.getMonth();
        if (month == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        date = dateConstructor.getDate();
        if (date == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        hour = dateConstructor.getHour();
        minute = dateConstructor.getMinute();

        first = LocalDateTime.of(year, month, date, hour, minute);
        System.out.println("Ok, your calendar range starts at " + first.format(dtf));
        System.out.println("Please enter the second point (end).");
        dateConstructor = new Date(year,month,date,hour,minute);

        year = dateConstructor.getYear();
        if (year == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        month = dateConstructor.getMonth();
        if (month == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        date = dateConstructor.getDate();
        if (date == 0) {
            return Controller.MAIN_MENU_ACTION;
        }
        hour = dateConstructor.getHour();
        minute = dateConstructor.getMinute();

        second = LocalDateTime.of(year, month, date, hour, minute);
        System.out.println("Ok, your calendar range ends at " + second.format(dtf));
        System.out.println("Here is your calendar: \n" + Tasks.calendar(taskList,first,second));

        return Controller.MAIN_MENU_ACTION;
    }
}
