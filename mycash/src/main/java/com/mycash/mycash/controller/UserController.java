package com.mycash.mycash.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycash.mycash.model.User;
import com.mycash.mycash.repository.UserRepository;

@RestController
@RequestMapping({"/user"})
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@RequestMapping("/login")
	@GetMapping
//	http://localhost:8095/login
	public String login(HttpServletRequest request){
		String token = request.getHeader("Autorization")
				.substring("Basic".length()).trim();
		return token;
	}
	
//	http://localhost:8095/user
	public List findAll() {
		return repository.findAll();
	}
	
@GetMapping(value = "{id}")
//  http://localhost:8095/user/{id}
	public ResponseEntity<?> findById(@PathVariable long id) {
		return repository.findById(id)
			.map(record -> ResponseEntity.ok().body(record))
			.orElse(ResponseEntity.notFound().build());
}

@PostMapping
@PreAuthorize("hasRole('Admin')")
// http://localhost:8095/user
public User create(@RequestBody User user) {
	user.setPassword(criptografarSenha(user.getPassword()));
	return repository.save(user);
}

@PutMapping(value = "{id}")
@PreAuthorize("hasRole('Admin')")
//http://localhost:8095/user/{id}
public ResponseEntity update(@PathVariable long id, @RequestBody User user){
    return repository.findById(id)
    		.map(record -> {
    			record.setUsername(user.getUsername());
    			record.setPassword(criptografarSenha(user.getPassword()));
    			record.setAdmin(user.isAdmin());
    			User update = repository.save(record);
    			return ResponseEntity.ok().body(update);
    		}).orElse(ResponseEntity.notFound().build());
    }

@DeleteMapping(path = {"/{id}"})
@PreAuthorize("hasRole('Admin')")
//http://localhost:8095/user/{id}
public ResponseEntity<?> delete (@PathVariable long id){
	return repository.findById(id)
			.map(record -> {
				repository.deleteById(id);
				return ResponseEntity.ok().body("Deletado com Sucesso");
			}).orElse(ResponseEntity.notFound().build());
}

//Criptografar senha
private String criptografarSenha(String password){
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	String passwordParaCriptografar = passwordEncoder.encode(password);
	return passwordParaCriptografar;
	}

}




