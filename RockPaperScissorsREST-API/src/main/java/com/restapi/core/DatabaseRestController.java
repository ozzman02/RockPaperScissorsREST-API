package com.restapi.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DatabaseRestController {
	
	private static final int firstPlaceScore = 3;
	private static final int secondPlaceScore = 1;
	
	@Autowired
	ScoreRepository scoreRepository;
	
	@RequestMapping(value = "/rockpaperscissors/getScores", method = RequestMethod.GET)
	public ResponseEntity<?> getScores() {
		return new ResponseEntity<List<Score>>(this.scoreRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rockpaperscissors/championship/top", method = RequestMethod.GET)
	public ResponseEntity<?> getTopScores(@RequestParam(value="count") Integer count) {
		List<Score> scores = this.scoreRepository.findAll();
		if (scores.size() == 0) {
			return new ResponseEntity<Error>(new Error("Empty database"), 
					HttpStatus.BAD_REQUEST);
		} else if (scores.size() < count) {
			return new ResponseEntity<Error>(new Error("Top parameter exceeds max database records count"), 
					HttpStatus.BAD_REQUEST);
		} else if (count == 0) {
			return new ResponseEntity<Error>(new Error("Top parameter value can't be 0"), 
					HttpStatus.BAD_REQUEST);
		} else {
			List<Score> results = ApplicationUtils.sortList(scores, count);
			return new ResponseEntity<List<Score>>(results, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/rockpaperscissors/getPlayerScoreByName/{playerName}", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public Score getPlayerScoreByName(@PathVariable(value="playerName") String playerName) {
		Score entity = this.scoreRepository.findScoreByPlayerName(playerName);
		return entity;
	}
	
	@RequestMapping(value = "/rockpaperscissors/championShip/storeWinners", method = RequestMethod.POST)
	public ResponseEntity<?> updatePlayerScores(@RequestParam String playerName1, @RequestParam String playerName2) {
		
		if (playerName1.equals("") || playerName2.equals("")) {
			return new ResponseEntity<Error>(new Error("Error, empty parameters"), HttpStatus.BAD_REQUEST);
		} else {
			Score p1Entity = getPlayerScoreByName(playerName1);
			Score p2Entity = getPlayerScoreByName(playerName2);
			if (p1Entity == null) {
				Score s1 = new Score(playerName1, firstPlaceScore);
				this.scoreRepository.save(s1);
			} else {
				int newScore1 = p1Entity.getScore();	
				newScore1 += firstPlaceScore;
				p1Entity.setScore(newScore1);
				this.scoreRepository.save(p1Entity);
			}
			if (p2Entity == null) {
				Score s2 = new Score(playerName2, secondPlaceScore);
				this.scoreRepository.save(s2);
			} else {
				int newScore2 = p2Entity.getScore();	
				newScore2 += secondPlaceScore;
				p2Entity.setScore(newScore2);
				this.scoreRepository.save(p2Entity);
			}
		}
			
		return new ResponseEntity<Success>(new Success("Success"), HttpStatus.OK);		
	
	}
	
}
