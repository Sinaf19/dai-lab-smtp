package ch.heigvd.api;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents a prank. It contains a list of groups, each group contains a sender, a list of victims and a message.
 * The number of groups is given in the constructor, and the list of victims and messages are given in the constructor. For each group 2-5 email
 * adresses are chosen randomly from the list of victims. The first email address is the sender, and the others are the victims. The message is also chosen randomly.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class Prank {
   final static int MIN_EMAIL = 2;
   final static int MAX_EMAIL = 5;
   private final ArrayList<Group> groups = new ArrayList<>();

   /**
    * Constructor of the class Prank. It takes the number of groups, a list of victims and a list of messages.
    *
    * @param nbGroups the number of groups
    * @param victims the list of victims
    * @param messages the list of messages
    * @throws InvalidParameterException if the number of victims is less than MIN_EMAIL
    */
   public Prank(int nbGroups, ArrayList<String> victims, ArrayList<Message> messages) {

      Random random = new Random(42);
      int numberOfVictims = victims.size();
      if(numberOfVictims < MIN_EMAIL) {
         throw new InvalidParameterException("There must be at least " + MIN_EMAIL + " victims.");
      }

      for(int i = 0; i < nbGroups; i++) {
         // choose MIN_EMAIL to MAX_EMAIL email randomly but at most victims number
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

      /**
       * This method sends the mails to all groups.
       */
   public void sendMails() {

      for(Group group : groups) {
         Mail mail = new Mail(group.getSender(), group.getVictims(), group.getMessage());
         SmtpClient.sendMail(mail);
      }

   }






}
