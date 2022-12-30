package com.beneficiary.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.beneficiary.entity.Beneficiary;
import com.beneficiary.repository.BeneficiaryRepository;

@ExtendWith(MockitoExtension.class)
class BeneficiaryServiceImplTest {
	@InjectMocks
	private BeneficiaryServiceImpl beneficiaryService;

	@Mock
	private BeneficiaryRepository beneficiaryRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateBeneficiary() {
		Beneficiary beneficiary = new Beneficiary(2,"asd","add","dhfee",Math.abs(new Random().nextLong()),"gdhs",3,78.8,4,new Date(),new Date(),5);
		 Mockito.when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);
		 Beneficiary actual=beneficiaryService.createBeneficiary(beneficiary);
		    assertEquals(beneficiary, actual);
	}

	@Test
	void testGetBeneficiaryByAccountNumber() {
		Beneficiary beneficiary = new Beneficiary(2,"asd","add","dhfee",Math.abs(new Random().nextLong()),"gdhs",3,78.8,4,new Date(),new Date(),5);
		 Mockito.when(beneficiaryRepository.findByAccountNumber(beneficiary.getAccountNumber())).thenReturn(beneficiary);
		 Beneficiary actual=beneficiaryService.getBeneficiaryByAccountNumber(beneficiary.getAccountNumber());
		 assertEquals(beneficiary, actual);
	}

	@Test
	void testGetAllBeneficiary() {
		Beneficiary beneficiary = new Beneficiary(2,"asd","add","dhfee",Math.abs(new Random().nextLong()),"gdhs",3,78.8,4,new Date(),new Date(),5);
		Beneficiary beneficiary1 = new Beneficiary(3,"asd","er","f",Math.abs(new Random().nextLong()),"rg",3,78.8,4,new Date(),new Date(),5);
		Beneficiary beneficiary2 = new Beneficiary(24,"asd","z","vc",Math.abs(new Random().nextLong()),"df",3,78.8,4,new Date(),new Date(),1);
		List<Beneficiary> beneficiaryList = new ArrayList<>();
		beneficiaryList.add(beneficiary);
		beneficiaryList.add(beneficiary1);
		beneficiaryList.add(beneficiary2);
		Mockito.when(beneficiaryRepository.findAll()).thenReturn(beneficiaryList);
		List<Beneficiary> actualList = beneficiaryService.getAllBeneficiary();
		 assertEquals(beneficiaryList, actualList);
	}

}
