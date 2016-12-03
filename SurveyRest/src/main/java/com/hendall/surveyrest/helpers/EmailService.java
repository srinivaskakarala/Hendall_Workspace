package com.hendall.surveyrest.helpers;
 
 import java.io.PrintStream;
 import java.util.Properties;
 import javax.mail.Authenticator;
 import javax.mail.Message;
 import javax.mail.Message.RecipientType;
 import javax.mail.MessagingException;
 import javax.mail.PasswordAuthentication;
 import javax.mail.Session;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeMessage;
 
 public class EmailService
 {
	 private final String username = "AKIAJXWU7J4EBFCSBHVA";
	 private final String password = "AgbPgoNg3BFi8tXcDQOU/g5Dj/Ma+ePKvjD6Kq7jQv+0";
   
   public void sendEmail(String fromAddrs, String toAddrs, String subject, String emailMessage) {
	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
	props.put("mail.smtp.port", "587");
	Session session = Session.getInstance(props, new Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {
    	   return new PasswordAuthentication("AKIAJXWU7J4EBFCSBHVA", "AgbPgoNg3BFi8tXcDQOU/g5Dj/Ma+ePKvjD6Kq7jQv+0");
       }
     });
     try
     {
    	 Message message = new MimeMessage(session);
    	 message.setFrom(new InternetAddress(fromAddrs));
    	 message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddrs));
    	 message.setSubject(subject);
    	 message.setText(emailMessage);
    	 javax.mail.Transport.send(message);
    	 System.out.println("Done");
     }
     catch (MessagingException e) {
    	 e.printStackTrace();
     }
   }
 }