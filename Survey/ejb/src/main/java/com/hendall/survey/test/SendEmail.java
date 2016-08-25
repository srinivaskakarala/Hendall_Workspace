package com.hendall.survey.test;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.*;

public class SendEmail {
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", "127.0.0.1");
		props.put("mail.smtp.port", "25");
		props.put("mail.debug", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("admin@specialtysurveys.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress("kalyan.pamula@hendall.com"));
		message.setSubject("Notification");
		message.setText("Successful!", "UTF-8"); // as "text/plain"
		message.setSentDate(new Date());
		Transport.send(message);
	}
}
