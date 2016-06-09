package game;

import java.io.Serializable;
import game.buildings.*;

/**
 * Class Distributor
 * 
 * The class Distributor interacts between the buildings and the resources and is used by the player.
 * 
 * @author Constantin Schulte
 * @version 0.2
 */
class Distributor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String oe = ConsoleInterface.oe;
	private String ae = ConsoleInterface.ae;
	private MainBuilding mainBuilding;
	private ResourceCollector lumbermill, lumbermill2, lumbermill3, lumbermill4, lumbermill5;
	private ResourceCollector quarry, quarry2, quarry3, quarry4, quarry5;
	private ResourceCollector mine, mine2, mine3, mine4, mine5;
	private Storage storage, storage2, storage3, storage4, storage5, storage6;
	private Apartment apartment, apartment2, apartment3, apartment4;
	private Resource wood;
	private Resource stone;
	private Resource iron;
	private Location[] locations;
	private int mainBuildingBuild, lumbermillBuild, quarryBuild, mineBuild, storageBuild, apartmentsBuild;
	
	private static java.util.Scanner scanner;		// Zu loeschen bei GUI

	  public Distributor(){
	    wood = new Resource();
	    stone = new Resource();
	    iron = new Resource();
	    mainBuildingBuild = 0;
	    lumbermillBuild = 0;
	    quarryBuild = 0;
	    mineBuild = 0;
	    storageBuild = 0;
	    locations = new Location[26];
	  }

	  public void collect(){
	    int wood = 0, stone = 0, iron = 0;
	   	switch( lumbermillBuild ){
	   	case 5:
	   		wood += lumbermill5.collect();
	   	case 4:
	   		wood += lumbermill4.collect();
	   	case 3:
	   		wood += lumbermill3.collect();
	   	case 2:
	   		wood += lumbermill2.collect();
	   	case 1:
	   		wood += lumbermill.collect();
	   	}
	   	
	   	switch( quarryBuild ){
	   	case 5:
	   		stone += quarry5.collect();
	   	case 4:
	   		stone += quarry4.collect();
	   	case 3:
	   		stone += quarry3.collect();
	   	case 2:
	   		stone += quarry2.collect();
	   	case 1:
	   		stone += quarry.collect();
	   	}
	   	
	   	switch( mineBuild ){
	   	case 5:
	   		iron += mine5.collect();
	   	case 4:
	   		iron += mine4.collect();
	   	case 3:
	   		iron += mine3.collect();
	   	case 2:
	   		iron += mine2.collect();
	   	case 1:
	   		iron += mine.collect();
	   	}
	   	
	   	this.wood.add(wood);
	   	this.stone.add(stone);
	   	this.iron.add(iron);
	   	draw();
	  }

	  public void printDetails(){
	    System.out.println("Holz: " + wood.getValue() + " / " + wood.getCapacity());
	    System.out.println("Stein: " + stone.getValue() + " / " + stone.getCapacity());
	    System.out.println("Eisen: " + iron.getValue() + " / " + iron.getCapacity());
	  }

	  public void setValues(int newWood, int newStone, int newIron){
	    wood.setValue(newWood);
	    stone.setValue(newStone);
	    iron.setValue(newIron);
	    draw();
	  }
	  
	  public int[] getValues(){
	    int[] values = {wood.getValue(), stone.getValue(), iron.getValue()};
	    return values;
	  }
	  
	  public void buildMainBuilding( Location location ){
		  if( mainBuildingBuild == 0){
			  mainBuilding = new MainBuilding(location);
			  mainBuildingBuild = 1;
			  locations[0] = location;
		  }
		  draw();
	  }
	  
	  public void buildLumbermill( Location location){
		  Location finalLocation = checkLocation(location);

		  
		 if(lumbermillBuild == 0){
	        if(wood.getValue() >= 5){
	            lumbermill = new ResourceCollector(1, 20, 15, 10, finalLocation);
	            lumbermillBuild = 1;
	            wood.subtract(5);
	            locations[1] = finalLocation;
	        }
	    }else if(lumbermillBuild == 1){
	    	if(wood.getValue() >= 10){
	    		lumbermill2 = new ResourceCollector(2, 30, 30, 30, finalLocation);
	    		wood.subtract(10);
	    		lumbermillBuild = 2;
	            locations[2] = finalLocation;
	    	}
	    }else if(lumbermillBuild == 2){
	    	if(wood.getValue() >= 25){
	    		lumbermill3 = new ResourceCollector(3, 50, 60, 70, finalLocation);
	    		wood.subtract(25);
	    		lumbermillBuild = 3;
	            locations[3] = finalLocation;
	    	}
	    }else if(lumbermillBuild == 3){
	    	if(wood.getValue() >= 100){
	    		lumbermill4 = new ResourceCollector(4, 200, 300, 300, finalLocation);
	    		lumbermillBuild = 4;
	            locations[4] = finalLocation;
	    	}
	    }else if(lumbermillBuild == 4){
	    	if(wood.getValue() >= 500){
	    		lumbermill5 = new ResourceCollector( 5, 1_000, 1_000, 1_000, finalLocation);
	    		wood.subtract(500);
	    		lumbermillBuild = 5;
	            locations[5] = finalLocation;
	    	}
	    }
	    draw();
	  }
	  
	  public void buildQuarry( Location location){
		  Location finalLocation = checkLocation(location);
		  
		  if(quarryBuild == 0){
			  if(wood.getValue() >= 5){
				wood.subtract(5);
				quarryBuild++;
	            locations[6] = finalLocation;
			  }
		  }else if(quarryBuild == 1){
			  if(wood.getValue() >= 10){
				quarry2 = new ResourceCollector( 2, 30, 30, 30, finalLocation);
				wood.subtract(10);
		    	quarryBuild++;
	            locations[7] = location;
			  }
		  }else if(quarryBuild == 2){
			  if(wood.getValue() >= 25){
		    	quarry3 = new ResourceCollector(3, 50, 60, 70, finalLocation);
		    	wood.subtract(25);
		   		quarryBuild++;
	            locations[8] = finalLocation;
		   		draw();
			  }
		  }else if(quarryBuild == 3){
			if(wood.getValue() >= 100){
				quarry4 = new ResourceCollector(4, 200, 300, 300, finalLocation);
				wood.subtract(100);
				quarryBuild++;
	            locations[9] = finalLocation;
		    }
		}else if(quarryBuild == 4){
	    	if(wood.getValue() >= 500){
	    		quarry5 = new ResourceCollector(5, 1_000, 1_000, 1_000, finalLocation);
	    		wood.subtract(500);
		   		quarryBuild++;
	            locations[10] = finalLocation;
		   	}
	    }
	  draw();
	  }
	  
	  public void buildMine(Location location){
		  Location finalLocation = checkLocation(location);
		  
		    if(mineBuild == 0){
		        if(wood.getValue() >= 5){
		            mine = new ResourceCollector(1, 20, 15, 10, finalLocation);
		            wood.subtract(5);
		            mineBuild++;
		            locations[11] = finalLocation;
		        }
		    }else if(mineBuild == 1){
		    	if(wood.getValue() >= 10){
		    		mine2 = new ResourceCollector(2, 30, 30, 30, finalLocation);
		    		wood.subtract(10);
		    		mineBuild++;
		            locations[12] = finalLocation;
		    	}
		    }else if(mineBuild == 2){
		    	if(wood.getValue() >= 25){
		    		mine3 = new ResourceCollector(3, 50, 60, 70, finalLocation);
		    		wood.subtract(25);
		    		mineBuild++;
		            locations[13] = finalLocation;
		    	}
		    }else if(mineBuild == 3){
		    	if(wood.getValue() >= 100){
		    		mine4 = new ResourceCollector(4, 200, 300, 400, finalLocation);
		    		wood.subtract(100);
		    		mineBuild++;
		            locations[14] = finalLocation;
		    	}
		    }else if(mineBuild == 4){
		    	if(wood.getValue() >= 500){
		    		mine5 = new ResourceCollector(5, 1_000, 1_000, 1_000, finalLocation);
		    		wood.subtract(500);
		    		mineBuild++;
		            locations[15] = finalLocation;
		    	}
		    }
	  }
	  
	  public void buildStorage(Location location){
		  Location finalLocation = checkLocation(location);
		  
		  if(storageBuild == 0){
			  storage = new Storage(1, 30, 30, 30, finalLocation);
			  locations[16] = finalLocation;
			  storageBuild = 1;
		  }else if(storageBuild == 1){
			  if(wood.getValue() >= 25){
				  storage2 = new Storage(1, 40, 50, 60, finalLocation);
				  locations[17] = finalLocation;
				  storageBuild = 2;
			  }
		  }else if(storageBuild == 2){
			  if(wood.getValue() >= 25){
				  storage3 = new Storage(1, 100, 200, 500, finalLocation);
				  locations[18] = finalLocation;
				  storageBuild = 3;
			  }
		  }else if(storageBuild == 3){
			  if(wood.getValue() >= 25){
				  storage4 = new Storage(1, 100, 200, 500, finalLocation);
				  locations[19] = finalLocation;
				  storageBuild = 4;
			  }
		  }else if(storageBuild == 4){
			  if(wood.getValue() >= 25){
				  storage5 = new Storage(1, 100, 200, 500, finalLocation);
				  locations[20] = finalLocation;
				  storageBuild = 5;
			  }
		  }else if(storageBuild == 5){
			  if(wood.getValue() >= 25){
				  storage6 = new Storage(1, 1000, 2000, 5000, finalLocation);
				  locations[21] = finalLocation;
				  storageBuild = 6;
			  }
		  }
		  draw();
	  }
	  
	  public void buildApartment( Location location){
		  Location finalLocation = checkLocation(location);
		  
		  if( apartmentsBuild == 0 ){
			  apartment = new Apartment(30, 30, 30, 1, finalLocation);
			  locations[22] = finalLocation;
			  apartmentsBuild = 1;
		  }else if(apartmentsBuild == 1){
			  apartment2 = new Apartment(50, 60, 70, 2, finalLocation);
			  locations[23] = finalLocation;
			  apartmentsBuild = 2;
		  }else if(apartmentsBuild == 2){
			  apartment3 = new Apartment(100, 200, 300, 3, finalLocation);
			  locations[24] = finalLocation;
			  apartmentsBuild = 3;
		  }else if(apartmentsBuild == 3){
			  apartment4 = new Apartment(500, 1_000, 2_000, 4, finalLocation);
			  locations[25] = finalLocation;
			  apartmentsBuild = 4;
		  }else{
			  System.err.println("Bereits Höchstzahl gebaut.");
		  }
		  draw();
	  }
	  
	  public int levelUp(String buildingToUpgrade, int number){
		  try{
			  if(buildingToUpgrade.equals("lumbermill")){
			  int[] costs;
			  
			  
			  if(number == 1){
				  wood.add(lumbermill.collect());
				  costs = lumbermill.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 2){
				  wood.add(lumbermill2.collect());
				  costs = lumbermill2.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 3){
				  wood.add(lumbermill3.collect());
				  costs = lumbermill3.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if( number == 4){
				  wood.add(lumbermill4.collect());
				  costs = lumbermill4.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 5){
				  wood.add(lumbermill5.collect());
				  costs = lumbermill5.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else{
				 	return 0;
			  }
			  
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  setLimit();
			  
			  draw();
			  switch( number ){
			  case 1:
				  return lumbermill.getLevel();
				  
			  case 2:
				  return lumbermill2.getLevel();
				  
			  case 3:
				  return lumbermill3.getLevel();
				  
			  case 4:
				  return lumbermill4.getLevel();
				  
			  case 5:
				  return lumbermill5.getLevel();
				  
			  }
			  
			  
			  
		  }else if( buildingToUpgrade.equals( "quarry" ) ){
			  int[] costs;
			  
			  if(number == 1){
				  stone.add(quarry.collect());
				  costs = quarry.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 2){
				  stone.add(quarry2.collect());
				  costs = quarry2.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 3){
				  stone.add(quarry3.collect());
				  costs = quarry3.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if( number == 4){
				  stone.add(quarry4.collect());
				  costs = quarry4.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 5){
				  stone.add(quarry5.collect());
				  costs = quarry5.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else{
				 	return 0;
			  }
			  
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  
			  draw();
			  switch( number ){
			  case 1:
				  return quarry.getLevel();
				  
			  case 2:
				  return quarry2.getLevel();
				  
			  case 3:
				  return quarry3.getLevel();
				  
			  case 4:
				  return quarry4.getLevel();
				  
			  case 5:
				  return quarry5.getLevel();
				  
			  }
			  
			  
		  }else if(buildingToUpgrade.equals("mine")){
			  int[] costs;
			  
			  if(number == 1){
				  iron.add(mine.collect());
				  costs = mine.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 2){
				  iron.add(mine2.collect());
				  costs = mine2.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 3){
				  iron.add(mine3.collect());
				  costs = mine3.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if( number == 4){
				  iron.add(mine4.collect());
				  costs = mine4.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 5){
				  iron.add(mine5.collect());
				  costs = mine5.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else{
				 	return 0;
			  }
			  
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  
			  draw();
			  switch( number ){
			  case 1:
				  return mine.getLevel();
				  
			  case 2:
				  return mine2.getLevel();
				  
			  case 3:
				  return mine3.getLevel();
				  
			  case 4:
				  return mine4.getLevel();
				  
			  case 5:
				  return mine5.getLevel();
				  
			  }
		  }else if(buildingToUpgrade.equals("storage")){
			  int[] costs;
			  
			  if(number == 1){
				  costs = storage.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 2){
				  costs = storage2.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 3){
				  costs = storage3.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if( number == 4){
				  costs = storage4.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 5){
				  costs = storage5.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 6){
				  costs = storage6.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else{
				  System.err.println("Kein Lager gewählt");
				 	return 0;
			  }
			  
			  System.out.println(costs[0] + costs[1] + costs[2]);
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  
			  draw();
			  switch( number ){
			  case 1:
				  return storage.getLevel();
				  
			  case 2:
				  return storage2.getLevel();
				  
			  case 3:
				  return storage3.getLevel();
				  
			  case 4:
				  return storage4.getLevel();
				  
			  case 5:
				  return storage5.getLevel();
			  case 6:
				  return storage6.getLevel();
				  
			  }
		  }else if(buildingToUpgrade.equals("apartment")){
			  int[] costs;
			  
			  if(number == 1){
				  costs = apartment.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 2){
				  costs = apartment2.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if(number == 3){
				  costs = apartment3.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else if( number == 4){
				  costs = apartment4.levelUp(wood.getValue(), stone.getValue(), iron.getValue());
			  }else{
				  System.err.println("Kein Wohnhaus gewählt");
				  return 0;
			  }
			  
			  System.out.println(costs[0] + "" + costs[1] +"" + costs[2]);
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  
			  draw();
			  switch( number ){
			  case 1:
				  return apartment.getLevel();
				  
			  case 2:
				  return apartment2.getLevel();
				  
			  case 3:
				  return apartment3.getLevel();
				  
			  case 4:
				  return apartment4.getLevel();
				  
				  
			  }
		  }else{
			  System.err.println("Kein Gebäude gewählt");
			  return 0;
		  }
			  }catch( NullPointerException e){
				  System.out.println("Gebäude nicht vorhanden.");
			  }
		  return 0;
	  }
	  
	  public void setLimit(){
		  int totalLimit = 0;
		  int woodLimit = 0;
		  int stoneLimit = 0;
		  int ironLimit = 0;
		  
		  switch( storageBuild ){
		  	case 6:
		  		totalLimit += storage6.getCapacity();
		  	case 5:
		  		totalLimit += storage5.getCapacity();
		  	case 4:
		  		totalLimit += storage4.getCapacity();
		  	case 3:
		  		totalLimit += storage3.getCapacity();
		  	case 2:
		  		totalLimit += storage2.getCapacity();
		  	case 1:
		  		totalLimit += storage.getCapacity();
		  
		  }
		  totalLimit += 100; // because of MainBuilding
		  if( ( totalLimit % 3 ) == 1 ){
			  woodLimit = (int) ( totalLimit / 3 ) + 1;
			  stoneLimit = (int) ( totalLimit / 3 );
			  ironLimit = (int) ( totalLimit / 3 );
		  }else if( ( totalLimit % 3 ) == 2 ){
			  woodLimit = (int) ( totalLimit / 3 ) + 1;
			  stoneLimit = (int) ( totalLimit / 3 ) + 1;
			  ironLimit = (int) ( totalLimit / 3 );
		  }else{
			  woodLimit = totalLimit / 3;
			  stoneLimit = totalLimit / 3;
			  ironLimit = totalLimit / 3;
		  }
		  wood.setLimit( woodLimit );
		  stone.setLimit( stoneLimit );
		  iron.setLimit( ironLimit );
	  }
	  
	  public void moveBuilding(String buildingToMove, int number, Location location ){
		  Location finalLocation = checkLocation(location);
		  
		  if( buildingToMove.equals("mainBuilding") && number == 1){
			  mainBuilding.moveBuilding(finalLocation);
			  locations[0] = finalLocation;
		  }else if(buildingToMove.equals("lumbermill") && number == 1){
			  lumbermill.moveBuilding(finalLocation);
			  locations[1] = finalLocation;
			  
		  }else if(buildingToMove.equals("lumbermill") && number == 2){
			  lumbermill.moveBuilding(finalLocation);
			  locations[2] = finalLocation;
			  
		  }else if(buildingToMove.equals("lumbermill") && number == 3){
			  lumbermill.moveBuilding(finalLocation);
			  locations[3] = finalLocation;
			  
		  }else if(buildingToMove.equals("lumbermill") && number == 4){
			  lumbermill.moveBuilding(finalLocation);
			  locations[4] = finalLocation;
			  
		  }else if(buildingToMove.equals("lumbermill") && number == 5){
			  lumbermill.moveBuilding(finalLocation);
			  locations[5] = finalLocation;
			  
			  
		  }else if(buildingToMove.equals("quarry") && number == 1){
			  quarry.moveBuilding(finalLocation);
			  locations[6] = finalLocation;
			  
		  }else if(buildingToMove.equals("quarry") && number == 2){
			  quarry2.moveBuilding(finalLocation);
			  locations[7] = finalLocation;
			  
		  }else if(buildingToMove.equals("quarry") && number == 3){
			  quarry3.moveBuilding(finalLocation);
			  locations[8] = finalLocation;
			  
		  }else if(buildingToMove.equals("quarry") && number == 4){
			  quarry4.moveBuilding(finalLocation);
			  locations[9] = finalLocation;
			  
		  }else if(buildingToMove.equals("quarry") && number == 5){
			  quarry5.moveBuilding(finalLocation);
			  locations[10] = finalLocation;
			  
			  
		  }else if(buildingToMove.equals("mine") && number == 1){
			  mine.moveBuilding(finalLocation);
			  locations[11] = finalLocation;
			  
		  }else if(buildingToMove.equals("mine") && number == 2){
			  mine2.moveBuilding(finalLocation);
			  locations[12] = finalLocation;
			  
		  }else if(buildingToMove.equals("mine") && number == 3){
			  mine3.moveBuilding(finalLocation);
			  locations[13] = finalLocation;
			  
		  }else if(buildingToMove.equals("mine") && number == 4){
			  mine4.moveBuilding(finalLocation);
			  locations[14] = finalLocation;
			  
		  }else if(buildingToMove.equals("mine") && number == 5){
			  mine5.moveBuilding(finalLocation);
			  locations[15] = finalLocation;
			  
			  
		  }else if(buildingToMove.equals("storage") && number == 1){
			  quarry5.moveBuilding(finalLocation);
			  locations[16] = finalLocation;
			    
		  }else if(buildingToMove.equals("storage") && number == 2){
			  mine.moveBuilding(finalLocation);
			  locations[17] = finalLocation;
			  
		  }else if(buildingToMove.equals("storage") && number == 3){
			  mine2.moveBuilding(finalLocation);
			  locations[18] = finalLocation;
			  
		  }else if(buildingToMove.equals("storage") && number == 4){
			  mine3.moveBuilding(finalLocation);
			  locations[19] = finalLocation;
			  
		  }else if(buildingToMove.equals("storage") && number == 5){
			  mine4.moveBuilding(finalLocation);
			  locations[20] = finalLocation;
			  
		  }else if(buildingToMove.equals("storage") && number == 6){
			  mine5.moveBuilding(finalLocation);
			  locations[21] = finalLocation;
			  
		  }else if(buildingToMove.equals("apartment") && number == 1){
			  quarry5.moveBuilding(finalLocation);
			  locations[22] = finalLocation;
			    
		  }else if(buildingToMove.equals("apartment") && number == 2){
			  mine.moveBuilding(finalLocation);
			  locations[23] = finalLocation;
			  
		  }else if(buildingToMove.equals("apartment") && number == 3){
			  mine2.moveBuilding(finalLocation);
			  locations[24] = finalLocation;
			  
		  }else if(buildingToMove.equals("apartment") && number == 4){
			  mine3.moveBuilding(finalLocation);
			  locations[25] = finalLocation;
		  }else{
			  System.err.println("Kein Geb"+ae+"ude gew"+ae+"hlt.");
		  }
	  }
	  
	  
	  public void draw(){
		  System.out.println("Holz:" + wood.getValue());
		  System.out.println("Stein:"+ stone.getValue());
		  System.out.println("Eisen:" + iron.getValue());
		  System.out.println("");
		  for ( int y = 1; y < 11; y++ ){
			  for ( int x = 1; x < 41; x++ ){
				  boolean isBuilding = false;
				  Location point = new Location(x, y);
				  
				  if(mainBuilding.getLocation().equals(point)){
					  System.out.print("R");
					  isBuilding = true;
				  }  
				  
				  if(lumbermillBuild >= 1){
					  if(lumbermill.getLocation().equals(point)){
	        			System.out.print(lumbermill.getLevel());
	        			isBuilding = true;
					  }
				  }
				  if(lumbermillBuild >= 2){
	        		if(lumbermill2.getLocation().equals(point)){
	        			System.out.print(lumbermill2.getLevel());
	        			isBuilding = true;
	        		}
				  }
				  if(lumbermillBuild >= 3){
					  if(lumbermill3.getLocation().equals(point)){
						  System.out.print(lumbermill3.getLevel());
						  isBuilding = true;
					  }
	        	}
	        	if(lumbermillBuild >= 4){
	        		if(lumbermill4.getLocation().equals(point)){
	        			System.out.print(lumbermill4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(lumbermillBuild == 5){
		        	   if(lumbermill5.getLocation().equals(point)){
		        	 	  System.out.print(lumbermill5.getLevel());
		        	 	  isBuilding = true;
		        	   }
	        	}
	        	
	        	if(quarryBuild >= 1){
	        		if(quarry.getLocation().equals(point)){
	        			System.out.print(quarry.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(quarryBuild >= 2){
	        		if(quarry2.getLocation().equals(point)){
	        			System.out.print(quarry2.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(quarryBuild >= 3){
	        		if(quarry3.getLocation().equals(point)){
	        			System.out.print(quarry3.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(quarryBuild >= 4){
	        		if(quarry4.getLocation().equals(point)){
	        			System.out.print(quarry4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(quarryBuild == 5){
		        	   if(quarry5.getLocation().equals(point)){
		        	 	  System.out.print(quarry5.getLevel());
		        	 	  isBuilding = true;
		        	   }
	        	}
	        	
	        	if(mineBuild >= 1){
	        		if(mine.getLocation().equals(point)){
	        			System.out.print(mine.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(mineBuild >= 2){
	        		if(mine2.getLocation().equals(point)){
	        			System.out.print(mine2.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(mineBuild >= 3){
	        		if(mine3.getLocation().equals(point)){
	        			System.out.print(mine3.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(mineBuild >= 4){
	        		if(mine4.getLocation().equals(point)){
	        			System.out.print(mine4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(mineBuild == 5){
		        	   if(mine5.getLocation().equals(point)){
		        	 	  System.out.print(mine5.getLevel());
		        	 	  isBuilding = true;
		        	   }
	        	}
	        	
	        	if(storageBuild >= 1){
		        	   if(storage.getLocation().equals(point)){
		        	 	  System.out.print(storage.getLevel());
		        	 	  isBuilding = true;
		        	   }
	        	}
	        	
	        	if(storageBuild >= 2){
	        		if(storage2.getLocation().equals(point)){
	        			System.out.print(storage2.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(storageBuild >= 3){
	        		if(storage3.getLocation().equals(point)){
	        			System.out.print(storage3.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(storageBuild >= 4){
	        		if(storage4.getLocation().equals(point)){
	        			System.out.print(storage4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(storageBuild >= 5){
	        		if(storage5.getLocation().equals(point)){
	        			System.out.print(storage5.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(storageBuild == 6){
		        	   if(storage6.getLocation().equals(point)){
		        	 	  System.out.print(storage6.getLevel());
		        	 	  isBuilding = true;
		        	   }
	        	}
	        	
	        	if(apartmentsBuild >= 1){
					  if(apartment.getLocation().equals(point)){
	        			System.out.print(apartment.getLevel());
	        			isBuilding = true;
					  }
				  }
				  if(apartmentsBuild >= 2){
	        		if(apartment2.getLocation().equals(point)){
	        			System.out.print(apartment2.getLevel());
	        			isBuilding = true;
	        		}
				  }
				  if(apartmentsBuild >= 3){
					  if(apartment3.getLocation().equals(point)){
						  System.out.print(apartment3.getLevel());
						  isBuilding = true;
					  }
	        	}
	        	if(apartmentsBuild == 4){
	        		if(apartment4.getLocation().equals(point)){
	        			System.out.print(apartment4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	
	        	if(!isBuilding){
	        		System.out.print("+");
	        	}
	        }
	        System.out.println();
	      }
	    System.out.println();
	  }
	  
	  private Location checkLocation(Location location){
		  while( location.getLocation()[0] > 40 || location.getLocation()[0] < 0 ){
			  System.err.println("Nicht innerhalb des Feldes.");
			  System.out.println("neue X-Koordinate (1 bis 40):");
			  location.setLocationX(enterInt());
		  }
		  while( location.getLocation()[1] > 10 || location.getLocation()[1] < 0 ){
			  System.err.println("Nicht innerhalb des Feldes.");
			  System.out.println("neue Y-Koordinate (1 bis 10):");
			  location.setLocationY(enterInt());
		  }
		  for( Location oldLocation : locations){
			  try{
				  while(location.getLocationX() == oldLocation.getLocationX() && location.getLocationY() == oldLocation.getLocationY()){
				  System.err.println("An dieser Stelle steht bereits ein Geb"+ae+"ude.");
					  System.out.println("neue X-Koordinate (1 bis 40):");
					  location.setLocationX(enterInt());
					  System.out.println("neue Y-Koordinate (1 bis 10):");
					  location.setLocationY(enterInt());
					  while(location.getLocation()[0] > 40 || location.getLocation()[0] < 0){
						  System.err.println("Nicht innerhalb des Feldes.");
						  System.out.println("neue X-Koordinate (1 bis 40):");
						  location.setLocationX(enterInt());
					  }
					  while(location.getLocation()[1] > 10 || location.getLocation()[1] < 0){
						  System.err.println("Nicht innerhalb des Feldes.");
						  System.out.println("neue Y-Koordinate (1 bis 10):");
						  location.setLocationY(enterInt());
					  }
				  	}
				  }catch( NullPointerException e){}
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
