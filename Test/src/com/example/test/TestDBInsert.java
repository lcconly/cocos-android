package com.example.test;

import android.app.Activity;
import android.os.Bundle;

public class TestDBInsert extends Activity {

    private SongDAL songHelper;
    private ScoreDAL scoreHelper;
    private ThemeDAL themeHelper;
    private SettingDAL settingHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test song
        songHelper = new SongDAL(TestDBInsert.this);
        SongItem song = new Song("HelloWorld","","","","","lcc");
        System.out.println("Insert a song");
        songHelper.insert(song);

        //test score
        scoreHelper = new ScoreDAL(TestDBInsert.this);
        ScoreItem score = new Score("lcc","0");
        System.out.println("Insert a score");
        scoreHelper.insert(score);

        //test theme
        themeHelper = new ThemeDAL(TestDBInsert.this);
        ThemeItem theme = new Theme("lccishandsome"," ",1,1);
        System.out.println("Insert a theme");
        themeHelper.insert(theme);

        //test setting
        settingHelper = new SettingDAL(TestDBInsert.this);
        SettingItem setting = new Setting("20","20");
        System.out.println("Insert a setting");
        settingHelper.insert(setting);




    }

}
