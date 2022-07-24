package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A class containing constructors and methods for working with tasks.
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private int interval;
    private boolean active, isRepeated;
    private LocalDateTime time, start, end;
    public final Logger logger = Logger.getLogger(Task.class);

    public Task(String title, LocalDateTime time) {
        if (time == null) {
            logger.error("Time is not set");
        }
        this.title = title;
        this.time = time;
        setActive(false);
        setRepeated(false);
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (isRepeated()) {
            return start;
        } else {
            return time;
        }
    }

    public void setTime(LocalDateTime time) {
        if (time == null) {
            logger.error("Time is not set");
        }
        if (isRepeated()) {
            setRepeated(false);
        }
        this.time = time;
    }

    public LocalDateTime getStartTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return start;
        }
    }

    public LocalDateTime getEndTime() {
        if (!isRepeated()) {
            return time;
        } else {
            return end;
        }
    }

    public int getRepeatInterval() {
        if (!isRepeated()) {
            return 0;
        } else {
            return interval;
        }
    }

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

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        this.isRepeated = repeated;
    }

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
