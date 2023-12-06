package ch.heigvd.api;

/**
 * Class representing a message.
 * It contains a body and a subject which are necessary to build an email.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class Message {
    private final String subject;
    private final String body;

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * Getter for the subject attribute
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Getter for the body attribute
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * Allows to represent in a String the Message class
     * @return String representing the Message class
     */
    public String toString() {
        return "Subject: " + this.subject + "\n" + this.body;
    }


}
