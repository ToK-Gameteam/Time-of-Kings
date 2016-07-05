package game.village;

import java.io.Serializable;

/**
 * The class Resource creates a special type of resource (wood, stone, iron), which has a value and a limit.
 * It implements the functions to add or subtract a value, and for admins only, to set the value of the resource.
 * There also setter and getter for the limit.
 * 
 * @author Constantin Schulte
 * @version 0.2
 **/
public class Resource implements Serializable {
	static final long serialVersionUID = 1;
	
	private final String type;
	private int value;
	private int limit;
	
	public Resource( String type, int limit ){
		this.type = type;
		this.limit = limit;
		value = 100;
	}
	
	public String getType(){
		return type;
	}
	
	public int getValue(){
		return value;
	}
	
	public int getLimit(){
		return limit;
	}
	
	public void setValue( int value ){
		if( value > limit){
			value = limit;
		}
		this.value = value;
	}
	
	public void addValue( int value ){
		this.value += value;
		if( this.value > limit ){
			this.value = limit;
		}
	}
	
	public void subtractValue( int value ){
		this.value -= value;
		if( this.value < 0 ) {
			this.value = 0;
		}
	}
	
	public void setLimit( int limit ){
		this.limit = limit;
		if( value > limit){
			value = limit;
		}
	}
}
