package ch.heigvd.api;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Class representing a SMTP Client. It will send the prank to a selected group.
 *
 * @author Quentin Surdez
 * @author Rachel Tranchida
 */
public class SmtpClient {
    private static final String EOL = "\r\n";
    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 1025;

    /**
     * Send mail to the SMTP server
     * @param mail
     */
    public static void sendMail(Mail mail)  {

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
            out.write("EHLO example.com" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);

            out.write("MAIL FROM: <" + mail.getFrom() + ">" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);

            for (var to : mail.getTo()) {
                out.write("RCPT TO: <" + to + ">" + EOL);
                out.flush();
                response = in.readLine();
                System.out.println(response);
            }

            out.write("DATA" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);
            out.write("Subject:" + mail.getSubject() + EOL);
            out.write("From: " + mail.getFrom() + EOL);
            out.write("To: ");
            StringBuilder recipients = new StringBuilder();

            Iterator<String> iterator = mail.getTo().iterator();
            while (iterator.hasNext()) {
                String victim = iterator.next();

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
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Exception " + e);
        }
    }
}
