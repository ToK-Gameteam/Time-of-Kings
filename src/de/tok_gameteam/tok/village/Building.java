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
	protected int level;
	protected int hitpoints;
	protected int type;
	protected int id, number;
	
	public Building( Location location, int hitpoints, int type, int number ){
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.number = number;
		level = 1;
		id = 0;
	}
	
	public Building( Location location, int hitpoints, int type, int level, int id, int number){
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
	
	public void levelUp(){
		++level;
		upgradeSpecification();
	}
	
	@Override public void upgradeSpecification(){}

	public int getId() {
		return id;
	}
	
	public int getNumber(){
		return number;
	}
}
