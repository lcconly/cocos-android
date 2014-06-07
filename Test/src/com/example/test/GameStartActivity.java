package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
	
	private Button button1;
	private Button button2;
	private Button button3;
	private int state; //1表示简单，2表示普通，3表示困难
	
	private SongDAL dal;
	private Cursor cursor;
	
	private String songName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_start);
		
		dal=new SongDAL(this);
		cursor=dal.getCursor();
		
		if(cursor.moveToFirst()==false) {
			Song song1=new Song("Young and Beautiful", "youngandbeautiful.mp3", "youngandbeautiful_1.txt", "youngandbeautiful_2.txt", "youngandbeautiful_3.txt", "Lana Del Rey");
			Song song2=new Song("残酷天使的行动纲领", "cktsdxdgl.mp3", "cktsdxdgl_1.txt", "cktsdxdgl_2.txt", "cktsdxdgl_3.txt", "高桥洋子");
			dal.insert(song1);
			dal.insert(song2);
			
			/*mList  = new ArrayList<Map<String,Object>>();
			
			Map<String, Object> map1= new HashMap<String, Object>();
			Map<String, Object> map2= new HashMap<String, Object>();
			
			map1.put("First", "Lana Del Rey"); 
			map1.put("Next",  "Young and Beautiful");
			map1.put("State", "");
			mList.add(map1);
			
			map2.put("First", "高桥洋子"); 
			map2.put("Next",  "残酷天使的行动纲领");
			map2.put("State", "");
			mList.add(map2);*/
		}
		
		cursor.moveToFirst();
		
		mList  = new ArrayList<Map<String,Object>>();
		
		while(true) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			String songNameString=cursor.getString(cursor.getColumnIndex(MyDatabase.SONG_COLUMN1));
			String singerNameString=cursor.getString(cursor.getColumnIndex(MyDatabase.SONG_COLUMN6));
			
			map.put("First", singerNameString); 
			map.put("Next",  songNameString);
			map.put("State", "");
			mList.add(map);
			
			if(cursor.moveToNext()==false)
				break;
				
		}
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("First", "待定"); 
		map.put("Next",  "待定"); 
		map.put("State", "");
		mList.add(map);
		mList.add(map);
		mList.add(map);
		mList.add(map);
		
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
                
            		Map<String, Object> map=mList.get(arg2);
            		String songNameString=(String)map.get("Next");
            		
            		if(songNameString.equals("Young and Beautiful") || songNameString.equals("残酷天使的行动纲领")) {
            			if(songNameString.equals("Young and Beautiful")) {
            				songName="youngandbeautiful";
            			}
            			else if(songNameString.equals("残酷天使的行动纲领")) {
            				songName="cktsdxdgl";
            			}
            			else
            				;
            			
            			 Intent intent = new Intent(GameStartActivity.this, GameActivity.class);
                         intent.putExtra("state", state);
                         intent.putExtra("songName", songName);
                         startActivity(intent);
                         
            		}
            		else {
            			
            		}
            		
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
