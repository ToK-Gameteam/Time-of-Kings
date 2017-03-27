package de.tok_gameteam.tok.update;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import de.tok_gameteam.tok.village.Village;

public class DatabaseUpdate {
	
	public static final int BALANCING_VERSION = 2;
	
	private Statement stmt;
	private Connection con;
	private String path;

	public DatabaseUpdate(){
		path = System.getProperty("user.dir")
				+ File.separator + "ToK" + File.separator + "TokDB";
	}
	
	public void execute(){
		updateValues();
		updateCosts();
		updateBuildables();
	}
	
	private void updateValues(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			stmt.executeQuery("UPDATE buildingValues SET"
					+ " valueL1 = 85, valueL2 = 89, valueL3 = 94, valueL4 = 100"
					+ ", valueL5 = 107, valueL6 = 115 WHERE building = " + Village.SAWMILL);
			
			stmt.executeQuery("UPDATE buildingValues SET"
					+ " valueL1 = 500, valueL2 = 850, valueL3 = 1300, valueL4 = 2000"
					+ ", valueL5 = 3000, valueL6 = 5000 WHERE building = " + Village.QUARRY);
			
			stmt.executeQuery("UPDATE buildingValues SET"
					+ " valueL1 = 100, valueL2 = 500, valueL3 = 2000, valueL4 = 8000"
					+ ", valueL5 = 20000, valueL6 = 50000 WHERE building = " + Village.APARTMENT);
			
			stmt.executeQuery("UPDATE buildingValues SET"
					+ " valueL1 = 3000, valueL2 = 9000, valueL3 = 30000, valueL4 = 120000"
					+ ", valueL5 = 300000, valueL6 = 750000 WHERE building = " + Village.STORAGE);

			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	private void updateCosts(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();

			stmt.executeQuery("UPDATE buildingCosts SET"
					+ " costsL1 = 0, costsL2 = 1000, costsL3 = 15000, costsL4 = 50000"
					+ ", costsL5 = 125000, costsL6 = 625000 WHERE building = " + Village.COMMUNITY_HALL);
			
			stmt.executeQuery("UPDATE buildingCosts SET"
					+ " costsL1 = 100, costsL2 = 200, costsL3 = 700, costsL4 = 2500"
					+ ", costsL5 = 8000, costsL6 = 20000 WHERE building = " + Village.SAWMILL);

			stmt.executeQuery("UPDATE buildingCosts SET"
					+ " costsL1 = 100, costsL2 = 500, costsL3 = 200, costsL4 = 8000"
					+ ", costsL5 = 20000, costsL6 = 50000 WHERE building = " + Village.STORAGE);
			
			stmt.executeQuery("UPDATE buildingCosts SET"
					+ " costsL1 = 100, costsL2 = 300, costsL3 = 1000, costsL4 = 3100"
					+ ", costsL5 = 10000, costsL6 = 20000 WHERE building = " + Village.APARTMENT);
			
			stmt.executeQuery("UPDATE buildingCosts SET"
					+ " costsL1 = 10, costsL2 = 100, costsL3 = 800, costsL4 = 2500"
					+ ", costsL5 = 6000, costsL6 = 13000 WHERE building = " + Village.WALL);

			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	private void updateBuildables(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 1, quarrys = 1, mines = 1, storages = 1"
					+ ", apartments = 1, walls = 5 WHERE level = 1");
			
			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 1, quarrys = 1, mines = 1, storages = 2"
					+ ", apartments = 2, walls = 10 WHERE level = 2");
			
			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 2, quarrys = 2, mines = 2, storages = 2"
					+ ", apartments = 3, walls = 15 WHERE level = 3");
			
			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 3, quarrys = 3, mines = 3, storages = 3"
					+ ", apartments = 3, walls = 20 WHERE level = 4");

			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 4, quarrys = 4, mines = 4, storages = 3"
					+ ", apartments = 3, walls = 30 WHERE level = 5");

			stmt.executeQuery("UPDATE buildableBuildings SET"
					+ " sawmills = 5, quarrys = 5, mines = 5, storages = 4"
					+ ", apartments = 4, walls = 40 WHERE level = 6");

			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	private void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
