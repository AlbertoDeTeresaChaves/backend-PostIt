package com.postIt.app.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.postIt.app.models.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

	private final JavaMailSender javaMailSender;
	private final TemplateEngine templateEngine;
	
	public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
		super();
		this.javaMailSender = javaMailSender;
		this.templateEngine = templateEngine;
	}
	
	@Override
	public void sendMail(Email email) throws MessagingException {
		try {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setTo(email.getAddressee());
		helper.setSubject(email.getSubject());
		
		Context context = new Context();
		context.setVariable("message", email.getMessage());
		String contentHTML = templateEngine.process("email",context);

		helper.setText(contentHTML, true);
		
		javaMailSender.send(message);
		
		}catch(Exception e){
			throw new RuntimeException("Error al enviar el email " + e.getMessage(), e);
		}
	}
	
}
