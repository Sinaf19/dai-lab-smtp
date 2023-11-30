package ch.heigvd.api;

import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Message messages;
        FileManager fileManager = new FileManager();
        messages = fileManager.getMessagesFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/main/java/config/vicitm.utf8");
    }
}
