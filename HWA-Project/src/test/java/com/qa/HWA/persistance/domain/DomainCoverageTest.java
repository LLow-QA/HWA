package com.qa.HWA.persistance.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;


class DomainCoverageTest {

	@Test
	void testCoach() {

		List<Passenger> pass = new ArrayList<>();
		Passenger newPass = new Passenger(1L,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(null,null,null, null, null, 0, 0, null));
		pass.add(newPass);
		EqualsVerifier.simple().forClass(Coach.class).withPrefabValues(Coach.class, new Coach(),
			new Coach(1L,"London","Cardiff","10:00","14:00",55,12.00f,pass)).verify();
		
	}
	
	@Test
	void testPassenger() {
		
		EqualsVerifier.simple().forClass(Passenger.class).withPrefabValues(Passenger.class, new Passenger(),new Passenger(
				1L,"Lloyd","Low","ll@qa.com", "Pass123", "20 Woodlands Road","OX28 2DN",1,6.00f,new Coach(1L))).verify();
	}

}
