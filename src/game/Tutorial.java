package game;

import java.io.Serializable;

/**
 * Class Tutorial
 * 
 * The class Tutorial introduces the player into the Game and explains the first steps.
 * It is started from the constructor of the class Player.
 * 
 * @author Constantin Schulte
 * @version 0.0 -> implemented in Game version 0.1
 **/
public class Tutorial implements Serializable {
	private static final long serialVersionUID = 1L;
	private static String ae = ConsoleInterface.ae;
	private static java.util.Scanner scanner;
	private static Distributor distributor;

	public static void startTutorial(Distributor myDistributor){
		distributor = myDistributor;
		System.out.println("Als erstes braucht dein Dorf ein Rathaus.");
		System.out.println("Tipp: Du baust Geb"+ConsoleInterface.ae+"ude indem du ??? bauen eingibst.");
		int mainBuildingBuild = 0;
		while( mainBuildingBuild == 0 ){
			String mainBuilding = enter();

			if(mainBuilding.equals( "Rathaus bauen" ) ){
				  while( mainBuildingBuild == 0 ){
					  System.out.println("Wo soll dein Rathaus stehen?");
					  System.out.println("X-Koordinate (zwischen 0 und 39):");
					  System.out.println("Tipp: Gib beim Bauen eines Geb"+ae+"udes an, wo es stehen soll. Stelle das Rathaus an die Position (3|3)");
					  int locationX = enterInt();
					  System.out.println("Y-Koordinate (zwischen 0 und 9):");
					  int locationY = enterInt();
					  if( locationX == 3 && locationY == 3 ){
						  distributor.buildMainBuilding(locationX, locationY);
						  break;
				  	}else{
					  	System.err.println("Falsche Koordinaten.");
					  	System.out.println("Tipp: Stelle das Geb"+ae+"ude an die Position (3|3).");
				  	}
				  }
				mainBuildingBuild = 1;
				break;
			}else{
				System.err.println("Du musst zuerst ein Rathaus bauen!");
				System.out.println("Tipp: Gib 'Rathaus bauen' ein, um dein Rathaus zu bauen.");
			}

		}
		
	}
	
	private static String enter(){
		scanner = new java.util.Scanner(System.in);
		String in = scanner.nextLine();
		return in;
	}
	
	private static int enterInt(){
		int inInt;
		scanner = new java.util.Scanner(System.in);
		String in = scanner.nextLine();
		try{
			inInt = Integer.parseInt(in);
		}catch( NumberFormatException e ){
			System.err.println("Keine ganze Zahl eingegeben");
			inInt = -1;
		}
		return inInt;
	}
}
