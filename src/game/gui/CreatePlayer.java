package game.gui;

import config.LanguageController;
import game.player.Player;
import game.sql.Db;
import javafx.geometry.Pos;
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
	private LanguageController langController;

	public CreatePlayer(PlayingGUI playingGUI, Db db, LanguageController langController){
		this.playingGUI = playingGUI;
		this.db = db;
		this.langController = langController;
		primaryStage = new Stage();
		name = new TextField();
		root = new VBox();
		failed = new Label();
		createScene = new Scene(root, 100, 200);
		init();
	}
	
	private void init(){
		
		name.setPromptText(langController.getString("name"));
		Button enter = new Button(langController.getString("create"));
		enter.setOnAction(e->{
			for(String name : db.loadPlayers()){
				if(name.equals(this.name.getText())){
					failed.setText(langController.getString("name_used"));
					this.name.setText("");
					return;
				}
			}
			Player ply = new Player(this.name.getText());
			db.enterPlayer(ply);
			playingGUI.play(ply);
		});
		
		root.getChildren().addAll(name, enter, failed);
		root.setAlignment(Pos.TOP_CENTER);
		
		primaryStage.setScene(createScene);
	}
	
	public void activate(){
		primaryStage.show();
	}
}
