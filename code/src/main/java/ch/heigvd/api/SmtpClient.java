package ch.heigvd.api;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class SmtpClient {
    private static final String EOL = "\r\n";
    private static final String SMTP_HOST = "localhost";
    private static final int SMTP_PORT = 25;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(SMTP_HOST, SMTP_PORT);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)
                );
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
                );
        ) {
            // Read the server's welcome message
            String response = in.readLine();
            System.out.println(response);

            // Send the EHLO command
            out.write("EHLO example.com" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the MAIL FROM command
            out.write("MAIL FROM: <sender@example.com>" + EOL);
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the RCPT TO command
            out.write("RCPT TO: <recipient@example.com>\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the DATA command
            out.write("DATA\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the email content
            out.write("Subject: Test Email\r\n");
            out.write("From: <sender@example.com>\r\n");
            out.write("To: <recipient@example.com>\r\n");
            out.write("\r\n");
            out.write(
                    "This is a test email sent using a raw SMTP client in Java.\r\n"
            );
            out.write("It can contain multiple lines.\r\n");
            out.write(
                    "To finish an email, it must have the character '.' on a single line.\r\n"
            );
            out.write(".\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);

            // Send the QUIT command
            out.write("QUIT\r\n");
            out.flush();
            response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void sendMail(Mail mail,  BufferedReader in, BufferedWriter out) throws IOException {
        String response;
        out.write("EHLO example.com\r\n");
        response = in.readLine();
        System.out.println(response);

        out.write("MAIL FROM: <" + mail.getFrom() + ">\r\n");
        out.flush();
        response = in.readLine();
        System.out.println(response);

        for(var to : mail.getTo()) {
            out.write("RCPT TO: <" + to.getMail() + ">" + EOL);
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
        out.write("To: <recipient@example.com>" + EOL);
        out.write("\r\n");


    }
}
