package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The class Apartment creates a building with people living in it.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in version 0.2
 **/
public class Apartment extends Building {
	
	private int resident;
	
	public Apartment( Location location, int number ){
		super( location, 100, Village.APARTMENT, number);
		resident = Village.BUILDING_VALUES[2][0];
	}
	
	public Apartment( Location location, int level, int id, int number){
		super(location, 100*(2^(level-1)), Village.APARTMENT, level, id, number);
		resident = Village.BUILDING_VALUES[2][level-1];
	}
	
	public int getResident(){
		return resident;
	}
	
	@Override public void upgradeSpecification(){
		resident = Village.BUILDING_VALUES[2][level-1];
		hitpoints *= 2;
	}
}
