package com.qa.HWA.persistance.domain;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Coach {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long coachID;
	

	private String startPoint;
	

	private String endPoint;
	
	@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
	private String departureTime;
	
	@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")
	private String arrivalTime;
	

	@Min(10)
	private int capacity;
	
	@Max(20)
	private float ticketCost;
	
	@OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Passenger> passengerList;

	public Coach(String startPoint, String endPoint, String departureTime, String arrivalTime,
			@Min(10) int capacity, @Max(20) float ticketCost) {
		super();
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
		this.ticketCost = ticketCost;
	}
	
	
}
