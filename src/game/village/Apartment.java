package game.village;

import game.*;

/**
 * Class Apartment
 * 
 * The class Apartment creates a building with people living in it.
 * 
 * @author Constantin Schulte
 * @version 0.1 -> implemented in version 0.2
 **/
public class Apartment extends Building {
	static final long serialVersionUID = 1;
	
	private int resident;
	
	public Apartment( Location location ){
		super(10, location, 100, "Wohnhaus");
	}
	
	public int getResident(){
		return resident;
	}
	
	@Override public void upgradeSpecification(){
		resident *= 2;
		hitpoints *= 2;
	}
}
