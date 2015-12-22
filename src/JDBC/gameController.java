package JDBC;


import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import JDBC.beans.Game;
import JDBC.beans.Player;
import JDBC.beans.PlayerAndGame;


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


	}  // End of display
	
	//INSERT ONE Row
		public static boolean insertRow(Game game, Player player, PlayerAndGame playerAndGame) throws Exception{

			String SQLQueryG = "INSERT into game " +
		                      "(game_title) " +
					          "VALUES (?)";
			
			String SQLQueryP = "INSERT into player " +
					 "(first_name,last_name, address, postal_code, province, phone_number) " +
					          "VALUES (?,?,?,?,?,?)";
			
			String SQLQueryPG = "INSERT into playerAndGame " +
                    "(game_id,player_id,playing_date,score) " +
			          "VALUES (?,?,?,?)";

			ResultSet keysG;
			ResultSet keysP;
			ResultSet keysPG;

			try(
				Connection connection = DBConfig.getConnection();
				PreparedStatement statementG = connection.prepareStatement(SQLQueryG, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement statementP = connection.prepareStatement(SQLQueryP, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement statementPG = connection.prepareStatement(SQLQueryPG, Statement.RETURN_GENERATED_KEYS);
					){
				
				statementG.setString(1, game.getTitle());
							
				statementP.setString(1, player.getFistName());
				statementP.setString(2, player.getLastName());
				statementP.setString(3, player.getAddress());
				statementP.setString(4, player.getPostalCode());
				statementP.setString(5, player.getProvince());
				statementP.setString(6, player.getPhoneNumber());
				
     			//get the number of return rows
				int affectedG = statementG.executeUpdate();
				int affectedP = statementP.executeUpdate();
				
				if(affectedG == 1 && affectedP == 1) {
					
					keysG = statementG.getGeneratedKeys();
					keysG.next();
					int newKeyG = keysG.getInt(1);
					game.setId(newKeyG);
					keysP = statementP.getGeneratedKeys();
					keysP.next();
					int newKeyP = keysP.getInt(1);
					player.setPlayerId(newKeyP);
					
					game.setId(newKeyG);
					player.setPlayerId(newKeyP);
					
					statementPG.setInt(1, game.getId());
					statementPG.setInt(2, newKeyP);
					statementPG.setDate(3, new java.sql.Date(playerAndGame.getPlayingDate().getTime()));
					statementPG.setInt(4, playerAndGame.getScore());
					int affectedPG = statementPG.executeUpdate();
					if(affectedPG == 1) {
						
						keysPG = statementPG.getGeneratedKeys();
						keysPG.next();
						int newKeyPG = keysPG.getInt(1);
						playerAndGame.setPlayerGameId(newKeyPG);
						
						playerAndGame.setPlayerGameId(newKeyPG);
					}
					
				} else {
					System.err.println("No Rows Affected");
				}
				
				
			} catch(SQLException exception) {
				DBConfig.displayException(exception);
				return false;
			} 
			
			return true;
		} // end of Insert one Row

		public static int getRowNumber() throws Exception {
			
			String SQLQuery = "select * from playerAndGame";
			ResultSet resultSet; 
			
			try(
					Connection connection = DBConfig.getConnection();
					PreparedStatement statement = connection.prepareStatement(SQLQuery);
					)
			{

				resultSet= statement.executeQuery();
				resultSet.last();
				int rowNumber = resultSet.getRow();
			
			return rowNumber;

		}// end of get Game row number
	
	}
}








