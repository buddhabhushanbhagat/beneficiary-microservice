package com.beneficiary.exception;

import lombok.Data;

@Data
public class RemitterLimitEndedException extends RuntimeException {
	private String message;

	public RemitterLimitEndedException(String string) {
		// TODO Auto-generated constructor stub
		this.message = string;
	}
}
