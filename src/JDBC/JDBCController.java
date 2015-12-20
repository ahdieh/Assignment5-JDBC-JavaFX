package JDBC;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.javafx.geom.transform.Identity;

import JDBC.beans.Game;
import JDBC.beans.Player;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class JDBCController {
	@FXML private ImageView imageView;
	
	@FXML private Label gameLabel;
	@FXML private Label firstNameLabel;
	@FXML private Label lastNameLabel;
	@FXML private Label addressLabel;
	@FXML private Label postalCodeLabel;
	@FXML private Label provinceLabel;
	@FXML private Label phoneLabel;
	
	@FXML private TextField gameTextField;
	@FXML private TextField firstNameTextField;
	@FXML private TextField lastNameTextField;
	@FXML private TextField postalCodeTextField;
	@FXML private TextField provinceTextField;
	@FXML private TextField phoneTextField;
	
	@FXML private TextArea addressTextArea;
	@FXML private TextArea textArea;


	@FXML private Button insertButton;
	@FXML private Button updateButton;
	@FXML private Button addPlayerButton;
	@FXML private Button updatePlayerButton;
	@FXML private Button deletePlayerButton;
	@FXML private Button displayPlayerButton;
	
	@FXML private GridPane gridPane;


	// Display Game

	// Add Game
	public void InsertButtonHandler(ActionEvent event){

	    	gameLabel.setOpacity(1);
	    	gameTextField.setVisible(true);
	    	

			firstNameLabel.setOpacity(1);
			lastNameLabel.setOpacity(1);
			addressLabel.setOpacity(1);
			postalCodeLabel.setOpacity(1);
			provinceLabel.setOpacity(1);
			phoneLabel.setOpacity(1);
			
			firstNameTextField.setVisible(true);
			lastNameTextField.setVisible(true);
			addressTextArea.setVisible(true);
			postalCodeTextField.setVisible(true);
			provinceTextField.setVisible(true);
			phoneTextField.setVisible(true);
			
			gameTextField.clear();
			firstNameTextField.clear();
			lastNameTextField.clear();
			addressTextArea.clear();
			postalCodeTextField.clear();
			provinceTextField.clear();
			phoneTextField.clear();
            
	  }
	
	// Update Game
	public void UpdateButtonHandler(ActionEvent event){
      // insert or create
    Game insertGame = new Game();
    Player insertPlayer = new Player();

    try {
	    insertGame.setTitle(gameTextField.getText());
	    
	    insertPlayer.setFistName(firstNameTextField.getText());
		insertPlayer.setLastName(lastNameTextField.getText());
		insertPlayer.setAddress(addressTextArea.getText());
		insertPlayer.setPostalCode(postalCodeTextField.getText());
		insertPlayer.setProvince(provinceTextField.getText());
		insertPlayer.setPhoneNumber(phoneTextField.getText());
		
	    boolean result = gameController.insertRow(insertGame, insertPlayer);
	
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
	

	public void ClearButtonHandler(ActionEvent event){
		
		gameTextField.clear();
		firstNameTextField.clear();
		lastNameTextField.clear();
		addressTextArea.clear();
		postalCodeTextField.clear();
		provinceTextField.clear();
		phoneTextField.clear();
		textArea.clear();
	}
	
	public void DisplayButtonHandler(ActionEvent event) throws SQLException{
		String string = "";

		String SQLQueryP = "select * from player";

		ResultSet resultSetG;
		ResultSet resultSetP;
		ResultSet resultSetPG = null;
		try(
				Connection connection = DBConfig.getConnection();

				PreparedStatement statementP = connection.prepareStatement(SQLQueryP);
				
			   ) {
			resultSetP = statementP.executeQuery();
			ArrayList<Integer> players = new ArrayList<>();
			ArrayList<String> firstNames = new ArrayList<>();
			ArrayList<Integer> gameIds = new ArrayList<>();
			ArrayList<String> games = new ArrayList<>();
			
			while(resultSetP.next()) {
				textArea.setVisible(true);
				players.add(resultSetP.getInt("player_id"));
				firstNames.add(resultSetP.getString("first_name"));
			}
			for (int i =0; i<firstNames.size();i++){

				
				string = string +"\nPlayer " +": " + firstNames.get(i) ;
			}	
			
			for (int i =0; i<players.size();i++){
				String SQLQueryPG = "select a.player_id, a.game_id , b.player_id from playerAndGame a, player b where a.player_game_id = b.player_id"  ;
				
		    	PreparedStatement statementPG = connection.prepareStatement(SQLQueryPG);
			     resultSetPG = statementPG.executeQuery();
			     gameIds.add(resultSetPG.getInt("game_id"));
			 }
			
			//for (int i =0; i<gameIds.size();i++){
			//	String SQLQueryG = "select a.game_id, a.game_title, b.game_id from game a, playerAndGame b where a.game_id = b.game_id";
			//	PreparedStatement statementG = connection.prepareStatement(SQLQueryG);
			//    resultSetG = statementG.executeQuery();
		    //    games.add(resultSetPG.getString("game_title"));
			// }
		

			textArea.setMaxSize(300, 300);

			
			for (int i =0; i<players.size();i++){

											
					string = string +"\nPlayer " +": " + firstNames.get(i) +" , the game: " ;
				}	
				
				textArea.setText(string);
				
	    } catch (SQLException exception) {
	    	System.err.println(exception);
	        } 
		
		//TableView table = new TableView();
		//Label label = new Label("Display data");
		
		// table.setEditable(true);
		 
		// TableColumn firstNameCol = new TableColumn("First Name");
	   //  TableColumn lastNameCol = new TableColumn("Last Name");
	   //  TableColumn gameCol = new TableColumn("Game");
	   //  
	   //  table.getColumns().addAll(firstNameCol, lastNameCol, gameCol);
	     
	     	     
	//	gridPane.getChildren().addAll( label, table);
		
	//	table.getSelectionModel().setCellSelectionEnabled(true);
	//	table.getColumns().setAll(firstNameCol, lastNameCol, gameCol);	
		
		//Callback<TableColumn<Map, String>, TableCell<Map, String>>
		// cellFactoryForMap = (TableColumn<Map, String> p) -> new TextFieldTableCell(){
		//	 public String toString(Object t) {
      //           return t.toString();
        //     }
		//	 public Object fromString(String string) {
        //         return string;
         //    }
		// };

		// firstNameCol.setCellFactory(cellFactoryForMap);
		// lastNameCol.setCellFactory(cellFactoryForMap);
		// gameCol.setCellFactory(cellFactoryForMap);
		
	}
}				
	
		




