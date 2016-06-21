package com.restapi.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class ApplicationRestController.
 */
@RestController
public class ApplicationRestController {
	
	/** The Constant firstPlaceScore. */
	private static final int firstPlaceScore = 3;
	
	/** The Constant secondPlaceScore. */
	private static final int secondPlaceScore = 1;
	
	/** The score repository. */
	@Autowired
	ScoreRepository scoreRepository;
	
	/**
	 * Single match.
	 *
	 * @param games the games
	 * @return the response entity
	 */
	@RequestMapping(value = "/rockpaperscissors/singlematch", method = RequestMethod.POST)
	public ResponseEntity<?> singleMatch(@RequestBody List<Game> games) {	
		
		if (games.size() % 2 != 0) 
		{
			return new ResponseEntity<Error>(new Error("The number of participantes must be an even number"),
				HttpStatus.BAD_REQUEST);
		} 
		else if (!ApplicationUtils.validStrategies(games)) 
		{
			return new ResponseEntity<Error>(new Error("Invalid strategies found."), 
				HttpStatus.BAD_REQUEST);
		} 
		else if (ApplicationUtils.validMatch(games)) 
		{ 
			ArrayList<Game> results = ApplicationUtils.resolveMatch(games);
			Game winner = results.get(0);
			return new ResponseEntity<Game>(winner, HttpStatus.OK);
		} 
		else 
		{
			return new ResponseEntity<Error>(new Error("There are duplicate player names"), 
				HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Championship result.
	 *
	 * @param tournament the tournament
	 * @return the response entity
	 */
	@RequestMapping(value = "/rockpaperscissors/championship/result", method = RequestMethod.POST)
	public ResponseEntity<?> championshipResult(@RequestBody Tournament tournament) {
		
		if (tournament.getGames().size() % 2 != 0) 
		{
			return new ResponseEntity<Error>(new Error("The The number of participantes must be an even number"),
				HttpStatus.BAD_REQUEST);
		} 
		else if (!ApplicationUtils.validStrategies(tournament.getGames())) 
		{
			return new ResponseEntity<Error>(new Error("Invalid strategies found."), 
				HttpStatus.BAD_REQUEST);
		} 
		else if (ApplicationUtils.validMatch(tournament.getGames())) 
		{
			ArrayList<Game> results = ApplicationUtils.resolveTournament(tournament.getGames());
			Game champion = results.get(0);
			updatePlayerScores(results.get(0).getPlayerName(), results.get(1).getPlayerName());
			return new ResponseEntity<Game>(champion, HttpStatus.OK);
		} 
		else {
			return new ResponseEntity<Error>(new Error("There are duplicate player names"), 
				HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	@RequestMapping(value = "/rockpaperscissors/getPlayer", method = RequestMethod.GET)
	public ResponseEntity<Player> getPlayer() {
		Player p1 = new Player("Oscar");
		return new ResponseEntity<Player>(p1, HttpStatus.OK);
	}
				
	/**
	 * Check tournament.
	 *
	 * @return the response entity
	 */
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
	
	/**
	 * Gets the scores.
	 *
	 * @return the scores
	 */
	@RequestMapping(value = "/rockpaperscissors/getScores", method = RequestMethod.GET)
	public ResponseEntity<?> getScores() {
		return new ResponseEntity<List<Score>>(this.scoreRepository.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Gets the top scores.
	 *
	 * @param count the count
	 * @return the top scores
	 */
	@RequestMapping(value = "/rockpaperscissors/championship/top", method = RequestMethod.GET)
	public ResponseEntity<?> getTopScores(@RequestParam(value="count") Integer count) {
		
		List<Score> scores = this.scoreRepository.findAll();
		
		if (scores.size() == 0) 
		{
			return new ResponseEntity<Error>(new Error("Empty database"), 
				HttpStatus.BAD_REQUEST);
		} 
		else if (scores.size() < count) 
		{
			return new ResponseEntity<Error>(new Error("Top parameter exceeds max database records count"), 
				HttpStatus.BAD_REQUEST);
		} 
		else if (count == 0) {
			return new ResponseEntity<Error>(new Error("Top parameter value can't be 0"), 
				HttpStatus.BAD_REQUEST);
		} 
		else 
		{
			List<Score> results = ApplicationUtils.sortList(scores, count);
			return new ResponseEntity<List<Score>>(results, HttpStatus.OK);
		}
		
	}
	
	/**
	 * Gets the player score by name.
	 *
	 * @param playerName the player name
	 * @return the player score by name
	 */
	@RequestMapping(value = "/rockpaperscissors/getPlayerScoreByName/{playerName}", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public Score getPlayerScoreByName(@PathVariable(value="playerName") String playerName) {
		Score entity = this.scoreRepository.findScoreByPlayerName(playerName);
		return entity;
	}
	
	/**
	 * Update player scores.
	 *
	 * @param playerName1 the player name 1
	 * @param playerName2 the player name 2
	 * @return the response entity
	 */
	@RequestMapping(value = "/rockpaperscissors/championShip/storeWinners", method = RequestMethod.POST)
	public ResponseEntity<?> updatePlayerScores(@RequestParam String playerName1, @RequestParam String playerName2) {
		
		if (playerName1.equals("") || playerName2.equals("")) 
		{
			return new ResponseEntity<Error>(new Error("Error, empty parameters"), HttpStatus.BAD_REQUEST);
		} 
		else 
		{
			Score p1Entity = getPlayerScoreByName(playerName1);
			Score p2Entity = getPlayerScoreByName(playerName2);
			if (p1Entity == null) 
			{
				Score s1 = new Score(playerName1, firstPlaceScore);
				this.scoreRepository.save(s1);
			} 
			else 
			{
				int newScore1 = p1Entity.getScore();	
				newScore1 += firstPlaceScore;
				p1Entity.setScore(newScore1);
				this.scoreRepository.save(p1Entity);
			}
			if (p2Entity == null) 
			{
				Score s2 = new Score(playerName2, secondPlaceScore);
				this.scoreRepository.save(s2);
			} 
			else 
			{
				int newScore2 = p2Entity.getScore();	
				newScore2 += secondPlaceScore;
				p2Entity.setScore(newScore2);
				this.scoreRepository.save(p2Entity);
			}
			
		}
			
		return new ResponseEntity<Success>(new Success("Success"), HttpStatus.OK);		
	
	}
	
	/**
	 * Delete data.
	 *
	 * @return the response entity
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rockpaperscissors/clearData", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteData() {
		
		ResponseEntity<?> scores = getScores();
		
		List<Score> list = (List<Score>) scores.getBody();
		
		if (list.size() > 0) 
		{
			this.scoreRepository.deleteAll();
			return new ResponseEntity<Success>(new Success("Data has been deleted"), HttpStatus.OK);   
		} 
		else 
		{
			return new ResponseEntity<Error>(new Error("Database does not have data"), HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
