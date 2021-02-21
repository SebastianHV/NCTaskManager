package mx.edu.j2se.hernandez.tasks;

public class Main {
	
	public static void main(String[] args) {

		//making a non-repetitive task;
		Tasks nonRepTask = new Tasks("Birthday", 7);
		nonRepTask.setActive(true);
		//making a repetitive task;
		Tasks repTask = new Tasks("Wake up alarm",6,78, 24);
		repTask.setActive(true);
		// making and inactive task;
		Tasks inactiveTask = new Tasks("Inactive task", 13);

		System.out.println("Next time of execution for " + repTask.getTitle() + ": " + repTask.nextTimeAfter(55));

	
	}
	
}
