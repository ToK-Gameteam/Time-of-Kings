package game.player;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *  Class Save
 *  
 *  The class SavePlayer saves the created player in a binary file.
 *  
 *  @author Constantin Schulte
 *  @version 0.1
 **/
public class Save {
	public static void save( Player playerToSave ){
		  try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.bin"))){
			  out.writeObject(playerToSave);
		  }catch(Exception e){
			  Stage dialog = new Stage();
				//dialog.initStyle(StageStyle.UTILITY);
				Scene scene = new Scene(new Group(new Text(25, 25, e.toString())));
				
				dialog.setScene(scene);
				dialog.show();
		  }
	  }
}
