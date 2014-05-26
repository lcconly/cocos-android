package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RankActivity extends ActionBarActivity {
	private List<Map<String, Object>> mList;
	private ListView mListView; 
	private SimpleAdapter mListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_rank);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		
		mList  = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("Name", "¥Û—ß∞‘"); 
		map.put("Score",  "999999999999"); 
		mList.add(map);
		mList.add(map);
		mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mList.add(map);mList.add(map);mList.add(map);mList.add(map);
		mListAdapter = new SimpleAdapter(this, mList, R.layout.ranklist_row, 
		new String[]{"Name", "Score"}, 
		new int[]{R.id.textName, R.id.textScore}); 
		
		mListView = (ListView) findViewById(R.id.rankListView);
		mListView.setAdapter(mListAdapter);
		mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
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
