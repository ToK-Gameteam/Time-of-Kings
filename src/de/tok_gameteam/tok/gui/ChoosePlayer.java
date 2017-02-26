package de.tok_gameteam.tok.gui;

import java.util.ArrayList;

import de.tok_gameteam.tok.sql.Db;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChoosePlayer {

	private Stage choosePlayerStage;
	private Button[] nameButtons;
	private ArrayList<String> names;
	private GridPane pane;
	private Scene scene;
	private PlayingGUI playingGUI;
	private Db db;
	
	public ChoosePlayer(Db db, PlayingGUI playingGUI){
		this.playingGUI = playingGUI;
		this.db = db;
		choosePlayerStage = new Stage();
		nameButtons = new Button[10];
		names = db.loadPlayers();
		pane = new GridPane();
		scene = new Scene(pane, 1000, 550);
		init();
	}
	
	private void init(){
		pane.setAlignment(Pos.CENTER);
		if(!names.isEmpty()){
			for(int index = 0; index < 10; ++index){
				if(names.size()>index){
					nameButtons[index] = new Button(names.get(index));
					nameButtons[index].setPrefSize(100, 20);
					pane.add(nameButtons[index], 0, index);
					final int i = index;
					nameButtons[index].setOnAction(e->{
						choosePlayerStage.close();
						playingGUI.play(db.getPlayer(nameButtons[i].getText()));
					});
				}
			}
		}
		choosePlayerStage.setScene(scene);
	}
	
	public void activate(){
		choosePlayerStage.show();
	}
}
