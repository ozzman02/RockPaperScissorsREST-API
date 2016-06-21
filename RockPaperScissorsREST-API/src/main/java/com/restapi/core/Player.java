package com.restapi.core;

/**
 * The Class Player.
 */
public class Player {
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new player.
	 */
	public Player() {}
		
	/**
	 * Instantiates a new player.
	 *
	 * @param name the name
	 */
	public Player(String name) {
		setName(name);
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}
	
}
