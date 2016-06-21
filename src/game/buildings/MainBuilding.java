package game.buildings;

import game.Location;

public class MainBuilding extends Building {
	private static final long serialVersionUID = 1L;
	
	private int capacity;
	private int storedWood, storedStone, storedIron;
	
	public MainBuilding(Location location){
		super(30, 30, 30, 1, location);
		capacity = 300;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public int[] getStored(){
		int[] stored = new int[3];
		stored[0] = storedWood;
		stored[1] = storedStone;
		stored[2] = storedIron;
		return stored;
	}
}
