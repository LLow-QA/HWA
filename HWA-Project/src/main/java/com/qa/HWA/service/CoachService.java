package com.qa.HWA.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.HWA.config.MyBeanUtils;
import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.persistance.repo.CoachRepo;

@Service
public class CoachService {

	private CoachRepo coachRepo;
	private ModelMapper mapper;
	
	@Autowired
	public CoachService(CoachRepo coachRepo, ModelMapper mapper) {
		super();
		this.coachRepo = coachRepo;
		this.mapper = mapper;
	}
	
	private CoachDTO mapToDTO(Coach model) {
		
		return this.mapper.map(model, CoachDTO.class);
		
	}
	
	public CoachDTO create(Coach coachModel) {
		
		Coach saved = this.coachRepo.save(coachModel);
		return mapToDTO(saved);
		
	}
	
	public List<CoachDTO> readAll(){
		
		return this.coachRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		
	}
	
	public CoachDTO readCoach(Long coachID) {
		
		return this.mapToDTO(coachRepo.findById(coachID).orElseThrow());
		
	}
	
	public CoachDTO update(Long coachID, Coach newCoach) {
		
		Coach updatedCoach = this.coachRepo.findById(coachID).orElseThrow();
		MyBeanUtils.mergeNotNull(newCoach, updatedCoach);
		return this.mapToDTO(this.coachRepo.save(updatedCoach));
		
	}
	
	public boolean delete(Long coachID) {
		
		this.coachRepo.deleteById(coachID);
		boolean exists = this.coachRepo.existsById(coachID);
		return !exists;
		
	}

}
