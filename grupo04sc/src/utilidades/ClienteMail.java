/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import config.VariablesConf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author KAKU
 */
public class ClienteMail {
    private final String host;
    private final String cuenta;
    private final String user;
    private final String password;

    public ClienteMail() {
        host = VariablesConf.CORREO_HOST;
        cuenta = VariablesConf.CORREO_CUENTA;
        user = VariablesConf.CORREO_USUARIO;
        password = VariablesConf.CORREO_CONTRASENA;
    }


    public List<Mensaje> obtenerMensajes(){
        List<Mensaje> mensajes = new ArrayList<>();

        try {

            // connect to my pop3 inbox
            Properties properties = System.getProperties();
            Session session = Session.getDefaultInstance(properties);
            Store store = session.getStore("pop3");
            store.connect(host, user, password);
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // get the list of inbox messages
            Message[] messages = inbox.getMessages();

            if (messages.length == 0) System.out.println("No messages found.");

            for (int i = 0; i < messages.length; i++) {
                System.out.println("Message " + (i + 1));
                System.out.println("From : " + messages[i].getFrom()[0]);
                System.out.println("Subject : " + messages[i].getSubject());
                System.out.println("Sent Date : " + messages[i].getSentDate());
                System.out.println();

                Mensaje mensaje = new Mensaje();
                mensaje.setCuenta(getCuenta(""+messages[i].getFrom()[0]));
                mensaje.setAsunto(messages[i].getSubject());

                mensajes.add(mensaje);

            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mensajes;
    }

    private String getCuenta(String cadena) {
        String cuenta = "";
        if (cadena.contains("<")){
            for (char c : cadena.toCharArray()){
                if (c == '>')
                    break;
                cuenta = cuenta + c;
                if (c == '<')
                    cuenta = "";
            }
            return cuenta;
        }

        return cadena;

    }


    public boolean enviar(String destinatario, String asunto, String mensaje){
        try{
            String subject = asunto;
            String messageText = mensaje;
            boolean sessionDebug = false;

            Properties props = System.getProperties();



            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(cuenta));
            InternetAddress[] address = {new InternetAddress(destinatario)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport=mailSession.getTransport("smtp");
            transport.connect();
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mensaje enviado satisfactoriamente.");
            return true;

        }catch(Exception e)
        {
            System.out.println(e);
        }

        return false;

    }




    public void delete()
    {
        try
        {
            // get the session object
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.pop3s.host", host);
            properties.put("mail.pop3s.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);
            // emailSession.setDebug(true);

            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3");

            store.connect(host, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                String subject = message.getSubject();
                System.out.print("Do you want to delete this message [y/n] ? ");
                String ans = reader.readLine();
                if ("Y".equals(ans) || "y".equals(ans)) {
                    // set the DELETE flag to true
                    message.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("Marked DELETE for message: " + subject);
                } else if ("n".equals(ans)) {
                    break;
                }
            }
            // expunges the folder to remove messages which are marked deleted
            emailFolder.close(true);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}
