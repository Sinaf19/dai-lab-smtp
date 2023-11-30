package ch.heigvd.api;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {

    static private final Charset CHARSET = StandardCharsets.UTF_8;

    static private final String SUBJECT = "Subject:";

    static private final String SEPARATOR = "$$";

    static private final String CRLF = "\r\n.\r\n";

    public ArrayList<String> getVictimsFromFile(String fileName) throws RuntimeException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), CHARSET))) {
            ArrayList<String> victims = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.matches()) {
                    victims.add(line);
                } else {
                    /* Error handling */
                    System.out.println("Invalid email address");
                }
            }
            return victims;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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

                /* Ce if va poser problème je pense ... */
                if (!subject.isEmpty() && !message.isEmpty() && message.endsWith(CRLF)) {
                    messages.add(new Message(subject, message));
                }
            }
            return messages;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
