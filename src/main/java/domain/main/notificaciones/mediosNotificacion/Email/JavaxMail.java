package domain.main.notificaciones.mediosNotificacion.Email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JavaxMail implements EmailAdapter{
  public void mandar(String mensaje, String email) {
    String from = "jcarriquirycastro@frba.utn.edu.ar"; // remitente
    String subject = "Sistema de Apoyo a Comunidades con Movilidad Reducida";

    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props);

    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
      message.setSubject(subject);
      message.setText(mensaje);

      Transport.send(message);
      System.out.println("Se mando el email exitosamente.");
    } catch (MessagingException e) {
      e.printStackTrace();
    }

  }
}
