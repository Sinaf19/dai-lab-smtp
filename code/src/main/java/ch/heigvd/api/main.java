package ch.heigvd.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Message> messages;
        ArrayList<String> victims;
        FileManager fileManager = new FileManager();
        messages = fileManager.getMessagesFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/main/java/config/vicitm.utf8");
    }
}
