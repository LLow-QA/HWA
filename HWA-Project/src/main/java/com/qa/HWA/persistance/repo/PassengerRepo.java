package com.qa.HWA.persistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.HWA.persistance.domain.Passenger;

@Repository
public interface PassengerRepo extends JpaRepository<Passenger,Long> {

}
