package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.util.Location;

public class Wall extends Building {

	public Wall( Location location, int number ){
		super( new int[]{10, 10, 10}, location, 200, Village.WALL, number );
	}
	
	public Wall( Location location, int level, int id, int number ){
		super( new int[]{10*(2^(level-1)), 10*(2^(level-1)), 10*(2^(level-1))},
				location, 200*(5^(level-1)), Village.WALL, level, id, number );
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 5;
	}
}
