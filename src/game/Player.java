package game;

import java.io.Serializable;

/*
 * Class Player
 * 
 * The class Player creates a player, which has a name (3 - 16 numbers) and a random ID between 0 and 99,999.
 * It implements functions to print Details (name, ID, how much of which resource), and functions to give to its distributor,
 * for example collecting resources or build Buildings, to draw the GUI or to move or level up a building.
 * 
 * @author Constantin Schulte
 * @version 0.0
 */
abstract class Player implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String name;
	  protected final int playerID;
	  protected Distributor distributor;

	  public Player(String name){
	    if(name.length() >= 3 && name.length() <= 16){
	      this.name = name;
	    }else{
	      System.out.println("Name zu kurz/ lang");
	      while(this.name == ""){
	        name = new java.util.Scanner(System.in).next();
	        if(name.length() >= 3 && name.length() <= 16){
	          this.name = name;
	        }else{
	          System.out.println("Wieder zu kurz/ lang.");
	        }
	      }
	    }
	    playerID = (int) (Math.random() * 100_000);
	    distributor = new Distributor();
	    Tutorial.startTutorial(distributor);
	  }

	  public void printDetails(){
	    System.out.println("Name: " + name + ", User-ID: " + playerID);
	    distributor.printDetails();
	  }

	  public void collect(){
	    distributor.collect();
	  }
	  
	  public void buildLumbermill(int locationX, int locationY){
	    distributor.buildLumbermill(locationX, locationY);
	  }
	  
	  public void buildQuarry(int locationX, int locationY){
	    distributor.buildQuarry(locationX, locationY);
	  }
	  
	  public void buildMine(int locationX, int locationY){
	    distributor.buildMine(locationX, locationY);
	  }
	  
	  public void buildStorage(int locationX, int locationY){
		    distributor.buildStorage(locationX, locationY);
		  }
	  
	  public void buildApartment(int locationX, int locationY){
		  distributor.buildApartment(locationX, locationY);
	  }
	  
	  public void moveBuilding(String building, int newX, int newY){
		  distributor.moveBuilding(building, newX, newY);
	  }
	  
	  public void draw(){
		  distributor.draw();
	  }
	  
	  public void levelUp(String buildingToUpgrade, int number){
		  distributor.levelUp(buildingToUpgrade, number);
	  }
}
