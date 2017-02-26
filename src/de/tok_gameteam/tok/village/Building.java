package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

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
	protected int upgradeCost[];
	protected int level;
	protected int hitpoints;
	protected int type;
	protected int id, number;
	
	public Building( int upgradeCost[], Location location, int hitpoints, int type, int number ){
		this.upgradeCost = upgradeCost;
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.number = number;
		level = 1;
		id = 0;
	}
	
	public Building(int upgradeCost[], Location location, int hitpoints, int type, int level, int id, int number){

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
	
	public int[] getUpgradeCosts(){
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
	
	public int[] levelUp(int[] resources){
		if( resources[0] >= upgradeCost[0] && resources[1] >= upgradeCost[1]
				&& resources[2] >= upgradeCost[2] && level < 7 ){
			++level;
			upgradeSpecification();
			return upgradeCost;
		}else{
			return new int[]{0, 0, 0};
		}
	}
	
	public void setUpgradeCost(){
		for(int index = 0; index < 3; ++index){
			upgradeCost[index] *= 2;
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
