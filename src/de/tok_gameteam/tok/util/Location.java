package de.tok_gameteam.tok.util;

/**
 * A util-class providing an object with x and y value.
 * 
 * @author Constantin
 * @version 1.0
 */
public class Location {
	
	private int x, y;
	
	/**
	 * Basic constructor with two int-values.
	 * 
	 * @param locationX :
	 * 		X-Value of the created location
	 * @param locationY :
	 * 		Y-Value of the created location
	 */
	public Location(int locationX, int locationY){
		this.x = locationX;
		this.y = locationY;
	}
	
	/**
	 * Constructor creating a location from another one, just like cloning it
	 * 
	 * @param location : The existing location which should be cloned.
	 */
	public Location( Location location ){
		if(location != null){
			x = location.getX();
			y = location.getY();
		}else{
			x = 0;
			y = 0;
		}
	}
	
	/**
	 * Basic getter for the x-Value of the location.
	 * 
	 * @return The specific x-Value of the location, either set in the constructor or later with a setter.
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Basic getter for the y-Value of the location.
	 * 
	 * @return The specific y-Value of the location, either set in the constructor or later with a setter.
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Basic setter for the x-Value of the location.
	 * 
	 * @param newX : The new x-Value.
	 */
	public void setX( int newX ){
		this.x =newX;
	}
	
	/**
	 * Basic setter for the y-Value of the location.
	 * 
	 * @param newY : The new y-Value.
	 */
	public void setY( int newY ){
		this.y = newY;
	}
	
	/**
	 * Compares the x- and y-Values of the given location and itself.
	 * 
	 * @param location : The location to be compared to.
	 * @return True if both values are the same.
	 */
	public boolean equals(Location location){
		if( x == location.getX() && y == location.getY()){
			return true;
		}else{
			return false;
		}
	}
}
