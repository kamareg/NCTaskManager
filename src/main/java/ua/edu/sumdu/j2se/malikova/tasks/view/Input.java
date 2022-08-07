package ua.edu.sumdu.j2se.malikova.tasks.view;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * A class containing the input request method.
 */
public class Input {
    private String input;
    public final Logger logger = Logger.getLogger(Input.class);
    public String setInput(){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            input = reader.readLine();
        } catch (IOException e) {
            logger.error("Input exception: ", e);
        }
        return input;
    }
}
