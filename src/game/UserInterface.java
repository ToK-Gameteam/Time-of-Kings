package game;

/**
 * Class UserInterface
 * 
 * The class UserInterface is the class which is used by the GUI, it is the only class the real Player directly interacts with.
 * It implements functions to create or save players or admins and to do all you can do with a player.
 * 
 * @author Constantin Schulte
 * @version 0.0
 */

public abstract class UserInterface {
	private static NormalPlayer player;
	private static Admin admin;
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
	
	public static void npBuildLumbermill(int locationX, int locationY){
		try{
			player.buildLumbermill(locationX, locationY);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildQuarry(int locationX, int locationY){
		try{
			player.buildQuarry(locationX, locationY);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildMine(int locationX, int locationY){
		try{
			player.buildMine(locationX, locationY);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildStorage(int locationX, int locationY){
		try{
			player.buildStorage(locationX, locationY);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npBuildApartment(int locationX, int locationY){
		try{
			player.buildApartment(locationX, locationY);
		}catch( NullPointerException e ){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void npLevelUp(String building){
		String buildingToUpgrade;
		int number;
		if(building.equals("Saegewerk")){
			buildingToUpgrade = "lumbermill";
			number = 1;
		}else if(building.equals("Saegewerk 2")){
			buildingToUpgrade = "lumbermill";
			number = 2;
		}else if(building.equals("Saegewerk 3")){
			buildingToUpgrade = "lumbermill";
			number = 3;
		}else if(building.equals("Saegewerk 4")){
			buildingToUpgrade = "lumbermill";
			number = 4;
		}else if(building.equals("Saegewerk 5")){
			buildingToUpgrade = "lumbermill";
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
	
	public static void npMove(String building, int locationX, int locationY){
		try{
			player.moveBuilding(building, locationX, locationX);
		}catch(NullPointerException e){
			System.err.println("Kein Spieler vorhanden, erstelle erst einen Spieler.");
		}
	}
	
	public static void createAdmin(){
		scanner = new java.util.Scanner(System.in);
		String name = (String) scanner.next().toString();
		admin = new Admin(name);
	}
	
	public static void loadAdmin(){
		admin = (Admin) LoadAdmin.main()[0];
		admin.printDetails();
	}
	
	public static void saveAdmin(){
		SaveAdmin.save(admin);
	}
	
	public static void aCollect(){
		admin.collect();
	}
	
	public static void aBuildLumbermill(int locationX, int locationY){
		admin.buildLumbermill(locationX, locationY);
	}
	
	public static void aBuildQuarry(int locationX, int locationY){
		admin.buildQuarry(locationX, locationY);
	}
	
	public static void aBuildMine(int locationX, int locationY){
		admin.buildMine(locationX, locationY);
	}
	
	public static void aSetValues(int newWood, int newStone, int newIron){
		admin.setValue(newWood, newStone, newIron);
	}
}
