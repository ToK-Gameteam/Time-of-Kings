package game.gui;

import java.util.ArrayList;

import game.sql.Db;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChoosePlayer {

	private Stage myStage;
	private Button[] nameButtons;
	private ArrayList<String> names;
	private GridPane pane;
	private Scene scene;
	private PlayingGUI playingGUI;
	private Db db;
	
	public ChoosePlayer(Db db, PlayingGUI playingGUI){
		this.playingGUI = playingGUI;
		myStage = new Stage();
		nameButtons = new Button[10];
		names = db.loadPlayers();
		this.db = db;
		pane = new GridPane();
		scene = new Scene(pane);
		init();
	}
	
	private void init(){
		if(!names.isEmpty()){
			for(int index = 0; index < 10; ++index){
				if(names.size()>index){
					nameButtons[index] = new Button(names.get(index));
					nameButtons[index].setPrefSize(100, 20);
					pane.add(nameButtons[index], index, 0);
					final int i = index;
					nameButtons[index].setOnAction(e->{
						myStage.close();
						playingGUI.play(db.getPlayer(nameButtons[i].getText()));
					});
				}
			}
		}
		myStage.setScene(scene);
		myStage.show();
	}
}
