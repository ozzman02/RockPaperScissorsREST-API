package com.restapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingleMatchRestController {
	
	static final Map<String, String> table = new HashMap<String, String>();
	
	@RequestMapping(value = "/rockpaperscissors/singlematch", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody List<Game> games) {
		if (games.size() % 2 != 0) {
			return new ResponseEntity<Error>(new Error("The number of participantes must be an even number"), 
					HttpStatus.FORBIDDEN);
		} else if (validMatch(games)) { 
			Game game = resolveMatch(games);
			return new ResponseEntity<Game>(game, HttpStatus.OK);
		} else {
			return new ResponseEntity<Error>(new Error("There are duplicate player names"), 
					HttpStatus.FORBIDDEN);
		}
	}
	
	@RequestMapping(value = "/rockpaperscissors/getPlayer", method = RequestMethod.GET)
	public ResponseEntity<Player> get() {
		Player p1 = new Player("Oscar");
		return new ResponseEntity<Player>(p1, HttpStatus.OK);
	}
	
	private static Game resolveMatch(List<Game> games) {
		
		Game g1 = games.get(0);
		Game g2 = games.get(1);
		
		table.put("P,R", g1.getPlayerName() + "," + g1.getStrategy());
		table.put("P,S", g2.getPlayerName() + "," + g2.getStrategy());
		table.put("P,P", g1.getPlayerName() + "," + g1.getStrategy());
		table.put("R,P", g2.getPlayerName() + "," + g2.getStrategy());
		table.put("R,S", g1.getPlayerName() + "," + g1.getStrategy());
		table.put("R,R", g1.getPlayerName() + "," + g1.getStrategy());
		table.put("S,P", g1.getPlayerName() + "," + g1.getStrategy());
		table.put("S,R", g2.getPlayerName() + "," + g2.getStrategy());
		table.put("S,S", g1.getPlayerName() + "," + g1.getStrategy());
		
		String player1Strategy = g1.getStrategy();
		String player2Strategy = g2.getStrategy();
		
		String key = player1Strategy + "," + player2Strategy;
		
		if(table.containsKey(key)) {
			String[] winnerData = table.get(key).split(",");
			Game winnerGame = new Game(winnerData[0].trim(), winnerData[1].trim());
			return winnerGame;
		} else {
			return null;
		}
	}
	
	private static boolean duplicatesFound(List<String> players) {
		Set<String> set = new HashSet<String>(players);
		if (set.size() < players.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	private static List<String> getPlayers(List<Game> games) {
		List<String> players = new ArrayList<String>();
		Iterator<Game> it = games.iterator();
		while (it.hasNext()) {
			players.add(it.next().getPlayerName());
		}
		return players;
	}
	
	private static boolean validMatch(List<Game> games) {
		List<String> players = getPlayers(games);
		if (duplicatesFound(players)) {
			return false;
		} else {
			return true;
		}
	}
	
}
