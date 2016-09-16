package game.gui;

import game.player.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class StartGUI extends Application {
	Stage primaryStage;
	
	Player ply;
	
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
		primaryStage.setResizable(false);
		primaryStage.getIcons().add(new Image(StartGUI.class.getResourceAsStream("../resources/Icon.png")));
				
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
		loginPane.setMaxSize(256, 256);
		loginPane.setId("loginPane");
		
		Button loadPlayer = new Button("Spieler laden");
		Button createPlayer = new Button("Spieler erstellen");
		
		loadPlayer.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	loadPlayer();
            }
        });
		createPlayer.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	createPlayer();
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
		
		Scene helloScene = new Scene( startPane, 800, 450 );
				
		helloScene.getStylesheets().add(StartGUI.class.getResource("startGUI.css").toExternalForm());
		
		primaryStage.setScene(helloScene);
		primaryStage.show();
	}
	
	public void createPlayer(){
		GridPane createPane = new GridPane();
		
		TextField name = new TextField();
		name.setText("Name");
		
		Button create = new Button("Erstellen.");
		create.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	if( name.getText() != ""){
            		ply = new Player(name.getText());
            		Save.save(ply);
            		createVillage();
            	}
            }
        });
		
		createPane.add(name, 0, 1);
		createPane.add(create, 0, 2);
		Scene createScene = new Scene(createPane, 100, 100);
		primaryStage.setScene(createScene);
		primaryStage.show();
	}
	
	private void createVillage(){
		GridPane createPane = new GridPane();
		
		TextField name = new TextField();
		name.setText("Dorfname");
		
		Button create = new Button("Erstellen.");
		create.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
            	if( name.getText() != ""){
            		ply.createVillage(name.getText());
            		Save.save(ply);
            		play();
            	}
            }
        });
		
		createPane.add(name, 0, 1);
		createPane.add(create, 0, 2);
		Scene createScene = new Scene(createPane, 100, 100);
		primaryStage.setScene(createScene);
		primaryStage.show();
	}
	
	private void play(){
		
	}
	
	public void loadPlayer(){
		GridPane loadPane = new GridPane();

		ply = Load.load();
		Button[] villages = new Button[5];
		for(int i = 0; i < 5; ++i){
			villages[i] = new Button();
			if(ply.getInformation(i)[5] != ""){
				villages[i].setText(ply.getInformation(i)[5]);
			}else{
				villages[i].setText(" < leer > ");
			}
			
			loadPane.add(villages[i], 1, i);
		}
		
		Scene loadScene = new Scene( loadPane, 150, 200);
		primaryStage.setScene(loadScene);
		primaryStage.show();
	}

}
