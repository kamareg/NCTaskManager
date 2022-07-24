package ua.edu.sumdu.j2se.malikova.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Enum;
import ua.edu.sumdu.j2se.malikova.tasks.view.DateView;
import ua.edu.sumdu.j2se.malikova.tasks.view.View;

import java.time.LocalDateTime;

/**
 * A class containing constructors and methods for working with dates.
 */

public class DateController extends Controller {
    private String input;
    private int interval, year, month, date, hour, minute;
    private LocalDateTime readyDate;
    public final Logger logger = Logger.getLogger(DateController.class);
    View view = new DateView();

    public DateController() {
    }

    public DateController(View view, int action) {
        super(view, action);
    }

    public DateController(int year, int month, int date, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public LocalDateTime readyDate() {
        year = getYear();
        month = getMonth();
        date = getDate();
        hour = getHour();
        minute = getMinute();
        readyDate = LocalDateTime.of(year, month, date, hour, minute);
        return readyDate;
    }

    public int getYear() {
        for (; ; ) {
            view.text(Enum.WRITE_YEAR);
            input = view.input();
            if (input.isEmpty()) {
                view.text(Enum.FIELD_CANT_BE_EMPTY);
                logger.error(Enum.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= Enum.START_MANAGER_YEAR && Integer.parseInt(input) < Enum.END_MANAGER_YEAR) {
                    year = Integer.parseInt(input);
                    break;
                } else {
                    view.text(Enum.CANT_BE_LESS, String.valueOf(Enum.START_MANAGER_YEAR), Enum.CANT_BE_MORE, String.valueOf(Enum.END_MANAGER_YEAR));
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                view.text(Enum.MUST_BE_INTEGER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return year;
    }

    public int getMonth() {
        month = 0;
        do {
            view.text(Enum.WRITE_MONTH);
            input = view.input();
            switch (input) {
                case ("JAN"), ("jan"), ("Jan"), ("January"), ("JANUARY"), ("january"), ("01"), ("1") -> {
                    month = 1;
                }
                case ("FEB"), ("feb"), ("Feb"), ("February"), ("FEBRUARY"), ("february"), ("02"), ("2") -> {
                    month = 2;
                }
                case ("MAR"), ("mar"), ("Mar"), ("March"), ("MARCH"), ("march"), ("03"), ("3") -> {
                    month = 3;
                }
                case ("APR"), ("apr"), ("Apr"), ("April"), ("APRIL"), ("april"), ("04"), ("4") -> {
                    month = 4;
                }
                case ("MAY"), ("may"), ("May"), ("05"), ("5") -> {
                    month = 5;
                }
                case ("JUN"), ("jun"), ("Jun"), ("June"), ("JUNE"), ("june"), ("06"), ("6") -> {
                    month = 6;
                }
                case ("JUL"), ("jul"), ("Jul"), ("July"), ("JULY"), ("july"), ("07"), ("7") -> {
                    month = 7;
                }
                case ("AUG"), ("aug"), ("Aug"), ("August"), ("AUGUST"), ("august"), ("08"), ("8") -> {
                    month = 8;
                }
                case ("SEP"), ("SEPT"), ("sep"), ("sept"), ("Sep"), ("Sept"), ("September"), ("SEPTEMBER"), ("september"), ("09"), ("9") -> {
                    month = 9;
                }
                case ("OCT"), ("oct"), ("Oct"), ("October"), ("OCTOBER"), ("october"), ("10") -> {
                    month = 10;
                }
                case ("NOV"), ("nov"), ("Nov"), ("November"), ("NOVEMBER"), ("november"), ("11") -> {
                    month = 11;
                }
                case ("DEC"), ("dec"), ("Dec"), ("December"), ("DECEMBER"), ("december"), ("12") -> {
                    month = 12;
                }
                default -> {
                    view.text(Enum.FIELD_NOT_FILLED_CORRECTLY);
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                }
            }
        } while (!(month > 0 && month <= 12));
        return month;
    }

    public int getDate() {
        for (; ; ) {
            view.text(Enum.WRITE_DAY);
            input = view.input();
            if (input.isEmpty()) {
                view.text(Enum.FIELD_CANT_BE_EMPTY);
                logger.error(Enum.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 31) {
                    if (month == 2) {
                        if (Integer.parseInt(input) > 29) {
                            view.text(Enum.LIMITS_DAYS);
                            logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                            continue;
                        }
                    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (Integer.parseInt(input) > 30) {
                            view.text(Enum.LIMITS_DAYS);
                            logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                            continue;
                        }
                    }
                    date = Integer.parseInt(input);
                    break;
                } else {
                    view.text(Enum.FIELD_NOT_FILLED_CORRECTLY);
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                view.text(Enum.MUST_BE_INTEGER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return date;
    }

    public int getHour() {
        for (; ; ) {
            view.text(Enum.WRITE_HOUR);
            input = view.input();
            if (input.isEmpty()) {
                view.text(Enum.FIELD_CANT_BE_EMPTY);
                logger.error(Enum.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 24) {
                    hour = Integer.parseInt(input);
                    break;
                } else {
                    view.text(Enum.LIMITS_HOURS);
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                view.text(Enum.MUST_BE_INTEGER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return hour;
    }

    public int getMinute() {
        for (; ; ) {
            view.text(Enum.WRITE_MINUTE);
            input = view.input();
            if (input.isEmpty()) {
                view.text(Enum.FIELD_CANT_BE_EMPTY);
                logger.error(Enum.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 60) {
                    minute = Integer.parseInt(input);
                    break;
                } else {
                    view.text(Enum.LIMITS_MINUTE);
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                view.text(Enum.MUST_BE_INTEGER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return minute;
    }

    public int getInterval() {
        for (; ; ) {
            view.text(Enum.WRITE_INTERVAL);
            input = view.input();
            if (input.isEmpty()) {
                view.text(Enum.FIELD_CANT_BE_EMPTY);
                logger.error(Enum.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    view.text(Enum.LIMITS_INTERVAL);
                    logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
                } else {
                    interval = Integer.parseInt(input);
                    break;
                }
            } else {
                view.text(Enum.MUST_BE_INTEGER);
                logger.error(Enum.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
        return interval;
    }
}
