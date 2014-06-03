package com.example.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-3
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class Theme implements ThemeItem {

    private String _themeName;
    private String _themeFile;
    private String _useFlag;
    private String _available;

    public Theme(String themeName, String themeFile, String useFlag, String available){
        _themeName = themeName;
        _themeFile = themeFile;
        _useFlag = useFlag;
        _available = available;
    }


    public String getThemeName(){
        return _themeName;
    }
    public String getThemeFile(){
        return _themeFile;
    }
    public String getUseFlag(){
        return _useFlag;
    }
    public String getAvailable(){
        return _available;
    }

}
