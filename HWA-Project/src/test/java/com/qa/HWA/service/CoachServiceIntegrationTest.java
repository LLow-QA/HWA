package com.qa.HWA.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.persistance.repo.CoachRepo;

@SpringBootTest
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"},
executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class CoachServiceIntegrationTest {

	@Autowired
	private CoachService service;
	
	@Autowired
	private CoachRepo repo;
	
	private Coach testCoach;
	
	private Coach testCoachWithID;
	
	@Autowired
	private ModelMapper mapper;
	
	private CoachDTO mapToDTO(Coach coach) {
		
		return this.mapper.map(coach, CoachDTO.class);
		
	}
	
	@BeforeEach
	void init() {
		List<Passenger> passe = new ArrayList<>();
		this.testCoach = new Coach (null,"London","Cardiff","10:00","14:00",55,12.00f,passe);
		
		
		this.testCoachWithID = this.repo.save(this.testCoach);
		
	}
	
	
	@Test
	void testCreate() {
		
		assertThat(this.mapToDTO(this.testCoachWithID)).isEqualTo(this.service.create(testCoach));
		
	}
	
	@Test
	void testReadCoach() {
		
		final Long id  = 3L;
		
		assertThat(this.service.readCoach(id)).isEqualTo(this.mapToDTO(testCoachWithID));
		
	}
	
	@Test
	void testReadAll() {
		
		List<PassengerDTO> coach1passengers = new ArrayList<>();
		coach1passengers.add(new PassengerDTO(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f, 1L));
		coach1passengers.add(new PassengerDTO(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L));
		
		List<PassengerDTO> coach2passengers = new ArrayList<>();
		coach2passengers.add(new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L));
		
		List<CoachDTO> TEST_LIST_COACHDTO = new ArrayList<>();
		TEST_LIST_COACHDTO.add(new CoachDTO(1L,"Cambridge","Oxford","10:00","12:00",50,6.00f,coach1passengers));
		TEST_LIST_COACHDTO.add(new CoachDTO(2L,"London","Manchester","10:00","15:45",60,10.99f,coach2passengers));
		TEST_LIST_COACHDTO.add(mapToDTO(testCoach));
		
	
		assertThat(this.service.readAll()).isEqualTo(TEST_LIST_COACHDTO);
	
	}
	
	@Test
	void testUpdate() {
		
		List<Passenger> pass = new ArrayList<>();
		Coach newCoach = new Coach(1L,"Glasgow", "Edinburgh","17:30", "19:25", 45,2.50f,pass);
		List<PassengerDTO> passDTO = new ArrayList<>();
		CoachDTO updatedCoach = mapToDTO(newCoach);
		
		assertThat(this.service.update(1L, newCoach)).isEqualTo(updatedCoach);
	}
	
	
	@Test
	void testDelete(){
		
		assertThat(this.service.delete(this.testCoachWithID.getCoachID())).isTrue();
		
		
	}
	
	
	
	
	

}
