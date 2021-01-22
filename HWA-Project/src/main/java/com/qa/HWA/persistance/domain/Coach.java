package com.qa.HWA.persistance.domain;

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
	
	@NotNull
	private String startPoint;
	
	@NotNull
	private String endPoint;
	
	@NotNull
	private LocalTime departureTime;
	
	@NotNull
	private LocalTime arrivalTime;
	
	@NotNull
	@Min(10)
	private int capacity;
	
	@Max(20)
	private float ticketCost;
	
	@OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Passenger> passengerList;
	
	//LocalTime
	//Should allow arrival and departure time to be set in a Time format
}
