package mx.edu.j2se.hernandez.tasks;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) {

		try {
			//making a non-repetitive task;
			Task nonRepTask = new Task("Birthday", LocalDateTime.of(2021, 8, 31, 0, 0));
			nonRepTask.setActive(true);
			//making a repetitive task;
			Task repTask = new Task("Wake up alarm", LocalDateTime.of(2021, 1, 1, 0, 0), LocalDateTime.of(2022, 01,01,0,0), 1);
			repTask.setActive(true);
			// making and inactive task;
			Task inactiveTask = new Task("Inactive task", LocalDateTime.of(2021,6,2,0,0));

			System.out.println("********************");
			System.out.println(nonRepTask.getTitle() + "Date and Time : " + repTask.getTime());
			System.out.println("We are going to compare against this date and time: " + LocalDateTime.of(2021,9,1,0,0));
			System.out.println("Next time of execution for " + repTask.getTitle() + ": " + repTask.nextTimeAfter(LocalDateTime.of(2021,9,1,0,0)));

			// Making lots of active tasks
			Task lunchTask = new Task("Lunch time alarm", LocalDateTime.of(2021,06,01,14,00), LocalDateTime.of(2021,12,25,14,0), 1);
			lunchTask.setActive(true);
			Task tvShowTask = new Task("My favorite TV show time", LocalDateTime.of(2021,04,8,11,00), LocalDateTime.of(2021,6,24,11,0), 7);
			tvShowTask.setActive(true);
			Task meetingTask = new Task("Important meeting with customers", LocalDateTime.of(2021,6,2,15,0));
			meetingTask.setActive(true);

			System.out.println("***** Creating a Task List with the TaskListFactory *****");
			AbstractTaskList factoryArrayList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
			AbstractTaskList factoryLinkedList = TaskListFactory.createTaskList(ListTypes.types.LINKED);

			factoryLinkedList.add(nonRepTask);
			factoryLinkedList.add(lunchTask);
			factoryLinkedList.add(tvShowTask);
			factoryLinkedList.add(meetingTask);

			System.out.println("********** Printing Task Array **********");
			System.out.println(factoryLinkedList);

			System.out.println("********************");
			System.out.println("***** Erasing " + nonRepTask.getTitle() + " Task from the list *****");
			System.out.println("Could erase a task form the list: " + factoryLinkedList.remove(nonRepTask));
			System.out.println("Size of the list: " + factoryLinkedList.size());
			System.out.println("Second task of the list: " + factoryLinkedList.getTask(1).getTitle());

			System.out.println("********** Printing Task Array **********");
			System.out.println(factoryLinkedList);

			System.out.println("Next time of execution for " + meetingTask.getTitle() + ": " + meetingTask.nextTimeAfter(LocalDateTime.of(2021,9,1,0,0)));

			System.out.println("********** Calling incoming static method from Tasks **********");
			Iterable resultList = Tasks.incoming(factoryLinkedList, LocalDateTime.of(2021,8,31,14,0), LocalDateTime.of(2021,8,31,14,0));
			resultList.forEach(System.out::println);


			System.out.println("********** Calling calendar static method from Tasks **********");
			SortedMap<LocalDateTime, Set<Task>> cal = new TreeMap<>();
			cal = Tasks.calendar(factoryLinkedList, LocalDateTime.of(2021,8,31,14,0), LocalDateTime.of(2021,12,31,13,59));
			System.out.println(cal);

//
//			ArrayTaskList taskList = new ArrayTaskList();
//			for (int i = 0; i < taskList.size(); i++) {
//				System.out.println(taskList.getTask(i));
//
//			}
//
//			taskList.add(lunchTask);
//			taskList.add(tvShowTask);
//			taskList.add(meetingTask);
//			taskList.add(nonRepTask);
//			System.out.println("********************");
//			System.out.println("***** Erasing " + nonRepTask.getTitle() + " Task from the list *****");
//			System.out.println("Could erase a task form the list: " + taskList.remove(nonRepTask));
//			System.out.println("Size of the list: " + taskList.size());
//			System.out.println("Second task of the list: " + taskList.getTask(1).getTitle());
//
//			System.out.println("********** Printing Task Array **********");
//			for (int i = 0; i < taskList.size(); i++) {
//				System.out.println(taskList.getTask(i).getTitle());
//
//			}
//			System.out.println("********************");
//
//			System.out.println("Printing the Array List: " + taskList.getArrayTaskList().toString());
//
//			System.out.println("***** Incoming tasks from hour 200 to 260: *****");
//			AbstractTaskList incomingTasks = taskList.incoming(200, 260);
//			for (int i = 0; i < incomingTasks.size(); i++) {
//				System.out.println(incomingTasks.getTask(i).getTitle());
//
//			}
//			System.out.println("********************");
//			System.out.println("Creating a Linked List of Tasks");
//			LinkedTaskList linkedTaskList = new LinkedTaskList();
//			linkedTaskList.add(lunchTask);
//			linkedTaskList.add(tvShowTask);
//			linkedTaskList.add(meetingTask);
//			linkedTaskList.add(nonRepTask);
//			System.out.println(linkedTaskList.getList());
//			System.out.println("********************");
//			System.out.println("***** Erasing " + nonRepTask.getTitle() + " Task from the Linked list *****");
//			System.out.println("Could erase a task form the list: " + linkedTaskList.remove(nonRepTask));
//			System.out.println("Size of the list: " + linkedTaskList.size());
//			System.out.println("Third task of the list: " + linkedTaskList.getTask(2).getTitle());
//			System.out.println("Hi");
//			System.out.println("***** Incoming tasks from hour 200 to 260: *****");
//			AbstractTaskList linkedIncomingTasks = linkedTaskList.incoming(200, 260);
//			System.out.println(linkedIncomingTasks);
//			System.out.println("***** Incoming tasks from hour 200 to 260 ENDS *****");
//			System.out.println("***** Creating a Task List with the TaskListFactory *****");
//			AbstractTaskList factoryArrayList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
//			AbstractTaskList factoryLinkedList = TaskListFactory.createTaskList(ListTypes.types.LINKED);
//			System.out.println("********************");
//			System.out.println("*** Printing List with Iterator ***");
//			// Iterator<Tasks> ite = factoryLinkedList.iterator();
//			System.out.println("factoryLinkedList reference: " + linkedTaskList);
//			for (Task task: linkedTaskList ) {
//				System.out.println(task.getTitle());
//
//			}
//			System.out.println("********************");
//            System.out.println("*** Trying equals method ***");
//            AbstractTaskList anotherTaskList = linkedTaskList;
//            System.out.println("Are both objects equal: " + anotherTaskList.equals(linkedTaskList));
//
//            System.out.println("********************");
//            System.out.println("*** Trying hashCode method ***");
//            System.out.println("anotherTaskList hashcode: " + anotherTaskList.hashCode());
//            System.out.println("linkedTaskList hashcode: " + linkedTaskList.hashCode());
//            System.out.println("factoryArrayList hashcode: " + factoryArrayList.hashCode());
//            System.out.println("nonRepTask hashcode: " + nonRepTask.hashCode());
//            System.out.println("repTask hashcode: " + repTask.hashCode());
//
//            System.out.println("********************");
//            System.out.println("*** Trying toString method ***");
//            System.out.println("anotherTaskList:\r " + anotherTaskList);
//            System.out.println("linkedTaskList:\r " + linkedTaskList);
//            System.out.println("factoryArrayList:\r " + factoryArrayList);
//            System.out.println("nonRepTask: " + nonRepTask);
//            System.out.println("repTask: " + repTask);
//
//            System.out.println("********************");
//            System.out.println("*** Trying clone method ***");
//            System.out.println("linkedTaskList:" + linkedTaskList);
//            AbstractTaskList clonedLinkedTaskList = (AbstractTaskList) linkedTaskList.clone();
//            System.out.println("clonedLinkedTaskList: " + clonedLinkedTaskList);
//
//            System.out.println("**** Trying Iterator ****");
//            for (Task task:
//                 linkedTaskList) {
//                System.out.println(task);
//            }
//
//
//
//
//
//
//
		}
		catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}

	}

}
