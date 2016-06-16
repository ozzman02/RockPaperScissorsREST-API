package com.restapi.core;

public class Player {
	
	private String name;
	
	public Player() {}
		
	public Player(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + "]";
	}
}
