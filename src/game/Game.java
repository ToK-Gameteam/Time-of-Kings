package game;

import game.player.Load;
import game.player.Player;
import game.player.Save;
import game.sql.Db;

public class Game {
	//TODO
	
	private Db db;
	
	private Player player;

	public Game(Db db){
		this.db = db;
	}
	
	public void loadPlayer(){
		player = Load.load();
	}
	
	public void savePlayer(){
		Save.save(player);
	}
	
	public void endGame(){
		db.closeConnection();
	}
}
