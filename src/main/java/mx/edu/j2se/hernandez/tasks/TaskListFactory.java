package mx.edu.j2se.hernandez.tasks;

public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type) throws IllegalArgumentException {
        AbstractTaskList taskList;
        switch (type) {
            case ARRAY:
                taskList = new ArrayTaskList();
                System.out.println("ArrayTaskList created.");
                break;
            case LINKED:
                taskList = new LinkedTaskList();
                System.out.println("LinkedTaskList created.");
                break;
            default:
                throw new IllegalArgumentException("The type of the list must be ARRAY or LINKED");
        }
        return taskList;
    }
}
