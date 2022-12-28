package com.beneficiary.exception;

import lombok.Data;

@Data
public class BeneficiaryCreationException extends RuntimeException {
	private String message;

	public BeneficiaryCreationException(String string) {
		this.message = string;
	}
}
