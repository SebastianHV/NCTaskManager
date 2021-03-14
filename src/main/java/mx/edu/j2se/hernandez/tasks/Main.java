package mx.edu.j2se.hernandez.tasks;


public class Main {
	
	public static void main(String[] args) {

		try {
			//making a non-repetitive task;
			Tasks nonRepTask = new Tasks("Birthday", 7);
			nonRepTask.setActive(true);
			//making a repetitive task;
			Tasks repTask = new Tasks("Wake up alarm", 6, 78, 24);
			repTask.setActive(true);
			// making and inactive task;
			Tasks inactiveTask = new Tasks("Inactive task", 13);

			System.out.println("********************");
			System.out.println("Next time of execution for " + inactiveTask.getTitle() + ": " + inactiveTask.nextTimeAfter(55));

			// Making lots of active tasks
			Tasks lunchTask = new Tasks("Lunch time alarm", 12, 252, 24);
			lunchTask.setActive(true);
			Tasks tvShowTask = new Tasks("My favorite TV show time", 20, 120, 24);
			tvShowTask.setActive(true);
			Tasks meetingTask = new Tasks("Important meeting with customers", 200);
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
			ArrayTaskList incomingTasks = taskList.incoming(200, 260);
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
			LinkedTaskList linkedIncomingTasks = linkedTaskList.incoming(200, 260);
			System.out.println(linkedIncomingTasks.getList());

		}
		catch (Exception e) {
			e.getMessage();
		}

	}
	
}
