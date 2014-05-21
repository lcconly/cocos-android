package com.example.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MallActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_mall);
		
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		View v = findViewById(R.id.mall_bg);//找到你要设透明背景的layout 的id 
		v.getBackground().setAlpha(200);//0~255透明度值
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mall, menu);
		return true;
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

}
