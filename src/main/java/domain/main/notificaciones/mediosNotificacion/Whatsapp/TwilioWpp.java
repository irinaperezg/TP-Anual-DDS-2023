package domain.main.notificaciones.mediosNotificacion.Whatsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class TwilioWpp implements WhatsappAdapter {

  public static final String ACCOUNT_SID = "gabyhaetino@gmail.com";
  public static final String AUTH_TOKEN = "gabyGenioCrackSetsi2002";

  public void mandar (String mensaje, String telefono) {
    /*Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
        new com.twilio.type.PhoneNumber("whatsapp:+5491134099892"),
        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
        mensaje).create();

    System.out.println(message.getSid());*/
  }

}
