package domain.main.notificaciones.mediosNotificacion.Email;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class JavaxMail implements EmailAdapter{
  public void mandar(String mensaje, String email) {
    String host = "sandbox.smtp.mailtrap.io"; // Host del servidor de correo
    int port = 2525; // Puerto del servidor de correo
    String username = "f3258c1802f6f0"; // Nombre de usuario del correo
    String password = "860559ef0fabec"; // Contraseña del correo
    String from = "jcarriquirycastro@frba.utn.edu.ar"; // remitente
    String subject = "Sistema de Apoyo a Comunidades con Movilidad Reducida";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);

    Authenticator auth = new Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    };

    Session session = Session.getInstance(props, auth);

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
