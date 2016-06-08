package game;

class Location {
	private int locationX;
	private int locationY;
	
	Location(int locationX, int locationY){
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public void setLocation(int[] newLocation){
		locationX = newLocation[0];
		locationY = newLocation[1];
	}
	
	public void setLocationX(int locationX){
		this.locationX = locationX;
	}
	
	public void setLocationY( int locationY ){
		this.locationY = locationY;
	}
	
	public int getLocationX(){
		return locationX;
	}
	
	public int getLocationY(){
		return locationY;
	}
	
	public int[] getLocation(){
		int[] location = {locationX, locationY};
		return location;
	}
	
	public boolean equals(Location location){
		if(location.getLocationX() == locationX && location.getLocationY() == locationY){
			return true;
		}else{
			return false;
		}
	}
}
