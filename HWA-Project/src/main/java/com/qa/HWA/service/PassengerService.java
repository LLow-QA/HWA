package com.qa.HWA.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.HWA.config.MyBeanUtils;
import com.qa.HWA.persistance.domain.Passenger;
import com.qa.HWA.persistance.dto.PassengerDTO;
import com.qa.HWA.persistance.repo.PassengerRepo;

@Service
public class PassengerService {

	private PassengerRepo passengerRepo;
	private ModelMapper mapper;
	
	@Autowired
	public PassengerService(PassengerRepo passengerRepo, ModelMapper mapper) {
		super();
		this.passengerRepo = passengerRepo;
		this.mapper = mapper;
	}
	
	private PassengerDTO mapToDTO(Passenger model) {
		
		return this.mapper.map(model,PassengerDTO.class);
		
	}
	
	public PassengerDTO create(Passenger passengerModel) {
		
		Passenger saved = this.passengerRepo.save(passengerModel);
		return mapToDTO(saved);
		
	}
	
	public List<PassengerDTO> readAll() {
		
		return this.passengerRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	
	}
	
	public PassengerDTO readPassenger(Long passengerID) {
		
		return this.mapToDTO(passengerRepo.findById(passengerID).orElseThrow());
		
	}
	
	public PassengerDTO update(Long passengerID, Passenger newPassenger) {
		
		Passenger updatedPassenger = this.passengerRepo.findById(passengerID).orElseThrow();
		MyBeanUtils.mergeNotNull(newPassenger, updatedPassenger);
		return this.mapToDTO(this.passengerRepo.save(updatedPassenger));
		
	}
	
	public boolean delete(Long passengerID) {
		
		this.passengerRepo.deleteById(passengerID);
		boolean exists = this.passengerRepo.existsById(passengerID);
		return !exists;
		
	}
	
}
