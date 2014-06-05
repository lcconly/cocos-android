package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GameStartActivity extends Activity {
	private List<Map<String, Object>> mList;     
	private ListView mListView; 
	private SimpleAdapter mListAdapter;
	
	Button button1;
	Button button2;
	Button button3;
	int state; //1表示简单，2表示普通，3表示困难
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_start);
		mList  = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("First", "这是标题"); 
		map.put("Next",  "这是内容"); 
		map.put("State", "状态");
		mList.add(map);
		mList.add(map);
		mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mListAdapter = new SimpleAdapter(this, mList, R.layout.list_row, 
		new String[]{"First", "Next", "State"}, 
		new int[]{R.id.textOwn, R.id.textTo, R.id.textState}); 
		
		mListView = (ListView) findViewById(R.id.ListView01);
		mListView.setAdapter(mListAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener(){
			 
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                // TODO Auto-generated method stub
                
                    Intent intent = new Intent(GameStartActivity.this, GameActivity.class);
                    intent.putExtra("state", state);
                    startActivity(intent);
               
            }
             
        });
		
		button1=(Button)findViewById(R.id.buttoneasy);
		button2=(Button)findViewById(R.id.buttonnormal);
		button3=(Button)findViewById(R.id.buttonhard);
		
		button1.setBackgroundResource(R.drawable.button_easy_selected);
		button2.setBackgroundResource(R.drawable.button_norm);
		button3.setBackgroundResource(R.drawable.button_hard);
		
		state=1;
		
		button1.setOnClickListener(new listener());
		button2.setOnClickListener(new listener());
		button3.setOnClickListener(new listener());
	}
	
	class listener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0.getId()==R.id.buttoneasy) {
				if(state==1) {
					
				}
				else if(state==2) {
					button1.setBackgroundResource(R.drawable.button_easy_selected);
					button2.setBackgroundResource(R.drawable.button_norm);
					state=1;
				}
				else if(state==3) {
					button1.setBackgroundResource(R.drawable.button_easy_selected);
					button3.setBackgroundResource(R.drawable.button_hard);
					state=1;
				}
				else {
					
				}
			}
			else if(arg0.getId()==R.id.buttonnormal) {
				if(state==1) {
					button2.setBackgroundResource(R.drawable.button_norm_selected);
					button1.setBackgroundResource(R.drawable.button_easy);
					state=2;
				}
				else if(state==2) {
					
				}
				else if(state==3) {
					button2.setBackgroundResource(R.drawable.button_norm_selected);
					button3.setBackgroundResource(R.drawable.button_hard);
					state=2;
				}
				else {
					
				}
			}
			else if(arg0.getId()==R.id.buttonhard) {
				if(state==1) {
					button3.setBackgroundResource(R.drawable.button_hard_selected);
					button1.setBackgroundResource(R.drawable.button_easy);
					state=3;
				}
				else if(state==2) {
					button3.setBackgroundResource(R.drawable.button_hard_selected);
					button2.setBackgroundResource(R.drawable.button_norm);
					state=3;
				}
				else if(state==3) {
					
				}
				else {
					
				}
			}
			else {
				
			}
		}
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
		button1.setBackgroundResource(R.drawable.button_easy_selected);
		button2.setBackgroundResource(R.drawable.button_norm);
		button3.setBackgroundResource(R.drawable.button_hard);
		
		state=1;
	}


}
