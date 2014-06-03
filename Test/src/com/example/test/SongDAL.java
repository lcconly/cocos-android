package com.example.Test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-18
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 */
public class SongDAL {
    private SQLiteDatabase myDb;
    private Cursor songCursor;



    public SongDAL(Context context){
        MyDatabase dbHelper = new MyDatabase(context);
        myDb = dbHelper.getWritableDatabase();
        songCursor = myDb.query(MyDatabase.SONG_TABLE_NAME, new String[]{ "_id", MyDatabase.SONG_COLUMN1,MyDatabase.SONG_COLUMN2, MyDatabase.SONG_COLUMN3
        ,MyDatabase.SONG_COLUMN4, MyDatabase.SONG_COLUMN5},null,null,null,null,null);

        songCursor.moveToFirst();

    }

    public boolean insert(SongItem songItem){
        ContentValues song = new ContentValues();
        song.put(MyDatabase.SONG_COLUMN1, songItem.getSongName());
        song.put(MyDatabase.SONG_COLUMN2, songItem.getSongFile());
        song.put(MyDatabase.SONG_COLUMN3, songItem.getSongEasy());
        song.put(MyDatabase.SONG_COLUMN4, songItem.getSongNormal());
        song.put(MyDatabase.SONG_COLUMN5, songItem.getSongHard());

        long returnVal = myDb.insert(MyDatabase.SONG_TABLE_NAME, null, song);
        songCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;


    }

    public boolean update(SongItem songItem){
        ContentValues song = new ContentValues();
        song.put(MyDatabase.SONG_COLUMN1, songItem.getSongName());
        song.put(MyDatabase.SONG_COLUMN2, songItem.getSongFile());
        song.put(MyDatabase.SONG_COLUMN3, songItem.getSongEasy());
        song.put(MyDatabase.SONG_COLUMN4, songItem.getSongNormal());
        song.put(MyDatabase.SONG_COLUMN5, songItem.getSongHard());
        long returnVal = myDb.update(MyDatabase.SONG_TABLE_NAME, song, MyDatabase.SONG_COLUMN1+"=?",new String[]{songItem.getSongName()});

        songCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;
    }

    public boolean delete(SongItem songItem){
        long returnVal = myDb.delete(MyDatabase.SONG_TABLE_NAME, MyDatabase.SONG_COLUMN1+"=?",new String[]{songItem.getSongName()});

        songCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;
    }


    public Cursor query(SongItem songItem){


    }
}
