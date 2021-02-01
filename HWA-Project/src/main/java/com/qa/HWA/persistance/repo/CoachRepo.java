package com.qa.HWA.persistance.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.HWA.persistance.domain.Coach;

@Repository
public interface CoachRepo extends JpaRepository<Coach,Long>{

}
