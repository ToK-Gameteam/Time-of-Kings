package game.player;

import java.io.Serializable;
import game.village.Village;
import game.*;

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
	private int level;
	private int allEp;
	private int collectedEpForNextLevel;
	private int neededEpForNextLevel;
	private Village[] myVillages;

	public Player( String name ){
		this.name = name;
		this.level = 1;
		this.allEp = 0;
		this.collectedEpForNextLevel = 0;
		this.neededEpForNextLevel = 10;
		this.myVillages = new Village[5];
		for( int i = 0; i < 5; ++i ){
			myVillages[i] = new Village();
		}
	}
	
	public String getName(){
		return name;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getEp(){
		return allEp;
	}
	
	public int getNeededEp(){
		return neededEpForNextLevel;
	}
	
	public String[] getInformation( int number ){
		--number;
		String[] information = new String[5];
		information[0] = name;
		information[1] = level + " (" + collectedEpForNextLevel + "/" + neededEpForNextLevel + " Ep)";
		information[2] = "Holz: " + myVillages[number].getResourceValues()[0] + "/" + myVillages[number].getResourceLimits()[0];
		information[3] = "Stein: " + myVillages[number].getResourceValues()[1] + "/" + myVillages[number].getResourceLimits()[1];
		information[4] = "Eisen: " + myVillages[number].getResourceValues()[2] + "/" + myVillages[number].getResourceLimits()[2];
		return information;
	}
	
	public void collect( int number ){
		--number;
		myVillages[number].collect();
	}
	
	public void build( int number, String buildingType, Location location ){
		--number;
		collectedEpForNextLevel += myVillages[number].build(location, buildingType);
		checkLevelUp();
	}
	
	public void levelUp( int villageNumber, String buildingToUpgrade, int number ){
		--villageNumber;
		collectedEpForNextLevel += myVillages[villageNumber].levelUp(buildingToUpgrade, number);
		checkLevelUp();
	}
	
	public void move( int villageNumber, String buildingToMove, int number, Location newLocation ){
		--villageNumber;
		myVillages[villageNumber].move(buildingToMove, number, newLocation);
	}
	
	private void checkLevelUp(){
		if( collectedEpForNextLevel >= neededEpForNextLevel){
			++level;
			collectedEpForNextLevel -= neededEpForNextLevel;
			allEp += neededEpForNextLevel;
			neededEpForNextLevel *= 10;
		}
	}
}
