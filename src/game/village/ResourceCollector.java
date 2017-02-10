package game.village;

import game.util.Location;

/**
 * The class RessourceCollector creates a building, which has a production and is producing a special type of resource (wood, stone, iron).
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class ResourceCollector extends Building {
	static final long serialVersionUID = 1;
	
	private int production;
	private int limit;
	private String resourceType;
	private long lastCollected;
	private double rest;
	
	
	public ResourceCollector ( Location location, String type ){
		super( 10, location, 100, "Sammler");
		this.production = 5;
		this.limit = 50;
		this.resourceType = type;
		this.lastCollected = System.currentTimeMillis();
	}
	
	public int getProduction(){
		return production;
	}
	
	public int getLimit(){
		return limit;
	}
	
	public String getResourceType(){
		return resourceType;
	}
	
	public int collect(){
		long timeSinceLastCollected =  System.currentTimeMillis() - lastCollected;
		lastCollected = System.currentTimeMillis();
		double produced = (double) ( production * timeSinceLastCollected * (1000*60*60) + rest );
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
		production *= 2;
		hitpoints *= 2;
	}
}
