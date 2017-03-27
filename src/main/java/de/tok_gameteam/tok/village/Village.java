package de.tok_gameteam.tok.village;

import de.tok_gameteam.tok.sql.BuildingValues;
import de.tok_gameteam.tok.util.Location;

/** 
 * The village of a player is made of buildings, which could be upgraded.
 * 
 * @author Constantin Schulte
 * @version 1.0
 **/
public class Village {
	
	/**
	 * The first array-index of a community hall in the buildings-array
	 * 	and the representation of the CommunityHall in the whole project.
	 */
	public static final int COMMUNITY_HALL = 0;
	
	/**
	 * The first array-index of a sawmill in the buildings-array
	 * 	and the representation of the sawmill in the whole project. -> ResourceCollector
	 */
	public static final int SAWMILL = 1;
	
	/**
	 * The first array-index of a quarry in the buildings-array
	 * 	and the representation of the quarry in the whole project. -> ResourceCollector
	 */
	public static final int QUARRY = 6;

	/**
	 * The first array-index of a mine in the buildings-array
	 * 	and the representation of the mine in the whole project. -> ResourceCollector
	 */
	public static final int MINE = 11;

	/**
	 * The first array-index of an apartment in the buildings-array
	 * 	and the representation of the Apartment in the whole project.
	 */
	public static final int APARTMENT = 16;

	/**
	 * The first array-index of an storage in the buildings-array
	 * 	and the representation of the Storage in the whole project.
	 */
	public static final int STORAGE = 20;

	/**
	 * The first array-index of an wall in the buildings-array
	 * 	and the representation of the Wall in the whole project.
	 */
	public static final int WALL = 24;
	
	/**
	 * An int-array with all costs of the buildings. -> BuildingValues
	 */
	public static final int[][] BUILDING_COSTS = new BuildingValues().getBuildingCosts();
	
	/**
	 * An int-array with all specific values of the buildings. -> BuildingValues
	 */
	public static final int[][] BUILDING_VALUES = new BuildingValues().getBuildingValues();
	
	/**
	 * An int-array with the number of buildable buildings, ordered by level of community hall and building.
	 */
	public static final int[][] BUILDABLE_BUILDINGS = new BuildingValues().getBuildableBuildings();
	
	
	/**
	 * The buildings of a village. -> Building
	 */
	private Building[] buildings;
	
	/**
	 * The three resources of the village(wood, stone, iron). -> Resource
	 */
	private Resource[] resources;
	
	/**
	 * An int-array with the number of Building built.
	 */
	private int[] buildingsBuild;
	
	/**
	 * Basic constructor creating all needed variables and initializes them.
	 */
	public Village(){
		buildings = new Building[(1 /*CommmunityHall*/ + 15 /*ResourceCollectors*/
								+ 4 /*Apartments*/ + 4 /*Storages*/ + 40 /*Walls */ )]; // total = 64
		
		resources = new Resource[3]; // Wood, Stone, Iron
		resources[0] = new Resource( "wood" );
		resources[1] = new Resource( "stone" );
		resources[2] = new Resource( "iron" );
		
		buildingsBuild = new int[7]; //one for every type of Building
		for( int index = 0; index < 7; ++index){
			buildingsBuild[index] = 0;
		}
	}
	
	/**
	 * Constructor for a Village loaded from a database or being hardcoded.
	 * 
	 * @param buildings : A Building-array with the buildings of the village.
	 * @param resourceValues : An int-array with the values of wood, stone and iron. -> Resource
	 * @param buildingsBuild : An int-array with the number of built buildings of a type.
	 */
	public Village(Building[] buildings, int[] resourceValues, int[] buildingsBuild ){
		this.buildings = buildings;
		resources = new Resource[3];
		resources[0] = new Resource( "wood", resourceValues[0] );
		resources[1] = new Resource( "stone", resourceValues[1] );
		resources[2] = new Resource( "iron", resourceValues[2] );
		
		this.buildingsBuild = buildingsBuild;
		
		setLimit();
	}
	
