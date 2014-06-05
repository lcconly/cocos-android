package com.example.test;

public class NameScore {
	
	private String name;
	private int score;
	
	public NameScore(String nameString,String scoreString) {
		
		name=nameString;
		score=Integer.parseInt(scoreString);
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
}
