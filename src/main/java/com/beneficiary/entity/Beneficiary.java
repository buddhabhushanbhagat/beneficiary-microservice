package com.beneficiary.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.remitter.entity.Remitter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	
//	@Email
//	@NotNull
	private String email;
	
	private String address;
	
//	@Pattern(regexp = "^[a-zA-Z]+$",message = "name must be in characters only")
	private String name;
	
//	@NotNull
//	@Pattern(regexp = "^[0-9]+$",message = "name must be in characters only")
	@Column(unique=true)
	private Long accountNumber;
	
	private String ifscCode;
	
	private int accountStatus;
	
//	@NotNull
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
