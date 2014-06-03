package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
//import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class MallActivity extends ActionBarActivity {
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	//private int state1;
	private int state2;
	private int state3;
	private int state4;
	
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
		
		button1=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button3=(Button)findViewById(R.id.button3);
		button4=(Button)findViewById(R.id.button4);

		if(true) {//读取数据库
			//state1=1;
			state2=1;
			state3=0;
			state4=0;
			button1.setBackgroundResource(R.drawable.button_bought);
			button2.setBackgroundResource(R.drawable.button_used);
			button3.setBackgroundResource(R.drawable.button_buy);
			button4.setBackgroundResource(R.drawable.button_use);
		}
		
		button1.setOnClickListener(new listener());
		button2.setOnClickListener(new listener());
		button3.setOnClickListener(new listener());
		button4.setOnClickListener(new listener());
			
	}
	
	class listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//LayoutParams lp;
			if(v.getId()==R.id.button1) {
		
			} else if(v.getId()==R.id.button2) {
				if(state2==0) {
					button2.setBackgroundResource(R.drawable.button_used);
					/*lp=(LayoutParams)button2.getLayoutParams();
					lp.width=80;
					lp.height=24;
					button2.setLayoutParams(lp);*/
					//button2.setWidth(80);
					//button2.setHeight(24);
					//Toast.makeText(getApplicationContext(), "橙色主题启用成功！", Toast.LENGTH_SHORT).show();
					state2=1;
					button4.setBackgroundResource(R.drawable.button_use);
					/*lp=(LayoutParams)button4.getLayoutParams();
					lp.width=82;
					lp.height=30;
					button4.setLayoutParams(lp);*/
					//button4.setWidth(82);
					//button4.setHeight(30);
					state4=0;
					//存入数据库
				}
		
			} else if(v.getId()==R.id.button3) {
				if(state3==0) {
					button3.setBackgroundResource(R.drawable.button_bought);
					/*lp=(LayoutParams)button3.getLayoutParams();
					lp.width=80;
					lp.height=24;
					button3.setLayoutParams(lp);*/
					//button3.setWidth(80);
					//button3.setHeight(24);
					//Toast.makeText(getApplicationContext(), "红色主题购买成功！", Toast.LENGTH_SHORT).show();
					state3=1;
					//存入数据库
				}
				
			} else if(v.getId()==R.id.button4) {
				if(state3==0) {
					Toast.makeText(getApplicationContext(), "红色主题尚未购买，无法启用！", Toast.LENGTH_SHORT).show();
				}
				else if(state4==0) {
					button4.setBackgroundResource(R.drawable.button_used);
					/*lp=(LayoutParams)button4.getLayoutParams();
					lp.width=80;
					lp.height=24;
					button4.setLayoutParams(lp);*/
					//button4.setWidth(80);
					//button4.setHeight(24);
					//Toast.makeText(getApplicationContext(), "红色主题启用成功！", Toast.LENGTH_SHORT).show();
					state4=1;
					button2.setBackgroundResource(R.drawable.button_use);
					/*lp=(LayoutParams)button2.getLayoutParams();
					lp.width=82;
					lp.height=30;
					button2.setLayoutParams(lp);*/
					//button2.setWidth(82);
					//button2.setHeight(30);
					state2=0;
					//存入数据库
				}
				else {
					
				}
			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.mall, menu);
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
