package com.qa.HWA.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.persistance.repo.CoachRepo;

@SpringBootTest
class CoachServiceUnitTest {

	@Autowired
	private CoachService service;

	@MockBean
	private CoachRepo repo;

	@MockBean
	private ModelMapper mapper;

	private List<Coach> coachList;

	private Coach testCoach;

	private Coach testCoachWithID;

	private CoachDTO coachDTO;

	private final long ID = 3L;

	@BeforeEach
	void init() {

		Coach coach1 = new Coach(1L, "Cambridge", "Oxford", "10:00", "12:00", 50, 6.00f, null);
		Coach coach2 = new Coach(2L, "London", "Manchester", "10:00", "15:45", 60, 10.99f, null);

		this.coachList = new ArrayList<>();
		this.coachList.add(coach1);
		this.coachList.add(coach2);
		this.coachList.add(testCoach);
		this.testCoach = new Coach("London", "Cardiff", "10:00", "14:00", 55, 12.00f);
		this.testCoachWithID = new Coach(testCoach.getStartPoint(), testCoach.getEndPoint(),
				testCoach.getDepartureTime(), testCoach.getArrivalTime(), testCoach.getCapacity(),
				testCoach.getTicketCost());
		this.testCoachWithID.setCoachID(ID);
		this.coachDTO = new ModelMapper().map(testCoachWithID, CoachDTO.class);

	}

	@Test
	void testCreateCoach() {
		Coach newCoach = new Coach(null, "London", "Cardiff", "10:00", "14:00", 55, 12.00f, null);

		when(this.mapper.map(newCoach, Coach.class)).thenReturn(testCoach);
		when(this.repo.save(testCoach)).thenReturn(testCoachWithID);
		when(this.mapper.map(testCoachWithID, CoachDTO.class)).thenReturn(coachDTO);

		assertThat(this.coachDTO).isEqualTo(this.service.create(newCoach));

		verify(this.repo, times(1)).save(this.testCoach);

	}

	@Test
	void testReadAll() {

		when(repo.findAll()).thenReturn(this.coachList);
		when(this.mapper.map(testCoachWithID, CoachDTO.class)).thenReturn(coachDTO);

		assertThat(this.service.readAll().isEmpty()).isFalse();

		verify(this.repo, times(1)).findAll();
	}

	@Test
	void testReadCoach() {

		when(this.repo.findById(this.ID)).thenReturn(Optional.of(this.testCoachWithID));
		when(this.mapper.map(testCoachWithID, CoachDTO.class)).thenReturn(coachDTO);

		assertThat(this.coachDTO).isEqualTo(this.service.readCoach(this.ID));

		verify(this.repo, times(1)).findById(this.ID);

	}

	@Test
	void testUpdate() {

		Coach updatedCoach = new Coach(null, "Glasgow", "Edinburgh", "17:30", "19:25", 45, 2.50f, null);
		updatedCoach.setCoachID(ID);
		CoachDTO updatedDTO = new ModelMapper().map(updatedCoach, CoachDTO.class);

		when(this.repo.findById(ID)).thenReturn(Optional.of(testCoach));
		when(this.repo.save(updatedCoach)).thenReturn(updatedCoach);
		when(this.mapper.map(updatedCoach, CoachDTO.class)).thenReturn(updatedDTO);

		assertThat(updatedDTO).isEqualTo(this.service.update(ID, updatedCoach));

		verify(this.repo, times(1)).findById(ID);
		verify(this.repo, times(1)).save(updatedCoach);
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
