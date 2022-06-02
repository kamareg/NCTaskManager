package ua.edu.sumdu.j2se.malikova.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] array = new Task[10];
    private int counter;

    public ArrayTaskList() {
        super();
    }

    public void add(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Потрібно ввести задачу");
        }
        if (array.length == counter) {
            array = Arrays.copyOf(array, (array.length + 3));
        }
        array[counter] = task;
        counter++;
    }

    public boolean remove(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Потрібно ввести задачу");
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == task) {
                Task[] arrayForRemove = new Task[array.length];
                for (int index = 0; index < i; index++) {
                    arrayForRemove[index] = array[index];
                }
                for (int j = i + 1; j < array.length; j++, i++) {
                    arrayForRemove[i] = array[j];
                }
                array = Arrays.copyOf(arrayForRemove, (arrayForRemove.length));
                counter--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        int countingTasks = 0;
        for (int i = 0; i < array.length; i++) {

            if (array[i] != null) {
                countingTasks += 1;
            }
        }
        return countingTasks;

    }

    public Task getTask(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Необхідно ввести допустиме значення");
        }
        return array[index];
    }

    public ArrayTaskList incoming(int from, int to) {
        if (from < 0 || from > to) {
            throw new IllegalArgumentException("Необхідно ввести допустимі значення");
        }
        ArrayTaskList incomingArray = new ArrayTaskList();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].nextTimeAfter(from) != (-1)) {
                    if (array[i].nextTimeAfter(from) < to) {
                        incomingArray.add(array[i]);
                    }
                }
            }
        }
        return incomingArray;


    }

    @Override
    public String toString() {
        return "array=" + Arrays.toString(array) +
                ", count=" + counter +
                '}';
    }
}

