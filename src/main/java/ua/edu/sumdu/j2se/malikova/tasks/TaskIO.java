package ua.edu.sumdu.j2se.malikova.tasks;

import java.io.*;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {

        try {
           // OutputStream outputStream = new DataOutputStream(out);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);{
                objectOutputStream.writeObject(tasks);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*  AbstractTaskList list = tasks;
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new DataOutputStream(out));
            for (int i = 1000000; i>=0; i--) {
                outputStream.write(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    void read(AbstractTaskList tasks, InputStream in) throws IOException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);

            AbstractTaskList list = (AbstractTaskList) objectInputStream.readObject();
            list.getStream().forEach(tasks::add);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


     /*   FileInputStream inputStream = new FileInputStream("c:/data.txt");
        long sum = 0;

        while (inputStream.available() > 0) //пока остались непрочитанные байты
        {
            int data = inputStream.read(); //прочитать очередной байт
            sum += data; //добавить его к общей сумме
        }
        inputStream.close(); // закрываем поток

        System.out.println(sum); //выводим сумму на экран.*/
    }

    //DataInputStream(InputStream stream)

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
}
