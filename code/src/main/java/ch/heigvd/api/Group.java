package ch.heigvd.api;

import java.util.ArrayList;

/**
 * Class reprenting a group to send an email to.
 * This class will be used to construct the structure of the mail.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class Group {

    private final String sender;
    private final ArrayList<Victim> victims;

    private final Message message;

    Group(String sender, ArrayList<Victim> victims, Message message) {
        this.sender = sender;
        this.victims = victims;
        this.message = message;
    }

    /**
     * Getter for the sender attribute
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Getter for the victims attribute.
     * @return copy of victims
     */
    public ArrayList<Victim> getVictims() {
        return new ArrayList<Victim>(victims);
    }

    /**
     * Getter for the message attribute
     * @return message
     */
    public Message getMessage() {
        return message;
    }


}
