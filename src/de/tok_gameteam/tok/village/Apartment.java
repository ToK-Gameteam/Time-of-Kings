package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The class Apartment creates a building with people living in it.
 * 
 * @author Constantin Schulte
 * @version 1.0
 */
public class Apartment extends Building {
	
	/**
	 * The number of people living in the Apartment, depending on its level.
	 */
	private int resident;
	
	/**
	 * Standard constructor
	 * 
	 * @param location : The Location to build the Apartment at.
	 * @param number : The number of the Building.
	 */
	public Apartment( Location location, int number ){
		super( location, 100, Village.APARTMENT, number);
		resident = Village.BUILDING_VALUES[2][0];
	}
	
	/**
	 * The constructor for an existing Apartment.
	 * 
	 * @param location : The Location of the Building.
	 * @param level : The level of the Apartment.
	 * @param id : The id in the database.
	 * @param number : The number of the Building.
	 */
	public Apartment( Location location, int level, int id, int number){
		super(location, 100*(2^(level-1)), Village.APARTMENT, level, id, number);
		resident = Village.BUILDING_VALUES[2][level-1];
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The number of resident.
	 */
	public int getResident(){
		return resident;
	}
	
	/**
	 * Sets the number of resident and hitpoints.
	 */
	@Override public void upgradeSpecification(){
		resident = Village.BUILDING_VALUES[2][level-1];
		hitpoints *= 2;
	}
}
