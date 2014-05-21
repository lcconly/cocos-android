package com.example.test;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCRenderTexture;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;
import android.widget.Toast;

public class GameRunTimeCCLayer extends CCLayer
{
	 private CCSprite sprite_game_bg = null;
	 private CCSprite sprite_game_nav = null;
	 private CCSprite sprite_game_pause = null;
	 
	 public GameRunTimeCCLayer() {  
		 this.setIsTouchEnabled(true);
	     init();  
	    } 
	 
	 private void init() {  
	        sprite_game_bg = CCSprite.sprite("game_bg.png");  
	        // CCSprite��Ϸ�����࣬��Ҫ����һ��ͼƬ������Ϸ����  
	  
	        sprite_game_bg.setAnchorPoint(CGPoint.getZero());  
	        // ���þ����ê��  
	        // ê������������Ļ����ʾ��λ�ã�ԭ��Ϊ�������½�Ϊ׼��ê���ֵ���Ա�����ê���Ԫ�ؿ��ߣ�Ϊ�ƶ��ľ���  
	  
	        sprite_game_bg.setPosition(CGPoint.getZero());  
	        // ���þ�������꣬����Ļ�����½�Ϊԭ�㣬���ң�����Ϊ������,����OpenGL����ϵ  
	  
	        this.addChild(sprite_game_bg, 0, 0);
	        // thisָ����ǰ���󣬼�MyCCLayer  
	        // ����ǰͼ�����һ����Ԫ��  
	        // ����1����Ԫ�ض��󣬲���2����Ԫ����Ҫ�ԣ�����3����Ԫ�صı�ǩ(����ͨ����ǩȡ����Ԫ��)
	        
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
	        // ������ʾ����Ļ���м�  
	        this.addChild(sprite_helloword, 1, 1);  
	        // sprite_helloword�������Ҫ��Ϊ1����sprite_game_bg����Ҫ��Ϊ0  
	        // ����sprite_helloword����ʾ��sprite_game_bg���Ϸ�  
	        // ����֮������Ҫ��Խ�ߣ�Խ������ʾ���ϲ� 
	         */
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
		 return true;
	  }
}