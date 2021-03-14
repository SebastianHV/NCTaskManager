package mx.edu.j2se.hernandez.tasks;

// Parent class of ArrayTaskList and LinkedTaskList
public abstract class AbstractTaskList {
    public abstract void add(Tasks task) throws Exception;
    public abstract boolean remove(Tasks task);
    public abstract int size();
    public abstract  Tasks getTask(int index) throws Exception;
    /*
     In this case, even the incoming method depends on the way we access the list (array or linked)
     Thus, we make incoming(int from, int to) abstract, as well.
    */
    public abstract AbstractTaskList incoming(int from, int to);
}
