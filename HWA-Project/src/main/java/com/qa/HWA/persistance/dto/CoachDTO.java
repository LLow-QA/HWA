package com.qa.HWA.persistance.dto;

import java.time.LocalTime;
import java.util.List;

public class CoachDTO {

	private Long coachID;
	private String startPoint;
	private String endPoint;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private int capacity;
	private float ticketCost;
	private List<PassengerDTO> passengerList;
	
	
	
	public CoachDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CoachDTO(Long coachID, String startPoint, String endPoint, LocalTime departureTime, LocalTime arrivalTime,
			int capacity, float ticketCost, List<PassengerDTO> passengerList) {
		super();
		this.coachID = coachID;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.ticketCost = ticketCost;
		this.passengerList = passengerList;
	}



	public Long getCoachID() {
		return coachID;
	}



	public void setCoachID(Long coachID) {
		this.coachID = coachID;
	}



	public String getStartPoint() {
		return startPoint;
	}



	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}



	public String getEndPoint() {
		return endPoint;
	}



	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}



	public LocalTime getDepartureTime() {
		return departureTime;
	}



	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}



	public LocalTime getArrivalTime() {
		return arrivalTime;
	}



	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	public int getCapacity() {
		return capacity;
	}



	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}



	public float getTicketCost() {
		return ticketCost;
	}



	public void setTicketCost(float ticketCost) {
		this.ticketCost = ticketCost;
	}



	public List<PassengerDTO> getPassengerList() {
		return passengerList;
	}



	public void setPassengerList(List<PassengerDTO> passengerList) {
		this.passengerList = passengerList;
	}



	@Override
	public String toString() {
		return "CoachDTO [coachID=" + coachID + ", startPoint=" + startPoint + ", endPoint=" + endPoint
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", capacity=" + capacity
				+ ", ticketCost=" + ticketCost + ", passengerList=" + passengerList + "]";
	}
	
	
	
	
}
