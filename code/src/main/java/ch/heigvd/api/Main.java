package ch.heigvd.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileManager fileManager = new FileManager();

        ArrayList<String> victims = fileManager.getVictimsFromFile(args[1]);
        ArrayList<Message> messages = fileManager.getMessagesFromFile(args[2]);
        Prank prank = new Prank(Integer.parseInt(args[0]), victims, messages);
        prank.sendMails();

    }
}
