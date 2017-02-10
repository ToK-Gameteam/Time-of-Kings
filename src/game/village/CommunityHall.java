package game.village;

import game.util.Location;

public class CommunityHall extends Building {
	static final long serialVersionUID = 1;
	
	private int capacity;
	
	public CommunityHall( Location location ){
		super( 10, location, 100, "Lager" );
		capacity = 300;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 2;
	}
}
