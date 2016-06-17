package com.restapi.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseRestController {
	
	@Autowired
	ScoresRepository scoresRepository;
	
	@RequestMapping(value = "/rockpaperscissors/getScores", method = RequestMethod.GET)
	public ResponseEntity<?> getScores() {
		return new ResponseEntity<List<Score>>(this.scoresRepository.findAll(), HttpStatus.OK);
	}
	
}
