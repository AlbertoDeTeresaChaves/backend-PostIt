package com.postIt.app.models;

public class Email {

	private String addressee;
	private String affair;
	private String message;
	
	public Email(String addressee, String affair, String message) {
		super();
		this.addressee = addressee;
		this.affair = affair;
		this.message = message;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getAffair() {
		return affair;
	}

	public void setAffair(String affair) {
		this.affair = affair;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
