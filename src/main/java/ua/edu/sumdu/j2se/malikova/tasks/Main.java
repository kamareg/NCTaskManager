package ua.edu.sumdu.j2se.malikova.tasks;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
        Task task1 = new Task("first task", 12);
        Task task2 = new Task("second task", 5,100,20);
        task2.setActive(true);
        int a = task2.nextTimeAfter(35);
        System.out.println(task1);
        System.out.println(task2);
        String b = task1.getTitle();
        System.out.println(b);
        task1.setTitle("one");
        System.out.println(task1);
        System.out.println(a);
        int c = task1.getTime();
        int d = task2.getTime();
        System.out.println(c);
        System.out.println(d);
        task2.setTime(8);
        System.out.println(task2);
        int e = task2.nextTimeAfter(6);
        System.out.println(e);
    }


}
