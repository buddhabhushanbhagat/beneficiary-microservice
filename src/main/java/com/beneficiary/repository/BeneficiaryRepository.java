package com.beneficiary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beneficiary.entity.Beneficiary;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

	Beneficiary findByAccountNumber(Long accountNumber);

	List<Beneficiary> findAllByCustomerId(int customerId);
}
