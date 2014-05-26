package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

public class SettingActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_setting);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
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
