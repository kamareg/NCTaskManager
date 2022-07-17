package ua.edu.sumdu.j2se.malikova.tasks.view;

import ua.edu.sumdu.j2se.malikova.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View{
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("What do you want to do? Please, choose a number:");
        System.out.println("1. Add new task.");
        System.out.println("2. Remove task.");
        System.out.println("3. Edit task.");
        System.out.println("4. Check your tasks.");
        System.out.println("5. Set necessary time range.");
        System.out.println("6. Exit.");

        int choice = 0;

        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return choice;
    }
}
