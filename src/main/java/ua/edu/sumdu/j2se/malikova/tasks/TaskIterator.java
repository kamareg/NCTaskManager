package ua.edu.sumdu.j2se.malikova.tasks;

import java.util.Iterator;

public class TaskIterator implements Iterator<Task> {
    public ArrayTaskList list;
    public int size;
    public TaskIterator (ArrayTaskList list) {
        this.list = list;
        this.size = list.size();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Task next() {
        return null;
    }
}
