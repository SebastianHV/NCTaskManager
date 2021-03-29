package mx.edu.j2se.hernandez.tasks;

import javafx.concurrent.Task;

import java.util.Iterator;

// Parent class of ArrayTaskList and LinkedTaskList
public abstract class AbstractTaskList implements Iterable<Tasks>, Cloneable {

    public abstract void add(Tasks task) throws Exception;

    public abstract boolean remove(Tasks task);

    public abstract int size();

    public abstract Tasks getTask(int index) throws Exception;

    /*
     In this case, even the incoming method depends on the way we access the list (array or linked)
     Thus, we make incoming(int from, int to) abstract, as well.
    */
    public abstract AbstractTaskList incoming(int from, int to);

    // This is the iterator method that will let us use the enhanced for each loop in the TaskList Class
    @Override
    public Iterator<Tasks> iterator() {
        int size = this.size();
        AbstractTaskList taskList = this;
        // System.out.println("Iterator taskList reference" + taskList);

        Iterator<Tasks> it = new Iterator<Tasks>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                // System.out.println("Inside hasNext method, currentIndexValue: " + currentIndex);
                if (currentIndex < (size)) return true;
                return false;
            }

            @Override
            public Tasks next() {
                // System.out.println("Inside next method");
                // System.out.println("currentIndex value: " + currentIndex);
                Tasks newTask = null;
                try {
                    newTask = taskList.getTask(currentIndex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // System.out.println(newTask.getTitle());
                currentIndex ++;
                return newTask;
            }
        };
        return it;
    }

    // Implementation of the equals method
    // Checks if the lists contains the same tasks in the same order
    @Override
    public boolean equals(Object obj) {
        AbstractTaskList firstTaskList = this;
        if (obj instanceof AbstractTaskList || obj instanceof LinkedTaskList || obj instanceof ArrayTaskList) {
            AbstractTaskList secondTaskList = (AbstractTaskList) obj;
            if (firstTaskList.size() == secondTaskList.size()) {
                int listSize = firstTaskList.size();
                for (int i = 0; i < listSize; i++) {
                    try {
                        if (firstTaskList.getTask(i).equals(secondTaskList.getTask(i))) {
                            continue;
                    } else {
                            return false;
                        }
                    } catch (Exception e) {
                        e.getMessage();
                    }

                }
                return true;
            }
        }

        return false;
    }

    // Implementation of the hashCode method
    // Returns the hash code of the list of tasks, which is the sum of all the task's hashcode
    @Override
    public int hashCode() {
        int toRet = 0;
        for (Tasks task: this) {
            toRet = toRet + task.hashCode();
        }
        return toRet;
    }

    // More friendly way to retrieve the TaskList
    @Override
    public String toString() {
        // String toRet = "\nTitle             *** Repetitive *** Active *** Time (Non repetitive) *** Start Time - End Time *** Interval\n";
        String toRet = "\n*** Printing TaskList ****************************************************************************************************************\n";
        for (Tasks task:
             this) {
            // toRet = toRet + task.getTitle() + "          |---| " + task.isRepetitive() + " |---| " + task.isActive() + " |---| " + task.getTime() + " |---| " + task.getStartTime() + " |---| " + task.getEndTime() + task.getRepeatInterval() + " |\n";
            toRet += "| " + task + " |\n";
            toRet += "|************************************************************************************************************************************|\n";
        }
        toRet += "--------------------------------------------------------------------------------------------------------------------------------------\n";
        return toRet;
    }

    // Implementation of the cloning method for the TaskList
    @Override
    protected Object clone() throws CloneNotSupportedException {
        AbstractTaskList clonedList;
//        if (this instanceof ArrayTaskList) {
//            clonedList = new ArrayTaskList();
//        }
//        if (this instanceof LinkedTaskList){
//            clonedList = new LinkedTaskList();
//        }
//        else {
//            throw new CloneNotSupportedException("Cannot clone this type of object / list");
//        }
//        for (Tasks task:
//                this) {
//            try {
//                clonedList.add(task);
//            } catch (Exception e) {
//                e.getMessage();
//            }
//        }
        clonedList = (AbstractTaskList) super.clone();

        // This will remove the not cloned tasks from the cloned List
//        for (Tasks task : clonedList) {
//            try {
//                System.out.println("Current task: " + task);
//                clonedList.remove(task);
//            }
//            catch (Exception e){
//                e.getMessage();
//            }
//        }

        // This will add the cloned tasks from the original List
        for (Tasks task : this) {
            try {
                clonedList.add((Tasks) task.clone());
            }
            catch (Exception e){
                e.getMessage();
            }
        }

        return clonedList;
    }
}
