package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.malikova.tasks.Messages;

/**
 * Responsible for displaying Date part messages.
 */
public class DateView extends View {
    private String input;
    private static int month;

    public final Logger logger = Logger.getLogger(DateView.class);
    public int getYear() {
        for (; ; ) {
            System.out.println(Messages.WRITE_YEAR);
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println(Messages.FIELD_CANT_BE_EMPTY);
                logger.error(Messages.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= Messages.START_MANAGER_YEAR && Integer.parseInt(input) < Messages.END_MANAGER_YEAR) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println(Messages.CANT_BE_LESS + Messages.START_MANAGER_YEAR + Messages.CANT_BE_MORE + Messages.END_MANAGER_YEAR);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                System.out.println(Messages.MUST_BE_INTEGER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }

    public int getMonth() {
        do {
            System.out.println(Messages.WRITE_MONTH);
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
                    System.out.println(Messages.FIELD_NOT_FILLED_CORRECTLY);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                }
            }
        } while (!(month > 0 && month <= 12));
        return month;
    }

    public int getDate() {
        for (; ; ) {
            System.out.println(Messages.WRITE_DAY);
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println(Messages.FIELD_CANT_BE_EMPTY);
                logger.error(Messages.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= 31) {
                    if (month == 2) {
                        if (Integer.parseInt(input) > 29) {
                            System.out.println(Messages.LIMITS_DAYS);
                            logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                            continue;
                        }
                        return Integer.parseInt(input);
                    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (Integer.parseInt(input) > 30) {
                            System.out.println(Messages.LIMITS_DAYS);
                            logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                            continue;
                        }
                        return Integer.parseInt(input);
                    } else {
                    return Integer.parseInt(input);
                    }
                } else {
                    System.out.println(Messages.FIELD_NOT_FILLED_CORRECTLY);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                System.out.println(Messages.MUST_BE_INTEGER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }

    public int getHour() {
        for (; ; ) {
            System.out.println(Messages.WRITE_HOUR);
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println(Messages.FIELD_CANT_BE_EMPTY);
                logger.error(Messages.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 24) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println(Messages.LIMITS_HOURS);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                System.out.println(Messages.MUST_BE_INTEGER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }

    public int getMinute() {
        for (; ; ) {
            System.out.println(Messages.WRITE_MINUTE);
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println(Messages.FIELD_CANT_BE_EMPTY);
                logger.error(Messages.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) >= 0 && Integer.parseInt(input) < 60) {
                    return Integer.parseInt(input);
                } else {
                    System.out.println(Messages.LIMITS_MINUTE);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                }
            } else {
                System.out.println(Messages.MUST_BE_INTEGER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }
    public int getInterval() {
        for (; ; ) {
            System.out.println(Messages.WRITE_INTERVAL);
            input = new Input().setInput();
            if (input.isEmpty()) {
                System.out.println(Messages.FIELD_CANT_BE_EMPTY);
                logger.error(Messages.EMPTY_FIELD);
            } else if (input.matches("[0-9]*")) {
                if (Integer.parseInt(input) == 0) {
                    System.out.println(Messages.LIMITS_INTERVAL);
                    logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
                } else {
                    return Integer.parseInt(input);
                }
            } else {
                System.out.println(Messages.MUST_BE_INTEGER);
                logger.error(Messages.FIELD_NOT_FILLED_CORRECTLY);
            }
        }
    }
}
