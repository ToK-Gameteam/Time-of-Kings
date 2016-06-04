package game;

import java.io.Serializable;

/**
 * Class Resource
 * 
 * The class Resource creates a special type of resource (wood, stone, iron), which has a value and a limit.
 * It implements the functions to add or subtract a value, and for admins only, to set the value of the resource.
 * There also setter and getter for the limit.
 * 
 * @author Constantin Schulte
 * @version 0.1
 **/
public class Resource implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int value;
	  private int limit;

	  public Resource(){
	    value = 100;
	    limit = 100;
	  }

	  public int getValue(){
	    return value;
	  }

	  public void setValue(int value){
	    this.value = value;
	    if(value > limit){
	    	value = limit;
	    }
	  }
	  
	  public void subtract(int subtractValue){
		  value = value - subtractValue;
	  }
	  
	  public void add(int addValue){
		  value += addValue;
		  if(value > limit){
			  value = limit;
		  }
	  }
	  
	  public int getCapacity(){
		  return limit;
	  }
	  
	  public void setLimit(int limit){
		  if( value > this.limit ){
			  value = this.limit;
		  }
	  }
}
