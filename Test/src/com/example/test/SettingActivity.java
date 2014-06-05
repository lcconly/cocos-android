package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_setting);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		
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

        soundBar=(SeekBar)findViewById(R.id.seekBar1);  //音量设置  

        audiomanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE); 
  
        maxVolume=audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);  //获取系统最大音量  
        soundBar.setMax(maxVolume);   //拖动条最高值与系统最大声匹配  
        currentVolume=audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
        soundBar.setProgress(currentVolume);
   
        soundBar.setOnSeekBarChangeListener(new listener());
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
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
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
        
        return false;    
    }*/
	
	@Override
	public void onStop() {
		
		player.pause();
		super.onStop();
		
	}
	
	@Override
	public void onResume() {
		
		super.onResume();
		player.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//MenuInflater inflater = getMenuInflater();
	    //inflater.inflate(R.menu.setting_actions, menu);
//		CreateMenu(menu);
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
