package com.qa.HWA.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.persistance.dto.PassengerDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"},
				executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class CoachControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private CoachDTO mapToDTO(Coach coach) {
		
		return this.modelMapper.map(coach,CoachDTO.class);
	}
	
	private Long ID = 3L;
	
	private final Coach TEST_COACH = new Coach(this.ID,"London","Cardiff","10:00","14:00",55,12.00f,null);
	private final CoachDTO TEST_COACHDTO = new CoachDTO(this.ID,"London","Cardiff","10:00","14:00",55,12.00f);
	
	@BeforeEach
	void init() {
		
	}
	
	@Test
	public void testCreate() throws Exception {
		List<Coach> coachList = new ArrayList<>();
		coachList.add(new Coach(1L,"Cambridge","Oxford","10:00","12:00",50,6.00f, null));
		coachList.add(new Coach(2L,"London","Manchester","10:00","15:45",60,10.99f, null));
		
		
		
		//PREPARED REQUEST
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/coach/create")
													.contentType(MediaType.APPLICATION_JSON)
													.content(this.jsonifier.writeValueAsString(TEST_COACH))
													.accept(MediaType.APPLICATION_JSON);
		
		
		
		//ASSERTION CHECKS
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_COACHDTO));
		ResultMatcher matchStatus =  MockMvcResultMatchers.status().isCreated();
		
		//EXECUTE TEST
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	
	//READ
	@Test
	public void testReadCoach() throws Exception{
		
		final Long id = 2L;
		
		List<PassengerDTO> coach2passengers = new ArrayList<>();
		coach2passengers.add(new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L));
		
		CoachDTO coach2 = new CoachDTO(2L,"London","Manchester","10:00","15:45",60,10.99f,coach2passengers);
		
		//PREPARED REQUEST
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/coach/read/"+ id)
													.contentType(MediaType.APPLICATION_JSON)
													//.content(this.jsonifier.writeValueAsString(null))
													.accept(MediaType.APPLICATION_JSON);
		
		//ASSERTION CHECKS
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(coach2));
		ResultMatcher matchStatus =  MockMvcResultMatchers.status().isOk();
		
		//EXECUTE TEST
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
	
	//READ ALL
	@Test
	public void testReadAll() throws Exception {
		
		List<PassengerDTO> coach1passengers = new ArrayList<>();
		coach1passengers.add(new PassengerDTO(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f, 1L));
		coach1passengers.add(new PassengerDTO(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L));
		
		List<PassengerDTO> coach2passengers = new ArrayList<>();
		coach2passengers.add(new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L));
		
		List<CoachDTO> TEST_LIST_COACHDTO = new ArrayList<>();
		TEST_LIST_COACHDTO.add(new CoachDTO(1L,"Cambridge","Oxford","10:00","12:00",50,6.00f,coach1passengers));
		TEST_LIST_COACHDTO.add(new CoachDTO(2L,"London","Manchester","10:00","15:45",60,10.99f,coach2passengers));
		
		//PREPARED REQUEST
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/coach/readAll")
													.contentType(MediaType.APPLICATION_JSON)
													//.content(this.jsonifier.writeValueAsString(null))
													.accept(MediaType.APPLICATION_JSON);
		
		//ASSERTION CHECKS
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_LIST_COACHDTO));
		ResultMatcher matchStatus =  MockMvcResultMatchers.status().isOk();
		
		//EXECUTE TEST
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	
	}
	
	//UPDATE
	@Test
	public void testUpdate() throws Exception {
		
		final Long id = 2L;

		
		List<Passenger> coach2passengers = new ArrayList<>();
		coach2passengers.add(new Passenger(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f,new Coach(2L)));
		
		Coach coach2 = new Coach(2L,"London","Manchester","10:00","15:45",60,10.99f,coach2passengers);
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PATCH, "/coach/update/"+ id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(coach2))
				.accept(MediaType.APPLICATION_JSON);
		
		
		
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(coach2)));
		ResultMatcher matchStatus =  MockMvcResultMatchers.status().isAccepted();
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		
	}
	
	//DELETE
	@Test
	public void testDelete() throws Exception {
		 final Long id = 1L;
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/coach/delete/"+ id)
				.contentType(MediaType.APPLICATION_JSON)
				//.content(this.jsonifier.writeValueAsString(TEST_COACH))
				.accept(MediaType.APPLICATION_JSON);
		
		
		ResultMatcher matchStatus =  MockMvcResultMatchers.status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);

		
	}
}
