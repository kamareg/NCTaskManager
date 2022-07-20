package ua.edu.sumdu.j2se.malikova.tasks.model;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TaskIO {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd''HH:mm:ss.n");
    public static final Logger logger = Logger.getLogger(TaskIO.class);

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
            logger.error("RuntimeException", e);
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
            logger.error("RuntimeException", e);
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            TaskIO.write(tasks, fileOutputStream);
        } catch (IOException e) {
            logger.error("RuntimeException", e);
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            TaskIO.read(tasks, fileInputStream);
        } catch (IOException e) {
            logger.error("RuntimeException", e);
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try (JsonWriter jsonWriter = new JsonWriter(out)) {
            jsonWriter.beginObject();
            jsonWriter.name("Task list");
            jsonWriter.beginArray();
            for (Task task : tasks) {
                jsonWriter.beginObject();
                jsonWriter.name("title").value(task.getTitle());
                jsonWriter.name("activity").value(task.isActive());
                jsonWriter.name("interval").value(task.getRepeatInterval());
                if (task.isRepeated()) {
                    jsonWriter.name("start time").value(dtf.format(task.getStartTime()));
                    jsonWriter.name("end time").value(dtf.format(task.getEndTime()));
                } else {
                    jsonWriter.name("time").value(dtf.format(task.getTime()));
                }
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
        } catch (IOException e) {
            logger.error("RuntimeException", e);
        }
    }


    public static void read(AbstractTaskList tasks, Reader in) {
        Task task;
        try {
            Object obj = new JsonParser().parse(in);
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray array = (JsonArray) jsonObject.get("Task list");
            for (JsonElement jsonElement : array) {
                JsonObject taskFromArray = (JsonObject) jsonElement;
                String title = (String.valueOf(taskFromArray.get("title"))).replace("\"", "");
                boolean active = (taskFromArray.get("activity")).getAsBoolean();
                int interval = (taskFromArray.get("interval")).getAsInt();
                if (interval != 0) {
                    LocalDateTime startTime = LocalDateTime.parse(((taskFromArray.get("start time")).getAsString()), dtf);
                    LocalDateTime endTime = LocalDateTime.parse(((taskFromArray.get("end time")).getAsString()), dtf);
                    task = new Task(title, startTime, endTime, interval);
                } else {
                    LocalDateTime time = LocalDateTime.parse(((taskFromArray.get("time")).getAsString()), dtf);
                    task = new Task(title, time);
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (JsonIOException | JsonSyntaxException e) {
            logger.error("RuntimeException", e);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            TaskIO.write(tasks, fileWriter);
        } catch (IOException e) {
            logger.error("RuntimeException", e);
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (FileReader fileReader = new FileReader(file)) {
            TaskIO.read(tasks, fileReader);
        } catch (IOException e) {
            logger.error("RuntimeException", e);
        }
    }
}
