package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class GameStartActivity extends Activity {
	private List<Map<String, Object>> mList;     
	private ListView mListView; 
	private SimpleAdapter mListAdapter;
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
                    startActivity(intent);
               
            }
             
        });
	}
	


}
