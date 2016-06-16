package com.restapi.core;

public class Tournament {
	
	private int id;
	private Player player;
	private int score;
	
	public Tournament() {}
	
	public Tournament(int id, Player player, int score) {
		setId(id);
		setPlayer(player);
		setScore(score);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Tournament [id=" + id + ", player=" + player + ", score="
				+ score + "]";
	}
}
