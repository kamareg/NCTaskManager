package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDateTime;

/**
 * Клас, що містить конструктори та методи для роботи з задачами.
 */
public class Task implements Cloneable {

    private String title;
    private int time1;
    private int start1;
    private int end1;
    private int interval;
    private boolean active;
    private boolean isRepeated;
/*    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

//LocalDateTime jhjh = LocalDateTime.
LocalDateTime time = LocalDateTime.of(year, month, day, hour, minute); //{
//    this.year = year;
 //   this.month = month;
//    this.day = day;
//    this.hour = hour;
//    this.minute = minute;
 //   }
LocalDateTime start = LocalDateTime.of(year, month, day, hour, minute);
    LocalDateTime end = LocalDateTime.of(year, month, day, hour, minute);
    LocalDateTime interval = LocalDateTime.of(year, month, day, hour, minute);*/


    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    //   private LocalDateTime interval;

    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданий час без повторення.
     *
     * @param title назва задачі.
     * @param time  час виконання задачі.
     */

    public Task(String title, LocalDateTime time) {
        //     if (time < 0) {
        //        throw new IllegalArgumentException("Час не може бути від'ємним");
        //    }
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
        //      if (start < 0 || end < 0 || interval < 0) {
        //          throw new IllegalArgumentException("Час не може бути від'ємним");
        //     }
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
        //     if (time < 0) {
        //        throw new IllegalArgumentException("Час не може бути від'ємним");
        //    }
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
        //      if (start < 0 || end < 0 || interval < 0) {
        //          throw new IllegalArgumentException("Час не може бути від'ємним");
        //     }
        if (!isRepeated()) {
            setRepeated(true);
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
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
        //       if (current < 0) {
        //          throw new IllegalArgumentException("Час не може бути від'ємним");
        //     }
        LocalDateTime nextAfter = null;
        if (this.isActive() && this.isRepeated()) {
            if (current.isBefore(this.getStartTime())) {
                nextAfter = this.getStartTime();
            } else {

                LocalDateTime i = this.getStartTime();
                while (i.isBefore(this.getEndTime())) {
                    if (i.isBefore(current)) {
                        //&& !(i.isEqual(current))) {         //***
                        i = i.plusSeconds(this.interval);
                    } else {
                        break;
                    }
                }
                if (i.isBefore(this.getEndTime())) {
                    return i;
                }


            }
        } else if (!this.isRepeated()) {
            if (current.isBefore(this.getStartTime())) {
                nextAfter = this.getStartTime();
            }
        }
        return nextAfter;
    }



    /*    if (!this.isActive()) {
            return null;
  //          return -1;
        } else if (!this.isRepeated()) {
            if (current.isBefore(this.getStartTime())) {
                return this.getStartTime();
            } else {
                return null;
            }
        } else {
            if (current.isBefore(this.getStartTime())) {
                return this.getStartTime();
            } else {
                LocalDateTime i = this.getStartTime();
                while (i.isBefore(this.getEndTime())) {
                    if (i.isBefore(current)) {
                    //&& !(i.isEqual(current))) {         //***
                        i = i.plusSeconds(this.getRepeatInterval());
                    } else {
                        break;
                    }
                }
                if (i.isBefore(this.getEndTime())) {
                    return i;
                } else {
                    return null;
                }
            }
        }*/


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
        //     if (end == 0 && start == 0 && interval == 0) {
        description = "Task title \"" + title + "\", executed at the specified time " + time + activity + repeated;
        //     } else {
        //        description = "Task title \"" + title + "\", that begins at " + start + ", ends at " + end + ", has task time interval " + interval + activity + repeated;
        //    }
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
        int hash = title == null ? 0 : 1;
        hash = 11 * hash + time.getMinute();
        hash = 11 * hash + start.getMinute();
        hash = 11 * hash + end.getMinute();
        hash = 11 * hash + interval;
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


