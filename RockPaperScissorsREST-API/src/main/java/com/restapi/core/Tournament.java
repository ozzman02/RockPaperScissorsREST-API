package com.restapi.core;

import java.util.ArrayList;

/**
 * The Class Tournament.
 */
public class Tournament {
	
	/** The games. */
	private ArrayList<Game> games;
	
	/**
	 * Instantiates a new tournament.
	 */
	public Tournament() {}

	/**
	 * Instantiates a new tournament.
	 *
	 * @param games the games
	 */
	public Tournament(ArrayList<Game> games) {
		this.games = games;
	}

	/**
	 * Gets the games.
	 *
	 * @return the games
	 */
	public ArrayList<Game> getGames() {
		return games;
	}

	/**
	 * Sets the games.
	 *
	 * @param games the new games
	 */
	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tournament [games=" + games + "]";
	}
	
}
