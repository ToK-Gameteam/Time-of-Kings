package game;

import java.io.IOException;

import game.util.Update;

public class ConsoleInterface {

	public static double  GAME_VERSION = 0.3;
	private static java.util.Scanner scanner;
	public static String ae = "\u00e4"; // Klein
	public static String Ae = "\u00c4"; // Gross
	public static String oe = "\u00f6"; // Klein
	public static String Oe = "\u00d6"; // Gross
	public static String ue = "\u00fc"; // Klein
	public static String Ue = "\u00dc"; // Gross
	public static String ss = "\u00df";
	public static void main(String[] args) {
		try {
			Update.checkUpdate();
		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
		System.out.println("Herzlich willkommen!");
		System.out.println("Brauchst du Hilfe? -> Optionen <-");
		System.out.println("Version: Alpha Test, "+GAME_VERSION);
		System.out.println("Patchnotes können mit -> notes <- eingesehen werden");
		System.out.println("");
		System.out.println("Viel Spa"+ss+"!!");
		
		while( true ){
			System.out.println("");
			System.out.println( "Was willst du tun?" );
			String in = enter();
			if( in.equalsIgnoreCase( "Spieler erstellen" ) ){
				UserInterface.createPlayer();
			}else if(in.equalsIgnoreCase("S" + ae + "gewerk bauen")){
				System.out.println("Wo soll das S" + ae +"gewerk stehen?");
				System.out.println("X-Koordinate(zwischen 1 und 40):");
				int locationX = enterInt();
				System.out.println("Y-Koordinate (zwischen 1 und 40):");
				int locationY = enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npBuildLumbermill(location);
			}else if(in.equalsIgnoreCase("Steinbruch bauen")){
				System.out.println("Wo soll der Steinbruch stehen?");
				System.out.println("X-Koordinate(zwischen 1 und 40):");
				int locationX = enterInt();
				System.out.println("Y-Koordinate (zwischen 1 und 40):");
				int locationY =enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npBuildQuarry(location);
			}else if( in.equalsIgnoreCase("Mine bauen")){
				System.out.println("Wo soll die Mine stehen?");
				System.out.println("X-Koordinate(zwischen 1 und 40):");
				int locationX = enterInt();
				System.out.println("Y-Koordinate (zwischen 1 und 40):");
				int locationY =enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npBuildMine(location);
			}else if( in.equalsIgnoreCase("Lager bauen")){
				System.out.println("Wo soll das Lager stehen?");
				System.out.println("X-Koordinate(zwischen 1 und 40):");
				int locationX = enterInt();
				System.out.println("Y-Koordinate (zwischen 1 und 40):");
				int locationY =enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npBuildStorage(location);
			}else if( in.equalsIgnoreCase("Wohnhaus bauen")){
				System.out.println("Wo soll das Wohnhaus stehen?");
				System.out.println("X-Koordinate(zwischen 1 und 40):");
				int locationX = enterInt();
				System.out.println("Y-Koordinate (zwischen 1 und 40):");
				int locationY =enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npBuildApartment(location);
			}else if(in.equalsIgnoreCase("Mauer bauen")){
					System.out.println("Wo soll die Mauer stehen?");
					System.out.println("X-Koordinate(zwischen 1 und 40):");
					int locationX = enterInt();
					System.out.println("Y-Koordinate (zwischen 1 und 40):");
					int locationY =enterInt();
					Location location = new Location( locationX, locationY);
					UserInterface.npBuildWall(location);
			}else if( in.equalsIgnoreCase("Level Up")){
				System.out.println("Welches Geb"+ae+"ude willst du aufwerten?");
				String building = enter();
				UserInterface.npLevelUp(building);
			}else if(in.equalsIgnoreCase("Bewegen")){
				System.out.println("Welches Geb"+ae+"ude willst du bewegen?");
				String building = enter();
				System.out.println("Zu welcher X-Koordinate soll es bewegt werden (1 bis 40)?");
				int locationX = enterInt();
				System.out.println("Zu welcher Y-Koordinate soll es bewegt werden (1 bis 40)");
				int locationY = enterInt();
				Location location = new Location( locationX, locationY);
				UserInterface.npMove(building, location);
			}else if( in.equalsIgnoreCase("Speichern")){
				UserInterface.savePlayer();
			}else if(in.equalsIgnoreCase("Ressourcen sammeln")){
				UserInterface.npCollect();
			}else if( in.equals("Laden")){
				UserInterface.loadPlayer();
			}else if(in.equalsIgnoreCase("Optionen")){
				System.out.println("1. Spieler erstellen");
				System.out.println("2. Saegewerk bauen");
				System.out.println("3. Steinbruch bauen");
				System.out.println("4. Mine bauen");
				System.out.println("5. Lager bauen");
				System.out.println("6. Wohnhaus bauen");
				System.out.println("7. Level Up");
				System.out.println("8. Bewegen");
				System.out.println("9. Ressourcen sammeln");
				System.out.println("10. Speichern");
				System.out.println("11. Laden");
				System.out.println("12. Ende");
			}else if( in.equalsIgnoreCase("Ende")){
				System.exit(0);
			} else if(in.equalsIgnoreCase("notes")) {
				System.out.println("");
				System.out.println("Patchnotes (v" + GAME_VERSION + "):");
				System.out.println("Neuer Geb" + ae +"udetyp: Wohnhaus!");
				System.out.println("Baue jetzt deine Wohnh"+ae+"user mit 'Wohnhaus bauen'.");
				System.out.println("");
				System.out.println("Hotfix 2");
				System.out.println("UniCode fix");
				System.out.println("Updates k"+oe+"nnen nun auch manuell gecheckt werden mit -> checkupdate <-");
				System.out.println("");
			} else if(in.equalsIgnoreCase("checkupdate")) {
				try {
					Update.checkUpdate();
				} catch (IOException e) {
					System.err.println("Error: "+e);
				}
			} else {
				System.err.println("Unbekannt");
				System.out.println("Brauchst du Hilfe?");
				System.out.println("'Optionen' zeigt dir alle Features");
			}
		}
	}
	
	private static String enter(){
		scanner = new java.util.Scanner(System.in);
		String in = scanner.nextLine();
		return in;
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
