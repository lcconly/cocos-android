package com.example.test;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CCGLSurfaceView ccglSurfaceView = new CCGLSurfaceView(this);//cocos2d提供的SurfaceView 

		setContentView(ccglSurfaceView);//设置Activity显示的view
		CCDirector.sharedDirector().setDisplayFPS(false);
		CCDirector.sharedDirector().attachInView(ccglSurfaceView);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);
		CCDirector.sharedDirector().setDeviceOrientation(  
                CCDirector.kCCDeviceOrientationLandscapeLeft);
		
		
		CCDirector.sharedDirector().setScreenSize(1280, 720);
		
		CCScene ccScene = CCScene.node();  //创建一个场景，用来显示游戏界面  
        ccScene.addChild(new GameRunTimeCCLayer()); //将MyCCLayer层加到场景里  
        CCDirector.sharedDirector().runWithScene(ccScene);// 运行场景
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		new GameRunTimeCCLayer().cancel_all();
	}
	
	@Override  
    protected void onResume() {  
        super.onResume();  
        CCDirector.sharedDirector().resume();  
        //恢复游戏运行  
        // cocos2d提供3个生命周期方法，对应android的三个生命周期  
    }  
  
    @Override  
    protected void onPause() {  
        super.onPause();  
        CCDirector.sharedDirector().pause();  
        //暂停，游戏切出时候调用  
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        CCDirector.sharedDirector().end();  
        // 结束，游戏退出时调用  
    }
}
