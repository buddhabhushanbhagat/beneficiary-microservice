package com.beneficiary.exception;

import lombok.Data;

@Data
public class BeneficiaryNotFoundException extends RuntimeException {
	private String message;
	public BeneficiaryNotFoundException(String string) {
		// TODO Auto-generated constructor stub
		this.message=string;
	}

}
