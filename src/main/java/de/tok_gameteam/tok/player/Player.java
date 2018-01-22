package de.tok_gameteam.tok.player;

import de.tok_gameteam.tok.util.Location;
import de.tok_gameteam.tok.village.Building;
import de.tok_gameteam.tok.village.Village;

/**
 * The class Player creates a player, which has a name.
 * It implements functions to give details, and functions to give to its village,
 * for example collecting resources or build Buildings or to move or level up a building.
 * 
 * @author Constantin Schulte
 * @version 1.0
 **/
public class Player {
	
	/**
	 * The name of the player. Unique key in the database.
	 */
	private String name;
	
	/**
	 * The village of the player, limited to one so far.
	 */
	private Village village;

	/**
	 * Basic constructor for the player with a given name. Creates a new Village.
	 * 
	 * @param name : the name of the player.
	 */
	public Player( String name ){
		this.name = name;
		village = new Village();
	}
	
	/**
	 * Constructor for loading an existing player from the database.
	 * 
	 * @param name : the name of the player.
	 * @param village : the village of the player, usually constructed from existing values.
	 */
	public Player( String name, Village village ){
		this.name = name;
		this.village = village;
	}
	
	/**
	 * Basic getter for the name of the player.
	 * 
	 * @return The name of the player.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Basic getter for all resource values of the village.
	 * 
	 * @return int[] with the structure:
	 * 		resource:
	 * 			value, limit;
	 * 		resource:
	 * 			value, limit;
	 * 		...
	 */
	public int[] getResources(){
		int[] resources = new int[6];
		resources[0] = village.getResourceValues()[0];
		resources[1] = village.getResourceLimits()[0];
		
		resources[2] = village.getResourceValues()[1];
		resources[3] = village.getResourceLimits()[1];
		
		resources[4] = village.getResourceValues()[2];
		resources[5] = village.getResourceLimits()[2];
		return resources;
	}
	
	/**
	 * Basic getter for the village of the player.
	 * 
	 * @return The village of the player.
	 */
	public Village getVillage(){
		return village;
	}
	
	/**
	 * Collects resources from the building for the village.
	 * 
	 * @param building : A resource-collector which produced something for the village.
	 */
	public void collect(Building building){
		village.collect(building);
	}
	
	/**
	 * Builds a building at the given location.
	 * 
	 * @param building : The value of the building-type in the Village class
	 * @param location : The Location to build the building at.
	 */
	public void build( int building, Location location ){
		village.build(location, building);
	}
	
	/**
	 * Tries to upgrade the building.
	 * 
	 * @param building : The type of the building in the index of the Village.
	 * @param number : The number of the building in the index of the Village.
	 */
	public void levelUp( int building, int number ){
		village.levelUp(building, number);
	}
	
	/**
	 * Moves the building to the location.
	 * 
	 * @param building : The type of the building in the index of the Village.
	 * @param number : The number of the building in the index of the Village.
	 * @param newLocation : The location to move the building to.
	 */
	public void move( int building, int number, Location newLocation ){
		village.move(building, number, newLocation);
	}
}
