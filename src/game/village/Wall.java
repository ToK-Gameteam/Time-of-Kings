package game.village;

import game.util.Location;

public class Wall extends Building {

	public Wall( Location location, int number ){
		super( 5, location, 200, Village.WALL, number );
	}
	
	public Wall( Location location, int level, int id, int number ){
		super( 5*(2^(level-1)), location, 200*(5^(level-1)), Village.WALL, level, id, number );
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 5;
	}
}
