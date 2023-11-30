package ch.heigvd.api;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class Prank {
   public Prank(int nbGroups, ArrayList<Victim> victims, ArrayList<Message> messages) {
      Random random = new Random(42);
      if(victims.size() < 2) {
         throw new InvalidParameterException("The file of victims must contain at least two email addresses");
      }

      int nbOfMails
   }
   private ArrayList<Group> groups;
   private Message message;


}
