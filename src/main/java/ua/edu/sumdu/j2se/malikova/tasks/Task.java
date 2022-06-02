package ua.edu.sumdu.j2se.malikova.tasks;

/**
 * Клас, що містить конструктори та методи для роботи з задачами.
 */
public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean isRepeated;


    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданий час без повторення.
     *
     * @param title назва задачі.
     * @param time  час виконання задачі.
     */

    public Task(String title, int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Час не може бути від'ємним");
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

    public Task(String title, int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0) {
            throw new IllegalArgumentException("Час не може бути від'ємним");
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

    public int getTime() {
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

    public void setTime(int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Час не може бути від'ємним");
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

    public int getStartTime() {
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

    public int getEndTime() {
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

    public void setTime(int start, int end, int interval) {
        if (start < 0 || end < 0 || interval < 0) {
            throw new IllegalArgumentException("Час не може бути від'ємним");
        }
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

    public int nextTimeAfter(int current) {
        if (current < 0) {
            throw new IllegalArgumentException("Час не може бути від'ємним");
        }
        if (!this.isActive()) {
            return -1;
        } else if (!this.isRepeated()) {
            if (current < this.getStartTime()) {
                return this.getStartTime();
            } else {
                return -1;
            }
        } else {
            if (current < this.getStartTime()) {
                return this.getStartTime();
            } else {
                int i = this.getStartTime();
                while (i < this.getEndTime()) {
                    if (i <= current) {
                        i = i + this.getRepeatInterval();
                    } else {
                        break;
                    }
                }
                if (this.getEndTime() > i) {
                    return i;
                } else {
                    return -1;
                }
            }
        }
    }

    /**
     * Метод перевизначення.
     *
     * @return повертає дані в зручному вигляді.
     */

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", isRepeated=" + isRepeated +
                '}';
    }
}


