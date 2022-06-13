package ua.edu.sumdu.j2se.malikova.tasks;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public AbstractTaskList incoming(int from, int to) {
        if (from < 0 || from > to) {
            throw new IllegalArgumentException("Необхідно ввести допустимі значення");
        }
        AbstractTaskList incomingAbstractList = TaskListFactory.createTaskList(type);
        for (int i = 0; i < size(); i++) {
            if (this.getTask(i) != null && this.getTask(i).nextTimeAfter(from) != (-1) && this.getTask(i).nextTimeAfter(from) < to) {
                incomingAbstractList.add(this.getTask(i));
            }
        }
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
}
