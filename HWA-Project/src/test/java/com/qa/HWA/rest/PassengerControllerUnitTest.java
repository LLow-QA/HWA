package com.qa.HWA.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.service.PassengerService;

@SpringBootTest
class PassengerControllerUnitTest {

	@Autowired
	private PassengerController controller;
	
	@MockBean
	private PassengerService service;
	
	private List<PassengerDTO> passengerList;
	
	private Passenger testPassenger;
	
	private Passenger testPassengerWithID;
	
	private PassengerDTO passengerDTO;
	
	private final Long ID = 4L;
	
	private ModelMapper mapper = new ModelMapper();
	
	private PassengerDTO mapToDTO(Passenger passenger) {
		return this.mapper.map(passenger, PassengerDTO.class);
	}
	
	
	@BeforeEach
	void init() {
		
		PassengerDTO Passenger1 = new PassengerDTO(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f, 1L);
		PassengerDTO Passenger2 = new PassengerDTO(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L);
		PassengerDTO Passenger3 = new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L);
		
		this.passengerList = new ArrayList<>();
		this.testPassenger = new Passenger ("Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f);

		
		
				this.testPassengerWithID = new Passenger (testPassenger.getFirst_name(),testPassenger.getLast_name(),testPassenger.getEmail(),testPassenger.getPassword(),testPassenger.getAddress(),testPassenger.getPostcode(),testPassenger.getNumberOfTickets(),testPassenger.getTotalCost());
		this.testPassengerWithID.setPassengerID(ID);
		this.passengerDTO = mapToDTO(testPassengerWithID);
		
		this.passengerList.add(Passenger1);
		this.passengerList.add(Passenger2);
		this.passengerList.add(Passenger3);
		this.passengerList.add(passengerDTO);
		
	}
	
	
	@Test
	void testCreate() {
		
		when(this.service.create(testPassenger)).thenReturn(this.passengerDTO);
		
		assertThat(new ResponseEntity<PassengerDTO>(this.passengerDTO,HttpStatus.CREATED)).isEqualTo(this.controller.create(testPassenger));
		
		verify(this.service, times(1)).create(this.testPassenger);
		
	}
	
	@Test
	void testReadAll() {
		
		when(service.readAll()).thenReturn(this.passengerList);
		
		assertThat(this.controller.readAll().isEmpty()).isFalse();
		
		verify(service, times(1)).readAll();
	}
	
	@Test
	void testReadPassenger() {
		
		when(this.service.readPassenger(this.ID)).thenReturn(this.passengerDTO);
		
		assertThat(new ResponseEntity<PassengerDTO>(this.passengerDTO, HttpStatus.OK)).isEqualTo(this.controller.readPassenger(this.ID));
		
		verify(this.service, times(1)).readPassenger(this.ID);
		
	}
	
	@Test
	void testUpdate() {
		
		PassengerDTO updatedPassenger = new PassengerDTO(null,"Harry","Fair","hf@aol.com", "crashMet3", "7 Palace Row","J4 4RR",3,18.00f,2L);
		updatedPassenger.setPassengerID(ID);
		
		when(this.service.update(this.ID, testPassenger)).thenReturn(updatedPassenger);
		
		assertThat(new ResponseEntity<PassengerDTO>(updatedPassenger, HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(this.ID, this.testPassenger));
		
		verify(this.service, times(1)).update(this.ID, this.testPassenger);
	}
	
	@Test
	void testDelete() {
		
		this.controller.delete(this.ID);
		
		verify(this.service, times(1)).delete(this.ID);
		
	}

}