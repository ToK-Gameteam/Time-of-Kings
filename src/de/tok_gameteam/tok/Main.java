package de.tok_gameteam.tok;

import config.ChooseLanguage;
import config.Configuration;
import config.LanguageController;
import de.tok_gameteam.tok.gui.Gui;
import de.tok_gameteam.tok.sql.Db;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of the Game. Sets everything up.
 * 
 * @author Constantin
 * @version 1.0
 */
public class Main extends Application {
	
	/**
	 * The primary stage of the program.
	 */
	private Stage primaryStage;
	
	/**
	 * The Gui of the program.
	 */
	private Gui gui;
	
	/**
	 * The db of the program.
	 */
	private Db db;
	
	/**
	 * The langController for the chosen language.
	 */
	private LanguageController langController;
	
	/**
	 * The configuration object which changes configurations of the game.
	 */
	private Configuration config;
	
	/**
	 * The language chosen by the player.
	 */
	private String language;
	
	/**
	 * Just starts the application.
	 * 
	 * @param args : Given console-arguments by the user. Non so far.
	 */
	public static void main(String[] args){
		launch(args);
	}

	/**
	 * Declares all important stuff for the game and loads.
	 */
	@Override public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		config = new Configuration();
		language = config.getConfiguration("language");
		if(language.equals("default")){
			ChooseLanguage chooseLang = new ChooseLanguage();
			chooseLang.choose(this);
		}else{
			load();
		}
	}
	
	/**
	 * Sets the configuration "language" to the given.
	 * 
	 * @param language : The language the user chose.
	 */
	public void setLanguage(String language){
		this.language = language;
		config.changeConfiguration("language", language);
	}

	/**
	 * Creates LanguageController and initializes graphical elements.
	 */
	public void load(){
		langController = new LanguageController(language);
		db = new Db();
		gui = new Gui(primaryStage, db, langController);
		gui.initialize();
	}
}
