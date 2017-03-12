package de.tok_gameteam.tok.village;

/**
 * The class Resource creates a special type of resource (wood, stone, iron), which has a value and a limit.
 * It implements the functions to add or subtract a value.
 * There also setter and getter for the limit.
 * 
 * @author Constantin Schulte
 * @version 1.0
 */
public class Resource {
	
	/**
	 * The type of the Resource, e.g. wood, stone or iron.
	 */
	private final String type;
	
	/**
	 * The stored value of the resource right now.
	 */
	private int value;
	
	/**
	 * The capacity of the village to store this resource.
	 */
	private int limit;
	
	/**
	 * Basic constructor for creating a resource the same time when creating a new village.
	 * 
	 * @param type : The type of the resource.
	 */
	public Resource( String type ){
		this.type = type;
		limit = 1000;
		value = 1000;
	}
	
	/**
	 * The constructor for an already stored resource, limit is set shortly after creating the resource.
	 * 
	 * @param type : The type of the Resource.
	 * @param value : The value of the Resource right now.
	 */
	public Resource( String type, int value){
		this.type = type;
		limit = 0;
		this.value = value;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The type of the Resource.
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The stored value of this resource right now.
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Basic getter.
	 * 
	 * @return The limit of the Resource to be stored in the Village.
	 */
	public int getLimit(){
		return limit;
	}
	
	/**
	 * Adds the value to the stored one and checks whether the new value is too high for the capacity.
	 * 
	 * @param value : The value to be added to the Resource.
	 */
	public void addValue( int value ){
		this.value += value;
		if( this.value > limit ){
			this.value = limit;
		}
	}
	
	/**
	 * Removes the value from the stored one.
	 * 
	 * @param value : The value to be removed from the Resource.
	 */
	public void subtractValue( int value ){
		this.value -= value;
	}
	
	/**
	 * Sets the limit and checks whether the value is too high for the capacity.
	 * 
	 * @param limit : The new limit of the Resource.
	 */
	public void setLimit( int limit ){
		this.limit = limit;
		if( value > limit){
			value = limit;
		}
	}
}
