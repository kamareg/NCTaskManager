package ua.edu.sumdu.j2se.malikova.tasks;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TaskIO {
    public static void write (AbstractTaskList tasks, OutputStream out) {
        try {
            DataOutputStream outputStream = new DataOutputStream(out);
            outputStream.writeInt(tasks.size());
            for (Task task: tasks) {
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
    void read(AbstractTaskList tasks, InputStream in) {
        try {
            DataInputStream inputStream = new DataInputStream(in);
            int size = inputStream.readInt();
            while (inputStream.available() > 0) {
                int titleLength = inputStream.readInt();
                String title = inputStream.readUTF();
                Boolean isActive = inputStream.readBoolean();
                int repeatInterval = inputStream.readInt();
                if (repeatInterval!=0) {
                    LocalDateTime startTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                    LocalDateTime endTime = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                } else {
                    LocalDateTime time = LocalDateTime.ofEpochSecond(inputStream.readLong(), 0, ZoneOffset.UTC);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void writeBinary(AbstractTaskList tasks, File file) {

    }

    void readBinary(AbstractTaskList tasks, File file) {

    }

    void write(AbstractTaskList tasks, Writer out) {

    }

    void read(AbstractTaskList tasks, Reader in) {

    }

    void writeText(AbstractTaskList tasks, File file) {

    }

    void readText(AbstractTaskList tasks, File file) {

    }
     /*   FileInputStream inputStream = new FileInputStream("c:/data.txt");
        long sum = 0;

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int data = inputStream.read(); //прочитать очередной байт
            sum += data; //добавить его к общей сумме
        }
        inputStream.close(); // закрываем поток

        System.out.println(sum); //выводим сумму на экран.
    }*/
}
