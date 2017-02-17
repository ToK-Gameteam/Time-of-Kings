package game.gui;

import game.sql.Db;
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
	
	private Stage primaryStage;
	private PlayingGUI playingGUI;
	private Db game;
	private Rectangle2D screen;
	private int screenWidth, screenHeight;
	
	private Group root;
	private Canvas startCanvas;
	private GraphicsContext startGC;
	private Scene startScene;
	
	public Gui(Stage primaryStage, Db game){
		this.primaryStage = primaryStage;
		this.game = game;
		
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

		playingGUI = new PlayingGUI(primaryStage, screenWidth-100, screenHeight, game);	
	}
	
	public void initialize(){
		primaryStage.setTitle("Time of Kings");
		primaryStage.setResizable(false);
		if(getClass().getResourceAsStream("./../resources/Icon.png") == null){
			System.err.println("null 1");
		}
		primaryStage.getIcons().add(new Image(getClass().getResource("../resources/Icon.png").toExternalForm()));
		
		startGC.setFill(Color.GREEN);
		startGC.fillRect(0, 0, 800, 450);
		startGC.drawImage(new Image(getClass().getResource("../resources/loginPane.png").toExternalForm()),
				500, 150, 250, 50);
		startGC.drawImage(new Image(getClass().getResource("../resources/loginPane.png").toExternalForm()),
				500, 225, 250, 50);
		
		startGC.strokeText("Spieler laden", 550, 175);
		startGC.strokeText("Spieler erstellen", 550, 250);
		
		startCanvas.setOnMouseClicked(e->{
			if(e.getX() >= 500 && e.getX() <= 750 && e.getY() >= 150 && e.getY() <= 200){
            	@SuppressWarnings("unused")
				ChoosePlayer choose = new ChoosePlayer(game, playingGUI);
			}
			if(e.getX() >= 500 && e.getX() <= 750 && e.getY() >= 225 && e.getY() <= 275){
            	@SuppressWarnings("unused")
				CreatePlayer createPlayer = new CreatePlayer(primaryStage, playingGUI, game);
			}
		});
		
		primaryStage.setTitle("Time of Kings");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
}
