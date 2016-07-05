package game.village;

import game.Location;

/**
 * A storage is a building storing resources.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in Game Version 0.1
 **/
public class Storage extends Building {
	static final long serialVersionUID = 1;
	
	private int limit;
	
	public Storage( Location location){
		super( 10, location, 100, "Lager" );
		limit = 100;
	}
	
	public int getLimit(){
		return limit;
	}
	
	@Override public void upgradeSpecification(){
		limit *= 2;
		hitpoints *= 2;
	}
}
