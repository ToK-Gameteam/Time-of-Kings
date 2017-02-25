package game.village;

import game.util.Location;

public class CommunityHall extends Building {
	
	private int capacity;
	
	public CommunityHall( Location location ){
		super( new int[]{10, 10, 10}, location, 100, Village.COMMUNITY_HALL, 0 );
		capacity = 3000;
	}
	
	public CommunityHall( Location location, int level, int id ){
		super( new int[]{10*(2^(level-1)), 10*(2^(level-1)), 10*(2^(level-1))},
				location, 100*(2^(level-1)), Village.COMMUNITY_HALL, level, id, 0 );
		capacity = 3000*(2^(level-1));
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 2;
		capacity*=2;
	}
}
