package ch.heigvd.api;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

public class SmtpClient {
    private static final String EOL = "\r\n";
    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 1025;

    public static void main(String[] args) {
        Message message = new Message("Sujet", "body");
        String a = "mail1@mail.com";
        String b = "mail2@mail.com";
        ArrayList<String> victims= new ArrayList<>();
        victims.add(a);
        victims.add(b);
        Mail mail = new Mail("test@gmaille.com", victims, message);
        sendMail(mail);
    }

    public static void sendMail(Mail mail)  {
        // TODO check server responses
        try (
                Socket socket = new Socket(SMTP_HOST, SMTP_PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
                );
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
                )
        ) {

            String response = in.readLine();
            System.out.println(response);
            out.write("EHLO example.com\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);

            out.write("MAIL FROM: <" + mail.getFrom() + ">\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);

            for (var to : mail.getTo()) {
                out.write("RCPT TO: <" + to + ">" + EOL);
                out.flush();
                response = in.readLine();
                System.out.println(response);
            }

            out.write("DATA\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);
            out.write("Subject:" + mail.getSubject() + EOL);
            out.write("From: <sender@example.com>" + EOL);
            out.write("To: ");
            StringBuilder recipients = new StringBuilder();

            Iterator<String> iterator = mail.getTo().iterator();
            while (iterator.hasNext()) {
                String victim = iterator.next();
                //Do stuff
                recipients.append(victim);
                if (iterator.hasNext()) {
                    recipients.append(", ");
                    //last name
                }
            }

            out.write(recipients + EOL);
            out.write(EOL);
            out.write(mail.getBody() + EOL);
            out.write("." + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the QUIT command
            out.write("QUIT" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }


    }
}
