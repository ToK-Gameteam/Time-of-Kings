package game.buildings;

import java.awt.Point;

import game.Building;

/*
 * Class Storage
 * 
 * A storage is a building storing resources.
 * 
 * @author Constantin Schulte
 * @version 0.0 -> implemented in Game Version 0.1
 */
public class Storage extends Building {
	private static final long serialVersionUID = 1L;
	
	private int capacity;

	public Storage(int number, int woodCost, int stoneCost, int ironCost, int locationX, int locationY){
		super(number, woodCost, stoneCost, ironCost, new Point(locationX, locationY));
		capacity = 100;
	}
	
	public int getCapacity(){
		if(level == 1){
			capacity = 100;
		}else if(level == 2){
			capacity = 500;
		}else if(level == 3){
			capacity = 1_000;
		}else if(level == 4){
			capacity = 5_000;
		}else if(level == 5){
			capacity = 10_000;
		}else if(level == 6){
			capacity = 20_000;
		}else{
			capacity = 50_000;
		}
		return capacity;
	}
}
