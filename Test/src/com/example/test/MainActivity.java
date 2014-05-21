package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gameStart(View view) {
        Intent intent = new Intent(this, GameStartActivity.class);
        startActivity(intent);
    }
    public void gameSetting(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    public void gameMall(View view) {
        Intent intent = new Intent(this, MallActivity.class);
        startActivity(intent);
    }
    public void gameRank(View view) {
        Intent intent = new Intent(this, RankActivity.class);
        startActivity(intent);
    }
    
}
