package ua.edu.sumdu.j2se.malikova.tasks.controller;

import ua.edu.sumdu.j2se.malikova.tasks.view.DateView;
import java.time.LocalDateTime;

/**
 * A class containing constructors and methods for working with dates.
 */

public class DateController extends Controller {
    public DateController() {
    }

    public LocalDateTime readyDate() {
        int year = new DateView().getYear();
        int month = new DateView().getMonth();
        int date = new DateView().getDate();
        int hour = new DateView().getHour();
        int minute = new DateView().getMinute();
        return LocalDateTime.of(year, month, date, hour, minute);
    }

    public int getInterval() {
        return new DateView().getInterval();
    }
}
