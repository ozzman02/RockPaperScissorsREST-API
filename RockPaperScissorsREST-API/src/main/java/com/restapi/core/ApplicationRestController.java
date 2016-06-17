package com.restapi.core;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationRestController {
	
	@RequestMapping(value = "/rockpaperscissors/singlematch", method = RequestMethod.POST)
	public ResponseEntity<?> singleMatch(@RequestBody List<Game> games) {
		if (games.size() % 2 != 0) {
			return new ResponseEntity<Error>(new Error("The number of participantes must be an even number"), 
					HttpStatus.FORBIDDEN);
		} else if (ApplicationUtils.validMatch(games)) { 
			ArrayList<Game> results = ApplicationUtils.resolveMatch(games);
			Game winner = results.get(0);
			return new ResponseEntity<Game>(winner, HttpStatus.OK);
		} else {
			return new ResponseEntity<Error>(new Error("There are duplicate player names"), 
					HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/rockpaperscissors/tournament", method = RequestMethod.POST)
	public ResponseEntity<?> tournament(@RequestBody List<Tournament> tournaments) {
		if (tournaments.size() % 2 != 0) {
			return new ResponseEntity<Error>(new Error("The number of participantes must be an even number"), 
					HttpStatus.FORBIDDEN);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/rockpaperscissors/getPlayer", method = RequestMethod.GET)
	public ResponseEntity<Player> get() {
		Player p1 = new Player("Oscar");
		return new ResponseEntity<Player>(p1, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rockpaperscissors/getTournament", method = RequestMethod.GET)
	public ResponseEntity<Tournament> getTournament() {
		
		Game g1 = new Game("Oscar", "S");
		Game g2 = new Game("Allen", "P");
		Game g3 = new Game("Mike", "R");
		Game g4 = new Game("Luis", "S");
		
		ArrayList<Game> games = new ArrayList<Game>();
		
		games.add(g1);
		games.add(g2);
		games.add(g3);
		games.add(g4);
		
		Tournament tournament = new Tournament(games);
		
		return new ResponseEntity<Tournament>(tournament, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/rockpaperscissors/tournament", method = RequestMethod.GET)
	public ResponseEntity<Tournament> checkTournament() {
		
		Game g1 = new Game("Oscar", "S");
		Game g2 = new Game("Allen", "P");
		Game g3 = new Game("Mike", "R");
		Game g4 = new Game("Luis", "S");
		Game g5 = new Game("Mario", "S");
		Game g6 = new Game("Carlos", "P");
		Game g7 = new Game("Juana", "R");
		Game g8 = new Game("Laura", "S");
		
		ArrayList<Game> games = new ArrayList<Game>();
	
		games.add(g1);
		games.add(g2);
		games.add(g3);
		games.add(g4);		
		games.add(g5);
		games.add(g6);
		games.add(g7);
		games.add(g8);
		
		Tournament tournament = new Tournament(games);
		
		ApplicationUtils.resolveTournament(tournament.getGames());
		
		return new ResponseEntity<Tournament>(tournament, HttpStatus.OK);
	}
	
	
	
}
