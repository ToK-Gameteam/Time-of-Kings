package game.village;

import java.io.Serializable;
import game.*;

/**
 * The class Building creates a building, which has a level (at the beginning 1), a number (depending on the number of buildings of this type build before),
 * a location saved in a java.awt.Point, and allows to level the building up and to change the location.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public abstract class Building implements Serializable, BuildingUpgrade {
	static final long serialVersionUID = 1;
	
	protected String type;
	protected Location location;
	protected int upgradeCost;
	protected int level;
	protected int hitpoints;
	
	public Building( int upgradeCost, Location location, int hitpoints, String type ){
		this.type = type;
		this.upgradeCost = upgradeCost;
		this.location = location;
		this.hitpoints = hitpoints;
		level = 1;
	}
	
	public String getType(){
		return type;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getUpgradeCosts(){
		return upgradeCost;
	}
	
	public int getHitpoints(){
		return hitpoints;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setLocation( Location location ){
		this.location = location;
	}
	
	public int levelUp(int[] resources){
		if( resources[0] >= upgradeCost && resources[1] >= upgradeCost && resources[3] >= upgradeCost && level < 7 ){
			++level;
			upgradeSpecification();
			upgradeCost *= 2;
			return upgradeCost / 2;
		}else{
			return 0;
		}
	}
	
	@Override public void upgradeSpecification(){}
}
