package ua.edu.sumdu.j2se.malikova.tasks;

import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            outputStream.writeInt(tasks.size());
            for (Task task : tasks) {
                outputStream.writeInt(task.getTitle().length());
                outputStream.writeUTF(task.getTitle());
                outputStream.writeBoolean(task.isActive());
                outputStream.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    outputStream.writeLong(task.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    outputStream.writeLong(task.getEndTime().toEpochSecond(ZoneOffset.UTC));
                } else {
                    outputStream.writeLong(task.getTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        Task task;
        try (DataInputStream inputStream = new DataInputStream(in)) {
            int size = inputStream.readInt();
            while (inputStream.available() > 0) {
                int titleLength = inputStream.readInt();
                String title = inputStream.readUTF();
                boolean isActive = inputStream.readBoolean();
                int repeatInterval = inputStream.readInt();
                if (repeatInterval != 0) {
                    LocalDateTime startTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    LocalDateTime endTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, startTime, endTime, repeatInterval);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    task = new Task(title, time);
                }
                task.setActive(isActive);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            TaskIO.write(tasks, fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            TaskIO.read(tasks, fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try {GsonBuilder gsonBuilder = new GsonBuilder();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {

    }

    public static void writeText(AbstractTaskList tasks, File file) {

    }

    public static void readText(AbstractTaskList tasks, File file) {

    }

}
