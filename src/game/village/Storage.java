package game.village;

import game.util.Location;

/**
 * A storage is a building storing resources.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in Game Version 0.1
 **/
public class Storage extends Building {
	
	private int limit;
	
	public Storage( Location location, int number){
		super( 10, location, 100, Village.STORAGE, number);
		limit = 100;
	}
	
	public Storage( Location location, int level, int id, int number){
		super( 10*(2^(level-1)), location, 100*(2^(level-1)), Village.STORAGE, level, id, number );
		limit = 100*(2^(level-1));
	}
	
	public int getLimit(){
		return limit;
	}
	
	@Override public void upgradeSpecification(){
		limit *= 2;
		hitpoints *= 2;
	}
}
