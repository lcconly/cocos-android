package com.example.test;

import android.R.integer;


public class line_read {
	private int place;
	private int start_time;
	public int get_place(){
		return place;
	}
	public int get_start_time(){
		return start_time;
	}
	public void init(String line){
		String[] aString=line.split(" ");
		place=Integer.parseInt(aString[0]);
		start_time=Integer.parseInt(aString[1]);
	}
}
