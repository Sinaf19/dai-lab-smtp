package ch.heigvd.api;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;

public class Prank {
   final static int MIN_EMAIL = 2;
   final static int MAX_EMAIL = 5;
   public Prank(int nbGroups, ArrayList<Victim> victims, ArrayList<Message> messages) {
      Random random = new Random(42);
      int numberOfVictims = victims.size();
      if(numberOfVictims < MIN_EMAIL) {
         throw new InvalidParameterException("The file of victims must contain at least two email addresses");
      }
      // choose MIN_EMAIL to MAX_EMAIL email randomly but at most victims number
      for(int i = 0; i < nbGroups; i++) {
         int nbOfMails = Math.max(random.nextInt(MIN_EMAIL, MAX_EMAIL), numberOfVictims);
         groups.add()
      }

   }
   private ArrayList<Group> groups;
   private Message message;


}
