package com.example.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-9
 * Time: 下午8:03
 * To change this template use File | Settings | File Templates.
 */
public class TestDBUpdate extends Activity {

    private SongDAL songHelper;
    private ScoreDAL scoreHelper;
    private ThemeDAL themeHelper;
    private SettingDAL settingHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test song
        songHelper = new SongDAL(TestDBUpdate.this);
        SongItem song = new Song("MY LOVE","","","","","lcc1");
        System.out.println("Update a song");
        songHelper.update(song);

        //test score
        scoreHelper = new ScoreDAL(TestDBUpdate.this);
        ScoreItem score = new Score("lcc1","0");
        System.out.println("Update a score");
        scoreHelper.update(score);

        //test theme
        themeHelper = new ThemeDAL(TestDBUpdate.this);
        ThemeItem theme = new Theme("lcc1ishandsome"," ",0,1);
        System.out.println("Update a theme");
        themeHelper.update(theme);

        //test setting
        settingHelper = new SettingDAL(TestDBUpdate.this);
        SettingItem setting = new Setting("10","20");
        System.out.println("Update a setting");
        settingHelper.update(setting);

    }
}
