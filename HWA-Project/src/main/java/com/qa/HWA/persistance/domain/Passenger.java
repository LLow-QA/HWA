package com.qa.HWA.persistance.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	@Size(min = 2, max = 30)
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


	private int numberOfTickets;
	
	
	private float totalCost;
	
	@ManyToOne
	private Coach coach;

	public Passenger(@NotNull @Size(min = 2, max = 30) String first_name,
			@NotNull @Size(min = 2, max = 30) String last_name,
			@NotNull @Pattern(regexp = "^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$") String email, @NotNull String password,
			@NotNull String address, @NotNull @Size(max = 8) String postcode, int numberOfTickets,
			float totalCost) {
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

	public Passenger(Long passengerID, @NotNull @Size(min = 2, max = 30) String first_name,
			@NotNull @Size(min = 2, max = 30) String last_name,
			@NotNull @Pattern(regexp = "^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$") String email, @NotNull String password,
			@NotNull String address, @NotNull @Size(max = 8) String postcode, int numberOfTickets, float totalCost) {
		super();
		this.passengerID = passengerID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.postcode = postcode;
		this.numberOfTickets = numberOfTickets;
		this.totalCost = totalCost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (coach == null) {
			if (other.coach != null)
				return false;
		} else if (!coach.equals(other.coach))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (numberOfTickets != other.numberOfTickets)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		if (Float.floatToIntBits(totalCost) != Float.floatToIntBits(other.totalCost))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((coach == null) ? 0 : coach.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + numberOfTickets;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		result = prime * result + Float.floatToIntBits(totalCost);
		return result;
	}

	
	
	
}
