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
		CCGLSurfaceView ccglSurfaceView = new CCGLSurfaceView(this);//cocos2d�ṩ��SurfaceView 

		setContentView(ccglSurfaceView);//����Activity��ʾ��view
		CCDirector.sharedDirector().setDisplayFPS(false);
		CCDirector.sharedDirector().attachInView(ccglSurfaceView);
		CCDirector.sharedDirector().setAnimationInterval(1.0f / 60);
		CCDirector.sharedDirector().setDeviceOrientation(  
                CCDirector.kCCDeviceOrientationLandscapeLeft);
		
		
		CCDirector.sharedDirector().setScreenSize(1280, 720);
		
		CCScene ccScene = CCScene.node();  //����һ��������������ʾ��Ϸ����  
        ccScene.addChild(new GameRunTimeCCLayer()); //��MyCCLayer��ӵ�������  
        CCDirector.sharedDirector().runWithScene(ccScene);// ���г���
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
        //�ָ���Ϸ����  
        // cocos2d�ṩ3���������ڷ�������Ӧandroid��������������  
    }  
  
    @Override  
    protected void onPause() {  
        super.onPause();  
        CCDirector.sharedDirector().pause();  
        //��ͣ����Ϸ�г�ʱ�����  
    }  
  
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        CCDirector.sharedDirector().end();  
        // ��������Ϸ�˳�ʱ����  
    }
}
