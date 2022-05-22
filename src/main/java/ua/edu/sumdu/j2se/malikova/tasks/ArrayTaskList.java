package ua.edu.sumdu.j2se.malikova.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] array = new Task[0];
    private int count;

    public ArrayTaskList (Task[] array, int count) {
    this.array = array;
    this.count = count;
    }
    public ArrayTaskList () {super();}

    public void add(Task task) {
        if (array.length == count) {
            array = Arrays.copyOf(array, (array.length + 3));
        }
        array[count] = task;
        count++;
    }

    public boolean remove(Task task) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == task) {
                Task[] arrayForRemove = new Task[array.length];
                for (int index = 0; index < i; index++) {
                    arrayForRemove[index] = array[index];
                }
                for (int j = i+1; j < array.length - 1; j++) {
                    arrayForRemove[j] = array[j];
                }
                array = Arrays.copyOf(arrayForRemove, (arrayForRemove.length));
                return true;

            } // break;

        }
        return false;
    }

    public int size() {
        return array.length;
    }

    public Task getTask(int index) {
        return array[index];
    }

    public ArrayTaskList incoming(int from, int to) {

        return null;
    }

    @Override
    public String toString() {
        return "ArrayTaskList{" +
                "array=" + Arrays.toString(array) +
                ", count=" + count +
                '}';
    }
}

