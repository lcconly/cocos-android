package com.example.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-3
 * Time: ÏÂÎç5:17
 * To change this template use File | Settings | File Templates.
 */
public class Theme implements ThemeItem {

    private String _themeName;
    private String _themeFile;
    private boolean _useFlag;
    private boolean _available;

    public Theme(String themeName, String themeFile, boolean useFlag, boolean available){
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
    public boolean getUseFlag(){
        return _useFlag;
    }
    public boolean getAvailable(){
        return _available;
    }

}