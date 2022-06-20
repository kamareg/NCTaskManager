package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        LocalDateTime first = LocalDateTime.now();
        LocalDateTime second = LocalDateTime.of(2022, 12, 5, 12, 30);
        LocalDateTime third = LocalDateTime.of(2022, 6, 25, 15, 40);
        LocalDateTime fourth = LocalDateTime.of(2022, 6, 30, 15, 48);
        LocalDateTime fifty = LocalDateTime.of(2022, 7, 5, 5, 5);
        LocalDateTime sixty = LocalDateTime.of(2022, 7, 20, 18, 38);


        LocalDateTime NOW = LocalDateTime.now();
        LocalDateTime FROM_NOW_10 = NOW.plusSeconds(10);
        LocalDateTime FROM_NOW_30 = NOW.plusSeconds(30);
        LocalDateTime FROM_NOW_50 = NOW.plusSeconds(50);
        LocalDateTime FROM_NOW_100 = NOW.plusSeconds(100);
        Task task = new Task("some", FROM_NOW_10, FROM_NOW_100, 20);
        task.setActive(true);
     //   task.nextTimeAfter(FROM_NOW_30);

        System.out.println(task);


    }

        /*Task task1 = new Task("first task", 1);
        Task task2 = new Task("second task", 3, 100, 20);
        Task task3 = new Task("third task", 5);
        Task task4 = new Task("fourth task", 10);
        Task task5 = new Task("fifth task", 15);
        Task task6 = new Task("sixth task", 20);
        Task task7 = new Task("seventh task", 9, 90, 25);
        Task task8 = new Task("eighth task", 17, 50, 4);
        Task task9 = new Task("ninth task", 28, 48, 2);
        Task task10 = new Task("tenth task", 40, 69, 5);


        ArrayTaskList one = new ArrayTaskList();
        ArrayTaskList three = new ArrayTaskList();
        three.add(task1);
        three.add(task2);
        three.add(task3);
        one.add(task1);
        one.add(task2);
        one.add(task3);
       // one.add(task4);
        //one.add(task5);
        //one.add(task1);
        //one.add(task1);

        LinkedTaskList two = new LinkedTaskList();

        two.add(task1);
        two.add(task2);
        two.add(task3);
      //  two.add(task1);
       // two.add(task1);
        //two.add(task4);
        ArrayTaskList four = (ArrayTaskList) one.clone();
        System.out.println(four == one);
        four.add(task5);
        System.out.println(four);
        System.out.println(one);
    }*/
}

