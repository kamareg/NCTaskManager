package ua.edu.sumdu.j2se.malikova.tasks;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList {
    private Link first;

    public LinkedTaskList() {
        this.type = ListTypes.types.LINKED;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {
            private Link current = first;
            private int cursor = 0;
            private Link lastReturned = null;

            @Override
            public boolean hasNext() {
                return cursor < size();
            }

            @Override
            public Task next() {
                if (current == null) {
                    throw new IndexOutOfBoundsException();
                }
                lastReturned = current;
                current = current.next;
                cursor++;
                return lastReturned.task;
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                LinkedTaskList.this.remove(lastReturned.task);
                lastReturned = null;
                cursor--;
            }
        };
    }

    private static class Link {
        public Task task;
        public Link next;

        public Link(Task task) {
            this.task = task;
        }
    }

    @Override
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

    @Override
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

    @Override
    public int size() {
        Link current = first;
        int counter = 0;
        while (current != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    @Override
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

    @Override
    public AbstractTaskList incoming(int from, int to) {
        return super.incoming(from, to);
    }

    @Override
    public int hashCode() {
        int hash = 31;
        return hash * super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuffer linkedList = new StringBuffer();
        linkedList.append("Linked List with ");
        linkedList.append(this.size());
        linkedList.append(" tasks: \n");
        for (int i = 0, y = 0; i < size(); i++) {
            linkedList.append((++y) + ". ");
            linkedList.append(this.getTask(i));
            linkedList.append("\n");
        }
        return String.valueOf(linkedList);
    }
}
