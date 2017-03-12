package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The Wall is a building to defend your Village.
 * TODO: Implement the adjustments for the battle system.
 * 
 * @author Constantin Schulte
 * @version 1.0
 */
public class Wall extends Building {

	/**
	 * Standard constructor when building a wall.
	 * 
	 * @param location : The Location to build the wall at.
	 * @param number : The number of the Wall.
	 */
	public Wall( Location location, int number ){
		super( location, 200, Village.WALL, number );
	}
	
	/**
	 * Constructor of a loaded Wall -> Db
	 * 
	 * @param location : The Location the Wall is built at.
	 * @param level : The level of the Wall.
	 * @param id : The id of the Wall.
	 * @param number : The number of the Wall.
	 */
	public Wall( Location location, int level, int id, int number ){
		super(location, 200*(5^(level-1)), Village.WALL, level, id, number );
	}
	
	/**
	 * Sets the hitpoints of the Wall when upgrading.
	 */
	@Override public void upgradeSpecification(){
		hitpoints *= 5;
	}
}
