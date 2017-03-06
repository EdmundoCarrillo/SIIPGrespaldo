/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.siipg.dao.util;

import com.ipn.mx.siipg.modelo.Usuario;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author jresendiz
 */
public class MailService {
    private static final Logger LOGGER = Logger.getLogger( MailService.class.getName() );
    private String DEFAULT_EMAIL = "";
    private String PASSWORD = "";

    private String SMTP_HOST = "";
    private String SSL_PORT = "465";
    private Properties mailProperties;    

    public MailService() {
        loadConfigurationFromEnvironment();
        setEmailConnectionProperties();
    }

    private void setEmailConnectionProperties() {
        mailProperties = new Properties();
        mailProperties.put("mail.transport.protocol", "smtp");
        mailProperties.setProperty("mail.smtps.host", SMTP_HOST);
        mailProperties.setProperty("mail.smtp.port", SSL_PORT);
        mailProperties.setProperty("mail.smtp.startssl.enable", "true");
        mailProperties.setProperty("mail.smtp.startssl.required", "true");
        mailProperties.setProperty("mail.smtp.ssl.enable", "true");
        mailProperties.setProperty("mail.smtps.auth", "true");
        mailProperties.setProperty("mail.defaultEncoding", "UTF-8");
    }

    private void loadConfigurationFromEnvironment() {
        String systemConfiguredEmail = System.getenv("SIIPG_EMAIL");
        String smtpConfiguredHost = System.getenv("SIIPG_SMTP_HOST");
        String configuredSSLPort = System.getenv("SIIPG_SSL_PORT");
        DEFAULT_EMAIL = systemConfiguredEmail != null ? systemConfiguredEmail : "devtesting.jresendiz@gmail.com";
        SMTP_HOST = smtpConfiguredHost != null ? smtpConfiguredHost : "smtp.gmail.com";
        SSL_PORT = configuredSSLPort != null ? configuredSSLPort : "465";
        PASSWORD = System.getenv("SIIPG_PASSWORD");
    }

    public void sendRecoveryPasswordEmail(Usuario tempUser) throws AddressException, MessagingException {
        Session session = Session.getDefaultInstance(mailProperties, null);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(tempUser.getEmail()));
        mimeMessage.setSubject("Restauración de contraseña");
        mimeMessage.setSender(new InternetAddress(DEFAULT_EMAIL));
        Object[] params = new Object[]{tempUser.getFullName(), tempUser.getId().getRfc(), tempUser.getPassword()};
        String body = MessageFormat.format(EmailTemplates.RECOVERY_PASSWORD_TEMPLATE, params);
        mimeMessage.setContent(body, "text/html");
        Transport transport = session.getTransport("smtp");
        transport.connect(SMTP_HOST, DEFAULT_EMAIL, PASSWORD);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        LOGGER.log(Level.INFO, "Email has been sent!");
    }    
}
