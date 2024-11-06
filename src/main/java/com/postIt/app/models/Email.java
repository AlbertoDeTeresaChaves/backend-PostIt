package com.postIt.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Email {

	private String addressee;
	private String subject;
	private String message;
	
}
