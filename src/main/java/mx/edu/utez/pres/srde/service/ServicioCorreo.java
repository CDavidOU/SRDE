package mx.edu.utez.pres.srde.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class ServicioCorreo {

    // Aqui va Gmail y la contraseña de 16 letras que te dio Google
    private static final String CORREO_REMITENTE = "20253ds043@utez.edu.mx";
    private static final String PASSWORD_REMITENTE = "cjlh mvnv ccsf oszn";

    public boolean enviarPinRecuperacion(String destinatario, String pin) {
        boolean enviado = false;

        // Configuración para conectarse a los servidores de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Autenticación con tu cuenta
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CORREO_REMITENTE, PASSWORD_REMITENTE);
            }
        });

        try {
            // Armamos el mensaje
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(CORREO_REMITENTE));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject("PIN de Recuperación - Sistema de Estadías UTEZ");

            // Diseñamos el cuerpo del correo en HTML para que se vea presentable
            String contenidoHTML = "<div style='font-family: Arial, sans-serif; max-width: 500px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
                    + "<h2 style='color: #002E60; text-align: center;'>Recuperación de Contraseña</h2>"
                    + "<p>Hola,</p>"
                    + "<p>Has solicitado restablecer tu contraseña en el Sistema de Estadías. Tu PIN de seguridad de 4 dígitos es:</p>"
                    + "<h1 style='text-align: center; color: #429983; letter-spacing: 5px;'>" + pin + "</h1>"
                    + "<p>Ingresa este código en la plataforma para elegir una nueva contraseña.</p>"
                    + "<p style='font-size: 12px; color: #999; text-align: center; margin-top: 30px;'>Si no solicitaste este cambio, ignora este correo por seguridad.</p>"
                    + "</div>";

            mensaje.setContent(contenidoHTML, "text/html; charset=utf-8");

            // Disparamos el correo por internet
            Transport.send(mensaje);
            enviado = true;

        } catch (MessagingException e) {
            System.err.println("Hubo un error al enviar el correo electrónico.");
            e.printStackTrace();
        }

        return enviado;
    }
}