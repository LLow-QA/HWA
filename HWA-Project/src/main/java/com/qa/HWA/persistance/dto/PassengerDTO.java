package com.qa.HWA.persistance.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassengerDTO {
	
	private Long passengerID;
	private String first_name;
	private String last_name;
	private String email;
	private String password;
	private String address;
	private String postcode;
	private int numberOfTickets;
	private float totalCost;
	
	
	


	public PassengerDTO(String first_name, String last_name, String email, String password, String address,
			String postcode, int numberOfTickets, float totalCost) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postcode = postcode;
		this.numberOfTickets = numberOfTickets;
		this.totalCost = totalCost;
	}





}
