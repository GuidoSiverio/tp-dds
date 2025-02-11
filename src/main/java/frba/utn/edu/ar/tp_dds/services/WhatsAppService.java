package frba.utn.edu.ar.tp_dds.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private static final String ACCOUNT_SID = "ACd2d80910e014a8155e4d0490ea1022b4";
    private static final String AUTH_TOKEN = "53d5b27c5bba7e6d40f49782273ef220";
    private static final String FROM_WHATSAPP_NUMBER = "whatsapp:+14155238886"; // Twilio Sandbox

    public WhatsAppService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void enviarWhatsApp(String to, String mensaje) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber(FROM_WHATSAPP_NUMBER),
                mensaje
        ).create();
        System.out.println("Mensaje enviado a " + to);
    }
}

