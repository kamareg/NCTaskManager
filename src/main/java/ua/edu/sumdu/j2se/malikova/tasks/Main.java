package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        LocalDateTime first = LocalDateTime.now();
        LocalDateTime second = LocalDateTime.of(2022, 12, 5, 12, 30);
        LocalDateTime third = LocalDateTime.of(2022, 6, 25, 15, 40);
        LocalDateTime fourth = LocalDateTime.of(2022, 6, 30, 15, 48);
        LocalDateTime fifty = LocalDateTime.of(2022, 7, 5, 5, 5);
        LocalDateTime sixty = LocalDateTime.of(2022, 7, 20, 18, 38);
        Task task1 = new Task("ohlo", first);
        Task task2 = new Task("ohlo", fourth);
        Task task3 = new Task("ohlo", third);


        LocalDateTime NOW = LocalDateTime.now();
        LocalDateTime FROM_NOW_10 = NOW.plusSeconds(10);
        LocalDateTime FROM_NOW_30 = NOW.plusSeconds(30);
        LocalDateTime FROM_NOW_50 = NOW.plusSeconds(50);
        LocalDateTime FROM_NOW_100 = NOW.plusSeconds(100);
        Task task = new Task("some", FROM_NOW_10, FROM_NOW_100, 20);
        task.setActive(true);

        Collection<Task> set = new HashSet<>();
        set.add(task1);
        set.add(task);
        set.add(task2);
        set.add(task3);
        System.out.println(Tasks.incoming(set, third, second));



    }
}

