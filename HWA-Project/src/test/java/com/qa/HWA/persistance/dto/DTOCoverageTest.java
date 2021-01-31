package com.qa.HWA.persistance.dto;


import nl.jqno.equalsverifier.EqualsVerifier;

import org.junit.jupiter.api.Test;

class DTOCoverageTest {

	@Test
	void testCoachDTO() {
		EqualsVerifier.simple().forClass(CoachDTO.class).verify();
	}
	
	@Test
	void testPassengerDTO() {
		EqualsVerifier.simple().forClass(PassengerDTO.class).verify();
	}

}
