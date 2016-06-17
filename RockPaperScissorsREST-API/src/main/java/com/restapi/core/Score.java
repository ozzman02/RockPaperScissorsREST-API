package com.restapi.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Score {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String playerName;
	
	private int score;

	public Score() { }

	public Score(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Scores [id=" + id + ", playerName=" + playerName + ", score=" + score + "]";
	}
		
}
