package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.producer.HDFCProducer;
import com.request.HDFCRequest;

@RestController
public class Send_Money_From_HDFC {


	@Autowired
	private HDFCProducer producer;
	
	@PostMapping("/sendMoneyFromHDFC")
	public ResponseEntity<String>  sendMoney(@RequestBody HDFCRequest request){
		
		String bank_name = request.getBank_name();
		
		if(bank_name.equals("axis")) {
			producer.send_to_axis(request);
		}
		
		if(bank_name.equals("icici")) {
			producer.send_to_icici(request);
		}
		
		if(bank_name.equals("sbi")) {
			producer.send_to_sbi(request);
		}
		
		return new ResponseEntity<>("Money send from Account Number :"+request.getYour_bank_account_number(),HttpStatus.OK);
	}
}
