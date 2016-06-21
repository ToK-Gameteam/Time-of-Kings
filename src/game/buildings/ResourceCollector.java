package game.buildings;

import game.Location;

/**
 * Class RessourceCollector
 * 
 * The class RessourceCollector creates a building, which has a production and is producing a special type of resource (wood, stone, iron).
 * 
 * @author Constantin Schulte
 * @version 0.0
 */
public class ResourceCollector extends Building {
	private static final long serialVersionUID = 1L;
	
	  private long lastCollected;
	  private double rest;
	  private int limit;

	  public ResourceCollector(int number, int woodCost, int stoneCost, int ironCost, Location location){
	    super(number, woodCost, stoneCost, ironCost, location);
	    lastCollected = System.currentTimeMillis();
	  }

	  public int getProduction(){
		  int production = checkProduction();
		  return production;
	  }
	  
	  public int getLimit(){
		  this.limit = checkLimit();
		  return limit;
	  }
	  
	  public int collect(){
		  this.limit = checkLimit();
		  int production = checkProduction();
		  long actualTime = System.currentTimeMillis();
		  double timeSinceLastCollected =  actualTime - lastCollected;
		  double produced = (timeSinceLastCollected*production)/(1_000*60*60) + rest;
		  if(produced > limit){
			  produced = limit;
		  }
		  lastCollected= System.currentTimeMillis();
		  rest = produced;
		  while( rest > 1 && rest != 0 ){
			  rest--;
		  }
		  System.out.println(rest);
		  return (int) produced;
	  }
	  
	  private int checkProduction(){
		  int production;
		  if(level == 1){
			  production = 5;
		  }else if(level == 2){
			  production = 10;
		  }else if(level == 3 ){
			  production = 25;
		  }else if(level == 4){
			  production = 100;
		  }else{
			  production = 500;
		  }
		  return production;
	  }
	  
	  private int checkLimit(){
		  int limit;
		  if(level == 1){
			  limit = 50;
		  }else if(level == 2){
			  limit = 100;
		  }else if(level == 3){
			  limit = 250;
		  }else if(level == 4){
			  limit = 1_000;
		  }else if(level == 5){
			  limit = 5_000;
		  }else if(level == 6){
			  limit = 25_000;
		  }else{
			  limit = 100_000;
		  }
		  return limit;
	  }
}
