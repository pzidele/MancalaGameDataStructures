package mancala;

/**
 * This class is managing the pebbles in a Pit
 * @author P'nina Zidele
 */
public class Pit {
	private int pebbles;
	
	/**
	 * Constructor starts off with 4 pebbles in each pit
	 */
	public Pit() {
		this.pebbles = 4;
	}
	
	/**
	 * constructor to accept a number (used for the mancalas)
	 * @param num Number to set Pit to 
	 */
	public Pit(int num) {
		this.pebbles = num;
	}
	
	/**
	 * add one pebble to a Pit
	 */
	public void addPebble() {
		this.pebbles++;
	}
	
	/**
	 * get the pebbles from a pit
	 * @return number of pebbles in a pit
	 */
	public int getPebbles() {
		return pebbles;
	}
	
	/**
	 * set pebbles in a pit to desired int
	 * @param p int to set pebbles to
	 */
	public void setPebbles(int p) {
		this.pebbles = p;
	}
}