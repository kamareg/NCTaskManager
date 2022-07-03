package ua.edu.sumdu.j2se.malikova.tasks;


import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        LocalDateTime first = LocalDateTime.of(2022, 4, 5, 12, 30, 55, 55);
        LocalDateTime second = LocalDateTime.of(2022, 9, 5, 12, 30, 55, 55);
        LocalDateTime third = LocalDateTime.of(2022, 5, 5, 12, 30, 55, 55);
        LocalDateTime fourth = LocalDateTime.of(2022, 6, 5, 12, 30, 55, 55);
        LocalDateTime fifty = LocalDateTime.of(2022, 7, 5, 12, 30, 55, 55);
        LocalDateTime sixty = LocalDateTime.of(2022, 8, 5, 12, 30, 55, 55);
        Task task1 = new Task("task1", first);
        Task task2 = new Task("task2", fourth);
        Task task3 = new Task("task3", third);
        Task task4 = new Task("task4", first, sixty, 360000);
        Task task5 = new Task("task5", first, sixty, 360000);
        Task task6 = new Task("task6", third, fourth, 360000);
        Task task7 = new Task("task7", third, fifty, 360000);
        Task task8 = new Task("task8", third, sixty, 360000);
        Task task9 = new Task("task9", fourth, second, 360000);

        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);
        task4.setActive(true);
        task5.setActive(true);
        task6.setActive(true);
        task7.setActive(true);
        task8.setActive(true);
        task9.setActive(true);

        AbstractTaskList list = new LinkedTaskList();

        list.add(task1);
        list.add(task2);
        list.add(task4);
        System.out.println(list);

        File file = new File("try.txt");
        TaskIO.writeText(list, file);
        TaskIO.readText(list, file);
        System.out.println(list);

        Collection<Task> collection = new ArrayList<>();
        collection.add(task1);
        collection.add(task2);
        collection.add(task3);
        collection.add(task4);
        collection.add(task5);
        collection.add(task6);
        collection.add(task7);
        collection.add(task8);
        collection.add(task9);

        Tasks.calendar(collection,fifty,second);
    }
}

