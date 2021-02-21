package mx.edu.j2se.hernandez.tasks;

public class Tasks {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repetitive;

    // Constructor for non-repetitive tasks
    public Tasks(String title, int time) {
        this.title = title;
        this.time = time;
        this.interval = 0;
        this.active = false;
        this.repetitive = false;
    }

    // Constructor for repetitive tasks
    public Tasks(String title, int start, int end, int interval){
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
    public int getTime() {
        if (isRepetitive()) {
            return this.start;
        }
        return this.time;
    }

    // if the task was a repetitive one, it should become non-repetitive
    public void setTime(int time) {
        if (isRepetitive()){
            this.interval = 0;
            this.repetitive = false;
        } else {
            this.time = time;
        }
    }

    /* Methods for repetitive tasks */

    // if the task is a non-repetitive one, the method must return the start time of the execution;
    public int getStartTime() {
        if (isRepetitive()) {
            return this.start;
        } else {
            return this.time;
        }
    }

    // if the task is a non-repetitive one, the method must return the end time of the execution
    public int getEndTime() {
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
    public void setTime(int start, int end, int interval) {
        if (isRepetitive()) {
            this.start = start;
            this.end = end;
            this.interval = interval;
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.time = 0; //This makes the task non-repetitive
            this.repetitive = false; //This makes the task non-repetitive
        }
    }

    /*public boolean isRepeated() {
        if (this.interval > 0)
            return true;
        return false;
    }*/

    public int nextTimeAfter(int current) {
        if (isActive()) {
            if (isRepetitive()) {
                int sumTime = this.start; // the first time of execution is the start time
                while (this.end >= sumTime) {
                    if (sumTime >= current)
                        return sumTime;
                    else {
                        sumTime += this.interval; // this marks the next time of execution
                        if (sumTime > this.end) // if the next time of execution is after the end
                            // return this.end; // In case is needed if the last time of execution always is the end time
                            return -1;
                    }
                }
                return this.end;

            } else {
                if (this.time >= current) // if the current time hasn't passed the task time
                    return this.time;
                else {
                    return -1;
                }
            }
        }
        else {
            return -1;
        }
    }

    public boolean isRepetitive() {
        return repetitive;
    }

    public void setRepetitive(boolean repetitive) {
        this.repetitive = repetitive;
    }
}
