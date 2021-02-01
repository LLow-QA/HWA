package com.qa.HWA.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.HWA.persistance.domain.Coach;
import com.qa.HWA.persistance.dto.CoachDTO;
import com.qa.HWA.service.CoachService;

@RestController
@CrossOrigin
@RequestMapping("/coach")
public class CoachController {

	private CoachService coachService;

	@Autowired
	public CoachController(CoachService coachService) {
		super();
		this.coachService = coachService;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<CoachDTO> create(@RequestBody Coach coach){
		
		return new ResponseEntity<CoachDTO>(this.coachService.create(coach),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/readAll")
	public List<CoachDTO> readAll(){
		
		return this.coachService.readAll();
		
	}
	
	@GetMapping("/read/{coachId}")
	public ResponseEntity<CoachDTO> readCoach(@PathVariable("coachId") Long coachId){
		
		return ResponseEntity.ok(this.coachService.readCoach(coachId));
		
	}
	
	
	@PatchMapping("/update/{coachId}")
	public ResponseEntity<CoachDTO> update(@PathVariable("coachId") Long coachId, @RequestBody Coach coach){
		
		return new ResponseEntity<>(this.coachService.update(coachId, coach),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{coachId}")
	public ResponseEntity<CoachDTO> delete(@PathVariable("coachId") Long coachId){
		
		return this.coachService.delete(coachId) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	
	
	
	
}
