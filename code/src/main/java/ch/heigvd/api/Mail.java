package ch.heigvd.api;

import java.util.ArrayList;

public class Mail {

    private String from;

    private ArrayList<Victim> to;

    private String date;

    private Message message;

    private Group groupe;

    private String sender;

    public String getFrom() {
        return from;
    }

    public ArrayList<Victim> getTo() {
        return to;
    }
    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return message.getSubject();
    }

    public String getBody() {
        return message.getBody();
    }

    public Mail(String from, ArrayList<Victim> to, Message message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}
