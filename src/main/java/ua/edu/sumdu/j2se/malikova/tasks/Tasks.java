package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Stream;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (tasks == null || start == null || end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("Необхідно ввести допустимі значення");
        }
        AbstractTaskList incomingAbstractList = TaskListFactory.createTaskList(type);
        Stream<Task> stream = getStream();
        stream.filter(x -> x != null && x.nextTimeAfter(from) != null && x.nextTimeAfter(from).isBefore(to)).forEach(incomingAbstractList::add);
        return incomingAbstractList;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
return null;
    }

}
