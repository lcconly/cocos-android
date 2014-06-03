package com.example.test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-5-26
 * Time: 下午11:26
 * To change this template use File | Settings | File Templiates.
 */
public class Song implements SongItem {
    private String _songName;
    private String _songFile;
    private String _songEasy;
    private String _songNormal;
    private String _songHard;

    public Song(String songName,String songFile,String songEasy,String songNormal, String songHard) {
        _songName = songName;
        _songFile = songFile;
        _songEasy = songEasy;
        _songNormal = songNormal;
        _songHard = songHard;

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




}
