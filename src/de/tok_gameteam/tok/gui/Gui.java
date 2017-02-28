package de.tok_gameteam.tok.gui;

import config.LanguageController;
import de.tok_gameteam.tok.sql.Db;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Gui {
	public static final String PICTURE_PATH =  "/de/tok_gameteam/tok/resources/";
	
	private Stage primaryStage;
	private PlayingGUI playingGUI;
	private Db game;
	private LanguageController langController;
	private Rectangle2D screen;
	private int screenWidth, screenHeight;
	
	private Group root;
	private Canvas startCanvas;
	private GraphicsContext startGC;
	private Scene startScene;
	
	public Gui(Stage primaryStage, Db game, LanguageController langController){
		this.primaryStage = primaryStage;
		this.game = game;
		this.langController = langController;
		
		primaryStage.setFullScreen(true);
		screen = Screen.getPrimary().getVisualBounds();
		screenWidth = (int) screen.getMaxX();
		screenHeight = (int) screen.getMaxY();
		primaryStage.setFullScreen(false);
		
		startCanvas = new Canvas(800, 450);
		startGC = startCanvas.getGraphicsContext2D();
		root = new Group();
		root.getChildren().add(startCanvas);
		startScene = new Scene(root);

		playingGUI = new PlayingGUI(primaryStage, screenWidth-100, screenHeight, game, langController);	
	}
	
	public void initialize(){
		startGC.setFill(Color.GREEN);
		startGC.fillRect(0, 0, 800, 450);
		startGC.drawImage(new Image(PICTURE_PATH + "loginPane.png"),
				500, 150, 250, 50);
		startGC.drawImage(new Image(PICTURE_PATH + "loginPane.png"),
				500, 225, 250, 50);
		
		startGC.strokeText(langController.getString("load_player"), 550, 175);
		startGC.strokeText(langController.getString("create_player"), 550, 250);
		
		startCanvas.setOnMouseClicked(e->{
			if(e.getX() >= 500 && e.getX() <= 750 && e.getY() >= 150 && e.getY() <= 200){
				ChoosePlayer choose = new ChoosePlayer(game, playingGUI);
				choose.activate();
			}
			if(e.getX() >= 500 && e.getX() <= 750 && e.getY() >= 225 && e.getY() <= 275){
				CreatePlayer createPlayer = new CreatePlayer(playingGUI, game, langController);
            	createPlayer.activate();
			}
		});
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("Time of Kings");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
}
