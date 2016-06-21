package com.restapi.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Score.
 */
@Entity
public class Score {
	
	/** The id. */
	@Id
	@GeneratedValue
	private Long id;
	
	/** The player name. */
	private String playerName;
	
	/** The score. */
	private int score;

	/**
	 * Instantiates a new score.
	 */
	public Score() { }

	/**
	 * Instantiates a new score.
	 *
	 * @param playerName the player name
	 * @param score the score
	 */
	public Score(String playerName, int score) {
		this.playerName = playerName;
		this.score = score;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + score;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		if (score != other.score)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Scores [id=" + id + ", playerName=" + playerName + ", score=" + score + "]";
	}
		
}
