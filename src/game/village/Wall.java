package game.village;

import game.Location;

public class Wall extends Building {
	static final long serialVersionUID = 1;

	public Wall( Location location ){
		super( 5, location, 200, "Mauer");
	}
	
	@Override public void upgradeSpecification(){
		hitpoints *= 5;
	}
}
