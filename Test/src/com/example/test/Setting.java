package com.example.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-3
 * Time: 下午6:31
 * To change this template use File | Settings | File Templates.
 */
public class Setting implements SettingItem {

    private String _bgmVolume;
    private String _touchVolume;

    public Setting(String bgmVolume, String touchVolume){
        _bgmVolume = bgmVolume;
        _touchVolume = touchVolume;
    }

    public String getBgmVolume(){
        return _bgmVolume;
    }

    public String getTouchVolume(){
        return _touchVolume;
    }

}
