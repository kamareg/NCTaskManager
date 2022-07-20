package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Клас, що містить конструктори та методи для роботи з задачами.
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private int interval;
    private boolean active;
    private boolean isRepeated;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    public final Logger logger = Logger.getLogger(Task.class);

    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданий час без повторення.
     *
     * @param title назва задачі.
     * @param time  час виконання задачі.
     */

    public Task(String title, LocalDateTime time) {
        if (time == null) {
            logger.error("Time is not set");
        }
        this.title = title;
        this.time = time;
        setActive(false);
        setRepeated(false);
    }

    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданому проміжку часу із заданим інтервалом.
     *
     * @param title    назва задачі.
     * @param start    час початку виконання задачі.
     * @param end      час закінчення виконання задачі.
     * @param interval інтервал часу виконання задачі.
     */

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || interval < 0) {
            logger.error("Time is not set");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        setActive(false);
        setRepeated(true);
    }

    /**
     * Метод для повернення назви задачі.
     *
     * @return назва задачі.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Метод для встановлення назви задачі.
     *
     * @param title назва задачі.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод для повернення стану задачі.
     *
     * @return стан задачі.
     */

    public boolean isActive() {
        return active;
    }

    /**
     * Метод для встановлення стану задачі.
     *
     * @param active стан задачі.
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Метод для повернення часу виконання задачі, що не повторюється.
     *
     * @return у разі, якщо задача повторюється метод має
     * повертати час початку повторення.
     */

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    /**
     * Метод для зміни часу виконання задачі, що не повторюється.
     * У разі, якщо задача повторювалась, вона має стати такою,
     * що не повторюється.
     *
     * @param time час виконання задачі.
     */

    public void setTime(LocalDateTime time) {
        if (time == null) {
            logger.error("Time is not set");
        }
        if (isRepeated()) {
            setRepeated(false);
        }
        this.time = time;
    }

    /**
     * Метод для повернення часу виконання задачі, що повторюється.
     *
     * @return у разі, якщо задача не повторюється метод
     * має повертати час виконання задачі
     */

    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return start;
        }
    }

    /**
     * Метод для повернення часу виконання задачі, що повторюється.
     *
     * @return у разі, якщо задача не повторюється метод має
     * повертати час виконання задачі.
     */

    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return end;
        }
    }

    /**
     * Метод для повернення часу виконання задачі, що повторюється.
     *
     * @return у разі, якщо задача не повторюється метод має
     * повертати 0.
     */

    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        } else {
            return interval;
        }
    }

    /**
     * Метод для зміни часу виконання задачі, що повторюється. У разі,
     * якщо задача не повторювалася вона має стати такою,
     * що повторюється.
     *
     * @param start    час початку виконання задачі.
     * @param end      час закінчення виконання задачі.
     * @param interval інтервал часу виконання задачі.
     */

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start == null || end == null || interval < 0) {
            logger.error("Time is not set");
        }
        if (!isRepeated()) {
            setRepeated(true);
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Метод для перевірки повторюваності задачі.
     *
     * @return результат перевірки повторюваності задачі.
     */

    public boolean isRepeated() {
        return isRepeated;
    }

    /**
     * Метод для встановлення повторюваності задачі.
     *
     * @param repeated зміна стану повторюванності задачі.
     */

    public void setRepeated(boolean repeated) {
        this.isRepeated = repeated;
    }

    /**
     * Метод повертає час наступного виконання задачі після вказаного часу.
     *
     * @param current вказанний час.
     * @return якщо після вказаного часу задача не виконується, то
     * метод має повертати -1.
     */

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
       if (current == null) {
            logger.error("Time is not set");
        }
        LocalDateTime nextAfter = null;
        if (this.isActive()) {
            if (current.isBefore(this.getStartTime())) {
                return this.getStartTime();
            }
            if (this.isRepeated) {
                LocalDateTime i;
                for (i = this.getStartTime(); i.isBefore(this.getEndTime()) || i.isEqual(this.getEndTime()); i = i.plusSeconds(this.interval)) {
                    if (current.isBefore(i) || current.isEqual(i)) {
                        if (current.isEqual(i)) {
                            if (i.isEqual(this.getEndTime())) {
                                return null;
                            }
                            nextAfter = i.plusSeconds(this.interval);
                            break;
                        }
                        nextAfter = i;
                        break;
                    }
                }
            }
        }
        return nextAfter;
    }

    /**
     * Метод перевизначення.
     *
     * @return повертає дані в зручному вигляді.
     */

    @Override
    public String toString() {
        String description;
        String activity;
        String repeated;
        if (this.active) {
            activity = ", that is active";
        } else {
            activity = ", that is not active";
        }
        if (this.isRepeated) {
            repeated = " and repeated.";
        } else {
            repeated = " and not repeated.";
        }
        if (end == null && start == null && interval == 0) {
            description = "Task title \"" + title + "\", executed at the specified time " + time + activity + repeated;
        } else {
            description = "Task title \"" + title + "\", that begins at " + start + ", ends at " + end + ", has task time interval " + interval + activity + repeated;
        }
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        if (this.title != task.title || this.time != task.time || this.start != task.start || this.end != task.end || this.interval != task.interval || this.active != task.active || this.isRepeated != task.isRepeated())
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = title == null ? 1 : 11;
        hash = (time == null ? 1 : (11 + time.getMinute())) * hash;
        hash = (start == null ? 1 : (11 + start.getMinute())) * hash;
        hash = (end == null ? 1 : (11 + end.getMinute())) * hash;
        hash = (interval == 0 ? 1 : (11 + interval)) * hash;
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
