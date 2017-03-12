package de.tok_gameteam.tok.gui;

import config.LanguageController;
import de.tok_gameteam.tok.player.Player;
import de.tok_gameteam.tok.sql.Db;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreatePlayer {
	private Stage createStage;
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
		createStage = new Stage();
		name = new TextField();
		root = new VBox();
		failed = new Label();
		createScene = new Scene(root, 125, 200);
		init();
	}
	
	private void init(){
		root.setSpacing(10);
		root.setPadding(new Insets(20));
		
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
			createStage.close();
			Player ply = new Player(this.name.getText());
			db.enterPlayer(ply);
			playingGUI.play(ply);
			createStage.close();
		});
		
		root.getChildren().addAll(name, enter, failed);
		root.setAlignment(Pos.TOP_CENTER);
		
		createStage.setScene(createScene);
	}
	
	public void activate(){
		createStage.show();
		failed.requestFocus();
	}
}
