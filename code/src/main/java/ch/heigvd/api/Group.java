package ch.heigvd.api;

import java.util.ArrayList;

public class Group {

    Group(String sender, ArrayList<String> victims, Message message) {
        this.sender = sender;
        this.victims = victims;
        this.message = message;
    }
    private String sender;
    private ArrayList<String> victims;

    private Message message;

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
