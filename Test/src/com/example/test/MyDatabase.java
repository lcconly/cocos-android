package com.example.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-11
 * Time: 下午4:03
 * To change this template use File | Settings | File Templates.
 */
public class MyDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "test_db";
    //song table
    public static final String SONG_TABLE_NAME = "song_table";
    public static final String SONG_COLUMN1 = "song_name";
    public static final String SONG_COLUMN2 = "song_file";
    public static final String SONG_COLUMN3 = "timeline_easy";
    public static final String SONG_COLUMN4 = "timeline_normal";
    public static final String SONG_COLUMN5 = "timeline_hard";

    //score table
    public static final String SCORE_TABLE_NAME = "socre_table";
    public static final String SCORE_COLUMN1 = "player_name";
    public static final String SCORE_COLUMN2 = "score";

    //theme table
    public static final String THEME_TABLE_NAME = "theme_table";
    public static final String THEME_COLUMN1 = "theme_name";
    public static final String THEME_COLUMN2 = "theme_file";
    public static final String THEME_COLUMN3 = "use_flag";
    public static final String THEME_COLUMN4 = "avaliable";

    //settings table
    public static final String SETTINGS_TABLE_NAME = "settings_table";
    public static final String SETTINGS_COLUMN1 = "bgm_volume";
    public static final String SETTINGS_COLUMN2 = "touch_volume";


    public MyDatabase(Context context){
        super(context,DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //To change body of implemented methods use File | Settings | File Templates.
            db.execSQL("create table " + SONG_TABLE_NAME + " ( _id integer primary key autoincrement, "
                    + SONG_COLUMN1 + " text, "
                    + SONG_COLUMN2 + " text, "
                    + SONG_COLUMN3 + " text, "
                    + SONG_COLUMN4 + " text, "
                    + SONG_COLUMN5 + " text ); ");
            db.execSQL("create table " + SCORE_TABLE_NAME + " ( _id integer primary key autoincrement, "
                    + SCORE_COLUMN1 + " text, "
                    + SCORE_COLUMN2 + " text ); ");
            db.execSQL("create table " + THEME_TABLE_NAME + " ( _id integer primary key autoincrement, "
                    + THEME_COLUMN1 + " text, "
                    + THEME_COLUMN2 + " text, "
                    + THEME_COLUMN3 + " boolean, "
                    + THEME_COLUMN4 + " boolean ); ");
            db.execSQL("create table " + SETTINGS_TABLE_NAME + " ( _id integer primary key autoincrement, "
                    + SETTINGS_COLUMN1 + " text, "
                    + SETTINGS_COLUMN2 + " text ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
