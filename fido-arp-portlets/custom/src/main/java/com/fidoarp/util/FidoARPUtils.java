package com.fidoarp.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.portlet.PortletProps;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class FidoARPUtils {

    private static Log log = LogFactoryUtil.getLog(FidoARPUtils.class);

    public static Boolean sendMail(String from, String toMail, String subject, String body) {
        boolean sendMail = false;

        String host = PortletProps.get("send.mail.host");
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            message.setSubject(subject);

            message.setText(body);

            // Send message
            Transport.send(message);
            sendMail = true;

            log.info("Sent message successfully..to .." + toMail);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
        return sendMail;
    }

    /* fileProperties = "/i18n/Resources" or "/i18n/.../Resources"
     *  enc ="UTF-8" or .....
     */
    public ResourceBundle getResourceBundle(String fileProperties, Locale lc, String enc) {
        ResourceBundle rb = null;
        String fileSfx = ".properties";
        if (!lc.toString().startsWith("en_")) {
            fileProperties = fileProperties + "_" + lc.toString();
        }
        fileProperties = fileProperties + fileSfx;
        InputStream in = getClass().getResourceAsStream(fileProperties);
        if (in != null) {

            try {
                rb = new PropertyResourceBundle(new InputStreamReader(in, enc));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rb;
    }

}