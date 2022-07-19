package ua.edu.sumdu.j2se.malikova.tasks.model;

import java.time.LocalDateTime;


public class  Date {
    private String input;
    private int interval;
    private int year;
    private int month;
    private int date;
    private int hour;
    private int minute;
    private LocalDateTime readyDate;

    public Date() {
    }

    public Date(int year, int month, int date, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
    }

    public LocalDateTime readyDate(){
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
            System.out.println("Please, write the year.");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty!");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 2022 && Integer.parseInt(input) < 2100) {
                    year = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("Year cannot be earlier than 2022 and later than 2100!");
                }
            } else {
                System.out.println("Year is an integer");
            }
        }
        return year;
    }

    public int getMonth() {
        month = 0;
        do {
            System.out.println("Write the month.");
            input = new Input().setInput();
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
                    System.out.println("It's not correct format of month!");
                }
            }
        } while (!(month > 0 && month <= 12));
        return month;
    }

    public int getDate() {
        for (; ; ) {
            System.out.println("Please, write the day of month.");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty!");
            } else if (input.matches("[0-9]*")) {

                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 31) {
                    if (month == 2) {
                        if (Integer.parseInt(input) > 29) {
                            System.out.println("There aren't many days this month!");
                            continue;
                        }
                    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (Integer.parseInt(input) > 30) {
                            System.out.println("There aren't many days this month!");
                            continue;
                        }
                    }
                    date = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("Please write the correct format!");
                }
            } else {
                System.out.println("Day is an integer");
            }
        }
        return date;
    }

    public int getHour() {
        for (; ; ) {
            System.out.println("Now it's time for the hours.");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty!");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 24) {
                    hour = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("The hour cannot be less than 0 and greater than 23!");
                }
            } else {
                System.out.println("Hour is an integer");
            }
        }
        return hour;
    }

    public int getMinute() {
        for (; ; ) {
            System.out.println("And the last - enter the minutes.");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty!");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 60) {
                    minute = Integer.parseInt(input);
                    break;
                } else {
                    System.out.println("The minute cannot be less than 0 and greater than 59!");
                }
            } else {
                System.out.println("Minute is an integer");
            }
        }
        return minute;
    }
    public int getInterval() {
        for (; ; ) {
            System.out.println("Please input the repeat interval.");
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println("This field cannot be empty!");
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    System.out.println("Interval is bigger than 0!");
                } else {
                    interval = Integer.parseInt(input);
                    break;
                }
            } else {
                System.out.println("Interval is an integer");
            }
        }
        return interval;
    }

}
