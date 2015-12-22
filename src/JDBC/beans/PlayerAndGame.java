package JDBC.beans;

import java.sql.Date;

public class PlayerAndGame {

	// PRIVATE INSTANCE VARIABLES ++++++++++++++++++++++

     private int player_game_id;
     private int player_id;
     private int game_id;
     private java.util.Date playing_date;
     private int score;
	
     
 	// PUBLIC PROPERTIES +++++++++++++++++++++++++++++++

     public java.util.Date getPlayingDate() {
 		return  this.playing_date;
 	}
 	public void setPlayingDate(java.util.Date playingDate) {
 		this.playing_date = playingDate;
 	}
 	public int getScore() {
 		return this.score;
 	}
 	public void setScore(int score) {
 		this.score = score;
 	}
	public int getPlayerGameId() {
		return player_game_id;
	}
	public void setPlayerGameId(int player_game_id) {
		this.player_game_id = player_game_id;
	}
	public int getPlayerId() {
		return player_id;
	}
	public void setPlayerId(int player_id) {
		this.player_id = player_id;
	}
	public int getGameId() {
		return game_id;
	}
	public void setGameId(int game_id) {
		this.game_id = game_id;
	}
	
 	
 	


}
