package ua.edu.sumdu.j2se.malikova.tasks;

public abstract class AbstractTaskList {
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

}
