package ua.edu.sumdu.j2se.malikova.tasks.model;

import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * A class containing the input request method.
 */
public class Input {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String input;
    public final Logger logger = Logger.getLogger(Input.class);
    public String setInput(){
        try {
            input = reader.readLine();
        } catch (IOException e) {
            logger.error("Input exception: ", e);
        }
        return input;
    }
}
