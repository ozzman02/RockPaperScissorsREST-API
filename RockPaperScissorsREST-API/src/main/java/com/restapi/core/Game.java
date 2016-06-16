package com.restapi.core;


public class Game {
	
	private String playerName;
	private String strategy;
	
	public Game() {}

	public Game(String playerName, String strategy) {
		setPlayerName(playerName);
		setStrategy(strategy);
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	@Override
	public String toString() {
		return "Game [playerName=" + playerName + ", strategy=" + strategy + "]";
	}

}
