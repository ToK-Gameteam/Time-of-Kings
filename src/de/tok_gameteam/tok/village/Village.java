package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.sql.BuildingValues;
import de.tok_gameteam.tok.util.Location;

/** 
 * The class Village interacts between the buildings and the resources and is used by the player.
 * 
 * @author Constantin Schulte
 * @version 0.3
 **/
public class Village {
	
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
	
	public static final int[][] BUILDING_COSTS = new BuildingValues().getBuildingCosts();
	public static final int[][] BUILDING_VALUES = new BuildingValues().getBuildingValues();
	
	public Village(){
		buildings = new Building[(1 /*CommmunityHall*/ + 15 /*ResourceCollectors*/
								+ 4 /*Apartments*/ + 6 /*Storages*/ + 40 /*Walls */ )]; // total = 66
		
		resources = new Resource[3]; // Wood, Stone, Iron
		resources[0] = new Resource( "wood", 1000 );
		resources[1] = new Resource( "stone", 1000 );
		resources[2] = new Resource( "iron", 1000 );
		
		buildingsBuild = new int[7]; //one for every type of Building
		for( int index = 0; index < 7; ++index){
			buildingsBuild[index] = 0;
		}
	}
	
	public Village(Building[] buildings, int[] resourceValues, int[] buildingsBuild ){
		this.buildings = buildings;
		resources = new Resource[3];
		resources[0] = new Resource( "wood", resourceValues[0], true );
		resources[1] = new Resource( "stone", resourceValues[1], true );
		resources[2] = new Resource( "iron", resourceValues[2], true );
		
		this.buildingsBuild = buildingsBuild;
	}
	
	public void build( Location location, int building ){
		switch( building ){
		case COMMUNITY_HALL:
			if( ++buildingsBuild[0] == 1 ){
				buildings[0] = new CommunityHall( location );
			}
			break;
		case SAWMILL:
			if( buildingsBuild[1] < 5 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[1]+SAWMILL] = new ResourceCollector( location, SAWMILL, buildingsBuild[1]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case QUARRY:
			if( buildingsBuild[2] < 5 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[2]+QUARRY] = new ResourceCollector( location, QUARRY, buildingsBuild[2]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case MINE:
			if( buildingsBuild[3] < 5 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[3]+MINE] = new ResourceCollector( location, MINE, buildingsBuild[3]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case APARTMENT:
			if( buildingsBuild[4] < 4 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[4]+APARTMENT] = new Apartment( location, buildingsBuild[4]++ );
				subtractResources(new int[]{
						BUILDING_COSTS[2][0],
						BUILDING_COSTS[2][0], 
						BUILDING_COSTS[2][0]	
				});
			}
			break;
		case STORAGE:
			if( buildingsBuild[5] < 4 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[5]+STORAGE] = new Storage( location, buildingsBuild[5]++ );
				subtractResources(new int[]{BUILDING_COSTS[3][0], BUILDING_COSTS[3][0], BUILDING_COSTS[3][0]});
			}
			break;
		case WALL:
			if( buildingsBuild[6] < 40 && buildingsBuild[0] != 0 ){
				buildings[buildingsBuild[6]+WALL] = new Wall( location, buildingsBuild[6]++ );
				subtractResources(new int[]{BUILDING_COSTS[4][0], BUILDING_COSTS[4][0], BUILDING_COSTS[4][0]});
			}
		}
		setLimit();
	}
	
	public void collect(Building building){
		switch( building.getType() ){
		case SAWMILL:
			resources[0].addValue(((ResourceCollector)building).collect());
			break;
		case QUARRY:
			resources[1].addValue(((ResourceCollector)building).collect());
			break;
		case MINE:
			resources[2].addValue(((ResourceCollector)building).collect());
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
	
	public void levelUp( int building, int number ){
		int buildingIndex = building + number;
		int costIndex = 0;
		switch(buildings[buildingIndex].getType()){
		case COMMUNITY_HALL:costIndex = 0;break;
		case SAWMILL:
		case QUARRY:
		case MINE: costIndex = 1;break;
		case APARTMENT: costIndex = 2;break;
		case STORAGE: costIndex = 3;break;
		case WALL: costIndex = 4;break;
		}
		if(resources[0].getValue() >= BUILDING_COSTS[costIndex][buildings[buildingIndex].getLevel()] && 
				resources[1].getValue() >= BUILDING_COSTS[costIndex][buildings[buildingIndex].getLevel()] &&
				resources[2].getValue() >= BUILDING_COSTS[costIndex][buildings[buildingIndex].getLevel()]
						&& buildings[buildingIndex].getLevel() < 7){
			buildings[buildingIndex].levelUp();
			for(int index = 0; index < 3; ++index){
				resources[index].subtractValue(BUILDING_COSTS[costIndex][buildings[buildingIndex].getLevel()]);
			}
		}
		setLimit();
	}
	
	public void move( int building, int number, Location newLocation ){
		int moveIndex = building + number - 1;
		if( buildings[moveIndex] != null ){
			buildings[moveIndex].setLocation( newLocation );
		}
	}
	
	private void subtractResources( int[] subtractionValues ){
		for( int i = 0; i < 3; ++i){
			resources[i].subtractValue( subtractionValues[i] );
		}
	}
	
	public void setLimit(){
		int totalLimit = 3000; // because of CommunityHall
		for( int number = 0; number < buildingsBuild[5]; ++number ){
			Storage storage = (Storage) buildings[number+STORAGE];
			totalLimit += storage.getLimit();
		}
		
		int resourceLimit = (int) totalLimit / 3;
		for( int resource = 0; resource < 3; ++resource ){
			resources[resource].setLimit( resourceLimit );
		}
	}
	
	public int[] getBuildingsBuild(){
		return buildingsBuild;
	}
	
	public Building[] getBuildings(){
		return buildings;
	}
}
