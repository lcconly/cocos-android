package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
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
//import android.widget.Toast;

public class MallActivity extends ActionBarActivity {
	
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	//private int state1;
	private int state2;
	private int state3;
	private int state4;
	
	private ThemeDAL dal;
	private Cursor cursor;
	
	private Theme orangeTheme;
	private Theme redTheme;
	
	private SharedPreferences preferences;
	private Editor editor;
	private String color;
	
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

		dal=new ThemeDAL(this);
		
		preferences=getSharedPreferences("theme",Context.MODE_PRIVATE);
		editor=preferences.edit();
		
		//SharedPreferences preferences=getSharedPreferences("user", Context.MODE_PRIVATE);
		//String name=preferences.getString("name", "defaultname");
		//String age=preferences.getString("age", "0");
		
		cursor=dal.getCursor();
		
		if(cursor.moveToFirst()==false) {
			orangeTheme=new Theme("orange", "", 1, 1);
			dal.insert(orangeTheme);
			redTheme=new Theme("red", "", 0, 0);
			dal.insert(redTheme);
			//state1=1;
			state2=1;
			state3=0;
			state4=0;
			button1.setBackgroundResource(R.drawable.button_bought);
			button2.setBackgroundResource(R.drawable.button_used);
			button3.setBackgroundResource(R.drawable.button_buy);
			button4.setBackgroundResource(R.drawable.button_use);
			
			//color="orange";
			//editor.putString("color", color);
			//editor.commit();
			
		}
		else {
			
			while(true) {
				
				if(cursor.getString(cursor.getColumnIndex(MyDatabase.THEME_COLUMN1)).equals("orange")){
					
					if(cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN3))==1) {
						//state1=1;
						state2=1;
						button1.setBackgroundResource(R.drawable.button_bought);
						button2.setBackgroundResource(R.drawable.button_used);
						
						//color="orange";
						//editor.putString("color", color);
						//editor.commit();
						
					}
					else {
						//state1=1;
						state2=0;
						button1.setBackgroundResource(R.drawable.button_bought);
						button2.setBackgroundResource(R.drawable.button_use);
					}
					
				}
				else if(cursor.getString(cursor.getColumnIndex(MyDatabase.THEME_COLUMN1)).equals("red")) {
					
					if(cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN4))==1) {
						state3=1;
						button3.setBackgroundResource(R.drawable.button_bought);
					}
					else {
						state3=0;
						button3.setBackgroundResource(R.drawable.button_buy);
					}
					
					if(cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN3))==1) {
						state4=1;
						button4.setBackgroundResource(R.drawable.button_used);
						
						//color="red";
						//editor.putString("color", color);
						//editor.commit();
						
					}
					else {
						state4=0;
						button4.setBackgroundResource(R.drawable.button_use);
					}
					
				}
				else {
					
				}
				
				if(cursor.moveToNext()==false)
					break;
					
			}
			
			//cursor.close();
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
			
			cursor.moveToFirst();
			
			while(true) {
				
				if(cursor.getString(cursor.getColumnIndex(MyDatabase.THEME_COLUMN1)).equals("orange")){
					
					orangeTheme=new Theme("orange", "", cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN3)), cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN4)));
					
				}
				else if(cursor.getString(cursor.getColumnIndex(MyDatabase.THEME_COLUMN1)).equals("red")) {
					
					redTheme=new Theme("red", "", cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN3)), cursor.getInt(cursor.getColumnIndex(MyDatabase.THEME_COLUMN4)));
					
				}
				else {
					
				}
				
				if(cursor.moveToNext()==false)
					break;
					
			}
			
			//cursor.close();
			
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
					Theme temp1=new Theme("orange", "", 1, orangeTheme.getAvailable());
					Theme temp2=new Theme("red", "", 0, redTheme.getAvailable());
					dal.update(temp1);
					dal.update(temp2);
					
					color="orange";
					editor.putString("color", color);
					editor.commit();
					
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
					Theme temp2=new Theme("red", "", redTheme.getUseFlag(), 1);
					dal.update(temp2);
					
				}
				
			} else if(v.getId()==R.id.button4) {
				if(state3==0) {
					//Toast.makeText(getApplicationContext(), "红色主题尚未购买，无法启用！", Toast.LENGTH_SHORT).show();
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
					Theme temp1=new Theme("orange", "", 0, orangeTheme.getAvailable());
					Theme temp2=new Theme("red", "", 1, redTheme.getAvailable());
					dal.update(temp1);
					dal.update(temp2);
					
					color="red";
					editor.putString("color", color);
					editor.commit();
					
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
