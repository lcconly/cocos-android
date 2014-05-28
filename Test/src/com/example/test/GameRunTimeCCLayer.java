package com.example.test;

import java.text.Format;
import java.util.Date;
import java.util.Formatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;

import org.cocos2d.actions.CCScheduler;
import org.cocos2d.actions.UpdateCallback;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabelAtlas;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCRenderTexture;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.text.format.Time;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

public class GameRunTimeCCLayer extends CCLayer
{
	 private CCSprite sprite_game_bg = null;
	 private CCSprite sprite_game_nav = null;
	 private CCSprite sprite_game_pause = null;
	 private CCLabelAtlas lable_game_grade=null;
	 //private Formatter grade_temp=new Formatter();
	 private int game_grade=0;
	 private CCSprite game_button_1=null;
	 private CCSprite game_button_2=null;	 
	 private CCSprite game_button_3=null;
	 private CCSprite game_button_4=null;
	 private int speed=10;
	 private int timer_time=30;
	 private Timer timer_button_1=new Timer();
	 private Timer timer_button_2=new Timer();
	 private Timer timer_button_3=new Timer();
	 private Timer timer_button_4=new Timer();
	 private Timer end_game=new Timer();
	 private Date date=new Date();
	 public GameRunTimeCCLayer() {  
		 this.setIsTouchEnabled(true);
	     init();  
	     game_started();
	     date=new Date();
	     button_1_start();
   		 button_2_start();
   		 button_3_start();
   		 button_4_start();
//   		 end_game_start();
//	        Intent intent = new Intent(CCDirector.sharedDirector().getActivity(), 
//	        		GameEndActivity.class);
//		    CCDirector.sharedDirector().getActivity().startActivity(intent);
//	     end_game.schedule(new end_game_class(), 3000);
	     end_game.schedule(new end_game_class(), 12000);
	 } 
	 class end_game_class extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
				timer_button_1.cancel();
				timer_button_2.cancel();
				timer_button_3.cancel();
				timer_button_4.cancel();
		        Intent intent = new Intent(CCDirector.sharedDirector().getActivity(), 
		        		GameEndActivity.class);
			    CCDirector.sharedDirector().getActivity().startActivity(intent);
		}
		 
	 }
	 private void init() {  
	        sprite_game_bg = CCSprite.sprite("game_bg.png");  
	        // CCSprite游戏精灵类，需要加载一张图片代表游戏精灵  
	  
	        sprite_game_bg.setAnchorPoint(CGPoint.getZero());  
	        // 设置精灵的锚点  
	        // 锚点是设置在屏幕上显示的位置，原点为自身左下角为准，锚点的值乘以被设置锚点的元素宽或高，为移动的距离  
	  
	        sprite_game_bg.setPosition(CGPoint.getZero());  
	        // 设置精灵的坐标，以屏幕的左下角为原点，向右，向上为正方向,属于OpenGL坐标系  
	        this.addChild(sprite_game_bg, 0, 0);
	        // this指代当前对象，即MyCCLayer  
	        // 给当前图层添加一个子元素  
	        // 参数1：子元素对象，参数2：子元素重要性，参数3：子元素的标签(可以通过标签取出该元素)
	        
	        sprite_game_nav = CCSprite.sprite("actionbar_bg.png");
	        
	        sprite_game_nav.setAnchorPoint(0, 0);
	        
	        sprite_game_nav.setPosition(0, 620);
	        
	        this.addChild(sprite_game_nav, 1, 1);
	        
	        sprite_game_pause = CCSprite.sprite("pause.png");
	        
	        sprite_game_pause.setAnchorPoint(0, 0);
	        
	        sprite_game_pause.setPosition(40, 630);
	        
	        this.addChild(sprite_game_pause, 2, 2);
	  
/*	        sprite_helloword = CCSprite.sprite("helloworld.png");  
	        sprite_helloword.setAnchorPoint(CGPoint.getZero());  
	        sprite_helloword.setPosition(  
	                (this.getContentSize().width - sprite_helloword  
	                        .getContentSize().width) / 2,  
	                (this.getContentSize().height - sprite_helloword  
	                        .getContentSize().height) / 2);  
	        // 让其显示最屏幕正中间  
	        this.addChild(sprite_helloword, 1, 1);  
	        // sprite_helloword精灵的重要性为1，而sprite_game_bg的重要性为0  
	        // 所以sprite_helloword会显示在sprite_game_bg的上方  
	        // 换言之就是重要性越高，越优先显示在上层 
	         */
	    }
	 private void game_started(){
		 game_button_1=CCSprite.sprite("touch_button.png");
		 game_button_1.setAnchorPoint(0, 0);
		 game_button_1.setPosition(100, 550);
		 this.addChild(game_button_1, 4, 4);
		 
		 game_button_2=CCSprite.sprite("touch_button.png");
		 game_button_2.setAnchorPoint(0, 0);
		 game_button_2.setPosition(370, 450);
		 this.addChild(game_button_2, 5, 5);
		 
		 game_button_3=CCSprite.sprite("touch_button.png");
		 game_button_3.setAnchorPoint(0, 0);
		 game_button_3.setPosition(370+270, 350);
		 this.addChild(game_button_3, 6, 6);
		 
		 game_button_4=CCSprite.sprite("touch_button.png");
		 game_button_4.setAnchorPoint(0, 0);
		 game_button_4.setPosition(370+270+270, 250);
		 this.addChild(game_button_4, 7, 7);
		 lable_game_grade=CCLabelAtlas.label("12", "digits.png", 28,50, '0');
		 //grade_temp.format("%06d", game_grade);
		 lable_game_grade.setString(""+game_grade);
		 //lable_game_grade.setAnchorPoint(0, 0);
		 lable_game_grade.setPosition(370+270+270, 650);
		 this.addChild(lable_game_grade,8,8);	
		 
	 }
	 @Override
	  public boolean ccTouchesEnded(MotionEvent event) {
	    
		 CGPoint point = this.convertTouchToNodeSpace(event);
		 
		 if(CGRect.containsPoint(sprite_game_pause.getBoundingBox(), point))
		 {
			// CCDirector.sharedDirector().onPause();
			 this.pauseSchedulerAndActions();
			 CCDirector.sharedDirector().replaceScene(GamePauseScene.scene());
			 //CCRenderTexture renderTexture = CCRenderTexture.renderTexture(1280, 720);
			 //renderTexture.begin();
			 //this.getParent().visit(null);
			 //renderTexture.end();
			 
			 //CCDirector.sharedDirector().pushScene(GamePauseScene.scene(renderTexture,true));
		 }
		 if(CGRect.containsPoint(game_button_1.getBoundingBox(), point)
				 &&game_button_1.getPosition().y<100&&game_button_1.getPosition().y>=0){
			 game_grade+=100;
			 //grade_temp.format("%06d", game_grade);
			 lable_game_grade.setString(""+game_grade);
			 //System.out.println("grade: "+game_grade);
			 
		 }
		 if(CGRect.containsPoint(game_button_2.getBoundingBox(), point)
				 &&game_button_2.getPosition().y<100&&game_button_2.getPosition().y>=0){
			 game_grade+=100;
			 //grade_temp.format("%06d", game_grade);
			 lable_game_grade.setString(""+game_grade);
			 //System.out.println("grade: "+game_grade);
		 }
		 if(CGRect.containsPoint(game_button_3.getBoundingBox(), point)
				 &&game_button_3.getPosition().y<100&&game_button_3.getPosition().y>=0){
			 game_grade+=100;
			 //grade_temp.format("%06d", game_grade);
			 lable_game_grade.setString(""+game_grade);
			 //System.out.println("grade: "+game_grade);
		 }
		 if(CGRect.containsPoint(game_button_4.getBoundingBox(), point)
				 &&game_button_4.getPosition().y<100&&game_button_4.getPosition().y>=0){
			 game_grade+=100;
			 //grade_temp.format("%06d", game_grade);
			 lable_game_grade.setString(""+game_grade);
			 //System.out.println("grade: "+game_grade);
		 }
		 return true;
	  }
	 public void button_1_start(){
		 if(timer_button_1==null) 
			 timer_button_1=new Timer();
		 timer_button_1.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date newdate=new Date();
				if(newdate.getSeconds()-date.getSeconds()>0){
					game_button_1.setPosition(100, (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
					//game_button_2.setPosition(370, (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
					//game_button_3.setPosition(370+270, (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
					//game_button_4.setPosition(370+270+270, (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
				}
				if(game_button_1.getPosition().y==0){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game_button_1.setPosition(100, (game_button_1.getPosition().y=550));
					//timer_button_1.cancel();
				}
//				if(newdate.getMinutes()-date.getMinutes()==0
//						&&newdate.getSeconds()-date.getSeconds()>10){
//					timer_button_1.cancel();
//				}
			}
		}, 0, timer_time);
	 }
	 public void button_2_start(){
		 if(timer_button_2==null)
			 timer_button_2=new Timer();
		 timer_button_2.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date newdate=new Date();
				if(newdate.getSeconds()-date.getSeconds()>0){
					//game_button_1.setPosition(100, (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
					game_button_2.setPosition(370, (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
					//game_button_3.setPosition(370+270, (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
					//game_button_4.setPosition(370+270+270, (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
				}
				if(game_button_2.getPosition().y==0){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game_button_2.setPosition(370, (game_button_2.getPosition().y=550));
					//timer_button_2.cancel();
				}
//				if(newdate.getMinutes()-date.getMinutes()==0
//						&&newdate.getSeconds()-date.getSeconds()>10){
//					timer_button_2=null;
//				}

			}
		}, 0, timer_time);
	 }
	 public void button_3_start(){
		 if(timer_button_3==null) 
			 timer_button_3=new Timer();
		 timer_button_3.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date newdate=new Date();
				if(newdate.getSeconds()-date.getSeconds()>0){
					//game_button_1.setPosition(100, (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
					//game_button_2.setPosition(370, (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
					game_button_3.setPosition(370+270, (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
					//game_button_4.setPosition(370+270+270, (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
				}
				if(game_button_3.getPosition().y==0){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game_button_3.setPosition(370+270, (game_button_3.getPosition().y=550));
					//timer_button_3.cancel();
				}
//				if(newdate.getMinutes()-date.getMinutes()==0
//						&&newdate.getSeconds()-date.getSeconds()>10){
//					timer_button_3.cancel();
//				}
			}
		}, 0, timer_time);
	 }
	 public void button_4_start(){
		 if(timer_button_4==null) 
			 timer_button_4=new Timer();
		 timer_button_4.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date newdate=new Date();
				if(newdate.getSeconds()-date.getSeconds()>0){
					//game_button_1.setPosition(100, (game_button_1.getPosition().y=game_button_1.getPosition().y-speed));
					//game_button_2.setPosition(370, (game_button_2.getPosition().y=game_button_2.getPosition().y-speed));
					//game_button_3.setPosition(370+270, (game_button_3.getPosition().y=game_button_3.getPosition().y-speed));
					game_button_4.setPosition(370+270+270, (game_button_4.getPosition().y=game_button_4.getPosition().y-speed));
				}
				if(game_button_4.getPosition().y==0){
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game_button_4.setPosition(370+270+270, (game_button_4.getPosition().y=550));
					//timer_button_4.cancel();
				}
//				if(newdate.getMinutes()-date.getMinutes()==0
//						&&newdate.getSeconds()-date.getSeconds()>10){
//					timer_button_4.cancel();
//				}
			}
		}, 0, timer_time);
	 }
	 public void end_game_start(){
		 end_game.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Date newdate=new Date();
//				if(timer_button_1==null&&timer_button_2==null
//						&&timer_button_3==null&&timer_button_4==null){
				if(date.getSeconds()-newdate.getSeconds()>10){
			        Intent intent = new Intent(CCDirector.sharedDirector().getActivity(), 
			        		GameEndActivity.class);
				    CCDirector.sharedDirector().getActivity().startActivity(intent);
				}
			}
		}, 0, timer_time);
	 }
}