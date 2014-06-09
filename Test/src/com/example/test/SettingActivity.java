package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
//import android.view.KeyEvent;
//import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingActivity extends ActionBarActivity {
	
	private MediaPlayer player;  
	private AudioManager audiomanager;
	private SeekBar soundBar;  
	private int maxVolume;
	private int currentVolume;

	//private int volume=0;  //初始化声音
	
	private boolean isScool=false; //seekbar是否正在滑动
	
	private BroadcastReceiver soundReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			if ("android.media.VOLUME_CHANGED_ACTION".equals(intent.getAction()) && !isScool) {

				//int type = intent.getIntExtra(
				//		"android.media.EXTRA_VOLUME_STREAM_TYPE", 0);
				int index = intent.getIntExtra(
						"android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
				//int oldIndex = intent.getIntExtra(
				//		"android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0);
				soundBar.setProgress(index);
				//Log.e(MainActivity.class.getName(), "type=" + type
				//		+ "   index=" + index + "    oldIndex=" + oldIndex);
				//android.media.VOLUME_CHANGED_ACTION
				//Intent intent = new Intent(AudioManager.VOLUME_CHANGED_ACTION);
				// android.media.EXTRA_VOLUME_STREAM_TYPE
				// intent.putExtra(AudioManager.EXTRA_VOLUME_STREAM_TYPE,
				// streamType);
				// android.media.EXTRA_VOLUME_STREAM_VALUE
				// intent.putExtra(AudioManager.EXTRA_VOLUME_STREAM_VALUE,
				// index);
				// android.media.EXTRA_PREV_VOLUME_STREAM_VALUE
				// intent.putExtra(AudioManager.EXTRA_PREV_VOLUME_STREAM_VALUE,
				// oldIndex);
			}

		}
	};
	
	private final BroadcastReceiver sreenReceiver = new BroadcastReceiver(){    
	    
	    @Override    
	    public void onReceive(Context context, Intent intent) {    
	        // Do your action here    
	    
	    	if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
	    		System.out.println("off");
	    		//onStop();
	    		player.pause();
	    		System.out.println("off2");
	    	}
	    	else if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())){
	    		System.out.println("on");
	    		//onResume();
	    		player.start();
	    		System.out.println("on2");
	    	}
	    	else {
				
			}

	    }
	};
	    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_setting);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);

        soundBar=(SeekBar)findViewById(R.id.seekBar1);  //音量设置  

        audiomanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE); 
  
        maxVolume=audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);  //获取系统最大音量  
        
        soundBar.setMax(maxVolume);   //拖动条最高值与系统最大声匹配  
        
        currentVolume=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
        
        soundBar.setProgress(currentVolume);
   
        soundBar.setOnSeekBarChangeListener(new listener());
        
        player=MediaPlayer.create(this, R.raw.youngandbeautiful);  //设置路径
        /*try {
			player.prepare();  //缓冲
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        player.start();
        
        player.setOnCompletionListener(new completionListener());
        
        IntentFilter soundFilter = new IntentFilter("android.media.VOLUME_CHANGED_ACTION");
		registerReceiver(soundReceiver, soundFilter);
		
		//IntentFilter sreenFilter = new IntentFilter();    
		//sreenFilter.addAction(Intent.ACTION_SCREEN_OFF);    
		//sreenFilter.addAction(Intent.ACTION_SCREEN_ON);    
	   // registerReceiver(sreenReceiver, sreenFilter); 
	}
	
	class listener implements OnSeekBarChangeListener {

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);    
            currentVolume=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
            soundBar.setProgress(currentVolume);
            
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			isScool=true;
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			isScool=false;
			
		}
		
	}
	
	class completionListener implements OnCompletionListener {

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			player.start();
			
		}
		
	}
	
	/*@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
		
        if(keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	player.stop();
        	player.release();
        	//player=null;
	        finish();
	        
        }
        else if(keyCode == KeyEvent.KEYCODE_HOME) {
        	
        	player.pause();
        	
        }
        else {
			
		}
		
		super.onKeyDown(keyCode, event);
		
		if(keyCode==KeyEvent.KEYCODE_VOLUME_UP || keyCode==KeyEvent.KEYCODE_VOLUME_DOWN) {
			//audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
            currentVolume=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
            soundBar.setProgress(currentVolume);
		}
        
        return super.onKeyDown(keyCode, event);   
    }*/
	
	@Override
	public void onStop() {
		System.out.println("stop");
		super.onStop();
		player.pause();
		
	}
	
	@Override
	public void onRestart() {
		System.out.println("restart");
		super.onRestart();
		player.start();
		
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		player.stop();
		player.release();
		unregisterReceiver(soundReceiver);
		//unregisterReceiver(sreenReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//MenuInflater inflater = getMenuInflater();
	    //inflater.inflate(R.menu.setting_actions, menu);
		//CreateMenu(menu);
	    return true;
	    //return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
	    switch (item.getItemId()) { 
	        case android.R.id.home: 
	            // This is called when the Home (Up) button is pressed 
	            // in the Action Bar. 
	            Intent mainActivityIntent = new Intent(this, MainActivity.class); 
	            mainActivityIntent.addFlags( 
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP | 
	                    Intent.FLAG_ACTIVITY_NEW_TASK); 
	            startActivity(mainActivityIntent); 
	            finish(); 
	            return true; 
	    } 
	    return super.onOptionsItemSelected(item); 
	}
/*	private void CreateMenu(Menu menu)    
    {    
        MenuItem mnu1 = menu.add(0, 0, 0, "Item 1");    
        {             
            mnu1.setIcon(R.drawable.ic_launcher);    
            MenuItemCompat.setShowAsAction(mnu1,    
            	MenuItemCompat.SHOW_AS_ACTION_ALWAYS);                
        }    
        MenuItem mnu2 = menu.add(0, 1, 1, "Item 2");    
        {             
            mnu2.setIcon(R.drawable.ic_launcher);                
            MenuItemCompat.setShowAsAction(mnu2,    
                	MenuItemCompat.SHOW_AS_ACTION_ALWAYS);    
        }    
        MenuItem mnu3 = menu.add(0, 2, 2, "Item 3");    
        {             
            mnu3.setIcon(R.drawable.ic_launcher);    
            MenuItemCompat.setShowAsAction(mnu3,    
                	MenuItemCompat.SHOW_AS_ACTION_ALWAYS);    
        }    
        MenuItem mnu4 = menu.add(0, 3, 3, "Item 4");    
        {                
        	MenuItemCompat.setShowAsAction(mnu4,    
                	MenuItemCompat.SHOW_AS_ACTION_ALWAYS);   
        }    
        MenuItem mnu5 = menu.add(0, 4, 4, "Item 5");    
        {                
        	MenuItemCompat.setShowAsAction(mnu5,    
                	MenuItemCompat.SHOW_AS_ACTION_ALWAYS);    
        }    
    }
   */

}
