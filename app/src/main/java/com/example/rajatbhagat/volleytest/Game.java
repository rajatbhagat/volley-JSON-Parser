package com.example.rajatbhagat.volleytest;

/**
 * Created by rajatbhagat on 18/1/17.
 */

public class Game {

//    private int game_id;
    private String gameTitle;
    private String gameDeveloper;

    public Game() {
        // Manddatory empty constructor
    }

//    public void set_game_id(int game_id) {
//        this.game_id = game_id;
//    }

//    public int get_game_id() { return game_id; }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameTitle() {return gameTitle;}

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public String getGameDeveloper() {return gameDeveloper;}
}
