package ua.edu.sumdu.j2se.malikova.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;

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
    public AbstractTaskList incoming(int from, int to) {
        return super.incoming(from, to);
    }


    @Override
    public String toString() {
        return "array=" + Arrays.toString(array) +
                ", count=" + counter +
                '}';
    }

    @Override
    public Iterator<Task> iterator() {
        return new TaskIterator(this);
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return super.spliterator();
    }

   /* @Override
    public Iterator<Task> iterator() {
        //Iterator<Task> iter = this.iterator();
      //  return new Itr();
        Iterator<Task> ListIterator = this.iterator();
        ListIterator(ArrayTaskList array, int size {
                    this.list = list;
                  this.size = size;
                    this.cursor = 0;
                    isListIterator = false;
                }
        return new ListIterator<Task>(this, size()) {
            @Override
            public boolean hasNext() {
                return (this.next()!=null);
            }

            @Override
            public Task next() {
                return null;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Task> action) {
        super.forEach(action);
    }

    @Override
    public Spliterator<Task> spliterator() {
        return super.spliterator();
    }*/
}