	/**
	 * Tries to build a building.
	 * 
	 * @param location : The Location of the building.
	 * @param building : The type of the building.
	 */
	public void build( Location location, int building ){
		switch( building ){
		case COMMUNITY_HALL:
			if( ++buildingsBuild[0] == 1 ){
				buildings[0] = new CommunityHall( location );
			}
			break;
		case SAWMILL:
			if( buildingsBuild[0] != 0 && buildingsBuild[1] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][0] &&
			 resources[0].getValue() >= BUILDING_COSTS[1][0] && resources[1].getValue() >= BUILDING_COSTS[1][0] 
					 && resources[2].getValue() >= BUILDING_COSTS[1][0]){
				buildings[buildingsBuild[1]+SAWMILL] = new ResourceCollector( location, SAWMILL, buildingsBuild[1]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case QUARRY:
			if( buildingsBuild[0] != 0 && buildingsBuild[2] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][1] &&
					 resources[0].getValue() >= BUILDING_COSTS[1][0] && resources[1].getValue() >= BUILDING_COSTS[1][0] 
							 && resources[2].getValue() >= BUILDING_COSTS[1][0]){
				buildings[buildingsBuild[2]+QUARRY] = new ResourceCollector( location, QUARRY, buildingsBuild[2]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case MINE:
			if( buildingsBuild[0] != 0 && buildingsBuild[3] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][2] 
					&& resources[0].getValue() >= BUILDING_COSTS[1][0] && resources[1].getValue() >= BUILDING_COSTS[1][0]
							&& resources[2].getValue() >= BUILDING_COSTS[1][0]){
				buildings[buildingsBuild[3]+MINE] = new ResourceCollector( location, MINE, buildingsBuild[3]++ );
				subtractResources(new int[]{BUILDING_COSTS[1][0], BUILDING_COSTS[1][0], BUILDING_COSTS[1][0]});
			}
			break;
		case STORAGE:
			if( buildingsBuild[0] != 0 && buildingsBuild[5] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][3] &&
					 resources[0].getValue() >= BUILDING_COSTS[2][0] && resources[1].getValue() >= BUILDING_COSTS[2][0]
							 && resources[2].getValue() >= BUILDING_COSTS[2][0]){
				buildings[buildingsBuild[4]+STORAGE] = new Storage( location, buildingsBuild[5]++ );
				subtractResources(new int[]{BUILDING_COSTS[2][0], BUILDING_COSTS[2][0], BUILDING_COSTS[2][0]});
			}
			break;
		case APARTMENT:
			if( buildingsBuild[0] != 0 && buildingsBuild[4] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][4] &&
					 resources[0].getValue() >= BUILDING_COSTS[3][0] && resources[1].getValue() >= BUILDING_COSTS[3][0]
							 && resources[2].getValue() >= BUILDING_COSTS[3][0]){
				buildings[buildingsBuild[5]+APARTMENT] = new Apartment( location, buildingsBuild[4]++ );
				subtractResources(new int[]{BUILDING_COSTS[3][0], BUILDING_COSTS[3][0], BUILDING_COSTS[3][0]});
			}
			break;
		case WALL:
			if( buildingsBuild[0] != 0 && buildingsBuild[6] < BUILDABLE_BUILDINGS[buildings[0].getLevel()-1][5] &&
					 resources[0].getValue() >= BUILDING_COSTS[4][0] && resources[1].getValue() >= BUILDING_COSTS[4][0]
							 && resources[2].getValue() >= BUILDING_COSTS[4][0]){
				buildings[buildingsBuild[6]+WALL] = new Wall( location, buildingsBuild[6]++ );
				subtractResources(new int[]{BUILDING_COSTS[4][0], BUILDING_COSTS[4][0], BUILDING_COSTS[4][0]});
			}
		}
		setLimit();
	}
	
	/**
	 * Collects the produced resources from the building.
	 * 
	 * @param building : The ResourceCollector which already produced some resources.
	 */
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
	
	/**
	 * Tries to upgrade the Building.
	 * 
	 * @param building : The type of the Building.
	 * @param number : The number of the Building.
	 */
	public void levelUp( int building, int number ){
		int buildingIndex = building + number;
		int costIndex = 0;
		switch(building){
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
						&& buildings[buildingIndex].getLevel() < 7 
						&& (buildings[0].getLevel() > buildings[buildingIndex].getLevel()
								|| buildings[buildingIndex].getType() == COMMUNITY_HALL)){
			buildings[buildingIndex].levelUp();
			for(int index = 0; index < 3; ++index){
				resources[index].subtractValue(BUILDING_COSTS[costIndex][buildings[buildingIndex].getLevel()-1]);
			}
		}
		setLimit();
	}
	
	/**
	 * Moves the Building to the specified Location.
	 * 
	 * @param building : The type of the Building.
	 * @param number : The number of the Building.
	 * @param newLocation : The Location to move the Building to.
	 */
	public void move( int building, int number, Location newLocation ){
		int moveIndex = building + number - 1;
		if( buildings[moveIndex] != null ){
			buildings[moveIndex].setLocation( newLocation );
		}
	}
	
	/**
	 * Removes the values from the Resource.
	 * 
	 * @param subtractionValues : An int-array with the values to be removed.
	 */
	private void subtractResources( int[] subtractionValues ){
		for( int i = 0; i < 3; ++i){
			resources[i].subtractValue( subtractionValues[i] );
		}
	}
	
	/**
	 * Calculates the capacities of each resource.
	 */
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
	
	/**
	 * Basic getter.
	 * 
	 * @return : The int-array with the numbers of buildings built.
	 */
	public int[] getBuildingsBuild(){
		return buildingsBuild;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return : The array with the Building.
	 */
	public Building[] getBuildings(){
		return buildings;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return An int-array with the values of the resources.
	 */
	public int[] getResourceValues(){
		int[] resourceValues = new int[3];
		for( int i = 0; i < 3; ++i ){
			resourceValues[i] = resources[i].getValue();
		}
		return resourceValues;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return An int-array with the capacities of the resources.
	 */
	public int[] getResourceLimits(){
		int[] resourceLimits = new int[3];
		for( int i = 0; i < 3; ++i ){
			resourceLimits[i] = resources[i].getLimit();
		}
		return resourceLimits;
	}
}