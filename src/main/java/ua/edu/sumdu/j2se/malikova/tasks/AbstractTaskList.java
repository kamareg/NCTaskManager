package ua.edu.sumdu.j2se.malikova.tasks;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {
        if (from == null || to == null || to.isBefore(from)) {
            throw new IllegalArgumentException("Необхідно ввести допустимі значення");
        }
        AbstractTaskList incomingAbstractList = TaskListFactory.createTaskList(type);
        Stream<Task> stream = getStream();
        stream.filter(x -> x != null && x.nextTimeAfter(from) != null && x.nextTimeAfter(from).isBefore(to)).forEach(incomingAbstractList::add);
        return incomingAbstractList;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        for (int i = 0; i < size(); i++) {
            hash = hash + this.getTask(i).hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbstractTaskList list = (AbstractTaskList) obj;
        if (this.size() != list.size()) return false;
        boolean equal = true;
        for (int i = 0; i < list.size(); i++) {
            equal = this.getTask(i).equals(list.getTask(i));
            if (!equal) {
                break;
            }
        }
        return equal;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Stream<Task> getStream() {
        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < this.size(); i++)
            tasks[i] = getTask(i);
        return Arrays.stream(tasks);
    }
}
