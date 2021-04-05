package mx.edu.j2se.hernandez.tasks;


public class Main {
	
	public static void main(String[] args) {

		try {
			//making a non-repetitive task;
			Task nonRepTask = new Task("Birthday", 7);
			nonRepTask.setActive(true);
			//making a repetitive task;
			Task repTask = new Task("Wake up alarm", 6, 78, 24);
			repTask.setActive(true);
			// making and inactive task;
			Task inactiveTask = new Task("Inactive task", 13);

			System.out.println("********************");
			System.out.println("Next time of execution for " + inactiveTask.getTitle() + ": " + inactiveTask.nextTimeAfter(55));

			// Making lots of active tasks
			Task lunchTask = new Task("Lunch time alarm", 12, 252, 24);
			lunchTask.setActive(true);
			Task tvShowTask = new Task("My favorite TV show time", 20, 120, 24);
			tvShowTask.setActive(true);
			Task meetingTask = new Task("Important meeting with customers", 200);
			meetingTask.setActive(true);


			ArrayTaskList taskList = new ArrayTaskList();
			for (int i = 0; i < taskList.size(); i++) {
				System.out.println(taskList.getTask(i));

			}

			taskList.add(lunchTask);
			taskList.add(tvShowTask);
			taskList.add(meetingTask);
			taskList.add(nonRepTask);
			System.out.println("********************");
			System.out.println("***** Erasing " + nonRepTask.getTitle() + " Task from the list *****");
			System.out.println("Could erase a task form the list: " + taskList.remove(nonRepTask));
			System.out.println("Size of the list: " + taskList.size());
			System.out.println("Second task of the list: " + taskList.getTask(1).getTitle());

			System.out.println("********** Printing Task Array **********");
			for (int i = 0; i < taskList.size(); i++) {
				System.out.println(taskList.getTask(i).getTitle());

			}
			System.out.println("********************");

			System.out.println("Printing the Array List: " + taskList.getArrayTaskList().toString());

			System.out.println("***** Incoming tasks from hour 200 to 260: *****");
			AbstractTaskList incomingTasks = taskList.incoming(200, 260);
			for (int i = 0; i < incomingTasks.size(); i++) {
				System.out.println(incomingTasks.getTask(i).getTitle());

			}
			System.out.println("********************");
			System.out.println("Crating a Linked List of Tasks");
			LinkedTaskList linkedTaskList = new LinkedTaskList();
			linkedTaskList.add(lunchTask);
			linkedTaskList.add(tvShowTask);
			linkedTaskList.add(meetingTask);
			linkedTaskList.add(nonRepTask);
			System.out.println(linkedTaskList.getList());
			System.out.println("********************");
			System.out.println("***** Erasing " + nonRepTask.getTitle() + " Task from the Linked list *****");
			System.out.println("Could erase a task form the list: " + linkedTaskList.remove(nonRepTask));
			System.out.println("Size of the list: " + linkedTaskList.size());
			System.out.println("Third task of the list: " + linkedTaskList.getTask(2).getTitle());
			System.out.println("Hi");
			System.out.println("***** Incoming tasks from hour 200 to 260: *****");
			AbstractTaskList linkedIncomingTasks = linkedTaskList.incoming(200, 260);
			System.out.println(linkedIncomingTasks);
			System.out.println("***** Incoming tasks from hour 200 to 260 ENDS *****");
			System.out.println("***** Creating a Task List with the TaskListFactory *****");
			AbstractTaskList factoryArrayList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
			AbstractTaskList factoryLinkedList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
			System.out.println("********************");
			System.out.println("*** Printing List with Iterator ***");
			// Iterator<Tasks> ite = factoryLinkedList.iterator();
			System.out.println("factoryLinkedList reference: " + linkedTaskList);
			for (Task task: linkedTaskList ) {
				System.out.println(task.getTitle());

			}
			System.out.println("********************");
            System.out.println("*** Trying equals method ***");
            AbstractTaskList anotherTaskList = linkedTaskList;
            System.out.println("Are both objects equal: " + anotherTaskList.equals(linkedTaskList));

            System.out.println("********************");
            System.out.println("*** Trying hashCode method ***");
            System.out.println("anotherTaskList hashcode: " + anotherTaskList.hashCode());
            System.out.println("linkedTaskList hashcode: " + linkedTaskList.hashCode());
            System.out.println("factoryArrayList hashcode: " + factoryArrayList.hashCode());
            System.out.println("nonRepTask hashcode: " + nonRepTask.hashCode());
            System.out.println("repTask hashcode: " + repTask.hashCode());

            System.out.println("********************");
            System.out.println("*** Trying toString method ***");
            System.out.println("anotherTaskList:\r " + anotherTaskList);
            System.out.println("linkedTaskList:\r " + linkedTaskList);
            System.out.println("factoryArrayList:\r " + factoryArrayList);
            System.out.println("nonRepTask: " + nonRepTask);
            System.out.println("repTask: " + repTask);

            System.out.println("********************");
            System.out.println("*** Trying clone method ***");
            System.out.println("linkedTaskList:" + linkedTaskList);
            AbstractTaskList clonedLinkedTaskList = (AbstractTaskList) linkedTaskList.clone();
            System.out.println("clonedLinkedTaskList: " + clonedLinkedTaskList);

            System.out.println("**** Trying Iterator ****");
            for (Task task:
                 linkedTaskList) {
                System.out.println(task);
            }







		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

	}
	
}
