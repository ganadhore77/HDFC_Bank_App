package com.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "hdfc_bank")
public class HDFC_Entity {
	
	@Id
	private String account_number;
	
	private String account_holder_name;
		
	private String mobile_number;
	
	private String ifsc_code;
	
	private Double balance;
	
}

