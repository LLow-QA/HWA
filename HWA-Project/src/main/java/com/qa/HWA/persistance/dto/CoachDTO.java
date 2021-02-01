package com.qa.HWA.persistance.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CoachDTO {

	private Long coachID;
	private String startPoint;
	private String endPoint;
	private String departureTime;
	private String arrivalTime;
	private int capacity;
	private float ticketCost;
	private List<PassengerDTO> passengerList;
	
	
	public CoachDTO(String startPoint, String endPoint, String departureTime, String arrivalTime, int capacity,
			float ticketCost) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.ticketCost = ticketCost;
	}


	public CoachDTO(Long coachID, String startPoint, String endPoint, String departureTime, String arrivalTime,
			int capacity, float ticketCost) {
		super();
		this.coachID = coachID;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.ticketCost = ticketCost;
	}
	
	
	

}
