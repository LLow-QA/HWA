package com.qa.HWA.persistance.dto;

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
	
	
	public PassengerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PassengerDTO(Long passengerID, String first_name, String last_name, String email, int numberOfTickets,
			float totalCost) {
		super();
		this.passengerID = passengerID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.numberOfTickets = numberOfTickets;
		this.totalCost = totalCost;
	}


	public Long getPassengerID() {
		return passengerID;
	}


	public void setPassengerID(Long passengerID) {
		this.passengerID = passengerID;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public int getNumberOfTickets() {
		return numberOfTickets;
	}


	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}


	public float getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	@Override
	public String toString() {
		return "PassengerDTO [passengerID = " + passengerID + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", password=" + password + ", address=" + address + ", postcode=" + postcode
				+ ", numberOfTickets=" + numberOfTickets + ", totalCost=" + totalCost + "]";
	}
	
	
	

}
