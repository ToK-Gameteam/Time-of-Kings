package game;

/*
 * Class Admin
 * 
 * The class admin creates a special type of player, who can set the values of his resources.
 * 
 * @author Constantin Schulte
 * @version 0.0
 */

class Admin extends Player {
	private static final long serialVersionUID = 1L;

	public Admin(String name){
		    super(name);
		  }

	  /*
	   * void setValue(int, int, int)
	   * 
	   * Sets the values of the three resource types to a specific value, only available for admins for testing.
	   */
	  public void setValue(int newWood, int newStone, int newIron){
		  distributor.setValues(newWood, newStone, newIron);
	  }
}
