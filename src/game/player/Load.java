package game.player;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * The class LoadPlayer loads an already saved Player so you can use him again.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class Load {
	public static Player[] load(){
		Player[] player = new Player[1];
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.bin"))){
			player[0] =  (Player) in.readObject();
			System.out.println("Laden erfolgreich");
		}catch(Exception e){
			System.err.println("Laden fehlgeschlagen");
		}
		return player;
	}
}