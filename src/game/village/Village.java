package game.village;

import game.Location;
import java.io.Serializable;

/** 
 * The class Village interacts between the buildings and the resources and is used by the player.
 * 
 * @author Constantin Schulte
 * @version 0.2
 **/
public class Village implements Serializable {
	static final long serialVersionUID = 1;
	
	private static java.util.Scanner scanner;

	private Building[] buildings;
	private Resource[] resources;
	private int[] buildingsBuild;
	
	/**
	 * These Constants are important for the index of a building.
	 **/
	private final int SAWMILL_FOR_ARRAY = 1;
	private final int QUARRY_FOR_ARRAY = 6;
	private final int MINE_FOR_ARRAY = 11;
	private final int APARTMENT_FOR_ARRAY = 16;
	private final int STORAGE_FOR_ARRAY = 20;
	private final int WALL_FOR_ARRAY = 26;
	
	public Village(){
		buildings = new Building[(1 /*CommmunityHall*/ + 15 /*ResourceCollectors*/
								+ 4 /*Apartments*/ + 6 /*Storages*/ + 40 /*Walls */ )]; // total = 66
		
		resources = new Resource[3]; // Wood, Stone, Iron
		resources[0] = new Resource( "Holz", 100 );
		resources[1] = new Resource( "Stein", 100 );
		resources[2] = new Resource( "Eisen", 100 );
		
		buildingsBuild = new int[7]; //one for every type of Building
		for( int i = 0; i < 7; ++i){
			buildingsBuild[i] = 0;
		}
	}
	
	public int build( Location location, String typeOfBuilding ){
		Location buildLocation = checkLocation( location );
		int ep = 0;
		switch( typeOfBuilding ){
		case "communityhall":
			if( ++buildingsBuild[0] == 1 ){
				buildings[0] = new CommunityHall( buildLocation );
				ep = 10;
			}
			break;
		case "sawmill":
			if( buildingsBuild[1] < 5 ){
				buildings[++buildingsBuild[1]] = new ResourceCollector( buildLocation, "Holz" );
				ep = 10;
			}
			break;
		case "quarry":
			if( buildingsBuild[2] < 5 ){
				buildings[++buildingsBuild[2]] = new ResourceCollector( buildLocation, "Stein" );
				ep = 10;
			}
			break;
		case "mine":
			if( buildingsBuild[3] < 5 ){
				buildings[++buildingsBuild[3]] = new ResourceCollector( buildLocation, "Eisen" );
				ep = 10;
			}
			break;
		case "apartment":
			if( buildingsBuild[4] < 4 ){
				buildings[++buildingsBuild[4]] = new Apartment( buildLocation );
				ep = 10;
			}
			break;
		case "storage":
			if( buildingsBuild[5] < 6 ){
				buildings[++buildingsBuild[5]] = new Storage( buildLocation );
				ep = 10;
			}
			break;
		case "wall":
			if( buildingsBuild[6] < 40 ){
				buildings[++buildingsBuild[6]] = new Wall( buildLocation );
				ep = 10;
			}
		}
		setLimit();
		return ep;
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
	
	public int levelUp( String buildingToUpgrade, int number ){
		int buildingIndex = getBuildingInArray(buildingToUpgrade) + number;
		int ep = 0;
		if( buildings[buildingIndex] != null ){
			int costs = buildings[buildingIndex].levelUp(getResourceValues());
			subtractResources( costs );
			ep = 10;
		}
		setLimit();
		return ep;
	}
	
	public void move( String buildingToMove, int number, Location newLocation ){
		Location moveLocation = checkLocation( newLocation );
		int moveIndex = getBuildingInArray( buildingToMove ) + number - 1;
		if( buildings[moveIndex] != null ){
			buildings[moveIndex].setLocation( moveLocation );
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
	
	private int getBuildingInArray( String building ){
		switch( building ){
		case "sawmill":
			return SAWMILL_FOR_ARRAY;
		case "quarry":
			return QUARRY_FOR_ARRAY;
		case "mine":
			return MINE_FOR_ARRAY;
		case "apartment":
			return APARTMENT_FOR_ARRAY;
		case "storage":
			return STORAGE_FOR_ARRAY;
		case "wall":
			return WALL_FOR_ARRAY;
		}
		return 0;
	}
	
	private Location checkLocation( Location location ){
		Location checkLocation = new Location( location );
		checkLocationOutOfField( checkLocation );
		boolean locationUnique = false;
		do{
			boolean locationChanged = false;
			for( int building = 0; building < 66; ++building ){
				if( buildings[building] != null){
					while( buildings[building].getLocation().equals(checkLocation) ){
						System.err.println("An dieser Stelle steht bereits ein Gebaeude.");
						System.out.println("Neue X-Koordinate (1 bis 40):");
						checkLocation.setLocationX(enterInt());
						System.out.println("Neue Y-Koordinate (1 bis 40):");
						checkLocation.setLocationY(enterInt());
						checkLocationOutOfField( checkLocation );
						locationChanged = true;
					}
				}
			}
			if( !locationChanged ){
				locationUnique = true;
			}	
		}while( !locationUnique );
		return checkLocation;
	}
	
	private Location checkLocationOutOfField( Location location ){
		while( location.getLocationX() > 40 || location.getLocationX() < 0 ){
			  System.err.println("Nicht innerhalb des Feldes.");
			  System.out.println("Neue X-Koordinate (1 bis 40):");
			  location.setLocationX( enterInt() );
		}
		while( location.getLocationY() > 40 || location.getLocationY() < 0 ){
			System.err.println("Nicht innerhalb des Feldes.");
			System.out.println("Neue Y-Koordinate (1 bis 40):");
			location.setLocationY( enterInt() );
		}
		return location;
	}
	
	private static int enterInt(){
		int value;
		value = -1;
		scanner = new java.util.Scanner(System.in);
		while ( value == -1){
			String value2;
			value2 = scanner.nextLine();
			try{
				value = Integer.parseInt( value2 );	
			}catch(java.lang.NumberFormatException e){
				System.err.println("Keine ganze Zahl eingegeben");
				System.out.println("");
			}
		}
		return value;
	}
}
