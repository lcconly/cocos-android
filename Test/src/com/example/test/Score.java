package com.example.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Shannon
 * Date: 14-6-3
 * Time: 下午4:44
 * To change this template use File | Settings | File Templates.
 */
public class Score implements ScoreItem {

    private String _playerName;
    private String _score;

    public Score(String playerName, String score){
        _playerName = playerName;
        _score = score;
    }

    public String getPlayerName(){
        return _playerName;
    }

    public String getScore(){
        return _score;
    }


}
