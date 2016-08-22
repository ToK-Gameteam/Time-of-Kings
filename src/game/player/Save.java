package game.player;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
			  System.out.println("Speichern erfolgreich");
		  }catch(Exception e){
			  System.err.println("Speichern fehlgeschlagen");
		  }
	  }
}
