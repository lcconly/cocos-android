package com.example.test;

import android.os.Bundle;
//import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameEndActivity extends ActionBarActivity {
	
	private EditText nameEditText;
	private TextView scoreTextView;
	private Button button;
	
	private int score_value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_end);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		
		nameEditText=(EditText)findViewById(R.id.editText1);
		
		scoreTextView=(TextView)findViewById(R.id.textView3);
		Intent intent=getIntent();
		score_value=intent.getIntExtra("grade_game", -1);
		scoreTextView.setText(String.valueOf(score_value));
		
		button=(Button)findViewById(R.id.button_save);
		button.setOnClickListener(new listener());
	}

	class listener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String name=nameEditText.getText().toString();
			if(name.equals("")) {
				Toast.makeText(getApplicationContext(), "名字不能为空！", Toast.LENGTH_SHORT).show();
			}
			else {
				Score score=new Score(name,String.valueOf(score_value));
				ScoreDAL dal=new ScoreDAL(GameEndActivity.this);
				dal.insert(score);
				
				//Intent intent=new Intent(GameEndActivity.this, GameStartActivity.class);
		        //startActivity(intent);
		        GameEndActivity.this.finish();
		        
			}
			
		}
		
	}
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
		
        if(keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	//Intent intent=new Intent(this, GameStartActivity.class);
	        //startActivity(intent);
	        finish();
	        
        }        
        return false;    
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.game_end, menu);
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
