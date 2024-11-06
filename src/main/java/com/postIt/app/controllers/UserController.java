package com.postIt.app.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postIt.app.models.Email;
import com.postIt.app.models.User;
import com.postIt.app.services.EmailServiceImpl;
import com.postIt.app.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	  
	@Autowired
	private UserService userService;
	
    @Autowired
    private EmailServiceImpl emailServiceImpl;
	//Create an user
    
    @CrossOrigin(origins = "http://localhost:8080")
	@PostMapping
	public ResponseEntity<?> create (@RequestBody User user){
		
		if(userService.existsByEmail(user.getEmail())) {
			 return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo ya está en uso");
		}
		
		User savedUser = userService.save(user);
		
		try {
			
			Email wellcomeEmail = new Email(null, null, null);
			wellcomeEmail.setAddressee(savedUser.getEmail());
			wellcomeEmail.setSubject("Bienvenido a nuestra plataforma");
			wellcomeEmail.setMessage("Bienvenid@ " + savedUser.getUsername());
			
			emailServiceImpl.sendMail(wellcomeEmail);
		}catch(Exception e){

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al enviar el email " +  e);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
		
	}
	
	//Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read (@PathVariable(value = "id") Long userId){
		
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUser);
	}
	
	//Read all users
	@GetMapping
	public ResponseEntity<?> readAll (){
		
		return ResponseEntity.ok(userService.findAll());
	}
	
	//Update an user
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@RequestBody User user, @PathVariable(value = "id") Long userId){
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		oUser.get().setUsername(user.getUsername());
		oUser.get().setEmail(user.getEmail());
		oUser.get().setPassword(user.getPassword());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
		
	}
	
	//verificate an user
	@PutMapping("/verificate/{id}")
	public ResponseEntity<?> verificate(@PathVariable(value = "id") Long userId){
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
	
		oUser.get().setEnabled(true);

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(oUser.get()));
	}
	
	//Delete an user
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete (@PathVariable(value = "id") Long userId){
		
		Optional<User> oUser = userService.findById(userId);
		
		if(!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		userService.deleteById(userId);
		return ResponseEntity.ok().build();
	}
}
