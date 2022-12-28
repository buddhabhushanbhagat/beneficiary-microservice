package com.beneficiary.exception;

import lombok.Data;

@Data
public class BeneficiaryAlreadyyExistException extends RuntimeException {
	private String message;
	public BeneficiaryAlreadyyExistException(String string) {
		// TODO Auto-generated constructor stub
		this.message=string;
	}

}
