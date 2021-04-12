package mx.edu.j2se.hernandez.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repetitive;
    private LocalDateTime MINIMUM_TIME = LocalDateTime.MIN;

    // Constructor for non-repetitive tasks
    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if (time.isBefore(LocalDateTime.MIN)) {
            throw new IllegalArgumentException("The entered Date and Time is invalid. Please enter a valid date and time.");
        }
        this.title = title;
        this.time = time;
        // this.interval = LocalDateTime.of(0,0,0,0,0);
        this.interval = 0;
        this.active = false;
        this.repetitive = false;
    }

    // Constructor for repetitive tasks
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (start.isBefore(MINIMUM_TIME) || start.isBefore(MINIMUM_TIME)) {
            throw new IllegalArgumentException("The start and end time must be a valid Date and Time");
        }
        if (interval < 1) {
            throw new IllegalArgumentException("The interval should be a valid date or time");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repetitive = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /* Methods for non-repetitive tasks */

    // if the task is a repetitive one, the method must return the start time of the repetition
    public LocalDateTime getTime() {
        if (isRepetitive()) {
            return this.start;
        }
        return this.time;
    }

    // if the task was a repetitive one, it should become non-repetitive
    public void setTime(LocalDateTime time) {
        if (isRepetitive()){
            this.interval = 0;
            this.repetitive = false;
        }
        this.time = time;
    }

    /* Methods for repetitive tasks */

    // if the task is a non-repetitive one, the method must return the start time of the execution;
    public LocalDateTime getStartTime() {
        if (isRepetitive()) {
            return this.start;
        } else {
            return this.time;
        }
    }

    // if the task is a non-repetitive one, the method must return the end time of the execution
    public LocalDateTime getEndTime() {
        if (isRepetitive()) {
            return this.end;
        } else {
            return this.time;
        }
    }

    // if the task is a non-repetitive one, the method must return 0
    public int getRepeatInterval() {
        if (isRepetitive()) {
            return this.interval;
        } else {
            return 0;
        }
    }

    // if the task is a non-repetitive one, it should become repetitive
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (isRepetitive()) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = MINIMUM_TIME; //This makes the task non-repetitive
            this.repetitive = false; //This makes the task non-repetitive
        }
    }

    // Gives the next execution time of the task, starting from the current time given.
    // Returns -1 if not next time of execution found.
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (isRepetitive()) {
                LocalDateTime sumTime = this.start; // the first time of execution is the start time
                while (sumTime.isBefore(this.end) /*this.end >= sumTime*/ ) { // While the sumTime is before the end time
                    if (sumTime.isAfter(current) || sumTime.isEqual(current) /*sumTime >= current*/)
                        return sumTime;
                    else {
//                        sumTime = sumTime.plusYears(this.interval.getYear()); //this.interval; this marks the next time of execution
//                        sumTime = sumTime.plusMonths(this.interval.getMonthValue());
//                        sumTime = sumTime.plusDays(this.interval.getDayOfMonth());
//                        sumTime = sumTime.plusHours(this.interval.getHour());
//                        sumTime = sumTime.plusMinutes(this.interval.getMinute());
                        sumTime = sumTime.plusDays(this.interval);
                        if (sumTime.isAfter(this.end)/*sumTime > this.end*/) // if the next time of execution is after the end
                            // return this.end;  In case is needed if the last time of execution always is the end time
                            // return -1;
                            return MINIMUM_TIME;
                    }
                }
                // return this.end;
                return MINIMUM_TIME;

            } else {
                if (this.time.isAfter(current)) // if the current time hasn't passed the task time
                    return this.time;
                else {
                    // return -1;
                    return MINIMUM_TIME;
                }
            }
        }
        else {
            // return -1;
            return MINIMUM_TIME;
        }
    }

    // Is the Task repetitive?
    public boolean isRepetitive() {
        return repetitive;
    }

    // Set if the Task is repetitive or not
    public void setRepetitive(boolean repetitive) {
        this.repetitive = repetitive;
    }

    @Override
    public boolean equals(Object o) {
        // if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task tasks = (Task) o;
        return time == tasks.time && start == tasks.start && end == tasks.end && interval == tasks.interval && active == tasks.active && repetitive == tasks.repetitive && Objects.equals(title, tasks.title);
    }



    @Override
    public int hashCode() {
        return Objects.hash(title, time, start, end, interval, active, repetitive);
    }

    @Override
    public String toString() {
        return "Tasks{" +
                "Task Title='" + title + '\'' +
                ", Repetitive = " + repetitive +
                ", Active = " + active +
                ", Time (Non Rep) = " + time +
                ", Start Time = " + start +
                ", End Time = " + end +
                ", Interval = " + interval +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
