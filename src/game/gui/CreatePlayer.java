package game.gui;

import game.player.Player;
import game.sql.Db;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreatePlayer {
	private Stage primaryStage;
	private TextField name;
	private Scene createScene;
	private VBox root;
	private PlayingGUI playingGUI;
	private Db db;
	private Label failed;

	public CreatePlayer(Stage primaryStage, PlayingGUI playingGUI, Db db){
		this.primaryStage = primaryStage;
		this.playingGUI = playingGUI;
		this.db = db;
		name = new TextField();
		root = new VBox();
		failed = new Label();
		createScene = new Scene(root, 100, 200);
		init();
	}
	
	private void init(){
		
		name.setPromptText("Name");
		Button enter = new Button("Erstellen");
		enter.setOnAction(e->{
			for(String name : db.loadPlayers()){
				if(name.equals(this.name.getText())){
					failed.setText("Name bereits vergeben.");
					this.name.setText("");
					return;
				}
			}
			Player ply = new Player(this.name.getText());
			db.enterPlayer(ply);
			playingGUI.play(ply);
		});
		
		root.getChildren().addAll(name, enter, failed);
		
		primaryStage.setScene(createScene);
		primaryStage.show();
	}
}
