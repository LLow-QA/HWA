package com.qa.HWA.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.service.PassengerService;

@RestController
@CrossOrigin
@RequestMapping("/passenger")
public class PassengerController {

	private PassengerService passengerService;

	public PassengerController(PassengerService passengerService) {
		super();
		this.passengerService = passengerService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<PassengerDTO> create(@RequestBody Passenger passenger){
		
		return new ResponseEntity<PassengerDTO>(this.passengerService.create(passenger),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/readAll")
	public List<PassengerDTO> readAll(){
		
		return this.passengerService.readAll();
		
	}
	
	@GetMapping("/read/{passengerId}")
	public ResponseEntity<PassengerDTO> readPassenger(@PathVariable("passengerId") Long passengerId){
		
		return ResponseEntity.ok(this.passengerService.readPassenger(passengerId));
		
	}
		
	@PutMapping("/update/{passengerId}")
	public ResponseEntity<PassengerDTO> update(@PathVariable("passengerId") Long passengerId , @RequestBody Passenger passenger){
		
		return new ResponseEntity<>(this.passengerService.update(passengerId, passenger),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{passengerId}")
	public ResponseEntity<PassengerDTO> delete(@PathVariable("passengerId") Long passengerId){
		
		return this.passengerService.delete(passengerId) ? 
				new ResponseEntity<>(HttpStatus.NO_CONTENT) : 
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}