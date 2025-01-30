package frba.utn.edu.ar.tp_dds.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private static final String SENDGRID_API_KEY = "secret";

    public void enviarEmail(String to, String subject, String body) {
        Email from = new Email("sistemaalimentario@gmail.com");
        Email recipient = new Email("gsiverio@frba.utn.edu.ar");
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, recipient, content);

        SendGrid sendGrid = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sendGrid.api(request);
        } catch (IOException ex) {
            System.err.println("Error enviando correo: " + ex.getMessage());
        }
    }
}

