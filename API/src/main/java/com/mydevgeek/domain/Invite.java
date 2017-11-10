package com.mydevgeek.domain;

import com.mydevgeek.domain.User;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message;
import javax.activation.*;

public class Invite {
	
	private User rec;
	private User sender;
	
	private String message;
	private String to;
	private String from;
	private String password;
	
	private boolean success;
	
	/* CONSTRUCTORS */
	
	public Invite() {
		setDefaults();
	}
	
	public Invite(User r, User s, String msg, String from, String pw) {
		setDefaults();
		this.rec = r;
		this.sender = s;
		this.message = msg;
		this.from = from;
		this.password = pw;
		this.to = r.getEmail();
	}
	
	/* PUBLIC METHODS */
	
	public void send() {
		
		//make sure necessary fields are populated
		if(this.to.isEmpty() || this.from.isEmpty() || this.rec.getEmail() == null || this.password.isEmpty()) {
			System.out.println("Required fields are not sent to send email.");
			this.success = false;
			return;
		}
		
		// Set SSL FACTORY
	    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	    
	    // Get a Properties object
	    Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = this.from; 	//MUST TURN SETTING 'Allow Less Secure Apps: ON' in the gmail account
        final String password = this.password;
	    
	    try {
	    	
	    		Session session = Session.getDefaultInstance(props, 
                    new Authenticator(){
                       protected PasswordAuthentication getPasswordAuthentication() {
                          return new PasswordAuthentication(username, password);
                       }});
	    		
	    		// Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));
	         
	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         
	         // Set Subject: header field
	         message.setSubject("Accept Your invitation from " + this.sender.getFirstName());
	         
	         // Now set the actual message
	         message.setText(this.message);
	         
	         // Send message
	         Transport.send(message);
	         this.success = true;
	         System.out.println("Sent message from " + sender.getFirstName() + " to " + rec.getFirstName() + " successfully....");
	         
	    } catch (MessagingException mex) {
	         mex.printStackTrace();
	    }
	}
	
	public void addLine(String line) {
		this.message += line + "\n";
	}
	
	public User getReceiver() {
		return this.rec;
	}
	
	public void setReceiver(User r) {
		this.rec = r;
	}
	
	public User getSender() {
		return this.sender;
	}
	
	public void setSender(User s) {
		this.sender = s;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String m) {
		this.message = m;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public void setFrom(String f) {
		this.from = f;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public void setTo(String t) {
		this.to = t;
	}
	
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setPassword(String pw) {
		this.password = pw;
	}
	
	/* PRIVATE METHODS */
	
	private void setDefaults() {
		this.rec = new User();
		this.sender = new User();
		this.message = "";
		this.from = "";
		this.success = false;
	}
	
}
