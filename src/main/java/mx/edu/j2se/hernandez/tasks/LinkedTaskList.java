package mx.edu.j2se.hernandez.tasks;


public class LinkedTaskList extends AbstractTaskList{

    // The first element of the linked list is our head
    private Node headOfList = null;

    // This class will allow us to store every task and link it to the following task in the list
    static class Node {
        Tasks data;
        Node next; // This is a reference to the next Task in the list

        Node(Tasks task) {
            data = task;
            next = null;
        }
    }

    // This method will add a Task to the list
    public void add(Tasks task) throws Exception {
        if (task instanceof Tasks) {
            Node newNode = new Node(task);
            // If head is empty, this new Task is the first one
            if (headOfList == null) {
                headOfList = new Node(task);
            }
            // Else, we reach the last task and add this one to the end
            else {
                Node lastNode = headOfList;
                while (lastNode.next != null) {
                    lastNode = lastNode.next;
                }
                lastNode.next = newNode;
            }
        }
        else {
            throw new Exception("Invalid instance of Tasks");
        }

    }

    // This method will search into the linked list for the Task to remove
    public boolean remove(Tasks task) {
        Node currentNode = headOfList; // The node we are placed in
        Node previousNode = null; // The previous node, will help us to link the previous node to the next node
        while (currentNode != null) {
            if (currentNode.data.equals(task)) {
                if (currentNode.equals(headOfList)){
                    headOfList.next = null; // We unlink the deleted Task
                    headOfList = currentNode.next;
                }
                else {
                    previousNode.next = currentNode.next;
                    currentNode.next = null; // We unlink the deleted Task
                }
                return true;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        return false;
    }

    // This method returns the size of the linked list
    public int size() {
        int noOfTasks = 0;
        Node currentNode = headOfList;
        while (currentNode != null) {
            noOfTasks++;
            currentNode = currentNode.next;
        }
        return noOfTasks;
    }

    // Returns the task from the list whose index is the given one
    public Tasks getTask(int index) throws IllegalArgumentException {
        int currentTaskNumber = 0;
        Node currentNode = headOfList;
        while (currentNode != null) {
            if (currentTaskNumber == index) {
                return currentNode.data;
            }
            currentTaskNumber++;
            currentNode = currentNode.next;
        }

        throw new IllegalArgumentException("Invalid index. Should be from 0 to i - 1.");

    }

    // Returns a list of tasks that occur from the time to the given time.
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList incomingTasksList = new LinkedTaskList();
        Node currentNode = headOfList;
        while (currentNode != null) {
            int nextTimeFrom = currentNode.data.nextTimeAfter(from);
            if (nextTimeFrom <= to && nextTimeFrom != -1) {
                try {
                    incomingTasksList.add(currentNode.data);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            currentNode = currentNode.next;
        }
        return incomingTasksList;
    }

    public String getList() {
        Node currentNode = headOfList;
        String toRet = "";
        while (currentNode.next != null) {
            toRet = toRet + (currentNode.data.getTitle() + " - ");
            currentNode = currentNode.next;
        }
        toRet = toRet + (currentNode.data.getTitle());
        return toRet;
    }


}
