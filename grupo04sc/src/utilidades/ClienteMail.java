/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import config.VariablesConf;
import datos.daoimpl.UsuarioDAOImpl;

import java.util.Date;
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


    public Mensaje obtemerPrimerMensaje()
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

            Store store = emailSession.getStore("pop3");
            store.connect(host, user, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            Message[] messages = emailFolder.getMessages();
            boolean sw = true;
            Mensaje mensaje = new Mensaje();
            if (messages.length > 0){
                String correo = getCuenta(""+messages[0].getFrom()[0]);
                mensaje.setCuenta(correo);
                mensaje.setAsunto(messages[0].getSubject());
                messages[0].setFlag(Flags.Flag.DELETED, true);
            }else {
                sw = false;
            }

            // expunges the folder to remove messages which are marked deleted
            emailFolder.close(true);
            store.close();

            if (sw) return mensaje;
            else return null;

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
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

    private String getCuenta(String cadena) {
        if (cadena.contains("<")){
            String cad = "";
            for (char c : cadena.toCharArray()){
                if (c == '>')
                    break;
                cad = cad + c;
                if (c == '<')
                    cad = "";
            }
            return cad;
        }

        return cadena;

    }


}
