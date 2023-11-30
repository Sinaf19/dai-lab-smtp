package ch.heigvd.api;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Prank {
   final static int MIN_EMAIL = 2;
   final static int MAX_EMAIL = 5;
   private ArrayList<Group> groups = new ArrayList<>();
   public Prank(int nbGroups, ArrayList<String> victims, ArrayList<Message> messages) {

      Random random = new Random(42);
      int numberOfVictims = victims.size();
      if(numberOfVictims < MIN_EMAIL) {
         throw new InvalidParameterException("The file of victims must contain at least two email addresses");
      }
      // choose MIN_EMAIL to MAX_EMAIL email randomly but at most victims number
      for(int i = 0; i < nbGroups; i++) {
         int nbOfMails = Math.min(random.nextInt(MIN_EMAIL, MAX_EMAIL), numberOfVictims);
         ArrayList<String> groupVictims = new ArrayList<>();
         Collections.shuffle(victims);
         String sender = victims.get(0);
         for(int j = 1; j < nbOfMails; j++) {
            groupVictims.add(victims.get(j));
         }

          this.groups.add(new Group(sender, groupVictims, messages.get(random.nextInt(messages.size()))));
      }

   }

   public void print() {
      for(int i = 0; i < groups.size(); i++) {
         System.out.println("Group " + i);
         System.out.println("Sender :" + groups.get(i).getSender());
         System.out.println("Victims :");
         System.out.println(groups.get(i).getVictims());
         System.out.println("Message :");
         System.out.println(groups.get(i).getMessage());
      }

   }


}
