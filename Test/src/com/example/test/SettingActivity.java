package com.example.test;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingActivity extends ActionBarActivity {
	
	//private ImageButton imageButton_white1;  
	private MediaPlayer player;  
	public  AudioManager audiomanage;  
	//private TextView mVolume ;  //显示当前音量  
	public  SeekBar soundBar;  
	private int maxVolume, currentVolume;   

	private int volume=0;  //初始化声音
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_setting);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		
		player = new MediaPlayer(); 
		
		///imageButton_white1=(ImageButton)findViewById(R.id.white1);  
        final SeekBar soundBar=(SeekBar)findViewById(R.id.seekBar1);  //音量设置  
        //mVolume = (TextView)findViewById(R.id.mVolume);    
        audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);    
  
  
        maxVolume = audiomanage.getStreamMaxVolume(AudioManager.STREAM_MUSIC);  //获取系统最大音量  
        soundBar.setMax(maxVolume);   //拖动条最高值与系统最大声匹配  
        currentVolume = audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
        soundBar.setProgress(currentVolume);    
        //mVolume.setText(currentVolume*100/maxVolume + " %");    
   
        soundBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() //调音监听器  
        {  
            public void onProgressChanged(SeekBar arg0,int progress,boolean fromUser)  
            {  
                audiomanage.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);    
                currentVolume = audiomanage.getStreamVolume(AudioManager.STREAM_MUSIC);  //获取当前值  
                soundBar.setProgress(currentVolume);    
                //mVolume.setText(currentVolume*100/maxVolume + " %");    
            }  
              
            @Override  
            public void onStartTrackingTouch(SeekBar seekBar) {  
                // TODO Auto-generated method stub  
                  
            }  
            @Override  
            public void onStopTrackingTouch(SeekBar seekBar) {  
                // TODO Auto-generated method stub  
                  
  
            }  
        }); 
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
