package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-26
 * Time: 涓����9:21
 * To change this template use File | Settings | File Templates.
 */
public class ThemeDAL {
    private SQLiteDatabase myDb;
    private Cursor themeCursor;

    public ThemeDAL(Context context){
        MyDatabase dbHelper = new MyDatabase(context);
        myDb = dbHelper.getWritableDatabase();
        themeCursor = myDb.query(MyDatabase.THEME_TABLE_NAME, new String[]{"_id", MyDatabase.THEME_COLUMN1, MyDatabase.THEME_COLUMN2,MyDatabase.THEME_COLUMN3
                ,MyDatabase.THEME_COLUMN4}, null, null, null, null, null);

        themeCursor.moveToFirst();

    }

    public boolean insert(ThemeItem themeItem){
        ContentValues theme = new ContentValues();
        theme.put(MyDatabase.THEME_COLUMN1, themeItem.getThemeName());
        theme.put(MyDatabase.THEME_COLUMN2, themeItem.getThemeFile());
        theme.put(MyDatabase.THEME_COLUMN3, themeItem.getUseFlag());
        theme.put(MyDatabase.THEME_COLUMN4, themeItem.getAvailable());

        long returnVal = myDb.insert(MyDatabase.THEME_TABLE_NAME, null, theme);
        themeCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;

    }


    public boolean update(ThemeItem themeItem){

        ContentValues theme = new ContentValues();
        theme.put(MyDatabase.THEME_COLUMN1, themeItem.getThemeName());
        theme.put(MyDatabase.THEME_COLUMN2, themeItem.getThemeFile());
        theme.put(MyDatabase.THEME_COLUMN3, themeItem.getUseFlag());
        theme.put(MyDatabase.THEME_COLUMN4, themeItem.getAvailable());

        long returnVal = myDb.update(MyDatabase.THEME_TABLE_NAME, theme, MyDatabase.THEME_COLUMN1+"=?", new String[]{themeItem.getThemeName()});
        themeCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;


    }

    public boolean delete(ThemeItem themeItem){
        long returnVal = myDb.delete(MyDatabase.THEME_TABLE_NAME, MyDatabase.THEME_COLUMN1+"=?", new String[]{themeItem.getThemeName()});
        themeCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;
    }
    
    public Cursor getCursor(){
    	return themeCursor;
    }
    
    /*public boolean ifEmpty(){
    	if(themeCursor.isNull(1)){
    		return true;
    	}
    	else{
    		return false;
    	}
    }*/
}
