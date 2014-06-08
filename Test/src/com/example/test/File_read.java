package com.example.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabelAtlas;

import android.R.integer;
import android.R.string;
import android.os.IInterface;
import android.util.Log;




public class File_read {
	private int button_num;
	private int end_time;
	private line_read[] line;
	public int get_button_num(){
		return button_num;
	}
	public int get_end_time(){
		return end_time;
	}
	public int get_certain_palce(int i){
		return line[i].get_place();
	}
	public int get_certain_start_time(int i){
		return line[i].get_start_time();
	}
	public void init(String name){
		BufferedReader bufferedReader = null;
		InputStreamReader inputStreamReader = null; 
		InputStream inputStream=null;
		try {
			if(name.equals("music_1.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
					.getResources().openRawResource(R.raw.music_1);  
			else if(name.equals("music_2.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
					.getResources().openRawResource(R.raw.music_2);  
			else if(name.equals("music_3.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
					.getResources().openRawResource(R.raw.music_3);  
			else if(name.equals("cktddxdgl_1.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
				.getResources().openRawResource(R.raw.cktsdxdgl_1);  
			else if(name.equals("cktddxdgl_2.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
				.getResources().openRawResource(R.raw.cktsdxdgl_2);  
			else if(name.equals("cktddxdgl_3.txt"))
				inputStream = CCDirector.sharedDirector().getActivity()
				.getResources().openRawResource(R.raw.cktsdxdgl_3);  
			inputStreamReader = new InputStreamReader(inputStream, "gbk");
			bufferedReader = new BufferedReader(inputStreamReader);
			try {
				String read = null;
				if((read = bufferedReader.readLine()) != null) 
					button_num=Integer.parseInt(read);
				if((read = bufferedReader.readLine()) != null) 
					end_time=Integer.parseInt(read);
				line=new line_read[button_num];
				int i=0;
				while((read = bufferedReader.readLine()) != null) {
					line[i]=new line_read();
					line[i].init(read);
					i++;
				}
			} catch (Exception e) {
				System.out.println("!!!!xx");
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("!!!!asasa");
			e.printStackTrace();
		} finally {
			if(inputStream!=null){
			try {
				inputStream.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			if(inputStreamReader!=null){
			try {
				inputStreamReader.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
