package com.mycash.mycash.controller;

import java.util.List;

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

import com.mycash.mycash.model.UserInformation;
import com.mycash.mycash.repository.UserInformationRepository;

@CrossOrigin()
@RestController
@RequestMapping({"/userinfo"})
public class UserInformationController {

	@Autowired
	private UserInformationRepository repository;
	
	@GetMapping
//	http://localhost:8095/userInformation
	public List findAll() {
		return repository.findAll();
}
@GetMapping(value = "{id}")
//  http://localhost:8095/userInformation/{id}
	public ResponseEntity<?> findById(@PathVariable long id) {
		return repository.findById(id)
			.map(record -> ResponseEntity.ok().body(record))
			.orElse(ResponseEntity.notFound().build());
}

@PostMapping
// http://localhost:8095/userInformation
public UserInformation create(@RequestBody UserInformation userInformation) {
	return repository.save(userInformation);
}

@PutMapping(value = "{id}")
//http://localhost:8095/userInformation/{id}
//public ResponseEntity update(@PathVariable long id, @RequestBody UserInformation userinfo) {
//	return repository.findById(id)
//			.map(record -> {
////				record.setId_autentication(userinfo.());
//				record.setNome(userinfo.getNome());
//				record.setTelefone(userinfo.getTelefone());					
//				UserInformation update = repository.save(record);
//				return ResponseEntity.ok().body(update);
//			}).orElse(ResponseEntity.notFound().build());		
//}
	
@DeleteMapping(path = {"/{id}"})
//http://localhost:8095/userInformation/{id}
public ResponseEntity<?> delete (@PathVariable long id){
	return repository.findById(id)
			.map(record -> {
				repository.deleteById(id);
				return ResponseEntity.ok().body("Deletado com Sucesso");
			}).orElse(ResponseEntity.notFound().build());
}
}



