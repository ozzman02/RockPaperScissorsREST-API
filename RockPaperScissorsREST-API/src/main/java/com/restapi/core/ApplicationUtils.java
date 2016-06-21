package com.restapi.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The Class ApplicationUtils.
 */
public class ApplicationUtils {
	
	/** The Constant table. */
	static final Map<String, String> table = new HashMap<String, String>();
	
	/** The match. */
	static ArrayList<Game> match = new ArrayList<Game>();
	
	/** The winners. */
	static ArrayList<Game> winners = new ArrayList<Game>();
	
	/**
	 * Duplicates found.
	 *
	 * @param players the players
	 * @return true, if successful
	 */
	private static boolean duplicatesFound(List<String> players) {
		
		Set<String> set = new HashSet<String>(players);
		
		if (set.size() < players.size()) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
		
	}
	
	/**
	 * Gets the players.
	 *
	 * @param games the games
	 * @return the players
	 */
	private static List<String> getPlayers(List<Game> games) {
		
		List<String> players = new ArrayList<String>();
		
		Iterator<Game> it = games.iterator();
		
		while (it.hasNext()) 
		{
			players.add(it.next().getPlayerName());
		}
		
		return players;
	}
	
	/**
	 * Gets the strategies.
	 *
	 * @param games the games
	 * @return the strategies
	 */
	private static List<String> getStrategies(List<Game> games) {
		
		List<String> strategies = new ArrayList<String>();
		
		Iterator<Game> it = games.iterator();
		
		while (it.hasNext()) 
		{
			strategies.add(it.next().getStrategy());
		}
		
		return strategies;
	}
	
	/**
	 * Resolve match.
	 *
	 * @param games the games
	 * @return the array list
	 */
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
			
			if (winnerData[0].trim().equals(g1.getPlayerName())) 
			{
				secondPlace.setPlayerName(g2.getPlayerName());
				secondPlace.setStrategy(g2.getStrategy());
			} 
			else if (winnerData[0].trim().equals(g2.getPlayerName())) 
			{
				secondPlace.setPlayerName(g1.getPlayerName());
				secondPlace.setStrategy(g1.getStrategy());
			}
			
			ArrayList<Game> results = new ArrayList<Game>();
			results.add(firstPlace);
			results.add(secondPlace);
			
			return results;
			
		} 
		else 
		{
			return null;
		}
		
	}
	
	/**
	 * Resolve tournament.
	 *
	 * @param games the games
	 * @return the array list
	 */
	public static ArrayList<Game> resolveTournament(List<Game> games) {
		
		int count = 0;
		ArrayList<Game> list = new ArrayList<Game>();
		
		if (games.size() == 2) 
		{
			return resolveMatch(games);
		} 
		else 
		{
			for (Game game : games) 
			{
				match.add(game);
				count++;
				if (count % 2 == 0) 
				{
					winners.add(resolveMatch(match).get(0));
					match.clear();
					count = 0;
				}
			}
			for(Game g : winners) 
			{
				Game data = new Game(g.getPlayerName(), g.getStrategy());
				list.add(data);
			}
			
			winners.clear();
			
			return resolveTournament(list);
		}
		
	}

	/**
	 * Valid match.
	 *
	 * @param games the games
	 * @return true, if successful
	 */
	public static boolean validMatch(List<Game> games) {
		
		List<String> players = getPlayers(games);
		
		if (duplicatesFound(players)) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
		
	}
	
	/**
	 * Valid strategies.
	 *
	 * @param games the games
	 * @return true, if successful
	 */
	public static boolean validStrategies(List<Game> games) {
		
		boolean found = false;
		
		List<String> strategies = getStrategies(games);
		
		Iterator<String> it = strategies.iterator();
		
		while (it.hasNext() && !found) 
		{
			String strategy = it.next();
			
			if (!strategy.equalsIgnoreCase("P") && !strategy.equalsIgnoreCase("S") 
					&& !strategy.equalsIgnoreCase("R")) 
			{
				found = true;
			}
		}
		
		if (found) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
		
	}
	
	/**
	 * Sort list.
	 *
	 * @param scores the scores
	 * @param count the count
	 * @return the list
	 */
	public static List<Score> sortList(List<Score> scores, Integer count) {
		
		List<Score> results = new ArrayList<Score>();
		int index = 0;
		
		Collections.sort(scores, new Comparator<Score>() {
			
			@Override
			public int compare(Score o1, Score o2) {
				return (Integer.valueOf(o2.getScore()).compareTo(Integer.valueOf(o1.getScore())));
			}
			
		});
		
		Iterator<Score> it = scores.iterator();
		
		while (it.hasNext() && index < count) 
		{
			results.add(it.next());
			index++;
		}
		
		return results;
	}
	
}
