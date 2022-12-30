package com.beneficiary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.beneficiary.entity.Beneficiary;
import com.beneficiary.exception.BeneficiaryAlreadyyExistException;
import com.beneficiary.exception.BeneficiaryCreationException;
import com.beneficiary.exception.BeneficiaryNotFoundException;
import com.beneficiary.exception.RemitterLimitEndedException;
import com.beneficiary.service.BeneficiaryService;
import com.beneficiary.util.ApplicationConstants;

@RestController
@CrossOrigin(value = "http://localhost:4200/")
@RequestMapping("/beneficiary")
public class BeneficiaryController {

	@Autowired
	BeneficiaryService beneService;

	@Autowired
	RestTemplate restTemplate;

	@PostMapping("")
	public ResponseEntity<Beneficiary> createBeneficiary(@RequestBody Beneficiary beneficiary) {
		Boolean result = false;
		Beneficiary beneDetails = beneService.getBeneficiaryByAccountNumber(beneficiary.getAccountNumber());
		if (beneDetails != null)
			throw new BeneficiaryAlreadyyExistException(
					"Beneficiary already found with account Number " + beneficiary.getAccountNumber());
		result = restTemplate.exchange(ApplicationConstants.UPDATE_MAX_LIMIT_URL + beneficiary.getCustomerId(),
				HttpMethod.PUT, null, Boolean.class).getBody();
		if (!result)
			throw new RemitterLimitEndedException("limit for beneficiary creation ended for remitter:" + beneficiary.getCustomerId());
		beneDetails = beneService.createBeneficiary(beneficiary);
		if (beneDetails == null)
			throw new BeneficiaryCreationException("Beneficiary creation failed");
		return ResponseEntity.status(HttpStatus.CREATED).body(beneDetails);
	}

	@GetMapping("/accountnumber/{accountNumber}")
	public ResponseEntity<Beneficiary> searchBeneficiaryByAccountNumber(@PathVariable Long accountNumber) {
		Beneficiary beneDetails = beneService.getBeneficiaryByAccountNumber(accountNumber);
		if (beneDetails == null)
			throw new BeneficiaryNotFoundException("Beneficiary not found for accountNo:" + accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(beneDetails);
	}

	@GetMapping("")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiary() {
		List<Beneficiary> beneDetailsList = beneService.getAllBeneficiary();
		return ResponseEntity.status(HttpStatus.OK).body(beneDetailsList);
	}
	@GetMapping("/remitter/{customerId}")
	public ResponseEntity<List<Beneficiary>> getAllBeneficiaryUnderRemitter(@PathVariable int customerId) {
		List<Beneficiary> beneDetailsList = beneService.getAllBeneficiaryUnderRemitter(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(beneDetailsList);
	}

	@PutMapping("/{accountNumber}")
	public ResponseEntity<Beneficiary> updateBeneficiaryByAccountNumber(@RequestBody Beneficiary beneficiary,
			@PathVariable Long accountNumber) {
		System.out.println(beneficiary);
		beneficiary.setAccountNumber(accountNumber);
		Beneficiary updatedBeneficiary = beneService.updateBeneficiaryByAccountNumber(beneficiary);
		return ResponseEntity.status(HttpStatus.OK).body(updatedBeneficiary);
	}

	@PutMapping("/creditAmount/{accountNumber}")
	public ResponseEntity<Boolean> updateBeneficiarAmountyByAccountNumber(@PathVariable Long accountNumber,
			@RequestParam Double amount) {
		System.out.println("account no:" + accountNumber + " amount:" + amount);
		Boolean isCredited = beneService.updateAmountByAccountNumber(accountNumber, amount);
		return ResponseEntity.status(HttpStatus.OK).body(isCredited);
	}
	@PutMapping("/debitTransactionLimit/{accountNumber}")
	public ResponseEntity<Integer> updateBeneficiarMaxTransferLimitByAccountNumber(@PathVariable Long accountNumber) {
		int limit = beneService.updateMaxTransferLimitByAccountNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(limit);
	}

	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<Boolean> deleteBeneficiaryByAccountNumber(@PathVariable Long accountNumber) {
		Boolean isDataExists = beneService.deleteBeneficiaryByAccountNumber(accountNumber);
		if (!isDataExists)
			throw new NullPointerException("beneficiary not found with accountNo: " + accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body(isDataExists);
	}
}
