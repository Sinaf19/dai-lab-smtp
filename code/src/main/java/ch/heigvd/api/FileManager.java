package ch.heigvd.api;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class allowing to read different config files and return their contents.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class FileManager {

    /* Regex that allows all characters permitted by RFC-5322,
    * restricts consecutive dots, the last and first character must not be dots,
    * the top level domain must only contain 2-6 letters */
    static private final String REGEX = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    
    static private final Pattern PATTERN = Pattern.compile(REGEX);

    static private final Charset CHARSET = StandardCharsets.UTF_8;

    static private final String SUBJECT = "Subject:";

    static private final String SEPARATOR = "$$";

    static private final String CRLF = "\r\n.\r\n";

    /**
     * Method to get all emails from the victim.utf8 file
     * @param fileName path to the config file vicitm.utf8
     * @return an ArrayList of String containing all the emails from the file
     * @throws RuntimeException
     */
    public ArrayList<Victim> getVictimsFromFile(String fileName) throws RuntimeException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), CHARSET))) {
            ArrayList<Victim> victims = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.matches()) {
                    victims.add(new Victim(line));
                } else {
                    /* Error handling */
                    throw new RuntimeException("Invalid email address format");
                }
            }
            return victims;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to get all messages from the file message.utf8
     * @param fileName path to the file message.utf8
     * @return an ArrayList of Message containing all the messages from the file
     * @throws FileNotFoundException
     */
    public ArrayList<Message> getMessagesFromFile(String fileName) throws FileNotFoundException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), CHARSET))) {

            ArrayList<Message> messages = new ArrayList<Message>();
            String line;
            String subject = "";
            String message = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith(SUBJECT)) {
                    subject = line.substring(SUBJECT.length() + 1);
                } else if (line.startsWith(SEPARATOR)) {
                    message += CRLF;
                } else {
                    message += (line + "\n");
                }

                if (!subject.isEmpty() && !message.isEmpty() && message.endsWith(CRLF)) {
                    messages.add(new Message(subject, message));
                    subject = "";
                    message = "";
                }
            }
            if (messages.get(0).getSubject().isEmpty()) {
                /* Error handling */
                throw new RuntimeException("Invalid message format");
            }
            return messages;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
