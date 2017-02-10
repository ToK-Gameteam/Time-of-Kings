package game.gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class PlayingGUI {
	private Stage primaryStage;
	private Canvas buildings, options, bg;
	private GraphicsContext buildingsC, optionsC, bgc;
	private Scene playingScene;
	private Group root;
	
	private int screenWidth, screenHeight;

	public PlayingGUI(Stage primaryStage, int screenWidth, int screenHeight){
		this.primaryStage = primaryStage;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		buildings = new Canvas(screenWidth, screenHeight);
		buildingsC = buildings.getGraphicsContext2D();
		
		options = new Canvas(screenWidth, screenHeight);
		optionsC = options.getGraphicsContext2D();
		
		bg = new Canvas(screenWidth, screenHeight);
		bgc = bg.getGraphicsContext2D();
		bgc.setFill(Paint.valueOf("GREEN"));
		
		root = new Group();
		root.getChildren().addAll(buildings, options, bg);
		
		options.toBack();
		buildings.toFront();

		playingScene = new Scene(root);
		initialize();
	}
	
	private void initialize(){
		bgc.fillRect(0, 0, 1000, 750);
		
		buildingsC.drawImage(new Image(PlayingGUI.class.getResourceAsStream("../resources/dummy_building.png"))
				, 0, 0, 50, 50);
		//TODO
	}
	
	public void play(){
		primaryStage.setScene(playingScene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		//TODO
	}
}
