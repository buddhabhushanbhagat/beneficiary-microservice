package com.beneficiary.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int beneficiaryId;
	
	@Email
	@NotNull
	private String email;
	
	private String address;
	
//	@Pattern(regexp = "^[a-zA-Z]+$",message = "name must be in characters only")
	private String name;
	
	@Column(unique=true)
	private Long accountNumber;
	
	private String ifscCode;
	
	private int accountStatus;
	
	@NotNull
	@Column(columnDefinition="Decimal(10,2) default '0.00'")
	private Double currentBalance;
	
	private int maxTsfrLimit;
	
	@Column(nullable = false, updatable = false,name="insert_date")
	@CreationTimestamp
	private Date insertDate; 

	@Column(nullable = false,name="update_date")
	@UpdateTimestamp
	private Date updateDate; 
	
	private int customerId;
}
