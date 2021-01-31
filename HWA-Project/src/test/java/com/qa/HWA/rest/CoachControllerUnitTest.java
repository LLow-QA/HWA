package com.qa.HWA.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.service.CoachService;

@SpringBootTest

class CoachControllerUnitTest {

	@Autowired
	private CoachController controller;
	
	@MockBean
	private CoachService service;
	
	private List<CoachDTO> coachList;
	
	private Coach testCoach;
	
	private Coach testCoachWithID;
	
	private CoachDTO coachDTO;
	
	private final Long ID = 3L;
	
	private ModelMapper mapper = new ModelMapper();
	
	private CoachDTO mapToDTO(Coach coach) {
		return this.mapper.map(coach, CoachDTO.class);
	}
	
	
	@BeforeEach
	void init() {
		
		CoachDTO coach1 = new CoachDTO(1L,"Cambridge","Oxford","10:00","12:00",50,6.00f, null);
		CoachDTO coach2 = new CoachDTO(2L,"London","Manchester","10:00","15:45",60,10.99f, null);
		
		this.coachList = new ArrayList<>();
		this.testCoach = new Coach ("London","Cardiff","10:00","14:00",55,12.00f);
		
		this.testCoachWithID = new Coach (testCoach.getStartPoint(),testCoach.getEndPoint(),testCoach.getDepartureTime(),testCoach.getArrivalTime(),testCoach.getCapacity(),testCoach.getTicketCost());
		this.testCoachWithID.setCoachID(ID);
		this.coachDTO = this.mapToDTO(testCoachWithID);
		
		this.coachList.add(coach1);
		this.coachList.add(coach2);
		this.coachList.add(coachDTO);
		
	}
	
	
	@Test
	void testCreate() {
		
		when(this.service.create(testCoach)).thenReturn(this.coachDTO);
		
		assertThat(new ResponseEntity<CoachDTO>(this.coachDTO,HttpStatus.CREATED)).isEqualTo(this.controller.create(testCoach));
		
		verify(this.service, times(1)).create(this.testCoach);
		
	}
	
	@Test
	void readAll() {
		
		when(service.readAll()).thenReturn(this.coachList);
		
		assertThat(this.controller.readAll().isEmpty()).isFalse();
		
		verify(service, times(1)).readAll();
	}
	
	@Test
	void readCoach() {
		
		when(this.service.readCoach(this.ID)).thenReturn(this.coachDTO);
		
		assertThat(new ResponseEntity<CoachDTO>(this.coachDTO, HttpStatus.OK)).isEqualTo(this.controller.readCoach(this.ID));
		
		verify(this.service, times(1)).readCoach(this.ID);
		
	}
	
	@Test
	void updateCoach() {
		
		CoachDTO updatedCoach = new CoachDTO(null, "Glasgow", "Edinburgh","17:30", "19:25", 45,2.50f, null);
		updatedCoach.setCoachID(ID);
		
		when(this.service.update(this.ID, testCoach)).thenReturn(updatedCoach);
		
		assertThat(new ResponseEntity<CoachDTO>(updatedCoach, HttpStatus.ACCEPTED)).isEqualTo(this.controller.update(this.ID, this.testCoach));
		
		verify(this.service, times(1)).update(this.ID, this.testCoach);
	}
	
	@Test
	void testDelete() {
		
		this.controller.delete(this.ID);
		
		verify(this.service, times(1)).delete(this.ID);
		
	}

}
