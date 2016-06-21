package com.restapi.core;


/**
 * The Class Game.
 */
public class Game {
	
	/** The player name. */
	private String playerName;
	
	/** The strategy. */
	private String strategy;
	
	/**
	 * Instantiates a new game.
	 */
	public Game() {}

	/**
	 * Instantiates a new game.
	 *
	 * @param playerName the player name
	 * @param strategy the strategy
	 */
	public Game(String playerName, String strategy) {
		setPlayerName(playerName);
		setStrategy(strategy);
	}

	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Sets the player name.
	 *
	 * @param playerName the new player name
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Gets the strategy.
	 *
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Sets the strategy.
	 *
	 * @param strategy the new strategy
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [playerName=" + playerName + ", strategy=" + strategy + "]";
	}

}
