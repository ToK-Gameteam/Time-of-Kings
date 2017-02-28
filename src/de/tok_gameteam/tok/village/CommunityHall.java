package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

public class CommunityHall extends Building {
	
	private int capacity;
	
	public CommunityHall( Location location ){
		super( location, 100, Village.COMMUNITY_HALL, 0 );
		capacity = 3000;
	}
	
	public CommunityHall( Location location, int level, int id ){
		super( location, 100*(2^(level-1)), Village.COMMUNITY_HALL, level, id, 0 );
		capacity = 3000;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 2;
	}
}
