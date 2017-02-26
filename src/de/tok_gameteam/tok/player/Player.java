package de.tok_gameteam.tok.player;

import de.tok_gameteam.tok.util.Location;
import de.tok_gameteam.tok.village.Building;
import de.tok_gameteam.tok.village.Village;

/**
 * The class Player creates a player, which has a name (3 - 16 numbers).
 * It implements functions to print Details (name, how much of which resource), and functions to give to its village,
 * for example collecting resources or build Buildings, to draw the GUI or to move or level up a building.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class Player {
	
	private String name;
	private Village village;

	public Player( String name ){
		this.name = name;
		village = new Village();
	}
	
	public Player( String name, Village village ){
		this.name = name;
		this.village = village;
	}
	
	public String getName(){
		return name;
	}
	
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
	
	public String[] getInformation(){
		String[] information = new String[8];
		information[0] = name;
		information[1] = village.getResourceValues()[0] + "";
		information[2] = village.getResourceLimits()[0] + "";
		information[3] = village.getResourceValues()[1] + "";
		information[4] = village.getResourceLimits()[1] + "";
		information[5] = village.getResourceValues()[2] + "";
		information[6] = village.getResourceLimits()[2] + "";
		information[7] = village.getName();
		return information;
	}
	
	public Village getVillage(){
		return village;
	}
	
	public void createVillage( String name ){
		village.setName(name);
	}
	
	public void collect(Building building){
		village.collect(building);
	}
	
	public void build( int building, Location location ){
		village.build(location, building);
	}
	
	public void levelUp( int building, int number ){
		village.levelUp(building, number);
	}
	
	public void move( int building, int number, Location newLocation ){
		village.move(building, number, newLocation);
	}
}
