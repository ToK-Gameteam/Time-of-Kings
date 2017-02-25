package game.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import game.player.Player;
import game.util.Location;
import game.village.Apartment;
import game.village.Building;
import game.village.CommunityHall;
import game.village.ResourceCollector;
import game.village.Storage;
import game.village.Village;
import game.village.Wall;

public class Db {
	private Connection con = null;
	private Statement stmt = null;
	
	public Db(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:gameDb;shutdown=true", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	public void initialize(){
		try {
			stmt = con.createStatement();
			
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS player("
					+ "id INT NOT NULL PRIMARY KEY "
					+ "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "name VARCHAR(255) NOT NULL, "
					+ "wood INT NOT NULL, "
					+ "stone INT NOT NULL, "
					+ "iron INT NOT NULL)");
			
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS village("
					+ "id INT NOT NULL PRIMARY KEY "
					+ "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "player INT NOT NULL, "
					+ "communityHalls INT NOT NULL, "
					+ "sawmills INT NOT NULL, "
					+ "quarrys INT NOT NULL, "
					+ "mines INT NOT NULL, "
					+ "storages INT NOT NULL, "
					+ "apartments INT NOT NULL, "
					+ "walls INT NOT NULL)");
			
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS buildings("
					+ "id INT NOT NULL PRIMARY KEY "
					+ "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "village INT NOT NULL, "
					+ "type INT NOT NULL, "
					+ "xValue INT NOT NULL, "
					+ "yValue INT NOT NULL, "
					+ "level INT NOT NULL,"
					+ "number INT NOT NULL, "
					+ "additionalInformation BIGINT)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void enterPlayer(Player player){
		try {
			stmt = con.createStatement();
			
			Village village = player.getVillage();
			
			stmt.executeQuery("INSERT INTO player VALUES(DEFAULT, '"
					+ player.getName()+ "', "
					+ player.getInformation()[1] + ", "
					+ player.getInformation()[3] + ", "
					+ player.getInformation()[5] + ")");
			
			ResultSet rslt = stmt.executeQuery("SELECT id FROM player WHERE name='" + player.getName() + "'");
			rslt.next();
			String villageQuery = "INSERT INTO village VALUES(DEFAULT, "
					+ rslt.getInt(1);
			for(int index = 0; index < 7; ++index){
				villageQuery += ", ";
				villageQuery += village.getBuildingsBuild()[index];
			}
			villageQuery += ")";
			
			stmt.executeQuery(villageQuery);
			
			stmt.close();
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> loadPlayers(){
		ArrayList<String> names = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			ResultSet nameSet = stmt.executeQuery("SELECT name FROM player");
			while(nameSet.next()){
				names.add(nameSet.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				stmt.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			closeConnection();
		}
		return names;
	}
	
	public Player getPlayer(String name){
		Player ply = new Player(name);
		
		try {
			stmt = con.createStatement();
			ResultSet player = stmt.executeQuery("SELECT * FROM player WHERE name = '" + name + "'");
			player.next();
			ResultSet village = stmt.executeQuery("SELECT * FROM village WHERE player = " + player.getInt(1));
			village.next();
			int[] resources = {player.getInt(3), player.getInt(4), player.getInt(5)};
			int[] buildingsBuild = {village.getInt(3), village.getInt(4), village.getInt(5), 
					village.getInt(6), village.getInt(6), village.getInt(8), village.getInt(9)};
			ResultSet allBuildings = stmt.executeQuery("SELECT * FROM buildings WHERE village = " + village.getInt(1));
			Building[] buildings = new Building[66];
			
			while(allBuildings.next()){
				switch(allBuildings.getInt(3)){
				case Village.COMMUNITY_HALL:
					buildings[0] = new CommunityHall(new Location(allBuildings.getInt(4),
							allBuildings.getInt(5)), allBuildings.getInt(6), allBuildings.getInt(1));
					break;
				case Village.SAWMILL:
					buildings[Village.SAWMILL+allBuildings.getInt(7)] = new ResourceCollector(
							new Location(allBuildings.getInt(4), allBuildings.getInt(5)), Village.SAWMILL,
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getLong(8), allBuildings.getInt(7));
					break;
				case Village.QUARRY:
					buildings[Village.QUARRY+allBuildings.getInt(7)] = new ResourceCollector(
							new Location(allBuildings.getInt(4),allBuildings.getInt(5)), Village.QUARRY,
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getLong(8), allBuildings.getInt(7));
					break;
				case Village.MINE:
					buildings[Village.MINE+allBuildings.getInt(7)] = new ResourceCollector(
							new Location(allBuildings.getInt(4), allBuildings.getInt(5)), Village.MINE,
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getLong(8), allBuildings.getInt(7));
					break;
				case Village.APARTMENT:
					buildings[Village.APARTMENT+allBuildings.getInt(7)] = new Apartment(
							new Location(allBuildings.getInt(4), allBuildings.getInt(5)),
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getInt(7));
					break;
				case Village.STORAGE:
					buildings[Village.STORAGE+allBuildings.getInt(7)] = new Storage(
							new Location(allBuildings.getInt(4), allBuildings.getInt(5)),
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getInt(7));
					break;
				case Village.WALL:
					buildings[Village.WALL+allBuildings.getInt(7)] = new Wall(
							new Location(allBuildings.getInt(4), allBuildings.getInt(5)),
							allBuildings.getInt(6), allBuildings.getInt(1), allBuildings.getInt(7));
					break;
				}
				
				ply = new Player(name, new Village(buildings, resources, buildingsBuild));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ply;
	}
	
	public void updatePlayer(Player player){
		try {
			stmt = con.createStatement();
			
			Village village = player.getVillage();
			
			stmt.executeQuery("UPDATE player SET "
					+ "wood = " +player.getInformation()[1] + ", "
					+ "stone = " + player.getInformation()[3] + ", "
					+ "iron = " +player.getInformation()[5] + ""
							+ "WHERE name = '" + player.getName() + "'");
			
			ResultSet rslt = stmt.executeQuery("SELECT id FROM player WHERE name='" + player.getName() + "'");
			String villageQuery = "UPDATE village SET communityHalls = " + village.getBuildingsBuild()[0]
					+ ", sawmills = " + village.getBuildingsBuild()[1]
							+ ", quarrys = " + village.getBuildingsBuild()[2]
									+ ", mines = " + village.getBuildingsBuild()[3]
											+ ", apartments = " + village.getBuildingsBuild()[4]
													+ ", storages = " + village.getBuildingsBuild()[5]
															+ ", walls = " + village.getBuildingsBuild()[6];
			
			stmt.executeQuery(villageQuery);
			

			rslt.next();
			
			ResultSet rsltVillage = stmt.executeQuery("SELECT id FROM village WHERE player = " + rslt.getInt(1));
			rsltVillage.next();
			Building[] buildings = village.getBuildings();
			for(Building building : buildings){
				if(building != null){
					if(building.getId() == 0){if(building.getType() == Village.SAWMILL || building.getType() == Village.QUARRY ||
						building.getType() == Village.MINE){
						String query = "INSERT INTO buildings VALUES(DEFAULT, "
								+ rsltVillage.getInt(1) + ", "
								+ building.getType() + ", "
								+ building.getLocation().getLocationX() + ", "
								+ building.getLocation().getLocationY() + ", "
								+ building.getLevel() + ", "
								+ building.getNumber() + ", ";
						ResourceCollector collector = (ResourceCollector) building;
						query += (collector.getLastCollected() +  ")");
						stmt.executeQuery(query);
					}else{
						stmt.executeQuery("INSERT INTO buildings VALUES(DEFAULT, "
								+ rsltVillage.getInt(1) + ", "
								+ building.getType() + ", "
								+ building.getLocation().getLocationX() + ", "
								+ building.getLocation().getLocationY() + ", "
								+ building.getLevel() + ", " 
								+ building.getNumber() +  ", 0)");
					}
						
					}else{
						stmt.executeQuery("UPDATE buildings SET xValue = " + building.getLocation().getLocationX()
							+ ", yValue = " + building.getLocation().getLocationY()
							+ ", level = " + building.getLevel() + "WHERE id = " + building.getId());
					}
				}
			}
			
			stmt.close();
		} catch (SQLException e) {
			closeConnection();
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
