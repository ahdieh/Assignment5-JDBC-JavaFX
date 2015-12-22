package JDBC;


import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import JDBC.beans.Game;
import JDBC.beans.Player;
import JDBC.beans.PlayerAndGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class JDBCController {
	@FXML private ImageView imageView;
	
	@FXML private Label gameLabel;
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label addressLabel;
	@FXML private Label postalCodeLabel;
	@FXML private Label provinceLabel;
	@FXML private Label phoneLabel;
	@FXML private Label playingDateLabel;
	@FXML private Label scoreLabel;
	@FXML private Label searchPlayerIdLabel;
	
	@FXML private TextField gameTextField;
	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField postalCodeTextField;
	@FXML private TextField provinceTextField;
	@FXML private TextField phoneTextField;
	@FXML private TextField playingDateTextField;
	@FXML private TextField scoreTextField;
	@FXML private TextField searchPlayerIdTextField;
	
	@FXML private TextArea addressTextArea;
	@FXML private TextArea textArea;

	@FXML private Button addButton;
	@FXML private Button InsertButton;
	@FXML private Button UpdateButton;
	@FXML private Button addPlayerButton;
	@FXML private Button updatePlayerButton;
	@FXML private Button deletePlayerButton;
	@FXML private Button displayPlayerButton;
	@FXML private Button searchButton;
	
	@FXML private GridPane gridPane;


	// Display Game

	// Add Player and Game
	public void addButtonHandler(ActionEvent event){

		clearAndVisible();
	  }
	
	public void clearAndVisible(){
		gameLabel.setOpacity(1);
    	gameTextField.setVisible(true);
    	

		firstNameLabel.setOpacity(1);
		lastNameLabel.setOpacity(1);
		addressLabel.setOpacity(1);
		postalCodeLabel.setOpacity(1);
		provinceLabel.setOpacity(1);
		phoneLabel.setOpacity(1);
		playingDateLabel.setOpacity(1);
		scoreLabel.setOpacity(1);
		
		
		firstNameTextField.setVisible(true);
		lastNameTextField.setVisible(true);
		addressTextArea.setVisible(true);
		postalCodeTextField.setVisible(true);
		provinceTextField.setVisible(true);
		phoneTextField.setVisible(true);
		playingDateTextField.setVisible(true);
		scoreTextField.setVisible(true);
		
		gameTextField.clear();
		firstNameTextField.clear();
		lastNameTextField.clear();
		addressTextArea.clear();
		postalCodeTextField.clear();
		provinceTextField.clear();
		phoneTextField.clear();
		playingDateTextField.clear();
		scoreTextField.clear();
	}
	
	// Insert Game
	public void InsertButtonHandler(ActionEvent event){
      // insert or create
    Game insertGame = new Game();
    Player insertPlayer = new Player();
    PlayerAndGame insertPlayerAndGame = new PlayerAndGame();

    try {
	    insertGame.setTitle(gameTextField.getText());
	    
	    insertPlayer.setFistName(firstNameTextField.getText());
		insertPlayer.setLastName(lastNameTextField.getText());
		insertPlayer.setAddress(addressTextArea.getText());
		insertPlayer.setPostalCode(postalCodeTextField.getText());
		insertPlayer.setProvince(provinceTextField.getText());
		insertPlayer.setPhoneNumber(phoneTextField.getText());
		
		insertPlayerAndGame.setPlayerId(insertPlayer.getPlayerId());
		insertPlayerAndGame.setGameId(insertGame.getId());
		
		String date = playingDateTextField.getText();
		DateFormat  formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date playingDate = (Date)formatter.parse(date);
		insertPlayerAndGame.setPlayingDate(playingDate);
				
		insertPlayerAndGame.setScore(Integer.parseInt(scoreTextField.getText()));
		
	    boolean result = gameController.insertRow(insertGame, insertPlayer, insertPlayerAndGame);
	
	    if(result) {
		System.out.println("The game with id " + insertGame.getId() + " was added to the list");
		System.out.println("The player with id " + insertPlayer.getPlayerId() + " was added to the list");
	    textArea.setVisible(true);
		textArea.setText("The game with id " + insertGame.getId() + " was added to the list");
		textArea.setText("The player with id " + insertPlayer.getPlayerId() + " was added to the list");

	    }
	
    } catch (Exception exception) {
	System.err.println("Invalid Input: " + exception);
    } // End of Insert
  }
	

	public void searchButtonHandler(ActionEvent event){
		
		searchPlayerIdLabel.setOpacity(1);
		searchPlayerIdTextField.setVisible(true);
		
	}
	
	public void DisplaySearchButtonHandler(ActionEvent event){
		int playerId = Integer.parseInt(searchPlayerIdTextField.getText());
		DisplaySearch( playerId);
	} 

		public void DisplaySearch(int playerId){
			
		clearAndVisible();
	
		String SqlQueryP = "SELECT * FROM player " +
				   "where player_id=" + playerId ;
		
				
		String SqlQueryPG = "SELECT * FROM playerandgame " +
				  "where playerandgame.player_id=" + playerId;
		
		
		try(	Connection connection = DBConfig.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSetP = statement.executeQuery(SqlQueryP);
				){
			while(resultSetP.next()){
		
				firstNameTextField.setText(resultSetP.getString("player.first_name"));
				lastNameTextField.setText(resultSetP.getString("player.last_name"));
				addressTextArea.setText(resultSetP.getString("player.address"));
				postalCodeTextField.setText(resultSetP.getString("player.postal_code"));
				provinceTextField.setText(resultSetP.getString("player.province"));
				phoneTextField.setText(resultSetP.getString("player.phone_number"));
				
			}
			ResultSet resultSetPG = statement.executeQuery(SqlQueryPG);
			int gameId = 0;
			
			while(resultSetPG.next()){
				playingDateTextField.setText(resultSetPG.getString("playerandgame.playing_date"));
				scoreTextField.setText(Integer.toString(resultSetPG.getInt("playerandgame.score")));
				gameId = resultSetPG.getInt("game_id");
			}
			
			String SqlQueryG = "SELECT * FROM game "
					+ "where game_id =" + gameId;
			ResultSet resultSetG = statement.executeQuery(SqlQueryG);
			while(resultSetG.next()){
				gameTextField.setText(resultSetG.getString("game.game_title"));

			}
			
		}catch(SQLException exception){
			 DBConfig.displayException(exception);
		}	}

		public void UpdateButtonHandler(ActionEvent event) throws SQLException{
			
			int playerId = Integer.parseInt(searchPlayerIdTextField.getText());
			Update(playerId);
		}
			
	
			public void Update(int playerId) throws SQLException{
			
		
				String SqlQueryP = "Update player set " + 
			                    "first_name ='"+firstNameTextField.getText()+"'" +
			                    ",last_name ='"+lastNameTextField.getText()+"'" +
						        ",address = '"+addressTextArea.getText()+"'" +
			                    ",postal_code = '"+postalCodeTextField.getText()+"'"+
						        ",province ='"+provinceTextField.getText()+"'"+
        	   				    ",phone_number ='"+ phoneTextField.getText()+"'"+
								" where player_id ='" + playerId +"'";
				
				String SqlQuery = "Select * from playerandgame " +
				       "where player_id ='" + playerId +"'";
				
				String SqlQueryPG = "Update playerandgame set " +
				        "playing_date ='"+playingDateTextField.getText()+"'"+
						",score = '" + scoreTextField.getText()+"'"+
						"where player_id ='" + playerId +"'";
							
				try(
						Connection connection = DBConfig.getConnection();
						Statement statement = connection.createStatement();
						
						){
					statement.executeUpdate(SqlQueryP);
					ResultSet rs = statement.executeQuery(SqlQuery);

						int gameId = 0;
						while(rs.next()) {
							 
							gameId = rs.getInt("game_id");
						}
						
						statement.executeUpdate(SqlQueryPG);
						String SqlQueryG = "Update game set " +
						        "game.game_title ='"+gameTextField.getText()+"'"+
						        " where game_id ='" + gameId +"'";
						
						statement.executeUpdate(SqlQueryG);
					
					
				}catch (SQLException exception) {
					DBConfig.displayException(exception);
				} 
					
			}

	
	public void ClearButtonHandler(ActionEvent event){
		
		gameTextField.clear();
		firstNameTextField.clear();
		lastNameTextField.clear();
		addressTextArea.clear();
		postalCodeTextField.clear();
		provinceTextField.clear();
		phoneTextField.clear();
		textArea.clear();
		playingDateTextField.clear();
		scoreTextField.clear();
	}
	
	
	
	public void DisplayButtonHandler(ActionEvent event) throws SQLException{
		
textArea.setVisible(true);
textArea.getStyleClass().add("custom");
textArea.setText(gameController.DisplayAllGames().toString());

	}
}				

		




