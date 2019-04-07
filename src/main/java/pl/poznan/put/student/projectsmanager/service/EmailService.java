package pl.poznan.put.student.projectsmanager.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.URLDecoder;
import java.util.Properties;

public class EmailService {
	
	private static Properties mailConfig;
	private static String email;
	private static String password;
	private static Authenticator authenticator;
	
	private static EmailService instance = new EmailService();
	
	private EmailService() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String propsPath = rootPath + "mail.properties";
		try {
			propsPath = URLDecoder.decode(propsPath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UTF-8 unsupported encoding?!");
		}
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(propsPath));
		} catch (IOException e) {
			throw new RuntimeException("Email config file not found!");
		}
		
		mailConfig = new Properties();
		mailConfig.put("mail.transport.protocol", props.getProperty("mail.transport.protocol"));
		mailConfig.put("mail.smtp.host", props.getProperty("mail.smtp.host"));
		mailConfig.put("mail.smtp.port", props.getProperty("mail.smtp.port"));
		mailConfig.put("mail.smtp.auth", props.getProperty("mail.smtp.auth"));
		mailConfig.put("mail.smtp.starttls.enable", props.getProperty("mail.smtp.starttls.enable"));

		email = props.getProperty("email.login");
		password = props.getProperty("email.password");
		
		authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		};
	}
	
	public void sendEmail(String address, String subject, String content) throws MessagingException {
		Session session = Session.getDefaultInstance(mailConfig, authenticator);
		
		MimeMessage message = new MimeMessage(session);
		message.setSubject(subject);
		message.setContent(content, "text/html; charset=utf-8");
		message.setFrom(email);
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
		Transport.send(message);
	}
	
	public static EmailService getInstance() {
		if(instance == null)
			instance = new EmailService();
		return instance;
	}
}
