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
	static ArrayList<Game> match = new ArrayList<Game>();
	static ArrayList<Game> winners = new ArrayList<Game>();
	
	public static ArrayList<Game> resolveMatch(List<Game> games) {
		
		Game g1 = games.get(0);
		Game g2 = games.get(1);
		Game firstPlace = new Game();
		Game secondPlace = new Game();
		
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
			firstPlace.setPlayerName(winnerData[0].trim());
			firstPlace.setStrategy(winnerData[1].trim());
			
			if (winnerData[0].trim().equals(g1.getPlayerName())) {
				secondPlace.setPlayerName(g2.getPlayerName());
				secondPlace.setStrategy(g2.getStrategy());
			} else if (winnerData[0].trim().equals(g2.getPlayerName())) {
				secondPlace.setPlayerName(g1.getPlayerName());
				secondPlace.setStrategy(g1.getStrategy());
			}
			
			ArrayList<Game> results = new ArrayList<Game>();
			results.add(firstPlace);
			results.add(secondPlace);
			
			return results;
			
		} else {
			return null;
		}
	}
	
	public static ArrayList<Game> resolveTournament(List<Game> games) {
		int count = 0;
		for (Game game : games) {
			match.add(game);
			count++;
			if (count % 2 == 0) {
				winners.add(resolveMatch(match).get(0));
				match.remove(0);
				match.remove(0);
				count = 0;
			}
		}
		ArrayList<Game> list = new ArrayList<Game>();
		for(Game g : winners) {
			Game data = new Game(g.getPlayerName(), g.getStrategy());
			list.add(data);
		}
		winners.clear();
		if(list.size() == 2) {
			winners = resolveMatch(list);
		} else {
			resolveTournament(list);
		}	
		
		return winners;
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
