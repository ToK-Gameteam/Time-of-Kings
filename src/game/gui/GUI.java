package game.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

public class GUI extends Application {
	Stage primaryStage;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		initialize();
	}
	
	public void initialize(){
		primaryStage.setTitle("Time of Kings");
		
		GridPane mainPane = new GridPane();
		
		GridPane startPane = new GridPane();
		startPane.setId("startPane");
		
		startPane.setAlignment(Pos.CENTER);
		startPane.setHgap(100);
		startPane.setVgap(100);
		startPane.setPadding(new Insets(145, 285, 32, 285));
		
		GridPane loginPane = new GridPane();
		loginPane.setHgap(16);
		loginPane.setVgap(16);
		loginPane.setPadding(new Insets(25, 25, 25, 25));
		loginPane.setMaxSize(128, 128);
		loginPane.setId("loginPane");
		
		Button loadPlayer = new Button("Spieler laden");
		Button createPlayer = new Button("Spieler erstellen");
		
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
		
		mainPane.add(startPane, 0, 0);
		
		//startPane.setGridLinesVisible(true);
		
		Scene helloScene = new Scene( mainPane, 800, 450 );
				
		helloScene.getStylesheets().add(GUI.class.getResource("GUI.css").toExternalForm());
		
		primaryStage.setScene(helloScene);
		primaryStage.show();
	}

}
