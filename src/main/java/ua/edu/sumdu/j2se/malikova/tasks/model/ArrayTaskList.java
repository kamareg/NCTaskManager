package ua.edu.sumdu.j2se.malikova.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList {
    private Task[] array = new Task[10];
    private int counter;

    public ArrayTaskList() {
        this.type = ListTypes.types.ARRAY;
    }

    @Override
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

    @Override
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

    @Override
    public int size() {
        int countingTasks = 0;
        for (int i = 0; i < array.length; i++) {

            if (array[i] != null) {
                countingTasks += 1;
            }
        }
        return countingTasks;

    }

    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("Необхідно ввести допустиме значення");
        }
        return array[index];
    }

    @Override
    public String toString() {
        StringBuffer arrayList = new StringBuffer();
        arrayList.append("Array List with ");
        arrayList.append(this.size());
        arrayList.append(" tasks: \n");
        for (int i = 0, y = 0; i < size(); i++) {
            arrayList.append((++y) + ". ");
            arrayList.append(this.getTask(i));
            arrayList.append("\n");
        }
        return String.valueOf(arrayList);
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private int cursor = 0;
            private Task lastReturned = null;
            private Task current = array[cursor];

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public Task next() {
                lastReturned = current;
                cursor++;
                current = array[cursor];
                return lastReturned;
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(lastReturned);
                lastReturned = null;
                cursor--;
            }
        };
    }

    @Override
    public int hashCode() {
        int hash = 19;
        return hash * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Object clone() {
        try {
            ArrayTaskList result = (ArrayTaskList) super.clone();
            result.array = array.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public Stream<Task> getStream() {
        return super.getStream();
    }
}



