package com.qa.HWA.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.persistance.repo.PassengerRepo;

@SpringBootTest
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"},
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PassengerServiceIntegrationTest {
	

	@Autowired
	private PassengerService service;
	
	@Autowired
	private PassengerRepo repo;
	
	private Passenger testPassenger;
	
	private Passenger testPassengerWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private PassengerDTO mapToDTO(Passenger passenger) {
		
		return this.mapper.map(passenger, PassengerDTO.class);
		
	}
	
	@BeforeEach
	void init() {

		this.testPassenger = new Passenger(null,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(2L));
		
		
		this.testPassengerWithID = this.repo.save(this.testPassenger);
		
	}
	
	
	@Test
	void testCreate() {
		
		assertThat(this.mapToDTO(this.testPassengerWithID)).isEqualTo(this.service.create(testPassenger));
		
	}
	
	@Test
	void testReadPassenger() {
		
		final Long id  = 4L;
		
		assertThat(this.service.readPassenger(id)).isEqualTo(this.mapToDTO(testPassengerWithID));
		
	}
	
	@Test
	void testReadAll() {
		
		List<PassengerDTO> TEST_LIST_PASSENGERDTO = new ArrayList<>();
		TEST_LIST_PASSENGERDTO.add(new PassengerDTO(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f,1L));
		TEST_LIST_PASSENGERDTO.add(new PassengerDTO(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L));
		TEST_LIST_PASSENGERDTO.add(new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L));
		TEST_LIST_PASSENGERDTO.add(mapToDTO(testPassenger));
		
	
		assertThat(this.service.readAll()).isEqualTo(TEST_LIST_PASSENGERDTO);
	
	}
	
	@Test
	void testUpdate() {
		
		final Long id = testPassengerWithID.getPassengerID();


		Passenger passenger2 = new Passenger(id,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(2L));
		
		assertThat(this.service.update(id, testPassenger)).isEqualTo(mapToDTO(passenger2));
	}
	
	
	@Test
	void testDelete(){
		
		assertThat(this.service.delete(this.testPassengerWithID.getPassengerID())).isTrue();
		
		
	}
	
	
	
	
	

}



