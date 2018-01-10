package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping(path = "/usersList",produces = "application/json")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PostMapping(path = "/adduser")
	public User createUser(@Valid @RequestBody User usuario) {
		return userRepository.save(usuario);
	}
	
	@GetMapping(path = "/usersList/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id")Integer userid){
		User usuario = userRepository.findOne(userid);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(usuario);
	}
	
	@PutMapping(path = "/update/{id}")
	public ResponseEntity<User> updateNote(@PathVariable(value = "id") Integer userId, @Valid @RequestBody User userDetails){
		User usuario = userRepository.findOne(userId);
	    if(usuario == null) {
	        return ResponseEntity.notFound().build();
	    }
	    usuario.setName(userDetails.getName());
	    usuario.setLastname(userDetails.getLastname());
	    usuario.setMail(userDetails.getMail());
	    usuario.setAge(userDetails.getAge());
	    usuario.setTelephone(userDetails.getTelephone());

	    User updatedUser = userRepository.save(usuario);
	    return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<User> deleteNote(@PathVariable(value = "id") Integer userId) {
	    User usuario = userRepository.findOne(userId);
	    if(usuario == null) {
	        return ResponseEntity.notFound().build();
	    }

	    userRepository.delete(usuario);
	    return ResponseEntity.ok().build();
	}
}
