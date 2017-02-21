package game;

import game.gui.Gui;
import game.sql.Db;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Gui gui;
	private Db db;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		db = new Db();
		gui = new Gui(primaryStage, db);
		gui.initialize();
	}

}
