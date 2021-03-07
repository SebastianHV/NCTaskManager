package mx.edu.j2se.hernandez.tasks;

public class ArrayTaskList {

    private Tasks[] arrayTaskList = new Tasks[0];

    // Adds a tasks to the array of tasks
    public void add (Tasks task) {
        Tasks newArray[] = new Tasks[arrayTaskList.length + 1];
        System.arraycopy(arrayTaskList, 0, newArray, 0, arrayTaskList.length);

        newArray[newArray.length - 1] = task;

        arrayTaskList = newArray;

    }

    // Removes a task from the array of tasks
    public boolean remove(Tasks task) {
        for (int i = 0; i < arrayTaskList.length; i++) {
            if (task.equals(arrayTaskList[i])) {
                Tasks newArray[] = new Tasks[arrayTaskList.length - 1];
                System.arraycopy(arrayTaskList, 0, newArray, 0, i);
                System.arraycopy(arrayTaskList, i + 1, newArray, i, arrayTaskList.length - i - 1);
                arrayTaskList = newArray;
                return true;
            }
        }
        return false;
    }

    // Returns the number of elements of the array of tasks
    public int size() {
        if (getArrayTaskList() == null)
            return 0;
        return arrayTaskList.length;
    }

    // Returns the task from the list whose index is the given one
    public Tasks getTask(int index) {
        return arrayTaskList[index];
    }

    // Returns a list of tasks that occur from the time to the given time.
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList incomingTasksList = new ArrayTaskList();
        for (int i = 0; i < arrayTaskList.length; i++) {
//            if (arrayTaskList[i].isActive()){
//                if (arrayTaskList[i].isRepetitive()){
//                    int sumTime = arrayTaskList[i].getStartTime();
//                    while (sumTime <= arrayTaskList[i].getEndTime()) {
//                        if (sumTime >= from && sumTime <= to) {
//                            incomingTasksList.add(arrayTaskList[i]);
//                            break;
//                        }
//                        else {
//                            sumTime += arrayTaskList[i].getRepeatInterval();
//                        }
//                    }
//
//                }
//                else {
//                    if (arrayTaskList[i].getTime() >= from && arrayTaskList[i].getTime() <= to){
//                        incomingTasksList.add(arrayTaskList[i]);
//                    }
//                }
//            }
            int nextTimeFrom = arrayTaskList[i].nextTimeAfter(from);
            if (nextTimeFrom <= to && nextTimeFrom != -1) {
                incomingTasksList.add(arrayTaskList[i]);
            }
        }

        return incomingTasksList;
    }

    // Returns the array of tasks
    public Tasks[] getArrayTaskList() {
        return arrayTaskList;
    }
}
