package game;

import game.ConsoleInterface;

/**
 * Class UserInterface
 * 
 * The class UserInterface is the class which is used by the GUI, it is the only class the real Player directly interacts with.
 * It implements functions to create or save players or admins and to do all you can do with a player.
 * 
 * @author Constantin Schulte
 * @version 0.0
 **/
public class UserInterface {
	private static String ae = ConsoleInterface.ae;
	private static NormalPlayer player;
	private static java.util.Scanner scanner;

	
	public static void createPlayer(){
		System.out.println("Wie ist dein Name?");
		scanner = new java.util.Scanner(System.in);
		String name = (String) scanner.next().toString();
		player = new NormalPlayer(name);
	}
	
	public static void loadPlayer(){
		try{
			player = (NormalPlayer) LoadPlayer.main()[0];
			player.printDetails();
		}catch( NullPointerException e){
			System.err.println("Kein Spieler vorhanden;");
		}
	}
	
	public static void savePlayer(){
		try{
			SavePlayer.save(player);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npCollect(){
		try{
			player.collect();
		}catch( NullPointerException e ){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildLumbermill( Location location ){
		try{
			player.buildLumbermill(location);
		}catch( NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildQuarry( Location location ){
		try{
			player.buildQuarry( location );
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildMine( Location location ){
		try{
			player.buildMine(location);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildStorage( Location location ){
		try{
			player.buildStorage(location);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildApartment( Location location ){
		try{
			player.buildApartment(location);
		}catch( NullPointerException e ){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildWall(Location location){
		try{
			player.buildWall(location);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
		
	}
	
	public static void npLevelUp(String building){
		String buildingToUpgrade;
		int number;
		if(building.equalsIgnoreCase("S" + ae + "gewerk")){
			buildingToUpgrade = "sawmill";
			number = 1;
		}else if(building.equals("S" + ae + "gewerk 2")){
			buildingToUpgrade = "sawmill";
			number = 2;
		}else if(building.equals("S" + ae + "gewerk 3")){
			buildingToUpgrade = "sawmill";
			number = 3;
		}else if(building.equals("S" + ae + "gewerk 4")){
			buildingToUpgrade = "sawmill";
			number = 4;
		}else if(building.equals("S" + ae + "gewerk 5")){
			buildingToUpgrade = "sawmill";
			number = 5;
		}else if(building.equals("Steinbruch")){
			buildingToUpgrade = "quarry";
			number = 1;
		}else if(building.equals("Steinbruch 2")){
			buildingToUpgrade = "quarry";
			number = 2;
		}else if(building.equals("Steinbruch 3")){
			buildingToUpgrade = "quarry";
			number = 3;
		}else if(building.equals("Steinbruch 4")){
			buildingToUpgrade = "quarry";
			number = 4;
		}else if(building.equals("Steinbruch 5")){
			buildingToUpgrade = "quarry";
			number = 5;
		}else if(building.equals("Mine")){
			buildingToUpgrade = "mine";
			number = 1;
		}else if(building.equals("Mine 2")){
			buildingToUpgrade = "mine";
			number = 2;
		}else if(building.equals("Mine 3")){
			buildingToUpgrade = "mine";
			number = 3;
		}else if(building.equals("Mine 4")){
			buildingToUpgrade = "mine";
			number = 4;
		}else if(building.equals("Mine 5")){
			buildingToUpgrade = "mine";
			number = 5;
		}else if(building.equals("Lager")){
			buildingToUpgrade = "storage";
			number = 1;
		}else if(building.equals("Lager 2")){
			buildingToUpgrade = "storage";
			number = 2;
		}else if(building.equals("Lager 3")){
			buildingToUpgrade = "storage";
			number = 3;
		}else if(building.equals("Lager 4")){
			buildingToUpgrade = "storage";
			number = 4;
		}else if(building.equals("Lager 5")){
			buildingToUpgrade = "storage";
			number = 5;
		}else if(building.equals("Lager 6")){
			buildingToUpgrade = "storage";
			number = 6;
		}else if(building.equals("Wohnhaus")){
			buildingToUpgrade = "apartment";
			number = 1;
		}else if(building.equals("Wohnhaus 2")){
			buildingToUpgrade = "apartment";
			number = 2;
		}else if(building.equals("Wohnhaus 3")){
			buildingToUpgrade = "apartment";
			number = 3;
		}else if(building.equals("Wohnhaus 4")){
			buildingToUpgrade = "apartment";
			number = 4;
			
		}else if(building.equalsIgnoreCase("Mauer")){
			buildingToUpgrade= "wall";
			number = 1;
		}else if(building.equalsIgnoreCase("Mauer 2")){
			buildingToUpgrade= "wall";
			number = 2;
		}else if(building.equalsIgnoreCase("Mauer 3")){
			buildingToUpgrade= "wall";
			number = 3;
		}else if(building.equalsIgnoreCase("Mauer 4")){
			buildingToUpgrade= "wall";
			number = 4;
		}else if(building.equalsIgnoreCase("Mauer 5")){
			buildingToUpgrade= "wall";
			number = 5;
		}else if(building.equalsIgnoreCase("Mauer 6")){
			buildingToUpgrade= "wall";
			number = 6;
		}else if(building.equalsIgnoreCase("Mauer 7")){
			buildingToUpgrade= "wall";
			number = 7;
		}else if(building.equalsIgnoreCase("Mauer 8")){
			buildingToUpgrade= "wall";
			number = 8;
		}else if(building.equalsIgnoreCase("Mauer 9")){
			buildingToUpgrade= "wall";
			number = 9;
		}else if(building.equalsIgnoreCase("Mauer 10")){
			buildingToUpgrade= "wall";
			number = 10;
		}else if(building.equalsIgnoreCase("Mauer 11")){
			buildingToUpgrade= "wall";
			number = 11;
		}else if(building.equalsIgnoreCase("Mauer 12")){
			buildingToUpgrade= "wall";
			number = 12;
		}else if(building.equalsIgnoreCase("Mauer 13")){
			buildingToUpgrade= "wall";
			number = 13;
		}else if(building.equalsIgnoreCase("Mauer 14")){
			buildingToUpgrade= "wall";
			number = 14;
		}else if(building.equalsIgnoreCase("Mauer 15")){
			buildingToUpgrade= "wall";
			number = 15;
		}else if(building.equalsIgnoreCase("Mauer 16")){
			buildingToUpgrade= "wall";
			number = 16;
		}else if(building.equalsIgnoreCase("Mauer 17")){
			buildingToUpgrade= "wall";
			number = 17;
		}else if(building.equalsIgnoreCase("Mauer 18")){
			buildingToUpgrade= "wall";
			number = 18;
		}else if(building.equalsIgnoreCase("Mauer 19")){
			buildingToUpgrade= "wall";
			number = 19;
		}else if(building.equalsIgnoreCase("Mauer 20")){
			buildingToUpgrade= "wall";
			number = 20;
		}else if(building.equalsIgnoreCase("Mauer 21")){
			buildingToUpgrade= "wall";
			number = 21;
		}else if(building.equalsIgnoreCase("Mauer 22")){
			buildingToUpgrade= "wall";
			number = 22;
		}else if(building.equalsIgnoreCase("Mauer 23")){
			buildingToUpgrade= "wall";
			number = 23;
		}else if(building.equalsIgnoreCase("Mauer 24")){
			buildingToUpgrade= "wall";
			number = 24;
		}else if(building.equalsIgnoreCase("Mauer 25")){
			buildingToUpgrade= "wall";
			number = 25;
		}else if(building.equalsIgnoreCase("Mauer 26")){
			buildingToUpgrade= "wall";
			number = 16;
		}else if(building.equalsIgnoreCase("Mauer 27")){
			buildingToUpgrade= "wall";
			number = 17;
		}else if(building.equalsIgnoreCase("Mauer 28")){
			buildingToUpgrade= "wall";
			number = 28;
		}else if(building.equalsIgnoreCase("Mauer 29")){
			buildingToUpgrade= "wall";
			number = 29;
		}else if(building.equalsIgnoreCase("Mauer 30")){
			buildingToUpgrade= "wall";
			number = 20;
		}else if(building.equalsIgnoreCase("Mauer 31")){
			buildingToUpgrade= "wall";
			number = 31;
		}else if(building.equalsIgnoreCase("Mauer 32")){
			buildingToUpgrade= "wall";
			number = 32;
		}else if(building.equalsIgnoreCase("Mauer 33")){
			buildingToUpgrade= "wall";
			number = 33;
		}else if(building.equalsIgnoreCase("Mauer 34")){
			buildingToUpgrade= "wall";
			number = 34;
		}else if(building.equalsIgnoreCase("Mauer 35")){
			buildingToUpgrade= "wall";
			number = 35;
		}else if(building.equalsIgnoreCase("Mauer 36")){
			buildingToUpgrade= "wall";
			number = 36;
		}else if(building.equalsIgnoreCase("Mauer 37")){
			buildingToUpgrade= "wall";
			number = 37;
		}else if(building.equalsIgnoreCase("Mauer 38")){
			buildingToUpgrade= "wall";
			number = 38;
		}else if(building.equalsIgnoreCase("Mauer 39")){
			buildingToUpgrade= "wall";
			number = 39;
		}else if(building.equalsIgnoreCase("Mauer 40")){
			buildingToUpgrade= "wall";
			number = 40;
			
		}else{
			System.err.println("Kein Gebäude gewählt.");
			return;
		}
		try{
			player.levelUp(buildingToUpgrade, number);
		}catch( NullPointerException e ){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npMove(String building, Location location){
		String buildingToMove;
		int number;
		if(building.equals("S" + ae + "gewerk")){
			buildingToMove = "sawmill";
			number = 1;
		}else if(building.equals("S" + ae + "gewerk 2")){
			buildingToMove = "sawmill";
			number = 2;
		}else if(building.equals("S" + ae + "gewerk 3")){
			buildingToMove = "sawmill";
			number = 3;
		}else if(building.equals("S" + ae + "gewerk 4")){
			buildingToMove = "sawmill";
			number = 4;
		}else if(building.equals("S" + ae + "gewerk 5")){
			buildingToMove = "sawmill";
			number = 5;
		}else if(building.equals("Steinbruch")){
			buildingToMove = "quarry";
			number = 1;
		}else if(building.equals("Steinbruch 2")){
			buildingToMove = "quarry";
			number = 2;
		}else if(building.equals("Steinbruch 3")){
			buildingToMove = "quarry";
			number = 3;
		}else if(building.equals("Steinbruch 4")){
			buildingToMove = "quarry";
			number = 4;
		}else if(building.equals("Steinbruch 5")){
			buildingToMove = "quarry";
			number = 5;
		}else if(building.equals("Mine")){
			buildingToMove = "mine";
			number = 1;
		}else if(building.equals("Mine 2")){
			buildingToMove = "mine";
			number = 2;
		}else if(building.equals("Mine 3")){
			buildingToMove = "mine";
			number = 3;
		}else if(building.equals("Mine 4")){
			buildingToMove = "mine";
			number = 4;
		}else if(building.equals("Mine 5")){
			buildingToMove = "mine";
			number = 5;
		}else if(building.equals("Lager")){
			buildingToMove = "storage";
			number = 1;
		}else if(building.equals("Lager 2")){
			buildingToMove = "storage";
			number = 2;
		}else if(building.equals("Lager 3")){
			buildingToMove = "storage";
			number = 3;
		}else if(building.equals("Lager 4")){
			buildingToMove = "storage";
			number = 4;
		}else if(building.equals("Lager 5")){
			buildingToMove = "storage";
			number = 5;
		}else if(building.equals("Lager 6")){
			buildingToMove = "storage";
			number = 6;
		}else if(building.equals("Wohnhaus")){
			buildingToMove = "apartment";
			number = 1;
		}else if(building.equals("Wohnhaus 2")){
			buildingToMove = "apartment";
			number = 2;
		}else if(building.equals("Wohnhaus 3")){
			buildingToMove = "apartment";
			number = 3;
		}else if(building.equals("Wohnhaus 4")){
			buildingToMove = "apartment";
			number = 4;
			
		}else if(building.equalsIgnoreCase("Mauer")){
			buildingToMove= "wall";
			number = 1;
		}else if(building.equalsIgnoreCase("Mauer 2")){
			buildingToMove= "wall";
			number = 2;
		}else if(building.equalsIgnoreCase("Mauer 3")){
			buildingToMove= "wall";
			number = 3;
		}else if(building.equalsIgnoreCase("Mauer 4")){
			buildingToMove= "wall";
			number = 4;
		}else if(building.equalsIgnoreCase("Mauer 5")){
			buildingToMove= "wall";
			number = 5;
		}else if(building.equalsIgnoreCase("Mauer 6")){
			buildingToMove= "wall";
			number = 6;
		}else if(building.equalsIgnoreCase("Mauer 7")){
			buildingToMove= "wall";
			number = 7;
		}else if(building.equalsIgnoreCase("Mauer 8")){
			buildingToMove= "wall";
			number = 8;
		}else if(building.equalsIgnoreCase("Mauer 9")){
			buildingToMove= "wall";
			number = 9;
		}else if(building.equalsIgnoreCase("Mauer 10")){
			buildingToMove= "wall";
			number = 10;
		}else if(building.equalsIgnoreCase("Mauer 11")){
			buildingToMove= "wall";
			number = 11;
		}else if(building.equalsIgnoreCase("Mauer 12")){
			buildingToMove= "wall";
			number = 12;
		}else if(building.equalsIgnoreCase("Mauer 13")){
			buildingToMove= "wall";
			number = 13;
		}else if(building.equalsIgnoreCase("Mauer 14")){
			buildingToMove= "wall";
			number = 14;
		}else if(building.equalsIgnoreCase("Mauer 15")){
			buildingToMove= "wall";
			number = 15;
		}else if(building.equalsIgnoreCase("Mauer 16")){
			buildingToMove= "wall";
			number = 16;
		}else if(building.equalsIgnoreCase("Mauer 17")){
			buildingToMove= "wall";
			number = 17;
		}else if(building.equalsIgnoreCase("Mauer 18")){
			buildingToMove= "wall";
			number = 18;
		}else if(building.equalsIgnoreCase("Mauer 19")){
			buildingToMove= "wall";
			number = 19;
		}else if(building.equalsIgnoreCase("Mauer 20")){
			buildingToMove= "wall";
			number = 20;
		}else if(building.equalsIgnoreCase("Mauer 21")){
			buildingToMove= "wall";
			number = 21;
		}else if(building.equalsIgnoreCase("Mauer 22")){
			buildingToMove= "wall";
			number = 22;
		}else if(building.equalsIgnoreCase("Mauer 23")){
			buildingToMove= "wall";
			number = 23;
		}else if(building.equalsIgnoreCase("Mauer 24")){
			buildingToMove= "wall";
			number = 24;
		}else if(building.equalsIgnoreCase("Mauer 25")){
			buildingToMove= "wall";
			number = 25;
		}else if(building.equalsIgnoreCase("Mauer 26")){
			buildingToMove= "wall";
			number = 16;
		}else if(building.equalsIgnoreCase("Mauer 27")){
			buildingToMove= "wall";
			number = 17;
		}else if(building.equalsIgnoreCase("Mauer 28")){
			buildingToMove= "wall";
			number = 28;
		}else if(building.equalsIgnoreCase("Mauer 29")){
			buildingToMove= "wall";
			number = 29;
		}else if(building.equalsIgnoreCase("Mauer 30")){
			buildingToMove= "wall";
			number = 20;
		}else if(building.equalsIgnoreCase("Mauer 31")){
			buildingToMove= "wall";
			number = 31;
		}else if(building.equalsIgnoreCase("Mauer 32")){
			buildingToMove= "wall";
			number = 32;
		}else if(building.equalsIgnoreCase("Mauer 33")){
			buildingToMove= "wall";
			number = 33;
		}else if(building.equalsIgnoreCase("Mauer 34")){
			buildingToMove= "wall";
			number = 34;
		}else if(building.equalsIgnoreCase("Mauer 35")){
			buildingToMove= "wall";
			number = 35;
		}else if(building.equalsIgnoreCase("Mauer 36")){
			buildingToMove= "wall";
			number = 36;
		}else if(building.equalsIgnoreCase("Mauer 37")){
			buildingToMove= "wall";
			number = 37;
		}else if(building.equalsIgnoreCase("Mauer 38")){
			buildingToMove= "wall";
			number = 38;
		}else if(building.equalsIgnoreCase("Mauer 39")){
			buildingToMove= "wall";
			number = 39;
		}else if(building.equalsIgnoreCase("Mauer 40")){
			buildingToMove= "wall";
			number = 40;
		}else{
			System.err.println("Kein Gebäude gewählt.");
			return;
		}
		try{
			player.moveBuilding(buildingToMove, number, location);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
}
