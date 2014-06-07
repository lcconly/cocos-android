package com.example.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-26
 * Time: 涓����11:26
 * To change this template use File | Settings | File Templiates.
 */
public class Song implements SongItem {
    private String _songName;
    private String _songFile;
    private String _songEasy;
    private String _songNormal;
    private String _songHard;
    private String _singerName;

    public Song(String songName,String songFile,String songEasy,String songNormal, String songHard,String singerName) {
        _songName = songName;
        _songFile = songFile;
        _songEasy = songEasy;
        _songNormal = songNormal;
        _songHard = songHard;
        _singerName = singerName;
    }

    public String getSongName(){
        return _songName;
    }
    public String getSongFile(){
        return _songFile;
    }
    public String getSongEasy(){
        return _songEasy;

    }
    public String getSongNormal(){
        return _songNormal;
    }
    public String getSongHard(){
        return _songHard;
    }
    public String getSingerName(){
    	return _singerName;
    }




}
