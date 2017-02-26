package de.tok_gameteam.tok;

import config.ChooseLanguage;
import config.Configuration;
import config.LanguageController;
import de.tok_gameteam.tok.gui.Gui;
import de.tok_gameteam.tok.sql.Db;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private Gui gui;
	private Db db;
	private LanguageController langController;
	private Configuration config;
	private String language;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		config = new Configuration();
		language = "German";
		language = config.getConfiguration("language");
		if(language.equals("default")){
			ChooseLanguage chooseLang = new ChooseLanguage();
			chooseLang.choose(this);
		}else{
			load();
		}
	}
	
	public void setLanguage(String language){
		this.language = language;
		config.changeConfiguration("language", language);
		System.out.println(language);
	}

	public void load(){
		langController = new LanguageController(language);
		db = new Db();
		gui = new Gui(primaryStage, db, langController);
		gui.initialize();
	}
}
