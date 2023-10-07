package com.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HDFCRequest {
	
	private String your_bank_account_number;

	private String account_number;

	private String account_holder_name;

	private String mobile_number;

	private String ifsc_code;

	private Double balance;

	private String bank_name;

}
