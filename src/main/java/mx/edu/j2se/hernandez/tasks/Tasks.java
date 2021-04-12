package mx.edu.j2se.hernandez.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Tasks {

    // Returns a list of tasks that occur from the time to the given time.
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        Stream<Task> tasksStream = StreamSupport.stream(tasks.spliterator(), false);
        Iterator<Task> taskIterator;
        taskIterator = tasksStream.filter(task ->
                (task.nextTimeAfter(start).isBefore(end) || task.nextTimeAfter(start).isEqual(end))  && !((task.nextTimeAfter(start).equals(LocalDateTime.MIN)))
        ).iterator();

        Iterable<Task> toRet = getIterableFromIterator(taskIterator);

        return toRet;
    }

    // Function to get the Spliterator
    public static <T> Iterable<T>
    getIterableFromIterator(Iterator<T> iterator)
    {
        return () -> iterator;
    }

    // Method that gets a Calendar of tasks in the specified time
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        LocalDateTime currentDate = start; // We start from the start time
        while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
            Set<Task> taskSet = new HashSet<>();
            // System.out.println("Current time: " + currentDate);
            Iterable<Task> taskIterable = Tasks.incoming(tasks, currentDate, currentDate);
            taskIterable.forEach(task -> taskSet.add(task));
            if (!taskSet.isEmpty()) {
                calendar.put(currentDate, taskSet);
            }
            currentDate = currentDate.plusMinutes(1);
        }

        return calendar;
    }
}
