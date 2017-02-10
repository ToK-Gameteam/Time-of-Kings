package game.sql;

import java.sql.*;

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
			//TODO
			stmt.executeQuery("CREATE TABLE IF NOT EXISTS player("
					+ "id INT NOT NULL PRIMARY KEY "
					+ "GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
					+ "name VARCHAR(255) NOT NULL, "
					+ "level int NOT NULL)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void enterPlayer(String name){
		try {
			stmt = con.createStatement();
			
			//TODO
			stmt.close();
		} catch (SQLException e) {
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
