package com.example.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
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
	
	private ScoreDAL dal;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_rank);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.home);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setIcon(R.drawable.home);
		
		dal=new ScoreDAL(this);
		cursor=dal.getCursor();
		
		ArrayList<NameScore> arrayList=new ArrayList<NameScore>();
		
		if(cursor.moveToFirst()==false) {
			//记录为空！
		}
		else {

			while(true) {
				
				String nameString=cursor.getString(cursor.getColumnIndex(MyDatabase.SCORE_COLUMN1));
				String scoreString=cursor.getString(cursor.getColumnIndex(MyDatabase.SCORE_COLUMN2));
				
				//System.out.println(nameString);
				//System.out.println(scoreString);
				
				NameScore nameScore=new NameScore(nameString, scoreString);
				
				arrayList.add(nameScore);
				
				if(cursor.moveToNext()==false)
					break;
			}
			
			Collections.sort(arrayList, new SortByScore());
			
			mList  = new ArrayList<Map<String,Object>>();
			/*Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("Name", "大学霸"); 
			map.put("Score",  "999999999999"); 
			mList.add(map);
			mList.add(map);
			mList.add(map);
			mList.add(map);mList.add(map);mList.add(map);mList.add(map);
			mList.add(map);mList.add(map);mList.add(map);mList.add(map);
			mList.add(map);mList.add(map);mList.add(map);mList.add(map);
			mList.add(map);mList.add(map);mList.add(map);mList.add(map);*/
			
			for(NameScore nameScore:arrayList) {
				
				System.out.println(nameScore.getName());
				System.out.println(nameScore.getScore());
				
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("Name", nameScore.getName());
				map.put("Score", String.valueOf(nameScore.getScore()));
				mList.add(map);
				
			}
			
			mListAdapter = new SimpleAdapter(this, mList, R.layout.ranklist_row, 
			new String[]{"Name", "Score"}, 
			new int[]{R.id.textName, R.id.textScore}); 
			
			mListView = (ListView) findViewById(R.id.rankListView);
			mListView.setAdapter(mListAdapter);
			mListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
			
		}
		
	}

	class SortByScore implements Comparator {

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			
			NameScore nameScore1=(NameScore)arg0;
			NameScore nameScore2=(NameScore)arg1;
			return nameScore2.getScore()-nameScore1.getScore();
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
