package mancala;
/**
 * @author P'nina Zidele
 * This class is managing the pebbles in a store
 */
public class Mancala extends Pit {
	
	// pebbles in a mancala
	// has the pebbles field from the parent class

	/**
	 * Mancala constructor, calls parent constructor, passing it 0
	 */
	public Mancala() {
		super(0);
	}

	/**
	 * get the pebbles from a Pit, calls parent class
	 */
	public int getPebbles() {
		return super.getPebbles();
	}

	
	/**
	 * Sets pebbles in Pit to desired int, call parent class
	 */
	public void setPebbles(int p) {
		super.setPebbles(p);
	}
	
	/**
	 * Add one pebble, call parent class
	 */
	public void addPebble() {
		super.addPebble();
	}

	/**
	 * Add pebbles, call parent class
	 * @param num Desired int to add 
	 */
	public void addPebbles(int num) {
		super.addPebbles(num);
	}
}