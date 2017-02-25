package game.village;

import game.util.Location;

/**
 * The class Apartment creates a building with people living in it.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in version 0.2
 **/
public class Apartment extends Building {
	
	private int resident;
	
	public Apartment( Location location, int number ){
		super(new int[]{100, 50, 75}, location, 100, Village.APARTMENT, number);
	}
	
	public Apartment( Location location, int level, int id, int number){
		super(new int[]{10*(2^(level-1)), 20*(2^(level-1)), 15*(2^(level-1))},
				location, 100*(2^(level-1)), Village.APARTMENT, level, id, number);
	}
	
	public int getResident(){
		return resident;
	}
	
	@Override public void upgradeSpecification(){
		resident *= 2;
		hitpoints *= 2;
	}
}
