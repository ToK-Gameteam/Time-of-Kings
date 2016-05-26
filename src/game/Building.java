package game;

/**
 * Class Building
 * 
 * The class Building creates a building, which has a level (at the beginning 1), a number (depending on the number of buildings of this type build before),
 * a location saved in a java.awt.Point, and allows to level the building up and to change the location.
 * 
 * @author Constantin Schulte
 * @version 0.0
 */

import java.io.Serializable;

 abstract class Building implements Serializable{
	protected static final long serialVersionUID = 1L;

	  protected final int number;
	  protected int level;
	  protected java.awt.Point location;
	  protected int woodCost;
	  protected int stoneCost;
	  protected int ironCost;
	  protected int hitpoints;
	  
	  protected Building(int woodCost, int stoneCost, int ironCost, int number, int locationX, int locationY){
	    level = 1;
	    location = new java.awt.Point(locationX, locationY);
	    this.number = number;
	    hitpoints = 50;
	  }



	  /**
	   * int[] levelUp(int, int, int)
	   * 
	   * Levels a building up.
	   */
	  public int[] levelUp(int wood, int stone, int iron){
			  if(level == 1){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 2;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*2;
					  this.stoneCost = stoneCost*2;
					  this.ironCost = ironCost*2;
					  this.hitpoints = 100;
					  return costs;
				  }else{
					  System.err.println("Upgrade fehlgeschlagen");
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
			  }else if(level == 2){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 3;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*3;
					  this.stoneCost = stoneCost*3;
					  this.ironCost = ironCost*3;
					  this.hitpoints = 250;
					  return costs;
				  }else{
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
			  }else if(level == 3){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 4;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*4;
					  this.stoneCost = stoneCost*4;
					  this.ironCost = ironCost*4;
					  this.hitpoints = 500;
					  return costs;
				  }else{
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
			  }else if(level == 4){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 5;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*5;
					  this.stoneCost = stoneCost*5;
					  this.ironCost = ironCost*5;
					  this.hitpoints = 1000;
					  return costs;
				  }else{
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
			  }else if(level == 5){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 6;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*10;
					  this.stoneCost = stoneCost*10;
					  this.ironCost = ironCost*10;
					  this.hitpoints = 5000;
					  return costs;
				  }else{
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
			  }else if(level == 6){
				  if(wood >= woodCost && stone >= stoneCost && iron >= ironCost){
					  level = 7;
					  int[] costs = {woodCost, stoneCost, ironCost};
					  this.woodCost = woodCost*10;
					  this.stoneCost = stoneCost*10;
					  this.ironCost = ironCost*10;
					  this.hitpoints = 10000;
					  return costs;
				  }else{
					  int[] costs = {0, 0, 0};
					  return costs;
				  }
				  
			  }else{
				  int[] costs = {0, 0, 0};
				  return costs;
			  }
	  }
	  
	  /**
	   * java.awt.Point getLocation()
	   * 
	   * Returns the location of a building.
	   */
	  public java.awt.Point getLocation(){
	      return location;
	  }
	  
	  
	  /**
	   * int getLevel()
	   * 
	   * Returns the level of a building.
	   */
	  public int getLevel(){
	      return level;
	  }
	  
	  /**
	   * void moveBuilding(int, int)
	   * 
	   * Moves a building to the new Points X and Y.
	   */
	  public void moveBuilding(int newX, int newY){
		  location.x = newX;
		  location.y = newY;
	  }
	  
	  public int getHitpoints(){
		  return hitpoints;
	  }
}
