package game.buildings;

import game.Location;

/**
 * Class Apartment
 * 
 * The class Apartment creates a building with people living in it.
 * 
 * @author Constantin Schulte
 * @version 0.0 -> implemented in version 0.2
 */
public class Apartment extends Building {
	private static final long serialVersionUID = 1L;
	
	private int resident;

	public Apartment(int woodCost, int stoneCost, int ironCost, int number, Location location){
		super(woodCost, stoneCost, ironCost, number, location);
		resident = 10;
	}

	public int getLiving() {
		resident = checkResident();
		return resident;
	}
	
	public int checkResident(){
		int resident;
		if(level == 1){
			resident = 10;
		}else if( level == 2 ){
			resident = 25;
		}else if( level == 3 ){
			resident = 50;
		}else if(level == 4){
			resident = 100;
		}else if(level == 5){
			resident = 500;
		}else if(level == 6){
			resident = 2_000;
		}else{
			resident = 5_000;
		}
		this.resident = resident;
		return resident;
	}
}
