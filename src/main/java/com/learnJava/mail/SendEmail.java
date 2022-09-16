package com.learnJava.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Hello");
		
		FileTester mailText=new FileTester();
		
		Properties prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\mail\\src\\main\\java\\config.properties");
		
		prop.load(fis);
		
		final String username=prop.getProperty("userID");
		final String password=prop.getProperty("passcode");
		final String recipient=prop.getProperty("recipient");
		final String subject=prop.getProperty("subject");
		
		final String[] recipients=recipient.split(",");
		
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		 session.setDebug(true);
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			for (String to : recipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			message.setSubject(subject);
			message.setText(mailText.fileConfirmer());
			System.out.println("working");
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
