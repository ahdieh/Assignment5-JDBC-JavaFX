package JDBC.beans;


import java.sql.Date;

// Game MODEL - JAVA BEAN
// We can also call this our Database Schema

public class Game {
	// PRIVATE INSTANCE VARIABLES ++++++++++++++++++++++
	private int _game_id;
	private String _game_title;
		
	// PUBLIC PROPERTIES +++++++++++++++++++++++++++++++
	public int getId() {
		return this._game_id;
	}
	
	public void setId(int id) {
		this._game_id = id;
	}
	
	public String getTitle() {
		return this._game_title;
	}
	
	public void setTitle(String name) {
		this._game_title = name;
	}
	
	
}

