package config;

import de.tok_gameteam.tok.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChooseLanguage {
	private Stage chooseStage;
	private Scene chooseScene;
	private GridPane root;
	private Button choose;
	private Label chooseLabel;
	private ComboBox<String> languageBox;

	public ChooseLanguage(){
		ObservableList<String> languages = FXCollections.observableArrayList(
				"German", "English");
		languageBox = new ComboBox<String>(languages);
		chooseStage = new Stage();
		root = new GridPane();
		chooseScene = new Scene(root, 400, 200);
		choose = new Button("Choose");
		chooseLabel = new Label("Choose your prefered language.");
		init();
	}
	
	private void init(){
		chooseStage.setTitle("Choose language");
		languageBox.setValue("English");
		root.setAlignment(Pos.CENTER);
		root.setHgap(50);
		root.setVgap(50);
		root.add(chooseLabel, 0, 0);
		root.add(choose, 1, 1);
		root.add(languageBox, 0, 1);
	}
	
	public void choose( Main main ){
		chooseStage.setScene(chooseScene);
		chooseStage.show();
		choose.setOnAction(e->{
			if(languageBox.getValue() != null){
				main.setLanguage(languageBox.getValue());
				main.load();
				chooseStage.close();
			}
		});
	}
}
