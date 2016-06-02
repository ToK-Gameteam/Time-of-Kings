package game;

import java.awt.Point;

public class Apartment extends Building {
	private static final long serialVersionUID = 1L;
	
	private int resident;

	public Apartment(int woodCost, int stoneCost, int ironCost, int number, int locationX, int locationY){
		super(woodCost, stoneCost, ironCost, number, new Point(locationX, locationY));
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
