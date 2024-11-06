package com.postIt.app.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = -612060330701344066L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=50)
	private String username;
	
	@Column(unique=true, length=50,nullable=false)
	private String email;
	
	private String password;
	
	private Boolean enabled = false;

	
	
	
}
