package game.player;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

/**
 * The class LoadPlayer loads an already saved Player so you can use him again.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class Load {
	public static Player load(){
		Player player = new Player("");
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.bin"))){
			player =  (Player) in.readObject();
		}catch(Exception e){
			Stage dialog = new Stage();
			//dialog.initStyle(StageStyle.UTILITY);
			Scene scene = new Scene(new Group(new Text(25, 25, e.toString())));
			
			dialog.setScene(scene);
			dialog.show();
		}
		return player;
	}
}