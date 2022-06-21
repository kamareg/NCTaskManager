package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return StreamSupport.stream(tasks.spliterator(), false)
                .filter(x -> x != null && x.nextTimeAfter(start) != null && x.nextTimeAfter(start).isBefore(end))
                .collect(Collectors.toList());
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Iterable<Task> incoming = Tasks.incoming(tasks, start, end);
        SortedMap<LocalDateTime, Set<Task>> calendarTasks = new TreeMap<>();
        for (Task o: incoming) {
            calendarTasks.keySet(o.getTime());
        }
        return null;
    }

}
