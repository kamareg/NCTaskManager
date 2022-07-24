package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
/**
 * A class containing constructors and methods for working with Array Task List.
 */
public class ArrayTaskList extends AbstractTaskList {
    private Task[] array = new Task[10];
    private int counter;
    public final Logger logger = Logger.getLogger(ArrayTaskList.class);

    public ArrayTaskList() {
        this.type = ListTypes.types.ARRAY;
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            logger.error("There is null task");
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
            logger.error("There is null task");
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
            logger.error("List border violation");
        }
        return array[index];
    }

    @Override
    public String toString() {
        StringBuffer arrayList = new StringBuffer();
        arrayList.append("Array List has ");
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
                    logger.error("There is null task");
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



