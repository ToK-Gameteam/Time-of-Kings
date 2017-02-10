package game.village;

import java.io.Serializable;

import game.util.Location;

/** 
 * The class Village interacts between the buildings and the resources and is used by the player.
 * 
 * @author Constantin Schulte
 * @version 0.3
 **/
public class Village implements Serializable {
	static final long serialVersionUID = 1;
	
	private String name = "";
	private boolean nameChanged = false;
	private Building[] buildings;
	private Resource[] resources;
	private int[] buildingsBuild;
	
	/**
	 * These Constants are important for the index of a building.
	 **/
	public static final int COMMUNITY_HALL = 0;
	public static final int SAWMILL = 1;
	public static final int QUARRY = 6;
	public static final int MINE = 11;
	public static final int APARTMENT = 16;
	public static final int STORAGE = 20;
	public static final int WALL = 26;
	
	public Village(){
		buildings = new Building[(1 /*CommmunityHall*/ + 15 /*ResourceCollectors*/
								+ 4 /*Apartments*/ + 6 /*Storages*/ + 40 /*Walls */ )]; // total = 66
		
		resources = new Resource[3]; // Wood, Stone, Iron
		resources[0] = new Resource( "wood", 100 );
		resources[1] = new Resource( "stone", 100 );
		resources[2] = new Resource( "iron", 100 );
		
		buildingsBuild = new int[7]; //one for every type of Building
		for( int index = 0; index < 7; ++index){
			buildingsBuild[index] = 0;
		}
	}
	
	public void build( Location location, int building ){
		switch( building ){
		case COMMUNITY_HALL:
			if( ++buildingsBuild[0] == 1 ){
				buildings[0] = new CommunityHall( location );
			}
			break;
		case SAWMILL:
			if( buildingsBuild[1] < 5 ){
				buildings[++buildingsBuild[1]] = new ResourceCollector( location, "wood" );
			}
			break;
		case QUARRY:
			if( buildingsBuild[2] < 5 ){
				buildings[++buildingsBuild[2]] = new ResourceCollector( location, "stone" );
			}
			break;
		case MINE:
			if( buildingsBuild[3] < 5 ){
				buildings[++buildingsBuild[3]] = new ResourceCollector( location, "iron" );
			}
			break;
		case APARTMENT:
			if( buildingsBuild[4] < 4 ){
				buildings[++buildingsBuild[4]] = new Apartment( location );
			}
			break;
		case STORAGE:
			if( buildingsBuild[5] < 6 ){
				buildings[++buildingsBuild[5]] = new Storage( location );
			}
			break;
		case WALL:
			if( buildingsBuild[6] < 40 ){
				buildings[++buildingsBuild[6]] = new Wall( location );
			}
		}
		setLimit();
	}
	
	public void collect(){
		for( int resource = 0; resource < 3; ++resource){
			for( int number = 1; number <= buildingsBuild[resource+1]; ++number){
				ResourceCollector collector = (ResourceCollector) buildings[number+resource*5];
				resources[resource].addValue(collector.collect());
			}
		}	
	}
	
	public int[] getResourceValues(){
		int[] resourceValues = new int[3];
		for( int i = 0; i < 3; ++i ){
			resourceValues[i] = resources[i].getValue();
		}
		return resourceValues;
	}
	
	public int[] getResourceLimits(){
		int[] resourceLimits = new int[3];
		for( int i = 0; i < 3; ++i ){
			resourceLimits[i] = resources[i].getLimit();
		}
		return resourceLimits;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName( String name ){
		if( !nameChanged ){
			this.name = name;
			nameChanged = true;
		}
	}
	
	public void levelUp( int building, int number ){
		int buildingIndex = building + number;
		if( buildings[buildingIndex] != null ){
			int costs = buildings[buildingIndex].levelUp(getResourceValues());
			subtractResources( costs );
		}
		setLimit();
	}
	
	public void move( int building, int number, Location newLocation ){
		int moveIndex = building + number - 1;
		if( buildings[moveIndex] != null ){
			buildings[moveIndex].setLocation( newLocation );
		}
	}
	
	private void subtractResources( int subtractionValue ){
		for( int i = 0; i < 3; ++i){
			resources[i].subtractValue( subtractionValue );
		}
	}
	
	private void setLimit(){
		int totalLimit = 300; // because of CommunityHall
		for( int number = 0; number < buildingsBuild[3]; ++number ){
			Storage storage = (Storage) buildings[number];
			totalLimit += storage.getLimit();
		}
		
		int resourceLimit = (int) totalLimit / 3;
		for( int resource = 0; resource < 3; ++resource ){
			resources[resource].setLimit( resourceLimit );
		}
	}
}
