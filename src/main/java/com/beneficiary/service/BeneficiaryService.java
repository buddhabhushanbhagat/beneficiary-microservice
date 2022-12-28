package com.beneficiary.service;

import java.util.List;

import com.beneficiary.entity.Beneficiary;

public interface BeneficiaryService {
	public Beneficiary createBeneficiary(Beneficiary beneficiary);
	public Beneficiary getBeneficiaryByAccountNumber(Long accountNumber);
	public List<Beneficiary> getAllBeneficiary();
	public Beneficiary updateBeneficiaryByAccountNumber(Beneficiary beneficiary);
	public Boolean deleteBeneficiaryByAccountNumber(Long accountNumber);
	public Boolean updateAmountByAccountNumber(Long accountNumber, Double amount);
	public int updateMaxTransferLimitByAccountNumber(Long accountNumber);
	public List<Beneficiary> getAllBeneficiaryUnderRemitter(int customerId);
}
