package com.postIt.app.services;

import com.postIt.app.models.Email;

import jakarta.mail.MessagingException;

public interface EmailService {
	
	public void sendMail(Email email) throws MessagingException;
}
