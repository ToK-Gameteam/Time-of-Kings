package game.gui;

import game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Gui {
	
	private Stage primaryStage;
	private PlayingGUI playingGUI;
	private Game game;
	private Rectangle2D screen;
	private int screenWidth, screenHeight;
	
	private Group root;
	private Canvas startCanvas;
	private GraphicsContext startGC;
	private Scene startScene;
	
	public Gui(Stage primaryStage, Game game){
		this.primaryStage = primaryStage;
		playingGUI = new PlayingGUI(primaryStage, screenWidth, screenHeight);
		this.game = game;
		
		screen = Screen.getPrimary().getVisualBounds();
		screenWidth = (int) screen.getMaxX();
		screenHeight = (int) screen.getMaxY();
		
		startCanvas = new Canvas(800, 450);
		startGC = startCanvas.getGraphicsContext2D();
		root = new Group();
		root.getChildren().add(startCanvas);
		startScene = new Scene(root);
		
		initialize2();
	}
	
	public void initialize(){
		primaryStage.setTitle("Time of Kings");
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(Gui.class.getResourceAsStream("../resources/Icon.png")));
				
		Group root = new Group();
		
		Canvas canvas = new Canvas(800, 450);
		GraphicsContext gcb = canvas.getGraphicsContext2D();
		gcb.drawImage(new Image(Gui.class.getResourceAsStream("../resources/Village.jpg")), 0, 0, 800, 450);
		GridPane startPane = new GridPane();
		startPane.setId("startPane");
		
		startPane.setAlignment(Pos.CENTER);
		startPane.setHgap(100);
		startPane.setVgap(100);
		startPane.setPadding(new Insets(145, 285, 32, 285));
		
		GridPane loginPane = new GridPane();
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setPrefSize(300, 300);
		loginPane.setHgap(16);
		loginPane.setVgap(16);
		loginPane.setId("loginPane");
		
		Button loadPlayer = new Button("Spieler laden");
		Button createPlayer = new Button("Spieler erstellen");
		
		loadPlayer.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	//loadPlayer();
            	playingGUI.play();
            }
        });
		createPlayer.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	//createPlayer();
            }
        });
		
		loginPane.add(loadPlayer, 0, 1);
		loginPane.add(createPlayer, 0, 2);
		
		GridPane updatePane = new GridPane();
		updatePane.setHgap(10);
		updatePane.setVgap(10);
		updatePane.setPadding(new Insets(10, 10, 10, 10));
		updatePane.setMaxSize(256, 64);
		updatePane.setId("updatePane");
		
		Label update = new Label("Update:");
		update.setId("update");
		
		updatePane.add(update, 0, 0);
		
		ProgressBar pb = new ProgressBar();
		
		updatePane.add(pb, 1, 0, 2, 1);
		
		startPane.add(updatePane, 0, 1, 2, 1);
		startPane.add(loginPane, 1, 0);
		
		//startPane.setGridLinesVisible(true);
		
		Scene helloScene = new Scene( root, 800, 450 );
				
		root.getChildren().addAll(canvas, startPane);
		helloScene.getStylesheets().add(Gui.class.getResource("startGUI.css").toExternalForm());
		
		primaryStage.setScene(helloScene);
		primaryStage.show();
	}
	
	private void initialize2(){
		startGC.drawImage(new Image(Gui.class.getResourceAsStream("../resources/Village.jpg")), 0, 0, 800, 450);
		startGC.drawImage(new Image(Gui.class.getResourceAsStream("../resources/loginPane.png")), 500, 100, 250, 230);
		startGC.drawImage(new Image(Gui.class.getResourceAsStream("../resources/loginPane.png")), 175, 100, 250, 230);
		startGC.drawImage(new Image(Gui.class.getResourceAsStream("../resources/updatePane.png")), 200, 350, 400, 50);
		
		//TODO
		
		primaryStage.getIcons().add(new Image(Gui.class.getResourceAsStream("../resources/Icon.png")));
		primaryStage.setTitle("Time of Kings");
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	public void play(){
		 
	}
}
