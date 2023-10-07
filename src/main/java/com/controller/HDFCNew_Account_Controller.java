package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.HDFC_Entity;
import com.producer.HDFCProducer;
import com.repository.HDFCRepository;
import com.request.HDFCRequest;

@RestController
public class HDFCNew_Account_Controller {
	
	@Autowired
	private HDFCRepository axisRepository;
		
	@PostMapping("/addNewToHDFC")
	public ResponseEntity<String>  addNewAccout(@RequestBody HDFC_Entity entity){
		HDFC_Entity save = axisRepository.save(entity);
		System.out.println(save);
		return new ResponseEntity<>("Account Open succesfully",HttpStatus.OK);
	}
	
	
	
}
