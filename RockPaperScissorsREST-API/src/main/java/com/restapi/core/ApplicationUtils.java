package com.restapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationUtils {
	
	static final Map<String, String> table = new HashMap<String, String>();
	
	public static Game resolveMatch(List<Game> games) {
		
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
	
	public static boolean validMatch(List<Game> games) {
		List<String> players = getPlayers(games);
		if (duplicatesFound(players)) {
			return false;
		} else {
			return true;
		}
	}
	
}
