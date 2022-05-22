package ua.edu.sumdu.j2se.malikova.tasks;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
        Task task1 = new Task("first task", 1);
        Task task2 = new Task("second task", 3, 100, 20);
        Task task3 = new Task("third task", 5);
        Task task4 = new Task("fourth task", 10);
        Task task5 = new Task("fifth task", 15);
        Task task6 = new Task("sixth task", 20);
        Task task7 = new Task("seventh task", 9, 90, 25);
        Task task8 = new Task("eighth task", 17, 50, 4);
        Task task9 = new Task("ninth task", 28, 48, 2);
        Task task10 = new Task("tenth task", 40, 69, 5);


        ArrayTaskList one = new ArrayTaskList(new Task[4], 0);
        one.add(task1);
        one.add(task2);
        one.add(task3);
        one.add(task4);
        one.add(task5);

        System.out.println(one);



        // task2.setActive(true);
        // int a = task2.nextTimeAfter(35);
        // System.out.println(task1);
        // System.out.println(task2);
        // String b = task1.getTitle();
        // System.out.println(b);
        // task1.setTitle("one");
        // System.out.println(task1);
        // System.out.println(a);
        // int c = task1.getTime();
        // int d = task2.getTime();
        // System.out.println(c);
        // System.out.println(d);
        // task2.setTime(8);
        // System.out.println(task2);
        // int e = task2.nextTimeAfter(6);
        // System.out.println(e);
    }


}
