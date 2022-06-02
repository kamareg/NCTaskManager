package ua.edu.sumdu.j2se.malikova.tasks;

public class Main {

    public static void main(String[] args) {
       Task task1 = new Task("Simple IN", 55);
        Task task3 = new Task("Inactive OUT", 0, 1000, 1);
        Task task4 = new Task("Simple bound OUT", 50);
        Task task5 = new Task("Simple bound IN", 60);
LinkedTaskList taskList = new LinkedTaskList();
    taskList.add(task1);
    taskList.add(task3);
    taskList.add(task4);
    taskList.add(task5);
    taskList.remove(task5);
        System.out.println(taskList.displayTasksLinkedTaskList());
   // taskList.remove(null);
  //  taskList.add(null);
    //    taskList.getTask(3);
        //   System.out.println(taskList.incoming(-5,3));



        /* Task task2 = new Task("Simple OUT", 10, true);
        Task task3 = new Task("Inactive OUT", 0, 1000, 1, false);
        Task task4 = new Task("Simple bound OUT", 50, true);
        Task task5 = new Task("Simple bound IN", 60, true);
        Task task6 = new Task("Repeat inside IN", 51, 58, 2, true);
        Task task7 = new Task("Repeat outside IN", 0, 100, 5, true);
        Task task8 = new Task("Repeat outside OUT", 0, 100, 22, true);
        Task task9 = new Task("Repeat left OUT", 0, 40, 1, true);
        Task task10 = new Task("Repeat right OUT", 65, 100, 1, true);
        Task task11 = new Task("Repeat left intersect IN 1", 0, 55, 13, true);
        Task task12 = new Task("Repeat left intersect IN 2", 0, 60, 30, true);
        Task task13 = new Task("Repeat left intersect OUT", 0, 55, 22, true);
        Task task14 = new Task("Repeat right intersect IN", 55, 100, 20, true);

ArrayTaskList taskList = new ArrayTaskList();
taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        taskList.add(task4);
        taskList.add(task5);
        taskList.add(task6);
        taskList.add(task7);
        taskList.add(task8);
        taskList.add(task9);
        taskList.add(task10);
        taskList.add(task11);
        taskList.add(task12);
        taskList.add(task13);
        taskList.add(task14);

        ArrayTaskList y = taskList.incoming(50,60);
        System.out.println(y);


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


        ArrayTaskList one = new ArrayTaskList();
        one.add(task1);
        one.add(task2);
        one.add(task3);
        one.add(task4);
        one.add(task5);
        one.add(task1);
        one.add(task1);


        System.out.println(one.size());
        one.remove(task1);
        one.remove(task1);
        one.remove(task2);
        one.remove(task2);
        one.remove(task5);

        one.add(task1);
        one.add(task3);
        one.add(task2);
        System.out.println(one.getTask(1));
        one.add(task4);
        one.add(task5);
        one.add(task6);
        one.add(task7);
        one.add(task8);

        one.remove(task2);
        one.remove(task4);

        System.out.println(one.size());
        System.out.println(one);
        one.incoming(2, 5);


        LinkedTaskList two = new LinkedTaskList();
        two.add(task1);
        two.add(task2);
        two.add(task3);
        two.add(task1);
        two.add(task1);
        two.add(task4);

        two.getTask(1);
        System.out.println(two.size());

        two.remove(task4);
        System.out.println(two.getTask(4));
        System.out.println(two.size());
        two.incoming(1, 100);
        System.out.println(two);


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
        // System.out.println(e);*/
    }


}

