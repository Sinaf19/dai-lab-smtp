package ch.heigvd.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws FileNotFoundException {

        FileManager fileManager = new FileManager();
        ArrayList<String> victims = fileManager.getVictimsFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/config/victim.utf8");
        ArrayList<Message> messages = fileManager.getMessagesFromFile("/home/sainane/IdeaProjects/dai-lab-smtp/code/src/config/message.utf8");
        Prank prank = new Prank(Integer.parseInt(args[0]), victims, messages);
        prank.sendMails();

    }
}
