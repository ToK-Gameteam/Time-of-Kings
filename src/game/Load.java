package game;

/*
 * Class Load
 * 
 * The classes LoadAdmin and LoadPlayer load an already saved Player so you can use him again.
 * 
 * @author Constantin Schulte
 * @version 0.0
 */

import java.io.FileInputStream;
import java.io.ObjectInputStream;

abstract class LoadAdmin {
	public static Admin[] main(){
		Admin[] admin = new Admin[10];
		admin[0] = (Admin) load()[0];
		return admin;
	}

	public static Admin[] load(){
		Admin[] admin = new Admin[1];
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("admin.bin"))){
			admin[0] =  (Admin) in.readObject();
			System.out.println("Laden erfolgreich");
		}catch(Exception e){
			System.err.println("Laden fehlgeschlagen");
		}
		return admin;
	}
}

abstract class LoadPlayer {
	public static NormalPlayer[] main(){
		NormalPlayer[] player = new NormalPlayer[1];
		player[0] = (NormalPlayer) load()[0];
		return player;
	}

	public static NormalPlayer[] load(){
		NormalPlayer[] player = new NormalPlayer[1];
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.bin"))){
			player[0] =  (NormalPlayer) in.readObject();
			System.out.println("Laden erfolgreich");
		}catch(Exception e){
			System.err.println("Laden fehlgeschlagen");
		}
		return player;
	}
}
