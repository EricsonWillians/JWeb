package com.jwillians.jweb;

/*

JWeb - Request-based Java Web Framework
Copyright (C) <2015>  <Ericson Willians - JWillians>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>. 

*/

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

/**
 * A JWEmailSender is an object that wraps javax.mail in order to send emails easily.
 * @author EricsonWillians
 */
@SuppressWarnings("FieldMayBeFinal")
public class JWEmailSender {
    
    private final String host;
    private final String port;
    private final String from;
    private final String password;
    private final String to;
    private final Properties properties;
    private final Session session;
    private MimeMessage msg;

    /**
     * It sets up the JWEmailSender object with all the data necessary to send emails.
     * @param host The host, such as "smtp.gmail.com".
     * @param port The port, such as 587.
     * @param from Your email.
     * @param password The password used to authenticate your email.
     * @param to The destination email.
     */
    public JWEmailSender(String host, String port, String from, String password, String to) {
        this.host = host;
        this.port = port;
        this.from = from;
        this.password = password;
        this.to = to;
        properties = System.getProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.password", password);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        session = Session.getDefaultInstance(properties);
    }
    
    /**
     * Sends the email.
     * @param subject The subject of the email.
     * @param content The content of the email.
     */
    public void send(String subject, String content) {
        setMsg(new MimeMessage(session));
        try {
            getMsg().setFrom(new InternetAddress(from));
            getMsg().addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            getMsg().setSubject(subject);
            getMsg().setText(content);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(getMsg(), getMsg().getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            Logger.getLogger(JWEmailSender.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * Returns the MimeMessage object.
     * @return MimeMessage
     */
    public MimeMessage getMsg() {
        return msg;
    }

    /**
     * Sets the MimeMessage object.
     * @param msg MimeMessage object.
     */
    public void setMsg(MimeMessage msg) {
        this.msg = msg;
    }
    
}
