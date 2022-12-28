package com.beneficiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beneficiary.entity.Beneficiary;
import com.beneficiary.repository.BeneficiaryRepository;
import com.beneficiary.util.ApplicationConstants;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

	@Autowired
	BeneficiaryRepository beneRepo;

	@Override
	public Beneficiary createBeneficiary(Beneficiary beneficiary) {
		// TODO Auto-generated method stub
		beneficiary.setCurrentBalance(ApplicationConstants.Default_Account_Balance);
		return beneRepo.save(beneficiary);
	}

	@Override
	public Beneficiary getBeneficiaryByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		Beneficiary beneficiary = beneRepo.findByAccountNumber(accountNumber);
		return beneficiary;
	}

	@Override
	public List<Beneficiary> getAllBeneficiary() {
		// TODO Auto-generated method stub
		List<Beneficiary> beneficiaryList = beneRepo.findAll();
		return beneficiaryList;
	}

	@Override
	public Beneficiary updateBeneficiaryByAccountNumber(Beneficiary beneficiary) throws NullPointerException {
		// TODO Auto-generated method stub
		Beneficiary oldBeneficiary = getBeneficiaryByAccountNumber(beneficiary.getAccountNumber());
		if (oldBeneficiary == null)
			throw new NullPointerException("Beneficiary not found with account number");
		oldBeneficiary.setAccountStatus((beneficiary.getAccountStatus()));
//		oldBeneficiary.setCurrentBalance(beneficiary.getCurrentBalance());
		oldBeneficiary.setEmail(beneficiary.getEmail());
		oldBeneficiary.setIfscCode(beneficiary.getIfscCode());
		oldBeneficiary.setMaxTsfrLimit(beneficiary.getMaxTsfrLimit());
		oldBeneficiary.setName(beneficiary.getName());
		oldBeneficiary.setAddress(beneficiary.getAddress());
		
		Beneficiary updatedBeneficiary = beneRepo.save(oldBeneficiary);
		return updatedBeneficiary;
	}

	@Override
	public Boolean deleteBeneficiaryByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		Boolean result = false;
		Beneficiary beneficiary = getBeneficiaryByAccountNumber(accountNumber);
		if (!beneficiary.equals(null)) {
			beneRepo.deleteById(beneficiary.getBeneficiaryId());
			result = true;
		}
		return result;
	}

	@Override
	public Boolean updateAmountByAccountNumber(Long accountNumber, Double amount) {
		// TODO Auto-generated method stub
		Boolean result = false;
		Beneficiary beneficiary = getBeneficiaryByAccountNumber(accountNumber);
		if (!beneficiary.equals(null)) {
			Double balance = beneficiary.getCurrentBalance();
			beneficiary.setCurrentBalance(amount + balance);
			beneRepo.save(beneficiary);
			result = true;
		}
		return result;
	}

	@Override
	public int updateMaxTransferLimitByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		Boolean result = false;
		Beneficiary beneficiary = getBeneficiaryByAccountNumber(accountNumber);
		if (!beneficiary.equals(null)) {
			int transferLimit = beneficiary.getMaxTsfrLimit();
			beneficiary.setMaxTsfrLimit(transferLimit-1);
			beneficiary = beneRepo.save(beneficiary);	
		}
		return beneficiary.getMaxTsfrLimit();
	}

	@Override
	public List<Beneficiary> getAllBeneficiaryUnderRemitter(int customerId) {
		// TODO Auto-generated method stub
		return beneRepo.findAllByCustomerId(customerId);
	}

}
