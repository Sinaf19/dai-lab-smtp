package ch.heigvd.api;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileManager {

    private ArrayList<Victim> victims;

    private ArrayList<Message> messages;

    static private final Charset CHARSET = StandardCharsets.UTF_8;

    static private final String SUBJECT = "Subject:";

    static private final String SEPARATOR = "$$";

    static private final String CRLF = "\r\n.\r\n";

    void getVictimsFromFile(String fileName) throws RuntimeException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), CHARSET))) {
            String line;
            while ((line = reader.readLine()) != null) {
                victims.add(new Victim(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void getMessagesFromFile(String fileName) throws FileNotFoundException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), CHARSET))) {
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
                if (subject != null && !subject.isEmpty() && !message.isEmpty() && message.endsWith(CRLF)) {
                    messages.add(new Message(subject, message));
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
