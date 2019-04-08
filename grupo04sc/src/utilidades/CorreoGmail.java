package utilidades;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class CorreoGmail {

    public CorreoGmail() {

    }

    public Mensaje obtenerPrimerMensaje()
    {
        // Se obtiene la Session
        Properties prop = new Properties();
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", "995");
        prop.setProperty("mail.pop3.socketFactory.port", "995");
        Session sesion = Session.getInstance(prop);
        // sesion.setDebug(true);

        try
        {
            Store store = sesion.getStore("pop3");
            store.connect("pop.gmail.com", "tecnowebgrupo04@gmail.com", "grupo04grupo04");
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            // Se obtienen los mensajes.
            Message[] messages = folder.getMessages();
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
            folder.close(true);
            store.close();
            
            
            
            if (sw) return mensaje;
            else return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void obternerMensajes(){
        // Se obtiene la Session
        Properties prop = new Properties();
        prop.setProperty("mail.pop3.starttls.enable", "false");
        prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.pop3.socketFactory.fallback", "false");
        prop.setProperty("mail.pop3.port", "995");
        prop.setProperty("mail.pop3.socketFactory.port", "995");
        Session sesion = Session.getInstance(prop);
        // sesion.setDebug(true);

        try
        {
            Store store = sesion.getStore("pop3");
            store.connect("pop.gmail.com", "tecnowebgrupo04@gmail.com", "grupo04grupo04");
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            // Se obtienen los mensajes.
            Message[] messages = folder.getMessages();

            Mensaje mensaje = new Mensaje();
            if (messages.length > 0){
                
                String correo = getCuenta(""+messages[0].getFrom()[0]);
                mensaje.setCuenta(correo);
                mensaje.setAsunto(messages[0].getSubject());
                messages[0].setFlag(Flags.Flag.DELETED, true);
                
                System.out.println(mensaje.getCuenta());
                System.out.println(mensaje.getAsunto());
            }else{
                System.out.println("No hay mensajes");
            }
            
            

            folder.close(true);
            store.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
    
    
    public void enviar(Mensaje mensaje){
        try{
            String host ="smtp.gmail.com" ;
            String user = "tecnowebgrupo04@gmail.com";
            String pass = "grupo04grupo04";
            String to = mensaje.getCuenta();
            String from = "tecnowebgrupo04@gmail.com";
            String subject = mensaje.getAsunto();
            String messageText = mensaje.getMensaje();
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getInstance(props);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println("Error al enviar mensaje: " + ex);
        }
    }
    
    
    public static void main(String[] args) {
        CorreoGmail c = new CorreoGmail();
        c.obternerMensajes();
    }
    

}
