package de.tok_gameteam.tok.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.tok_gameteam.tok.player.Player;
import de.tok_gameteam.tok.util.Location;
import de.tok_gameteam.tok.village.Apartment;
import de.tok_gameteam.tok.village.Building;
import de.tok_gameteam.tok.village.CommunityHall;
import de.tok_gameteam.tok.village.ResourceCollector;
import de.tok_gameteam.tok.village.Storage;
import de.tok_gameteam.tok.village.Village;
import de.tok_gameteam.tok.village.Wall;

public class Db {
	private Connection con = null;
	private Statement stmt = null;
	private String path;
	
	public Db(){
		path = System.getProperty("user.dir")
				+ File.separator + "ToK" + File.separator + "TokDB";
		initialize();
	}
	
	public void initialize(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
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
		}finally{
			closeConnection();
		}
	}
	
	public void enterPlayer(Player player){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			Village village = player.getVillage();
			
			stmt.executeQuery("INSERT INTO player VALUES(DEFAULT, '"
					+ player.getName()+ "', "
					+ player.getResources()[0] + ", "
					+ player.getResources()[2] + ", "
					+ player.getResources()[4] + ")");
			
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
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	public ArrayList<String> loadPlayers(){
		ArrayList<String> names = new ArrayList<String>();
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			ResultSet nameSet = stmt.executeQuery("SELECT name FROM player");
			while(nameSet.next()){
				names.add(nameSet.getString(1));
			}
			nameSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		return names;
	}
	
	public Player getPlayer(String name){
		Player ply = new Player(name);
		
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			ResultSet player = stmt.executeQuery("SELECT * FROM player WHERE name = '" + name + "'");
			player.next();
			ResultSet village = stmt.executeQuery("SELECT * FROM village WHERE player = " + player.getInt(1));
			village.next();
			int[] resources = {player.getInt(3), player.getInt(4), player.getInt(5)};
			int[] buildingsBuild = {village.getInt(3), village.getInt(4), village.getInt(5), 
					village.getInt(6), village.getInt(7), village.getInt(8), village.getInt(9)};
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
			}
			
			ply = new Player(name, new Village(buildings, resources, buildingsBuild));
			
			player.close();
			village.close();
			allBuildings.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return ply;
	}
	
	public void updatePlayer(Player player){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			Village village = player.getVillage();
			
			stmt.executeQuery("UPDATE player SET "
					+ "wood = " + player.getResources()[0] + ", "
					+ "stone = " + player.getResources()[2] + ", "
					+ "iron = " + player.getResources()[4] + ""
							+ "WHERE name = '" + player.getName() + "'");
			
			ResultSet rslt = stmt.executeQuery("SELECT id FROM player WHERE name='" + player.getName() + "'");
			String villageQuery = "UPDATE village SET communityHalls = " + village.getBuildingsBuild()[0]
					+ ", sawmills = " + village.getBuildingsBuild()[1]
							+ ", quarrys = " + village.getBuildingsBuild()[2]
									+ ", mines = " + village.getBuildingsBuild()[3]
											+ ", storages = " + village.getBuildingsBuild()[4]
													+ ", apartments = " + village.getBuildingsBuild()[5]
															+ ", walls = " + village.getBuildingsBuild()[6];
			
			stmt.executeQuery(villageQuery);
			

			rslt.next();
			
			ResultSet rsltVillage = stmt.executeQuery("SELECT id FROM village WHERE player = " + rslt.getInt(1));
			rsltVillage.next();
			Building[] buildings = village.getBuildings();
			for(Building building : buildings){
				if(building != null){
					if(building.getId() == 0){
						if(building.getType() == Village.SAWMILL || building.getType() == Village.QUARRY ||
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
			
			rsltVillage.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
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
