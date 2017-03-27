package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * A storage is a building storing resources.
 * 
 * @author Constantin Schulte
 * @version 1.0
 **/
public class Storage extends Building {
	
	/**
	 * The limit of the Storage depending on its level.
	 */
	private int limit;
	
	/**
	 * Standard constructor when building a new Storage.
	 * 
	 * @param location : The Location to built the Storage at.
	 * @param number : The number of the Storage.
	 */
	public Storage( Location location, int number){
		super( location, 9000, Village.STORAGE, number);
		limit = 3000;
	}
	
	/**
	 * The constructor of a loaded Storage -> Db
	 * 
	 * @param location : The Location of the Storage.
	 * @param level : The level of the Storage.
	 * @param id : The id of the Storage.
	 * @param number : The number of the Storage.
	 */
	public Storage( Location location, int level, int id, int number){
		super( location, 9000*(3^(level-1)), Village.STORAGE, level, id, number );
		limit = Village.BUILDING_VALUES[3][level-1];
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The limit of the Storage.
	 */
	public int getLimit(){
		return limit;
	}
	
	/**
	 * Sets the limit and the hitpoints of the Storage when upgrading.
	 */
	@Override public void upgradeSpecification(){
		limit = Village.BUILDING_VALUES[3][level-1];
		hitpoints *= 2;
	}
}
