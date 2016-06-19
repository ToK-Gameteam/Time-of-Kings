package game;

import java.io.Serializable;

public class Location implements Serializable {
	static final long serialVersionUID = 1;
	
	private int locationX, locationY;
	
	public Location(int locationX, int locationY){
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public int getLocationX(){
		return locationX;
	}
	
	public int getLocationY(){
		return locationY;
	}
	
	public void setLocationX( int newLocationX ){
		this.locationX =newLocationX;
	}
	
	public void setLocationY( int newLocationY ){
		this.locationY = newLocationY;
	}
}
