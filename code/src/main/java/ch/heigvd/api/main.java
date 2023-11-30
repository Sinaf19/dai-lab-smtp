package ch.heigvd.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Message> messages;
        ArrayList<String> victims;
        FileManager fileManager = new FileManager();
        messages = fileManager.getMessagesFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/config/message.utf8");
/*        for (var message : messages) {
            System.out.println(message);
        }*/
        victims = fileManager.getVictimsFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/config/victim.utf8");
        Prank prank = new Prank(3, victims, messages);
        prank.print();

    }
}
