package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The class RessourceCollector creates a Building,
 *  which has a production and is producing a special type of resource (wood, stone, iron).
 * 
 * @author Constantin Schulte
 * @version 1.0
 */
public class ResourceCollector extends Building {
	
	/**
	 * The production of the ResourceCollector, depending on its level.
	 */
	private int production;
	
	/**
	 * The limit of the ResourceCollector, depending on its level.
	 */
	private int limit;
	
	/**
	 * The time when the user collected the Resource the last time.
	 */
	private long lastCollected;
	
	/**
	 * The non-int rest, which was already produced when the user collected, but shouldn't be lost.
	 */
	private double rest;
	
	/**
	 * Standard constructor.
	 * 
	 * @param location : The Location to build the ResourceCollector at.
	 * @param type : The type of Resource.
	 * @param number : The number of the Building.
	 */
	public ResourceCollector ( Location location, int type, int number ){
		super( location, 100, type, number );
		this.production = Village.BUILDING_VALUES[0][0];
		this.limit = Village.BUILDING_VALUES[1][0];
		this.lastCollected = System.currentTimeMillis();
	}
	
	/**
	 * Constructor for an existing ResourceCollector
	 * 
	 * @param location : The Location of the Building.
	 * @param type : The type of Resource.
	 * @param level : The level of the ResourceCollector.
	 * @param id : The id with which it is saved.
	 * @param lastCollected : The time the user collected the last time.
	 * @param number : The number of the Building.
	 */
	public ResourceCollector ( Location location, int type, int level, int id, long lastCollected, int number ){
		super( location, 100*(2^(level-1)), type, level, id, number );
		this.production = Village.BUILDING_VALUES[0][level-1];
		this.limit = Village.BUILDING_VALUES[1][level-1];
		this.lastCollected = lastCollected;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The capacity of the ResourceCollector.
	 */
	public int getLimit(){
		return limit;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The last time when the user collected from this Building.
	 */
	public long getLastCollected(){
		return lastCollected;
	}
	
	/**
	 * Collects all resources from the Building and sets the rest.
	 * 
	 * @return The amount of resources produced.
	 */
	public int collect(){
		long timeSinceLastCollected =  System.currentTimeMillis() - lastCollected;
		lastCollected = System.currentTimeMillis();
		double produced = (double) ( production * timeSinceLastCollected / (1000*60*60) + rest );
		if( produced > limit){
			produced = limit;
		}
		rest = produced;
		while( rest > 1 ){
			--rest;
		}
		return (int) produced;
	}
	
	
	@Override public void upgradeSpecification(){
		production = Village.BUILDING_VALUES[0][level-1];
		hitpoints *= 2;
		limit = Village.BUILDING_VALUES[1][level-1];
	}
}
