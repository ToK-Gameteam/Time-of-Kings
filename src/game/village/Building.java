package game.village;

import game.util.Location;

/**
 * The class Building creates a building, which has a level (at the beginning 1), a number (depending on the number of buildings of this type build before),
 * a location saved in a java.awt.Point, and allows to level the building up and to change the location.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public abstract class Building implements BuildingUpgrade {
	static final long serialVersionUID = 1;
	
	protected Location location;
	protected int upgradeCost;
	protected int level;
	protected int hitpoints;
	protected int type;
	protected int id, number;
	
	public Building( int upgradeCost, Location location, int hitpoints, int type, int number ){
		this.upgradeCost = upgradeCost;
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.number = number;
		level = 1;
		id = 0;
	}
	
	public Building(int upgradeCost, Location location, int hitpoints, int type, int level, int id, int number){

		this.upgradeCost = upgradeCost;
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.level = level;
		this.id = id;
		this.number = number;
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
	
	public int getType(){
		return type;
	}
	
	public void setLocation( Location location ){
		this.location = location;
	}
	
	public int levelUp(int[] resources){
		if( resources[0] >= upgradeCost && resources[1] >= upgradeCost && resources[2] >= upgradeCost && level < 7 ){
			++level;
			upgradeSpecification();
			upgradeCost *= 2;
			return upgradeCost / 2;
		}else{
			return 0;
		}
	}
	
	@Override public void upgradeSpecification(){}

	public int getId() {
		return id;
	}
	
	public int getNumber(){
		return number;
	}
}
