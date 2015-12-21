package JDBC;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;


// Controller class for our game Model - Java Bean
public class gameController {

	
	// Display the game table
	public static StringBuffer DisplayAllGames(){
		

		String SqlQuery = "SELECT * FROM game, player,playerandgame where  game.game_id = playerandgame.game_id AND player.player_id = playerandgame.player_id";
		StringBuffer buffer = new StringBuffer();
		
		try(	Connection connection = DBConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SqlQuery);
				){
			while(resultSet.next()){
			 buffer.append(resultSet.getString("player.first_name") + " ");
			 buffer.append(resultSet.getString("player.last_name") + ": ");
			 buffer.append(resultSet.getString("game.game_title")+ "--"); 
			 Date playingDate = resultSet.getDate("playerandgame.playing_date");
			 DateFormat dateFormat = DateFormat.getDateInstance();
			 String formattedDate = dateFormat.format(playingDate);
			 buffer.append("Playing date: " + formattedDate + "--");
			 buffer.append("Score: " + resultSet.getInt("playerandgame.score")+ "\n");
			 
			 
			
			}
			
		}catch(SQLException exception){
			 DBConfig.displayException(exception);
		}
		System.out.println(buffer.toString());
		return buffer;

	}
	
	}  // End of gameController
	










