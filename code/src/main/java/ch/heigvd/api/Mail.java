package ch.heigvd.api;

import java.util.ArrayList;

public class Mail {

    private String from;

    private ArrayList<String> to;

    private String date;

    private Message message;






    public String getFrom() {
        return from;
    }

    public ArrayList<String> getTo() {
        return to;
    }


    public String getSubject() {
        return message.getSubject();
    }

    public String getBody() {
        return message.getBody();
    }

    public Mail(String from, ArrayList<String> to, Message message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }
}
