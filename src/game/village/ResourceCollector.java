package game.village;

import game.util.Location;

/**
 * The class RessourceCollector creates a building, which has a production and is producing a special type of resource (wood, stone, iron).
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class ResourceCollector extends Building {
	
	private int production;
	private int limit;
	private long lastCollected;
	private double rest;
	
	
	public ResourceCollector ( Location location, int type, int number ){
		super( 10, location, 100, type, number );
		this.production = 5;
		this.limit = 50;
		this.lastCollected = System.currentTimeMillis();
	}
	
	public ResourceCollector ( Location location, int type, int level, int id, long lastCollected, int number ){
		super( 10+(2^(level-1)), location, 100*(2^(level-1)), type, level, id, number );
		this.production = 5*(2^(level-1));
		this.limit = 50*(2^(level-1));
		this.lastCollected = lastCollected;
	}
	
	public int getProduction(){
		return production;
	}
	
	public int getLimit(){
		return limit;
	}
	
	public long getLastCollected(){
		return lastCollected;
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
		limit *= 2;
	}
}
