package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-26
 * Time: 涓����9:22
 * To change this template use File | Settings | File Templates.
 */
public class SettingDAL {

    private SQLiteDatabase myDb;

    private Cursor settingCursor;

    public SettingDAL(Context context){
        MyDatabase dbHelper = new MyDatabase(context);
        myDb = dbHelper.getWritableDatabase();
        settingCursor = myDb.query(MyDatabase.SETTINGS_TABLE_NAME, new String[]{"_id", MyDatabase.SETTINGS_COLUMN1, MyDatabase.SETTINGS_COLUMN2},
                null,null,null,null,null);
        settingCursor.moveToFirst();
    }

    public boolean insert(SettingItem settingItem){
        ContentValues setting = new ContentValues();
        setting.put(MyDatabase.SETTINGS_COLUMN1, settingItem.getBgmVolume());
        setting.put(MyDatabase.SETTINGS_COLUMN2, settingItem.getTouchVolume());

        long returnVal = myDb.insert(MyDatabase.SETTINGS_TABLE_NAME , null, setting);
        settingCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;

    }

    public boolean update(SettingItem settingItem){
        ContentValues setting = new ContentValues();
        setting.put(MyDatabase.SETTINGS_COLUMN1, settingItem.getBgmVolume());
        setting.put(MyDatabase.SETTINGS_COLUMN2, settingItem.getTouchVolume());

        long returnVal = myDb.update(MyDatabase.SETTINGS_TABLE_NAME , setting, MyDatabase.SETTINGS_COLUMN1+"=?", new String[]{settingItem.getBgmVolume()});
        settingCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;

    }

    public boolean delete(SettingItem settingItem){
        long returnVal = myDb.delete(MyDatabase.SETTINGS_TABLE_NAME, MyDatabase.SETTINGS_COLUMN1+"=?", new String[]{settingItem.getBgmVolume()});
        settingCursor.requery();
        if(returnVal == -1){
            return false;
        }
        return true;
    }
    
    public Cursor getCursor(){
    	return settingCursor;
    }
}

