package ua.edu.sumdu.j2se.malikova.tasks;

import org.w3c.dom.Node;

import java.util.Arrays;

public class LinkedTaskList {
    private Link first;

    private static class Link {
        public Task task;
        public Link next;

        public Link(Task task) {
            this.task = task;
        }
    }

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Потрібно ввести задачу");
        }
        if (first == null) {
            first = new Link(task);
        } else {
            Link newLink = new Link(task);
            newLink.next = first;
            first = newLink;
        }
    }

    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Потрібно ввести задачу");
        }
        Link current = first;
        Link previous = null;
        if (first == null) {
            return false;
        } else if (first.task.equals(task)) {
            first = first.next;
            return true;
        } else {
            while (current != null) {
                if (current.task.equals(task)) {
                    previous.next = current.next;
                    return true;
                }
                previous = current;
                current = current.next;
            }
        }
        return false;
    }


    public int size() {
        Link current = first;
        int counter = 0;
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }


    public Task getTask(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Необхідно ввести допустиме значення");
        }
        Link current = first;
        int i = 0;
        while (i != index) {
            current = current.next;
            i++;
        }
        return current.task;
    }

    public LinkedTaskList incoming(int from, int to) {
        if (from < 0 || from > to) {
            throw new IllegalArgumentException("Необхідно ввести допустимі значення");
        }
        LinkedTaskList incomingList = new LinkedTaskList();
        for (int i = 0; i < size(); i++) {
            if (this.getTask(i) != null && this.getTask(i).nextTimeAfter(from) != (-1) && this.getTask(i).nextTimeAfter(from) < to) {
                incomingList.add(this.getTask(i));
            }
        }
        return incomingList;
    }

    public String displayTasksLinkedTaskList() {
        for (int i = 0; i < size(); i++) {
            System.out.println(this.getTask(i));
        }
        return "";
    }


    @Override
    public String toString() {
        return "LinkedTaskList: " +
                "size=" + this.size() +
                " tasks" + this.displayTasksLinkedTaskList();
    }
}
