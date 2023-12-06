package ch.heigvd.api;

import java.util.ArrayList;

/**
 * Class representing an email.
 * All its attributes are a component of an email.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class Mail {

    private String from;

    private ArrayList<String> to;

    private String date;

    private Message message;

    public Mail(String from, ArrayList<String> to, Message message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    /**
     * Getter for the form attribute
     * @return form
     */
    public String getFrom() {
        return from;
    }

    /**
     * Getter for the to attribute
     * @return copy of to
     */
    public ArrayList<String> getTo() {
       return new ArrayList<>(to);
    }

    /**
     * Getter for the subject attribute
     * @return subject from message
     */
    public String getSubject() {
        return message.getSubject();
    }

    /**
     * Getter for the body attribute
     * @return body from message
     */
    public String getBody() {
        return message.getBody();
    }


}
