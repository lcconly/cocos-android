package com.example.test;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.view.MotionEvent;


public class GamePauseScene extends CCLayer {
	private CCSprite sprite_game_bg = null;
	private CCSprite sprite_pause_bg = null;
	private CCSprite sprite_pause_continue = null;
	private CCSprite sprite_pause_back = null;
	private CCSprite sprite_pause_home = null;
	public GamePauseScene()	{
		 this.setIsTouchEnabled(true);
		sprite_game_bg = CCSprite.sprite("pause_main_bg.png");  
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
		sprite_pause_bg = CCSprite.sprite("pause_bg.png");
		
		sprite_pause_bg.setAnchorPoint(0, 0);
		
		sprite_pause_bg.setPosition(390, 50);
		
		addChild(sprite_pause_bg, 1, 1);
		
		sprite_pause_continue = CCSprite.sprite("button_continue.png");
		
		sprite_pause_continue.setAnchorPoint(0, 0);
		
		sprite_pause_continue.setPosition(490, 350);
		
		addChild(sprite_pause_continue, 2, 2);
		
		sprite_pause_back = CCSprite.sprite("button_back.png");
		
		sprite_pause_back.setAnchorPoint(0, 0);
		
		sprite_pause_back.setPosition(490, 250);
		
		addChild(sprite_pause_back, 2, 3);
		
		sprite_pause_home = CCSprite.sprite("button_home.png");
		
		sprite_pause_home.setAnchorPoint(0, 0);
		
		sprite_pause_home.setPosition(490, 150);
		
		addChild(sprite_pause_home, 2, 4);
		
	}
	public static CCScene scene(){
		CCScene m_scene = CCScene.node();
		  m_scene.addChild(new GamePauseScene());
		  
		  return m_scene;
	}
	 @Override
	  public boolean ccTouchesEnded(MotionEvent event) {
	    
		 CGPoint point = this.convertTouchToNodeSpace(event);
		 
		 if(CGRect.containsPoint(sprite_pause_continue.getBoundingBox(), point))
		 {
			 CCDirector.sharedDirector().onResume();
			 //CCRenderTexture renderTexture = CCRenderTexture.renderTexture(1280, 720);
			 //renderTexture.begin();
			 //this.getParent().visit(null);
			 //renderTexture.end();
			 //CCDirector.sharedDirector().pushScene(GamePauseScene.scene(renderTexture,true));
		 }
		 return true;
	}
}