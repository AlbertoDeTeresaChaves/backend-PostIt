package com.postIt.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postIt.app.models.Email;
import com.postIt.app.services.EmailServiceImpl;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping()
public class EmailController {

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@PostMapping("/send-email")
	private ResponseEntity<String> sendEmail(@RequestBody Email email) throws MessagingException{
		emailServiceImpl.sendMail(email);
		
		return new ResponseEntity<>("Correo enviado exitosamente",HttpStatus.OK);
	}
}
