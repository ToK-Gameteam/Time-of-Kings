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
	private CommunityHall communityHall;
	private ResourceCollector sawmill, sawmill2, sawmill3, sawmill4, sawmill5;
	private ResourceCollector quarry, quarry2, quarry3, quarry4, quarry5;
	private ResourceCollector mine, mine2, mine3, mine4, mine5;
	private Storage storage, storage2, storage3, storage4, storage5, storage6;
	private Apartment apartment, apartment2, apartment3, apartment4;
	private Wall wall, wall2, wall3, wall4, wall5, wall6, wall7, wall8, wall9, wall10;
	private Wall wall11, wall12, wall13, wall14, wall15, wall16, wall17, wall18, wall19, wall20;
	private Wall wall21, wall22, wall23, wall24, wall25, wall26, wall27, wall28, wall29, wall30;
	private Wall wall31, wall32, wall33, wall34, wall35, wall36, wall37, wall38, wall39, wall40;
	private Resource wood;
	private Resource stone;
	private Resource iron;
	private Location[] locations;
	private int mainBuildingBuild, sawmillBuild, quarryBuild, mineBuild, storageBuild, apartmentsBuild, wallsBuild;
	
	private static java.util.Scanner scanner;

	  public Distributor(){
	    wood = new Resource();
	    stone = new Resource();
	    iron = new Resource();
	    mainBuildingBuild = 0;
	    sawmillBuild = 0;
	    quarryBuild = 0;
	    mineBuild = 0;
	    storageBuild = 0;
	    wallsBuild = 0;
	    locations = new Location[66];
	  }

	  public void collect(){
	    int wood = 0, stone = 0, iron = 0;
	   	switch( sawmillBuild ){
	   	case 5:
	   		wood += sawmill5.collect();
	   	case 4:
	   		wood += sawmill4.collect();
	   	case 3:
	   		wood += sawmill3.collect();
	   	case 2:
	   		wood += sawmill2.collect();
	   	case 1:
	   		wood += sawmill.collect();
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
			  communityHall = new CommunityHall(location);
			  mainBuildingBuild = 1;
			  locations[0] = location;
		  }
		  draw();
	  }
	  
	  public void buildLumbermill( Location location){
		  Location finalLocation = checkLocation(location);

		  
		 if(sawmillBuild == 0){
	        if(wood.getValue() >= 5){
	            sawmill = new ResourceCollector(1, 20, 15, 10, finalLocation);
	            sawmillBuild = 1;
	            wood.subtract(5);
	            locations[1] = finalLocation;
	        }
	    }else if(sawmillBuild == 1){
	    	if(wood.getValue() >= 10){
	    		sawmill2 = new ResourceCollector(2, 30, 30, 30, finalLocation);
	    		wood.subtract(10);
	    		sawmillBuild = 2;
	            locations[2] = finalLocation;
	    	}
	    }else if(sawmillBuild == 2){
	    	if(wood.getValue() >= 25){
	    		sawmill3 = new ResourceCollector(3, 50, 60, 70, finalLocation);
	    		wood.subtract(25);
	    		sawmillBuild = 3;
	            locations[3] = finalLocation;
	    	}
	    }else if(sawmillBuild == 3){
	    	if(wood.getValue() >= 100){
	    		sawmill4 = new ResourceCollector(4, 200, 300, 300, finalLocation);
	    		sawmillBuild = 4;
	            locations[4] = finalLocation;
	    	}
	    }else if(sawmillBuild == 4){
	    	if(wood.getValue() >= 500){
	    		sawmill5 = new ResourceCollector( 5, 1_000, 1_000, 1_000, finalLocation);
	    		wood.subtract(500);
	    		sawmillBuild = 5;
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
	  
	  public void buildWall( Location location ){
		  Location finalLocation = checkLocation( location );
		  
		  if( wallsBuild == 0 ){
			  wall = new Wall( 1, finalLocation );
			  locations[23] = finalLocation;
			  wallsBuild = 1;
		  }else if( wallsBuild == 1 ){
			  wall2 = new Wall( 2, finalLocation );
			  locations[24] = finalLocation;
			  wallsBuild = 2;
		  }else if( wallsBuild == 2 ){
			  wall3 = new Wall( 3, finalLocation );
			  locations[25] = finalLocation;
			  wallsBuild = 3;
		  }else if( wallsBuild == 3 ){
			  wall4 = new Wall( 4, finalLocation );
			  locations[26] = finalLocation;
			  wallsBuild = 4;
		  }else if( wallsBuild == 4 ){
			  wall5 = new Wall( 5, finalLocation );
			  locations[27] = finalLocation;
			  wallsBuild = 5;
		  }else if( wallsBuild == 5 ){
			  wall6 = new Wall( 6, finalLocation );
			  locations[28] = finalLocation;
			  wallsBuild = 6;
		  }else if( wallsBuild == 6 ){
			  wall7 = new Wall( 7, finalLocation );
			  locations[29] = finalLocation;
			  wallsBuild = 7;
		  }else if( wallsBuild == 7 ){
			  wall8 = new Wall( 8, finalLocation );
			  locations[30] = finalLocation;
			  wallsBuild = 8;
		  }else if( wallsBuild == 8 ){
			  wall9 = new Wall( 9, finalLocation );
			  locations[31] = finalLocation;
			  wallsBuild = 9;
		  }else if( wallsBuild == 9 ){
			  wall10 = new Wall( 10, finalLocation );
			  locations[32] = finalLocation;
			  wallsBuild = 10;
		  }else if( wallsBuild == 10 ){
			  wall11 = new Wall( 11, finalLocation );
			  locations[33] = finalLocation;
			  wallsBuild = 11;
		  }else if( wallsBuild == 11 ){
			  wall12 = new Wall( 12, finalLocation );
			  locations[34] = finalLocation;
			  wallsBuild = 12;
		  }else if( wallsBuild == 12 ){
			  wall13 = new Wall( 13, finalLocation );
			  locations[35] = finalLocation;
			  wallsBuild = 13;
		  }else if( wallsBuild == 13 ){
			  wall14 = new Wall( 14, finalLocation );
			  locations[36] = finalLocation;
			  wallsBuild = 14;
		  }else if( wallsBuild == 14 ){
			  wall15 = new Wall( 15, finalLocation );
			  locations[37] = finalLocation;
			  wallsBuild = 15;
		  }else if( wallsBuild == 15 ){
			  wall16 = new Wall( 16, finalLocation );
			  locations[38] = finalLocation;
			  wallsBuild = 16;
		  }else if( wallsBuild == 16 ){
			  wall17 = new Wall( 17, finalLocation );
			  locations[39] = finalLocation;
			  wallsBuild = 17;
		  }else if( wallsBuild == 17 ){
			  wall18 = new Wall( 18, finalLocation );
			  locations[40] = finalLocation;
			  wallsBuild = 18;
		  }else if( wallsBuild == 18 ){
			  wall19 = new Wall( 19, finalLocation );
			  locations[41] = finalLocation;
			  wallsBuild = 19;
		  }else if( wallsBuild == 19 ){
			  wall20 = new Wall( 20, finalLocation );
			  locations[42] = finalLocation;
			  wallsBuild = 20;
		  }else if( wallsBuild == 20 ){
			  wall21 = new Wall( 21, finalLocation );
			  locations[43] = finalLocation;
			  wallsBuild = 21;
		  }else if( wallsBuild == 21 ){
			  wall22 = new Wall( 22, finalLocation );
			  locations[44] = finalLocation;
			  wallsBuild = 22;
		  }else if( wallsBuild == 22 ){
			  wall23 = new Wall( 23, finalLocation );
			  locations[45] = finalLocation;
			  wallsBuild = 23;
		  }else if( wallsBuild == 23 ){
			  wall24 = new Wall( 24, finalLocation );
			  locations[46] = finalLocation;
			  wallsBuild = 24;
		  }else if( wallsBuild == 24 ){
			  wall25 = new Wall( 25, finalLocation );
			  locations[47] = finalLocation;
			  wallsBuild = 25;
		  }else if( wallsBuild == 25 ){
			  wall26 = new Wall( 26, finalLocation );
			  locations[48] = finalLocation;
			  wallsBuild = 26;
		  }else if( wallsBuild == 26 ){
			  wall27 = new Wall( 27, finalLocation );
			  locations[49] = finalLocation;
			  wallsBuild = 27;
		  }else if( wallsBuild == 27 ){
			  wall28 = new Wall( 28, finalLocation );
			  locations[50] = finalLocation;
			  wallsBuild = 28;
		  }else if( wallsBuild == 28 ){
			  wall29 = new Wall( 29, finalLocation );
			  locations[51] = finalLocation;
			  wallsBuild = 29;
		  }else if( wallsBuild == 29 ){
			  wall30 = new Wall( 30, finalLocation );
			  locations[52] = finalLocation;
			  wallsBuild = 30;
		  }else if( wallsBuild == 30 ){
			  wall31 = new Wall( 31, finalLocation );
			  locations[53] = finalLocation;
			  wallsBuild = 31;
		  }else if( wallsBuild == 31 ){
			  wall32 = new Wall( 32, finalLocation );
			  locations[54] = finalLocation;
			  wallsBuild = 32;
		  }else if( wallsBuild == 32 ){
			  wall33 = new Wall( 33, finalLocation );
			  locations[55] = finalLocation;
			  wallsBuild = 33;
		  }else if( wallsBuild == 33 ){
			  wall34 = new Wall( 34, finalLocation );
			  locations[56] = finalLocation;
			  wallsBuild = 34;
		  }else if( wallsBuild == 34 ){
			  wall35 = new Wall( 35, finalLocation );
			  locations[57] = finalLocation;
			  wallsBuild = 35;
		  }else if( wallsBuild == 35 ){
			  wall36 = new Wall( 36, finalLocation );
			  locations[58] = finalLocation;
			  wallsBuild = 36;
		  }else if( wallsBuild == 36 ){
			  wall37 = new Wall( 37, finalLocation );
			  locations[59] = finalLocation;
			  wallsBuild = 37;
		  }else if( wallsBuild == 37 ){
			  wall38 = new Wall( 38, finalLocation );
			  locations[60] = finalLocation;
			  wallsBuild = 38;
		  }else if( wallsBuild == 38 ){
			  wall39 = new Wall( 39, finalLocation );
			  locations[61] = finalLocation;
			  wallsBuild = 39;
		  }else if( wallsBuild == 39 ){
			  wall10 = new Wall( 40, finalLocation );
			  locations[62] = finalLocation;
			  wallsBuild = 40;
		  }		
		  draw();
	  }
	  
	  public int levelUp(String buildingToUpgrade, int number){
		  int[] costs = new int[3];
		  int[] resources = {wood.getValue(), stone.getValue(), iron.getValue()};
		  try{
			  
			  if(buildingToUpgrade.equals("sawmill")){
				  
				  switch( number ){
				  case 1:
					  wood.add(sawmill.collect());
					  costs = sawmill.levelUp(resources);
					  break;
				  case 2:
					  wood.add(sawmill2.collect());
					  costs = sawmill2.levelUp(resources);
					  break;
				  case 3:
					  wood.add(sawmill3.collect());
					  costs = sawmill3.levelUp(resources);
					  break;
				  case 4:
					  wood.add(sawmill4.collect());
					  costs = sawmill4.levelUp(resources);
					  break;
				  case 5:
					  wood.add(sawmill5.collect());
					  costs = sawmill5.levelUp(resources);
					  break;
				  default:
					  return 0;
				  }
			  
				  wood.subtract(costs[0]);
				  stone.subtract(costs[1]);
				  iron.subtract(costs[2]);
				  setLimit();
			  
				  draw();
				  switch( number ){
				  case 1:
					  return sawmill.getLevel();
					  
				  case 2:
					  return sawmill2.getLevel();
				  
				  case 3:
					  return sawmill3.getLevel();
				  
				  case 4:
					  return sawmill4.getLevel();
				  
				  case 5:
					  return sawmill5.getLevel();
				  
				  }
			  
			  
			  
		  }else if( buildingToUpgrade.equals( "quarry" ) ){
			  
			  switch( number ){
			  case 1:
				  stone.add(quarry.collect());
				  costs = quarry.levelUp(resources);
				  break;
			  case 2:
				  stone.add(quarry2.collect());
				  costs = quarry2.levelUp(resources);
				  break;
			  case 3:
				  stone.add(quarry3.collect());
				  costs = quarry3.levelUp(resources);
				  break;
			  case 4:
				  stone.add(quarry4.collect());
				  costs = quarry4.levelUp(resources);
				  break;
			  case 5:
				  stone.add(quarry5.collect());
				  costs = quarry5.levelUp(resources);
				  break;
			  default:
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
			  switch( number ){
			  case 1:
				  iron.add(mine.collect());
				  costs = mine.levelUp(resources);
				  break;
			  case 2:
				  iron.add(mine2.collect());
				  costs = mine2.levelUp(resources);
				  break;
			  case 3:
				  iron.add(mine3.collect());
				  costs = mine3.levelUp(resources);
				  break;
			  case 4:
				  iron.add(mine4.collect());
				  costs = mine4.levelUp(resources);
				  break;
			  case 5:
				  iron.add(mine5.collect());
				  costs = mine5.levelUp(resources);
				  break;
			  default:
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
			  
			  switch( number ){
			  case 1:
				  costs = storage.levelUp(resources);
				  break;
			  case 2:
				  costs = storage2.levelUp(resources);
				  break;
			  case 3:
				  costs = storage3.levelUp(resources);
				  break;
			  case 4:
				  costs = storage4.levelUp(resources);
				  break;
			  case 5:
				  costs = storage5.levelUp(resources);
				  break;
			  case 6:
				  costs = storage6.levelUp(resources);
				  break;
			  default:
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
			  
			  switch( number ){
			  case 1:
				  costs = apartment.levelUp(resources);
				  break;
			  case 2:
				  costs = apartment2.levelUp(resources);
				  break;
			  case 3:
				  costs = apartment3.levelUp(resources);
				  break;
			  case 4:
				  costs = apartment4.levelUp(resources);
				  break;
			  default:
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
			  
		  }else if(buildingToUpgrade.equals("wall")){
			  switch( number ){
			  case 1:
				  costs = wall.levelUp(resources);
				  break;
			  case 2:
				  costs = wall2.levelUp(resources);
				  break;
			  case 3:
				  costs = wall3.levelUp(resources);
				  break;
			  case 4:
				  costs = wall4.levelUp(resources);
				  break;
			  case 5:
				  costs = wall5.levelUp(resources);
				  break;
			  case 6:
				  costs = wall6.levelUp(resources);
				  break;
			  case 7:
				  costs = wall7.levelUp(resources);
				  break;
			  case 8:
				  costs = wall8.levelUp(resources);
				  break;
			  case 9:
				  costs = wall9.levelUp(resources);
				  break;
			  case 10:
				  costs = wall10.levelUp(resources);
				  break;
			  case 11:
				  costs = wall11.levelUp(resources);
				  break;
			  case 12:
				  costs = wall12.levelUp(resources);
				  break;
			  case 13:
				  costs = wall13.levelUp(resources);
				  break;
			  case 14:
				  costs = wall14.levelUp(resources);
				  break;
			  case 15:
				  costs = wall15.levelUp(resources);
				  break;
			  case 16:
				  costs = wall16.levelUp(resources);
				  break;
			  case 17:
				  costs = wall17.levelUp(resources);
				  break;
			  case 18:
				  costs = wall18.levelUp(resources);
				  break;
			  case 19:
				  costs = wall19.levelUp(resources);
				  break;
			  case 20:
				  costs = wall20.levelUp(resources);
				  break;
			  case 21:
				  costs = wall21.levelUp(resources);
				  break;
			  case 22:
				  costs = wall22.levelUp(resources);
				  break;
			  case 23:
				  costs = wall23.levelUp(resources);
				  break;
			  case 24:
				  costs = wall24.levelUp(resources);
				  break;
			  case 25:
				  costs = wall25.levelUp(resources);
				  break;
			  case 26:
				  costs = wall26.levelUp(resources);
				  break;
			  case 27:
				  costs = wall27.levelUp(resources);
				  break;
			  case 28:
				  costs = wall28.levelUp(resources);
				  break;
			  case 29:
				  costs = wall29.levelUp(resources);
				  break;
			  case 30:
				  costs = wall30.levelUp(resources);
				  break;
			  case 31:
				  costs = wall31.levelUp(resources);
				  break;
			  case 32:
				  costs = wall32.levelUp(resources);
				  break;
			  case 33:
				  costs = wall33.levelUp(resources);
				  break;
			  case 34:
				  costs = wall34.levelUp(resources);
				  break;
			  case 35:
				  costs = wall35.levelUp(resources);
				  break;
			  case 36:
				  costs = wall36.levelUp(resources);
				  break;
			  case 37:
				  costs = wall37.levelUp(resources);
				  break;
			  case 38:
				  costs = wall38.levelUp(resources);
				  break;
			  case 39:
				  costs = wall39.levelUp(resources);
				  break;
			  case 40:
				  costs = wall40.levelUp(resources);
				  break;
			  default:
				  costs[0] = 0;
				  costs[1] = 0;
				  costs[2] = 0;
			  }

			  System.out.println(costs[0] + "" + costs[1] +"" + costs[2]);
			  wood.subtract(costs[0]);
			  stone.subtract(costs[1]);
			  iron.subtract(costs[2]);
			  
			  draw();
			  
		  }else{
			  System.err.println("Kein Gebäude gewählt");
			  return 0;
		  }
			  }catch( NullPointerException e){
				  System.out.println("Gebäude nicht vorhanden.");
			  }
		  return 0;
	  }
	  
	  public int[] setLimit(){
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
		  totalLimit += 300; // because of MainBuilding
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
		  int[] limit = {woodLimit, stoneLimit, ironLimit};
		  return limit;
	  }
	  
	  public void moveBuilding(String buildingToMove, int number, Location location ){
		  Location finalLocation = checkLocation(location);
		  
		  if( buildingToMove.equals("mainBuilding") && number == 1){
			  communityHall.moveBuilding(finalLocation);
			  locations[0] = finalLocation;
		  }else if(buildingToMove.equals("sawmill") && number == 1){
			  sawmill.moveBuilding(finalLocation);
			  locations[1] = finalLocation;
			  
		  }else if(buildingToMove.equals("sawmill") && number == 2){
			  sawmill2.moveBuilding(finalLocation);
			  locations[2] = finalLocation;
			  
		  }else if(buildingToMove.equals("sawmill") && number == 3){
			  sawmill3.moveBuilding(finalLocation);
			  locations[3] = finalLocation;
			  
		  }else if(buildingToMove.equals("sawmill") && number == 4){
			  sawmill4.moveBuilding(finalLocation);
			  locations[4] = finalLocation;
			  
		  }else if(buildingToMove.equals("sawmill") && number == 5){
			  sawmill5.moveBuilding(finalLocation);
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
		  draw();
	  }
	  
	  
	  public void draw(){
		  setLimit();
		  System.out.println("Holz: " + wood.getValue() + "/" + setLimit()[0]);
		  System.out.println("Stein: "+ stone.getValue() + "/" + setLimit()[1]);
		  System.out.println("Eisen: " + iron.getValue() + "/" + setLimit()[2]);
		  System.out.println("");
		  for ( int y = 1; y < 41; y++ ){
			  for ( int x = 1; x < 41; x++ ){
				  boolean isBuilding = false;
				  Location point = new Location(x, y);
				  
				  if(communityHall.getLocation().equals(point)){
					  System.out.print("R");
					  isBuilding = true;
				  }  
				  
				  if(sawmillBuild >= 1){
					  if(sawmill.getLocation().equals(point)){
	        			System.out.print(sawmill.getLevel());
	        			isBuilding = true;
					  }
				  }
				  if(sawmillBuild >= 2){
	        		if(sawmill2.getLocation().equals(point)){
	        			System.out.print(sawmill2.getLevel());
	        			isBuilding = true;
	        		}
				  }
				  if(sawmillBuild >= 3){
					  if(sawmill3.getLocation().equals(point)){
						  System.out.print(sawmill3.getLevel());
						  isBuilding = true;
					  }
	        	}
	        	if(sawmillBuild >= 4){
	        		if(sawmill4.getLocation().equals(point)){
	        			System.out.print(sawmill4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(sawmillBuild == 5){
		        	   if(sawmill5.getLocation().equals(point)){
		        	 	  System.out.print(sawmill5.getLevel());
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
	        	
	        	if(wallsBuild >= 1){
	        		if( wall.getLocation().equals(point)){
	        			System.out.print(wall.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 2){
	        		if( wall2.getLocation().equals(point)){
	        			System.out.print(wall2.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 3){
	        		if( wall3.getLocation().equals(point)){
	        			System.out.print(wall3.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 4){
	        		if( wall4.getLocation().equals(point)){
	        			System.out.print(wall4.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 5){
	        		if( wall5.getLocation().equals(point)){
	        			System.out.print(wall5.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 6){
	        		if( wall6.getLocation().equals(point)){
	        			System.out.print(wall6.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 7){
	        		if( wall7.getLocation().equals(point)){
	        			System.out.print(wall7.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 8){
	        		if( wall8.getLocation().equals(point)){
	        			System.out.print(wall8.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 9){
	        		if( wall9.getLocation().equals(point)){
	        			System.out.print(wall9.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 10){
	        		if( wall10.getLocation().equals(point)){
	        			System.out.print(wall10.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 11){
	        		if( wall11.getLocation().equals(point)){
	        			System.out.print(wall11.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 12){
	        		if( wall12.getLocation().equals(point)){
	        			System.out.print(wall12.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 13){
	        		if( wall13.getLocation().equals(point)){
	        			System.out.print(wall13.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 14){
	        		if( wall14.getLocation().equals(point)){
	        			System.out.print(wall14.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 15){
	        		if( wall15.getLocation().equals(point)){
	        			System.out.print(wall15.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 16){
	        		if( wall16.getLocation().equals(point)){
	        			System.out.print(wall16.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 17){
	        		if( wall17.getLocation().equals(point)){
	        			System.out.print(wall17.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 18){
	        		if( wall18.getLocation().equals(point)){
	        			System.out.print(wall18.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 19){
	        		if( wall19.getLocation().equals(point)){
	        			System.out.print(wall19.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 20){
	        		if( wall20.getLocation().equals(point)){
	        			System.out.print(wall20.getLevel());
	        			isBuilding = true;
	        		}
	        	}	        	
	        	if(wallsBuild >= 21){
	        		if( wall21.getLocation().equals(point)){
	        			System.out.print(wall21.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 22){
	        		if( wall22.getLocation().equals(point)){
	        			System.out.print(wall22.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 23){
	        		if( wall23.getLocation().equals(point)){
	        			System.out.print(wall23.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 24){
	        		if( wall24.getLocation().equals(point)){
	        			System.out.print(wall24.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 25){
	        		if( wall25.getLocation().equals(point)){
	        			System.out.print(wall25.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 26){
	        		if( wall26.getLocation().equals(point)){
	        			System.out.print(wall26.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 27){
	        		if( wall27.getLocation().equals(point)){
	        			System.out.print(wall27.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 28){
	        		if( wall28.getLocation().equals(point)){
	        			System.out.print(wall28.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 29){
	        		if( wall29.getLocation().equals(point)){
	        			System.out.print(wall29.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 30){
	        		if( wall30.getLocation().equals(point)){
	        			System.out.print(wall30.getLevel());
	        			isBuilding = true;
	        		}
	        	}	        	
	        	if(wallsBuild >= 31){
	        		if( wall31.getLocation().equals(point)){
	        			System.out.print(wall31.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 32){
	        		if( wall32.getLocation().equals(point)){
	        			System.out.print(wall32.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 33){
	        		if( wall33.getLocation().equals(point)){
	        			System.out.print(wall33.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 34){
	        		if( wall34.getLocation().equals(point)){
	        			System.out.print(wall34.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 35){
	        		if( wall35.getLocation().equals(point)){
	        			System.out.print(wall35.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 36){
	        		if( wall36.getLocation().equals(point)){
	        			System.out.print(wall36.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 37){
	        		if( wall37.getLocation().equals(point)){
	        			System.out.print(wall37.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 38){
	        		if( wall38.getLocation().equals(point)){
	        			System.out.print(wall38.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild >= 39){
	        		if( wall39.getLocation().equals(point)){
	        			System.out.print(wall39.getLevel());
	        			isBuilding = true;
	        		}
	        	}
	        	if(wallsBuild == 40){
	        		if( wall40.getLocation().equals(point)){
	        			System.out.print(wall40.getLevel());
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
			  System.out.println("Neue X-Koordinate (1 bis 40):");
			  location.setLocationX(enterInt());
		  }
		  while( location.getLocation()[1] > 40 || location.getLocation()[1] < 0 ){
			  System.err.println("Nicht innerhalb des Feldes.");
			  System.out.println("Neue Y-Koordinate (1 bis 40):");
			  location.setLocationY(enterInt());
		  }
		  for( Location oldLocation : locations){
			  try{
				  while(location.getLocationX() == oldLocation.getLocationX() && location.getLocationY() == oldLocation.getLocationY()){
				  System.err.println("An dieser Stelle steht bereits ein Geb"+ae+"ude.");
					  System.out.println("Neue X-Koordinate (1 bis 40):");
					  location.setLocationX(enterInt());
					  System.out.println("Neue Y-Koordinate (1 bis 40):");
					  location.setLocationY(enterInt());
					  while(location.getLocation()[0] > 40 || location.getLocation()[0] < 0){
						  System.err.println("Nicht innerhalb des Feldes.");
						  System.out.println("Neue X-Koordinate (1 bis 40):");
						  location.setLocationX(enterInt());
					  }
					  while(location.getLocation()[1] > 40 || location.getLocation()[1] < 0){
						  System.err.println("Nicht innerhalb des Feldes.");
						  System.out.println("Neue Y-Koordinate (1 bis 40):");
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
