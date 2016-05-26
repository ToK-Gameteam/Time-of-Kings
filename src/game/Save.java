package game;

/**
 *  Class Save
 *  
 *  The classes SaveAdmin and SavePlayer save the created player in a binary document.
 *  
 *  @author Constantin Schulte
 *  @version 0.0
 */

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

abstract class SaveAdmin {
	public static void save(Admin adminToSave){
		  try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("admin.bin"))){
			  out.writeObject(adminToSave);
			  System.out.println("Speichern erfolgreich");
		  }catch(Exception e){
			  System.err.println("Speichern fehlgeschlagen");
		  }
	  }
}

abstract class SavePlayer {
	public static void save(NormalPlayer playerToSave){
		  try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.bin"))){
			  out.writeObject(playerToSave);
			  System.out.println("Speichern erfolgreich");
		  }catch(Exception e){
			  System.err.println("Speichern fehlgeschlagen");
		  }
	  }
}
