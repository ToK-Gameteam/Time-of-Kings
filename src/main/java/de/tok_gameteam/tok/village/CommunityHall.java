package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

/**
 * The CommunityHall forms the main part of a Village. Without, you can't build anything.
 * 
 * @author Constantin
 * @version 1.0
 */
public class CommunityHall extends Building {
	
	/**
	 * The capacity of the CommunityHall, 3000 at all time so far.
	 */
	private int capacity;
	
	/**
	 * Standard constructor.
	 * 
	 * @param location : The Location of the new CommunityHall.
	 */
	public CommunityHall( Location location ){
		super( location, 100, Village.COMMUNITY_HALL, 0 );
		capacity = 3000;
	}
	
	/**
	 * Constructor for an existing CommunityHall.
	 * 
	 * @param location : The Location of the CommunityHall.
	 * @param level : The level of the CommunityHall.
	 * @param id : The id of the CommunityHall.
	 */
	public CommunityHall( Location location, int level, int id ){
		super( location, 100*(2^(level-1)), Village.COMMUNITY_HALL, level, id, 0 );
		capacity = 3000;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The capacity of the CommunityHall.
	 */
	public int getCapacity(){
		return capacity;
	}
	
	/**
	 * Sets the hitpoints.
	 */
	@Override public void upgradeSpecification(){
		hitpoints *= 2;
	}
}
