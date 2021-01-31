package com.qa.HWA.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.persistance.repo.PassengerRepo;

@SpringBootTest
class PassengerServiceUnitTest {

	@Autowired
	private PassengerService service;

	@MockBean
	private PassengerRepo repo;

	@MockBean
	private ModelMapper mapper;

	private List<Passenger> passengerList;

	private Passenger testPassenger;

	private Passenger testPassengerWithID;

	private PassengerDTO passengerDTO;

	private final long ID = 4L;

	@BeforeEach
	void init() {

		Passenger Passenger1 = new Passenger(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1,
				6.00f, null);
		Passenger Passenger2 = new Passenger(2L, "James", "George", "jg@gmail.com", "Broken45", "20 Load Drive",
				"DS54 5EE", 2, 12.00f, null);
		Passenger Passenger3 = new Passenger(3L, "Dani", "Lu", "dx@hotmail.com", "XuDani34", "5 Blocktown Road",
				"BF4 4FB", 1, 10.99f, null);

		this.passengerList = new ArrayList<>();
		this.passengerList.add(Passenger1);
		this.passengerList.add(Passenger2);
		this.passengerList.add(Passenger3);
		this.passengerList.add(testPassenger);
		this.testPassenger = new Passenger("Lloyd", "Low", "ll@qa.com", "Pass123", "20 Woodlands Road", "OX28 2DN", 1,
				6.00f);
		this.testPassengerWithID = new Passenger(testPassenger.getFirst_name(), testPassenger.getLast_name(),
				testPassenger.getEmail(), testPassenger.getPassword(), testPassenger.getAddress(),
				testPassenger.getPostcode(), testPassenger.getNumberOfTickets(), testPassenger.getTotalCost());
		this.testPassengerWithID.setPassengerID(ID);
		this.passengerDTO = new ModelMapper().map(testPassengerWithID, PassengerDTO.class);

	}

	@Test
	void testCreate() {
		Passenger newPassenger = new Passenger(null, "Lloyd", "Low", "ll@qa.com", "Pass123", "20 Woodlands Road",
				"OX28 2DN", 1, 6.00f);

		when(this.mapper.map(newPassenger, Passenger.class)).thenReturn(testPassenger);
		when(this.repo.save(testPassenger)).thenReturn(testPassengerWithID);
		when(this.mapper.map(testPassengerWithID, PassengerDTO.class)).thenReturn(passengerDTO);

		assertThat(this.passengerDTO).isEqualTo(this.service.create(newPassenger));

		verify(this.repo, times(1)).save(this.testPassenger);

	}

	@Test
	void testReadAll() {

		when(repo.findAll()).thenReturn(this.passengerList);
		when(this.mapper.map(testPassengerWithID, PassengerDTO.class)).thenReturn(passengerDTO);

		assertThat(this.service.readAll().isEmpty()).isFalse();

		verify(this.repo, times(1)).findAll();
	}

	@Test
	void testReadCoach() {

		when(this.repo.findById(this.ID)).thenReturn(Optional.of(this.testPassengerWithID));
		when(this.mapper.map(testPassengerWithID, PassengerDTO.class)).thenReturn(passengerDTO);

		assertThat(this.passengerDTO).isEqualTo(this.service.readPassenger(this.ID));

		verify(this.repo, times(1)).findById(this.ID);

	}

	@Test
	void testUpdate() {

		Passenger updatedPassenger = new Passenger(null, "Harry", "Fair", "hf@aol.com", "crashMet3", "7 Palace Row",
				"J4 4RR", 3, 18.00f);
		updatedPassenger.setPassengerID(ID);
		PassengerDTO updatedDTO = new ModelMapper().map(updatedPassenger, PassengerDTO.class);

		when(this.repo.findById(ID)).thenReturn(Optional.of(testPassenger));
		when(this.repo.save(updatedPassenger)).thenReturn(updatedPassenger);
		when(this.mapper.map(updatedPassenger, PassengerDTO.class)).thenReturn(updatedDTO);

		assertThat(updatedDTO).isEqualTo(this.service.update(ID, updatedPassenger));

		verify(this.repo, times(1)).findById(ID);
		verify(this.repo, times(1)).save(updatedPassenger);
	}

	@Test
	void testDelete() {

		when(this.repo.existsById(1L)).thenReturn(false);

		assertTrue(this.service.delete(1L));

		verify(this.repo, times(1)).deleteById(1L);
		verify(this.repo, times(1)).existsById(1L);

	}

	@Test
	void testDeleteFalse() {

		when(this.repo.existsById(78L)).thenReturn(true);

		assertFalse(this.service.delete(78L));

		verify(this.repo, times(1)).deleteById(78L);
		verify(this.repo, times(1)).existsById(78L);

	}

}
