package game.player;

import java.io.Serializable;

import game.util.Location;
import game.village.Village;

/**
 * The class Player creates a player, which has a name (3 - 16 numbers).
 * It implements functions to print Details (name, how much of which resource), and functions to give to its village,
 * for example collecting resources or build Buildings, to draw the GUI or to move or level up a building.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class Player implements Serializable {
	static final long serialVersionUID = 1;
	
	private String name;
	private Village village;

	public Player( String name ){
		this.name = name;
		village = new Village();
	}
	
	public String getName(){
		return name;
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
	
	public void collect(){
		village.collect();
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
