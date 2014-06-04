package com.example.test;

import java.util.Timer;
import java.util.TimerTask;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.R.integer;
import android.nfc.Tag;
import android.view.MotionEvent;

public class Button_class extends CCLayer{
	private CCSprite buttonCcSprite;
	private int id;
	private Timer timer;
	private int speed=10;
	private int kind;
	private int place;
	private int[] grade;
	private int start_time;
	private int tags=0;
	private int sleep_time;
	GameRunTimeCCLayer ccLayer;
	TimerTask aTask;
	public CCSprite get_CCS(){
		return buttonCcSprite;
	}
	public void set_sleep(int sleep_time){
		this.sleep_time=sleep_time;
	}
	public Timer getTimer(){
		return timer;
	}
	public void set_tag(int tag) {
		this.tags=tag;
	}
	public Button_class(int id,int kind,int place,int start_time,int[] grade,GameRunTimeCCLayer ccLayer){
		this.id=id;
		tags=0;
		this.kind=kind;
		this.place=place;
		this.start_time=start_time;
		this.grade=grade;
		this.ccLayer=ccLayer;
		aTask=new button_start();
		timer=new Timer();
	} 
	public void start_run(){
		if(start_time>0){

			timer.schedule(aTask, start_time);			
		}	
	}
	class button_start extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(tags==1){
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(tags==4){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(sleep_time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tags=0;
			}
			if(kind==1)
				 buttonCcSprite=CCSprite.sprite("touch_button.png");
			else if(kind==2)
				buttonCcSprite=CCSprite.sprite("touch_button_red.png");
			if(place==1){
				 while(GameRunTimeCCLayer.tag[0]!=-1){
					 try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 GameRunTimeCCLayer.lock.lock();
				 buttonCcSprite.setAnchorPoint(0, 0);
				 buttonCcSprite.setPosition(100, 550);
				 Button_class.this.ccLayer.addChild(buttonCcSprite, 4, 4);
				 GameRunTimeCCLayer.tag[0]=id;
				 GameRunTimeCCLayer.lock.unlock();
			}
			else if(place==2){
				 while(GameRunTimeCCLayer.tag[1]!=-1){
					 try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 GameRunTimeCCLayer.lock.lock();
				 buttonCcSprite.setAnchorPoint(0, 0);
				 buttonCcSprite.setPosition(370, 550);
				 Button_class.this.ccLayer.addChild(buttonCcSprite, 5, 5);
				 GameRunTimeCCLayer.tag[1]=id;
				 GameRunTimeCCLayer.lock.unlock();

			}
			else if(place==3){
				 while(GameRunTimeCCLayer.tag[2]!=-1){
					 try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 GameRunTimeCCLayer.lock.lock();
				 buttonCcSprite.setAnchorPoint(0, 0);
				 buttonCcSprite.setPosition(370+270, 550);
				 Button_class.this.ccLayer.addChild(buttonCcSprite, 6, 6);
				 GameRunTimeCCLayer.tag[2]=id;
				 GameRunTimeCCLayer.lock.unlock();
			}
			else if(place==4){
				 while(GameRunTimeCCLayer.tag[3]!=-1){
					 try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 GameRunTimeCCLayer.lock.lock();
				 buttonCcSprite.setAnchorPoint(0, 0);
				 buttonCcSprite.setPosition(370+270+270, 550);
				 Button_class.this.ccLayer.addChild(buttonCcSprite, 7, 7);
				 GameRunTimeCCLayer.tag[3]=id;
				 GameRunTimeCCLayer.lock.unlock();
			}
			while(true){
				if(tags==1){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					continue;
				}
				if(tags==3){
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tags=0;
				}
				if(tags==2)
					break;
				buttonCcSprite.setPosition(buttonCcSprite.getPosition().x, (buttonCcSprite.getPosition().y=buttonCcSprite.getPosition().y-speed));
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(buttonCcSprite.getPosition().y==-50){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					buttonCcSprite.setPosition(buttonCcSprite.getPosition().x, -400);
//					 GameRunTimeCCLayer.lable_game_grade.setString(""+100);
					if(buttonCcSprite.getPosition().x==100&&GameRunTimeCCLayer.tag[0]!=-1){
						 GameRunTimeCCLayer.lock.lock();
						GameRunTimeCCLayer.tag[0]=-1;
				        ccLayer.removeChild(buttonCcSprite, true);
						 GameRunTimeCCLayer.lock.unlock();
					}
					else if(buttonCcSprite.getPosition().x==370&&GameRunTimeCCLayer.tag[1]!=-1){
						 GameRunTimeCCLayer.lock.lock();
						GameRunTimeCCLayer.tag[1]=-1;
				        ccLayer.removeChild(buttonCcSprite, true);
						 GameRunTimeCCLayer.lock.unlock();

					}
					if(buttonCcSprite.getPosition().x==370+270&&GameRunTimeCCLayer.tag[2]!=-1){
						 GameRunTimeCCLayer.lock.lock();
						GameRunTimeCCLayer.tag[2]=-1;
				        ccLayer.removeChild(buttonCcSprite, true);
						 GameRunTimeCCLayer.lock.unlock();
					}
					if(buttonCcSprite.getPosition().x==370+270+270&&GameRunTimeCCLayer.tag[3]!=-1){
						 GameRunTimeCCLayer.lock.lock();
						GameRunTimeCCLayer.tag[3]=-1;
				        ccLayer.removeChild(buttonCcSprite, true);
						 GameRunTimeCCLayer.lock.unlock();
					}
					break;
					//timer_button_1.cancel();
				}
			}
			
		}	
	}
//	 @Override
//	  public boolean ccTouchesEnded(MotionEvent event) {
//	    
//		 CGPoint point = this.ccLayer.convertTouchToNodeSpace(event);
//		 System.out.println(point);
//		 if(CGRect.containsPoint(buttonCcSprite.getBoundingBox(), point)
//				 &&buttonCcSprite.getPosition().y<100&&buttonCcSprite.getPosition().y>=0){
//			 grade[0]+=100;
//			 //grade_temp.format("%06d", game_grade);
//			 GameRunTimeCCLayer.lable_game_grade.setString(""+grade[0]);
//			 //System.out.println("grade: "+game_grade);
//		 }
//		return true;
//	 }
	 public void end_time(){
		 tags=2;
		 timer.cancel();
	 }
}
