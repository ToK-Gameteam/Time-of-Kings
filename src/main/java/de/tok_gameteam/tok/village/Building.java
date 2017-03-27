package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The class Building creates a building, which has a level (at the beginning 1),
 *  a number (depending on the number of buildings of this type build before),
 * a Location, and allows to level the building up and to change the location.
 * 
 * @author Constantin Schulte
 * @version 1.0
 **/
public abstract class Building implements BuildingUpgrade {
	
	/**
	 * The Location of the Building.
	 */
	protected Location location;
	
	/**
	 * The level of the Building.
	 */
	protected int level;
	
	/**
	 * The hitpoints of the Building. -> Needed when battle-system is implemented.
	 */
	protected int hitpoints;
	
	/**
	 * The type of the Building, values in -> Village.
	 */
	protected int type;
	
	/**
	 * The id of the Building, if unsaved 0.
	 */
	protected int id;
	
	/**
	 * The number of the Building.
	 */
	protected int number;
	
	/**
	 * Basic constructor for a new building.
	 * 
	 * @param location : The Location of the Building.
	 * @param hitpoints : The hitpoints of the Building.
	 * @param type : The type of the Building.
	 * @param number : The number of the Building.
	 */
	public Building( Location location, int hitpoints, int type, int number ){
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.number = number;
		level = 1;
		id = 0;
	}
	/**
	 * Constructor for a saved building when loaded.
	 * 
	 * @param location : The Location of the Building.
	 * @param hitpoints : The hitpoints of the Building.
	 * @param type : The type of the Building.
	 * @param level : The level of the Building.
	 * @param id : The id of the Building in the database. -> Db
	 * @param number : The number of the Building.
	 */
	public Building( Location location, int hitpoints, int type, int level, int id, int number){
		this.location = location;
		this.hitpoints = hitpoints;
		this.type = type;
		this.level = level;
		this.id = id;
		this.number = number;
	}
	
	/**
	 * Basic getter for the level of the Building.
	 * 
	 * @return : The level of the Building.
	 */
	public int getLevel(){
		return level;
	}
	
	/**
	 * Basic getter for the hitpoints of the Building.
	 * 
	 * @return : The hitpoints of the Building.
	 */
	public int getHitpoints(){
		return hitpoints;
	}
	
	/**
	 * Basic getter for the Location of the Building.
	 * 
	 * @return : The Location of the Building.
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * Basic getter for type of the Building.
	 * 
	 * @return : The type of the Building.
	 */
	public int getType(){
		return type;
	}

	/**
	 * Basic getter for the id of the Building.
	 * 
	 * @return : The id of the Building.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Basic getter for the number of the Building.
	 * 
	 * @return : The number of the Building.
	 */
	public int getNumber(){
		return number;
	}
	
	/**
	 * Basic setter for the Location of the Building.
	 * 
	 * @param location : The new Location of the Building.
	 */
	public void setLocation( Location location ){
		this.location = location;
	}
	
	/**
	 * The level of the Building is incremented and the method to set type-specific values is activated.
	 */
	public void levelUp(){
		++level;
		upgradeSpecification();
	}
	
	/**
	 * Sets the type-specific values when leveling-up.
	 * Only implemented in subclasses.
	 */
	@Override public void upgradeSpecification(){}
}
