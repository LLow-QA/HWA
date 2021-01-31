package com.qa.HWA.rest;


import java.util.ArrayList;
import java.util.List;

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
import com.qa.HWA.persistance.dto.PassengerDTO;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"},
				executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class PassengerControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private PassengerDTO mapToDTO(Passenger passenger) {
		
		return this.modelMapper.map(passenger, PassengerDTO.class);
		
	}
	
	private final Long ID = 4L;
	
	Passenger TEST_PASSENGER = new Passenger(null,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(2L));
	PassengerDTO TEST_PASSENGERDTO = new PassengerDTO(this.ID,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,2L);
	
	
	
			@Test
			public void testCreate() throws Exception {
				List<Passenger> passengerList = new ArrayList<>();
				passengerList.add(new Passenger(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f,new Coach(1L)));
				passengerList.add(new Passenger(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, new Coach(1L)));
				passengerList.add(new Passenger(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, new Coach(2L)));
				
				
				
				//PREPARED REQUEST
				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/passenger/create")
															.contentType(MediaType.APPLICATION_JSON)
															.content(this.jsonifier.writeValueAsString(TEST_PASSENGER))
															.accept(MediaType.APPLICATION_JSON);
				
				
				
				//ASSERTION CHECKS
				ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_PASSENGERDTO));
				ResultMatcher matchStatus =  MockMvcResultMatchers.status().isCreated();
				
				//EXECUTE TEST
				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
			}
			
			
			//READ
			@Test
			public void testReadPassenger() throws Exception{
				
				final Long id = 2L;
				
				
				
				PassengerDTO passenger2 = (new PassengerDTO(id,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L));
				
				//PREPARED REQUEST
				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/passenger/read/"+ id)
															.contentType(MediaType.APPLICATION_JSON)
															//.content(this.jsonifier.writeValueAsString(null))
															.accept(MediaType.APPLICATION_JSON);
				
				//ASSERTION CHECKS
				ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(passenger2));
				ResultMatcher matchStatus =  MockMvcResultMatchers.status().isOk();
				
				//EXECUTE TEST
				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
			}
			
			//READ ALL
			@Test
			public void testReadAll() throws Exception {
				
				
				
				List<PassengerDTO> TEST_LIST_PASSENGERDTO = new ArrayList<>();
				TEST_LIST_PASSENGERDTO.add(new PassengerDTO(1L, "Andy", "Qwert", "aa@bb.com", "Pass1234", "20 Woad Way", "BS8 6GA", 1, 6.00f,1L));
				TEST_LIST_PASSENGERDTO.add(new PassengerDTO(2L,"James","George","jg@gmail.com","Broken45","20 Load Drive","DS54 5EE",2,12.00f, 1L));
				TEST_LIST_PASSENGERDTO.add(new PassengerDTO(3L,"Dani","Lu","dx@hotmail.com","XuDani34","5 Blocktown Road","BF4 4FB",1,10.99f, 2L));
				
				//PREPARED REQUEST
				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/passenger/readAll")
															.contentType(MediaType.APPLICATION_JSON)
															//.content(this.jsonifier.writeValueAsString(null))
															.accept(MediaType.APPLICATION_JSON);
				
				//ASSERTION CHECKS
				ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_LIST_PASSENGERDTO));
				ResultMatcher matchStatus =  MockMvcResultMatchers.status().isOk();
				
				//EXECUTE TEST
				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
			
			}
			
			//UPDATE
			@Test
			public void testUpdate() throws Exception {
				
				final Long id = 2L;


				Passenger passenger2 = new Passenger(id,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(2L));
				
				
				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PATCH, "/passenger/update/"+ id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.jsonifier.writeValueAsString(passenger2))
						.accept(MediaType.APPLICATION_JSON);
				
				
				
				ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(passenger2)));
				ResultMatcher matchStatus =  MockMvcResultMatchers.status().isAccepted();
				
				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
				
			}
			
			//DELETE
			@Test
			public void testDelete() throws Exception {
				 final Long id = 1L;
				
				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE, "/passenger/delete/"+ id)
						.contentType(MediaType.APPLICATION_JSON)
						//.content(this.jsonifier.writeValueAsString(TEST_COACH))
						.accept(MediaType.APPLICATION_JSON);
				
				
				ResultMatcher matchStatus =  MockMvcResultMatchers.status().isNoContent();
				
				this.mock.perform(mockRequest).andExpect(matchStatus);

				
			}
}