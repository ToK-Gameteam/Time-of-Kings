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
		super( new int[]{15, 15, 15}, location, 100, Village.STORAGE, number);
		limit = 1000;
	}
	
	public Storage( Location location, int level, int id, int number){
		super( new int[]{15*(2^(level-1)), 15*(2^(level-1)), 15*(2^(level-1))},
				location, 100*(2^(level-1)), Village.STORAGE, level, id, number );
		limit = 1000*(2^(level-1));
	}
	
	public int getLimit(){
		return limit;
	}
	
	@Override public void upgradeSpecification(){
		limit *= 2;
		hitpoints *= 2;
	}
}
