package JDBC.beans;

import java.sql.Date;

public class PlayerAndGame {

	// PRIVATE INSTANCE VARIABLES ++++++++++++++++++++++

     private int player_game_id;
     private int player_id;
     private int game_id;
     private Date playing_date;
     private int score;
	public Date getPlayingDate() {
		return this.playing_date;
	}
	public void setPlayingDate(Date playing_date) {
		this.playing_date = playing_date;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(int score) {
		this.score = score;
	}
     
 	// PUBLIC PROPERTIES +++++++++++++++++++++++++++++++




}
