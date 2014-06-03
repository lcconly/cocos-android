package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-26
 * Time: 下午9:21
 * To change this template use File | Settings | File Templates.
 */
public class ScoreDAL {
    private Cursor scoreCursor;
    private SQLiteDatabase myDb;

    public ScoreDAL(Context context){
        MyDatabase dbHelper = new MyDatabase(context);
        myDb = dbHelper.getWritableDatabase();
        scoreCursor = myDb.query(MyDatabase.SCORE_TABLE_NAME, new String[]{"_id", MyDatabase.SCORE_COLUMN1, MyDatabase.SCORE_COLUMN2},null,null,null,null,null);

        scoreCursor.moveToFirst();
    }

    public boolean insert(ScoreItem scoreItem){
        ContentValues score = new ContentValues();
        score.put(MyDatabase.SCORE_COLUMN1, scoreItem.getPlayerName());
        score.put(MyDatabase.SCORE_COLUMN2, scoreItem.getScore());

        long returnVal = myDb.insert(MyDatabase.SCORE_TABLE_NAME, null, score);
        scoreCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;


    }

    public boolean update(ScoreItem scoreItem){

        ContentValues score = new ContentValues();
        score.put(MyDatabase.SCORE_COLUMN1, scoreItem.getPlayerName());
        score.put(MyDatabase.SCORE_COLUMN2, scoreItem.getScore());

        long returnVal = myDb.update(MyDatabase.SCORE_TABLE_NAME, score, MyDatabase.SCORE_COLUMN1+"=?",new String[]{scoreItem.getPlayerName()});
        scoreCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;


    }

    public boolean delete(ScoreItem scoreItem){
        long returnVal = myDb.delete(MyDatabase.SCORE_TABLE_NAME, MyDatabase.SCORE_COLUMN1+"=?", new String[]{scoreItem.getPlayerName()});
        scoreCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;
    }



}
