package com.example.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-9
 * Time: 下午9:10
 * To change this template use File | Settings | File Templates.
 */
public class TestDBDelete extends Activity {
    private SongDAL songHelper;
    private ScoreDAL scoreHelper;
    private ThemeDAL themeHelper;
    private SettingDAL settingHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test song
        songHelper = new SongDAL(TestDBDelete.this);
        SongItem song = new Song("MY LOVE","","","","","lcc1");
        System.out.println("Delete a song");
        songHelper.delete(song);

        //test score
        scoreHelper = new ScoreDAL(TestDBDelete.this);
        ScoreItem score = new Score("lcc1","0");
        System.out.println("Update a score");
        scoreHelper.delete(score);

        //test theme
        themeHelper = new ThemeDAL(TestDBDelete.this);
        ThemeItem theme = new Theme("lcc1ishandsome"," ",0,1);
        System.out.println("Update a theme");
        themeHelper.delete(theme);

        //test setting
        settingHelper = new SettingDAL(TestDBDelete.this);
        SettingItem setting = new Setting("10","20");
        System.out.println("Update a setting");
        settingHelper.delete(setting);

    }
}
