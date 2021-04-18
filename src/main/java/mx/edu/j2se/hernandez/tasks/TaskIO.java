package mx.edu.j2se.hernandez.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.org.jvnet.staxex.Base64EncoderStream;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TaskIO {

    // Writes the tasks from the list to the stream in a binary format
/*
    Binary Format:
    - The number of tasks
    For every task:
    - The length of the name
    - Name of the task
    - Activity of task: 0 or 1
    - Repetition interval
    If repeated:        If not repeated:
    - Start Time        - Execution time
    - End Time

 */
    public static void write(AbstractTaskList tasks, OutputStream out){
        try {
            DataOutput outputStream = new DataOutputStream(out);
            outputStream.writeInt(tasks.size()); // Number of tasks
            for(Task task : tasks){ // For every task in the list
                //outputStream.write(task.getTitle().getBytes(StandardCharsets.UTF_8).length); // The length of the name
                //outputStream.write(task.getTitle().getBytes(StandardCharsets.UTF_8), 0, task.getTitle().getBytes(StandardCharsets.UTF_8).length); // The
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                if (task.isActive()) {
                    outputStream.writeInt(1); // If it's active, write 1
                } else {
                    outputStream.writeInt(0); // If it's not, write 0
                }

                if (task.isRepetitive()) {
                    outputStream.writeInt(task.getRepeatInterval()); // Repetition Interval
                    outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC)); // Start Time
                    System.out.println("Value of epoch of start time of " + task.getTitle() + ": " + task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC)); // End Time
                    System.out.println("Value of epoch of end time of " + task.getTitle() + ": " + task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                } else {
                    outputStream.writeInt(0); // Repetition Interval
                    outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC)); // Execution Time
                }

                out = (OutputStream) outputStream;

            }
//            out.write(tasks.size()); // Number of tasks
//            for(Task task : tasks){ // For every task in te list
//                out.write();
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reads the tasks from the stream, to the current task list
/*
    Binary Format:
    - The number of tasks
    For every task:
    - The length of the name
    - Name of the task
    - Activity of task: 0 or 1
    - Repetition interval
    If repeated:        If not repeated:
    - Start Time        - Execution time
    - End Time

 */
    public static void read(AbstractTaskList tasks, InputStream in) {
        try{
            DataInput inputStream = new DataInputStream(in);
            int numberOfTasks = inputStream.readInt();
            for (int i = 0; i < numberOfTasks; i++) {
                int lengthName = inputStream.readInt();
                String taskName = inputStream.readUTF();
                boolean isActive = inputStream.readInt() == 1 ? true: false;
                int interval = inputStream.readInt();
                if (interval == 0){
                    long executionTime = inputStream.readLong();
                    LocalDateTime execTime = LocalDateTime.ofEpochSecond(executionTime,0,ZoneOffset.UTC);

                    Task newTask = new Task(taskName, execTime);
                    newTask.setActive(isActive);

                    tasks.add(newTask);
                } else {
                    long startTime = inputStream.readLong();
                    long endTime = inputStream.readLong();
                    LocalDateTime startT = LocalDateTime.ofEpochSecond(startTime,0,ZoneOffset.UTC);
                    LocalDateTime endT = LocalDateTime.ofEpochSecond(endTime,0,ZoneOffset.UTC);

                    Task newTask = new Task(taskName, startT, endT, interval);
                    newTask.setActive(isActive);

                    tasks.add(newTask);
                }


            in = (InputStream) inputStream;

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Writes tasks from the list to the file
    public static void writeBinary(AbstractTaskList tasks, File file){
        try {
            OutputStream out = new FileOutputStream(file);
            TaskIO.write(tasks, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // Reads tasks from the file to the task list
    public static void readBinary(AbstractTaskList tasks, File file){
        try {
            InputStream in = new FileInputStream(file);
            TaskIO.read(tasks, in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Writes tasks from the list to the stream in the JSON format
    public static void write(AbstractTaskList tasks, Writer out) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            for (Task task:
//                 tasks) {
//                System.out.println("Printing current task on write JSON method: " + task);
//                gson.toJson(task, out);
//            }
            // Type listType = new TypeToken<ArrayTaskList>(){}.getType();
            System.out.println("Task List to be serialized: " + tasks);
            gson.toJson(tasks, out);
            out.close();


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Reads the task from the stream to the list
    public static void read(AbstractTaskList tasks, Reader in) {
        try {
            Gson gson = new Gson();
            //Type listType = new TypeToken<AbstractTaskList>(){}.getType();
            // We get the JSON for both types of list
            BufferedReader bReader = new BufferedReader(in);
            bReader.mark(10000000);
            AbstractTaskList newArrayTaskList = new ArrayTaskList();
            newArrayTaskList = gson.fromJson(bReader, newArrayTaskList.getClass()); // First, we try with one list
            bReader.reset(); // We restart the reader so we can try to put tje json object to the other list
            AbstractTaskList newLinkedTaskList = new LinkedTaskList();
            newLinkedTaskList = gson.fromJson(bReader, newLinkedTaskList.getClass()); // Then, we try to read with the other
            
            if (newArrayTaskList.size() > newLinkedTaskList.size()) {
                for (Task task:
                     newArrayTaskList) {
                    tasks.add(task);
                }
            } else {
                for (Task task:
                        newLinkedTaskList) {
                    tasks.add(task);
                }
            }


            System.out.println("Tasks retrieved from file (ARRAYLIST): " + newArrayTaskList);
            System.out.println("Tasks retrieved from file (LINKEDLIST): " + newLinkedTaskList);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reads tasks from the file
    // Writes tasks to file in JSON format
    public static void writeText(AbstractTaskList tasks, File file) {
        try {
//            OutputStream outStream = new FileOutputStream(file);
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outStream, StandardCharsets.UTF_8));
            Writer writer = new FileWriter(file.getName());
            TaskIO.write(tasks, writer);


        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void readText(AbstractTaskList tasks, File file) {
        try {
            Reader readerFromFile = new FileReader(file);
            TaskIO.read(tasks, readerFromFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
