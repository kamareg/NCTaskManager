package ua.edu.sumdu.j2se.malikova.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return StreamSupport.stream(tasks.spliterator(), false)
                .filter(x -> x != null && x.nextTimeAfter(start) != null && !(x.nextTimeAfter(start).isAfter(end)))
                .collect(Collectors.toList());
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Iterable<Task> incoming = Tasks.incoming(tasks, start, end);
        SortedMap<LocalDateTime, Set<Task>> calendarTasks = new TreeMap<>();
        LocalDateTime key;
        Set<Task> set;
        for (Task task : incoming) {
            key = task.nextTimeAfter(start);
            while (key != null && !key.isAfter(end)) {
                if (calendarTasks.containsKey(key)) {
                    calendarTasks.get(key).add(task);
                } else {
                    set = new HashSet<>();
                    set.add(task);
                    calendarTasks.put(key, set);
                }
                key = task.nextTimeAfter(key);
            }
        }
        Set<LocalDateTime> keys = calendarTasks.keySet();
        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendarTasks.entrySet()) {
            LocalDateTime dateTime = entry.getKey();
            Set<Task> value = entry.getValue();
            System.out.print("|" + dateTime + "| \t : \t");
            System.out.println(value);
        }
            return calendarTasks;

    }
}
