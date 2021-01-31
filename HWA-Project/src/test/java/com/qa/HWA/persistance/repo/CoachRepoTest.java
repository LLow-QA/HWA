package com.qa.HWA.persistance.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.qa.HWA.persistance.domain.Coach;

@DataJpaTest
class CoachRepoTest {

	@Autowired
	private CoachRepo repo;
	
	private final String TEST_ENDPOINT = "London";
	
	private final Coach testCoach = new Coach("Manchester",TEST_ENDPOINT,"10:00","14:00",55,12.00f);
	
	private Coach testSavedCoach;
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		this.testSavedCoach = this.repo.save(this.testCoach);
	}
	
	@Test
	void testFindByEndPoint() {
		assertThat(this.repo.findByEndPoint(TEST_ENDPOINT));
	}

}
