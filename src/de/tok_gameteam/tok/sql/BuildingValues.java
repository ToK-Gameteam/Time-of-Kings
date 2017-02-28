package de.tok_gameteam.tok.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.tok_gameteam.tok.village.Village;

public class BuildingValues {
	private Connection con;
	private Statement stmt;
	private String path;

	public BuildingValues(){
		path = System.getProperty("user.dir")
				+ File.separator + "ToK" + File.separator + "TokDB";
		initialize();
	}
	
	private void initialize(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS buildingCosts("
					+ "building INT NOT NULL PRIMARY KEY, "
					+ "costsL1 INT NOT NULL, "
					+ "costsL2 INT NOT NULL, "
					+ "costsL3 INT NOT NULL, "
					+ "costsL4 INT NOT NULL, "
					+ "costsL5 INT NOT NULL, "
					+ "costsL6 INT NOT NULL)");
			
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS buildingValues("
					+ "building INT NOT NULL PRIMARY KEY, "
					+ "valueL1 INT NOT NULL, "
					+ "valueL2 INT NOT NULL, "
					+ "valueL3 INT NOT NULL, "
					+ "valueL4 INT NOT NULL, "
					+ "valueL5 INT NOT NULL, "
					+ "valueL6 INT NOT NULL)");
			System.out.println("jo");
			ResultSet valueExists = stmt.executeQuery("SELECT * FROM buildingCosts");
			
			if(! valueExists.next()){
				insertBuildingCosts();
				insertBuildingValues();
			}
			
			valueExists.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	private void insertBuildingCosts(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			stmt.executeQuery("INSERT INTO buildingCosts VALUES("
					+ Village.COMMUNITY_HALL + ", 0, 1000, 15000, 50000, 125000, 625000)");
			
			stmt.executeQuery("INSERT INTO buildingCosts VALUES("
					+ Village.SAWMILL + ", 100, 200, 700, 2500, 8000, 20000)");
			
			stmt.executeQuery("INSERT INTO buildingCosts VALUES("
					+ Village.STORAGE + ", 100, 500, 2000, 8000, 20000, 50000)");
			
			stmt.executeQuery("INSERT INTO buildingCosts VALUES("
					+ Village.APARTMENT + ", 100, 300, 1000, 3100, 10000, 20000)");
			
			stmt.executeQuery("INSERT INTO buildingCosts VALUES("
					+ Village.WALL + ", 10,  100,  800, 2500, 6000, 13000)");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	public void insertBuildingValues(){
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			stmt.executeQuery("INSERT INTO buildingValues VALUES("
					+ Village.SAWMILL + ", 85, 89, 94, 100, 107, 115)");
			
			stmt.executeQuery("INSERT INTO buildingValues VALUES("
					+ Village.STORAGE + ", 3000, 9000, 30000, 120000, 300000, 750000)");
			
			stmt.executeQuery("INSERT INTO buildingValues VALUES("
					+ Village.APARTMENT + ", 100, 500, 2000, 8000, 20000, 50000)");
			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
	}
	
	public int[][] getBuildingCosts(){
		int[][] buildingCosts = new int[7][6];
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			ResultSet rsltUpgradeCost = stmt.executeQuery("SELECT * FROM buildingCosts ORDER BY building");
			int index = 0;
			while(rsltUpgradeCost.next()){
				for(int level = 0; level < 6; ++level){
					buildingCosts[index][level] = rsltUpgradeCost.getInt(level+1);
				}
				++index;
			}
			
			rsltUpgradeCost.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return buildingCosts;
	}
	
	public int[][] getBuildingValues(){
		int[][] buildingValues = new int[3][6];
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "sa", "");
			stmt = con.createStatement();
			
			ResultSet rsltValues = stmt.executeQuery("SELECT * FROM buildingValues ORDER BY building");
			int index = 0;
			while(rsltValues.next()){
				for(int level = 0; level < 6; ++level){
					buildingValues[index][level] = rsltValues.getInt(level+1);
				}
				++index;
			}
			
			rsltValues.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection();
		}
		
		return buildingValues;
	}
	
	private void closeConnection(){
		try{
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
