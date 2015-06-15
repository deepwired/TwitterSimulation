/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author deep
 */
public class MailSender {

    String d_email = "14030142007@sicsr.ac.in",
            d_password = "Anno1701",
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "",
            m_subject = "Welcome to SICSR Twitter";

    public boolean sendMail(String to, String username) {
        m_to = to;
        String m_text = "Welcome to SICSRTwitter'15\n"
                + "<br>\n"
                + "Station: Symbiosis Institute of Computer Studies and Research(SICSR)\n"
                + "<br>\n"
                + "Atur Center,Gokhale Cross Road,Model Colony,\n"
                + "<br>\n"
                + "Pune-411016.\n"
                + "<br>\n"
                + "<br>\n"
                + "<br>\n"
                + "Hope to see you enjoy our site.\n"
                + "<br>\n"
                + "<a href='http://localhost/TwitterConfirmation.php?a&username=" + username + "'>Click here to Activate your account</a>"
                + "Contact us on Ph: +9819979357\n"
                + "<br>\n"
                + "<br>\n"
                + "With warm regards,\n"
                + "<br>\n"
                + "Team SICSRTwitter\n"
                + "<br>\n"
                + "SICSR,Pune";
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            SMTPAuthenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            MimeMessage msg = new MimeMessage(session);
            msg.setContent(m_text, "text/html");
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
            Transport.send(msg);
            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
        }
        return false;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}
