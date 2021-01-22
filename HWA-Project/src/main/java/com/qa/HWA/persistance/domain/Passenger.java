package com.qa.HWA.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long passengerID;
	
	@NotNull
	@Size(min = 2, max = 20)
	private String first_name;
	
	@NotNull
	@Size(min = 2, max = 30)
	private String last_name;
	
	@NotNull
	@Pattern(regexp ="^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$")
	private String email;
	
	@NotNull
	//find a way to encrypt
	private String password;
	
	@NotNull
	private String address;
	
	@NotNull
	@Size(max = 8)
	private String postcode;

	@Max(9)
	private int numberOfTickets;
	
	
	private float totalCost;
	
	@ManyToOne
	private Coach coach;
}
