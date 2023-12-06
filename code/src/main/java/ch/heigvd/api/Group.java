package ch.heigvd.api;

import java.util.ArrayList;

public class Group {

    Group(String sender, ArrayList<String> victims, Message message) {
        this.sender = sender;
        this.victims = victims;
        this.message = message;
    }
    private final String sender;
    private final ArrayList<String> victims;

    private final Message message;

    public String getSender() {
        return sender;
    }

    public ArrayList<String> getVictims() {
        return victims;
    }

    public Message getMessage() {
        return message;
    }


}
