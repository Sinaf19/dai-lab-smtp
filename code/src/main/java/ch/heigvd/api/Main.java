package ch.heigvd.api;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main class to run the program.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileManager fileManager = new FileManager();
        try {
            if (args.length < 3) {
                throw new IllegalArgumentException("You must provide 3 arguments : number of groups, message file path and victims file path");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return;
        }
        int numberOfGroups;
        try {
            numberOfGroups = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.out.println("The number of groups must be an integer");
            return;
        }


        ArrayList<Message> messages = fileManager.getMessagesFromFile(args[1]);
        ArrayList<Victim> victims = fileManager.getVictimsFromFile(args[2]);
        Prank prank = new Prank(numberOfGroups, victims, messages);
        prank.sendMails();

    }
}
