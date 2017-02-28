package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * A storage is a building storing resources.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in Game Version 0.1
 **/
public class Storage extends Building {
	
	private int limit;
	
	public Storage( Location location, int number){
		super( location, 9000, Village.STORAGE, number);
		limit = 3000;
	}
	
	public Storage( Location location, int level, int id, int number){
		super( location, 9000*(3^(level-1)), Village.STORAGE, level, id, number );
		limit = Village.BUILDING_VALUES[3][level-1];
	}
	
	public int getLimit(){
		return limit;
	}
	
	@Override public void upgradeSpecification(){
		limit = Village.BUILDING_VALUES[3][level-1];
		hitpoints *= 2;
	}
}
