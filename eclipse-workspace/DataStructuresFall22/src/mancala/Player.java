package mancala;

/**
 * This class manages a set of Pits
 * and one Mancala
 * @author P'nina Zidele
 */
public class Player {
	private String name;
	// player has a store / mancala
	private Mancala mancala;
	// set of pits 
	private Pit[] pits; 
	
	/**
	 * Constructor
	 * @param name Player name
	 */
	public Player(String name) {
		this.name = name;
		this.mancala = new Mancala();
		this.pits = new Pit[6];
		for (int p = 0; p < pits.length; p++) {
			pits[p] = new Pit();
		}
	}
	
	/**
	 * get pits array
	 * @return pits array
	 */
	public Pit[] getPits() {
		return pits;
	}
	
	/**
	 * get pit
	 * @param p index of wanted pit
	 * @return array[index] of the pit
	 */
	public Pit getPit(int p) {
		return pits[p];
	}
	
	/**
	 * get the index of a pit
	 * @param p Pit
	 * @return int index where the Pit is located in the Pit array
	 */
	public int getIndex(Pit p) {
		for (int i = 0; i < pits.length; i++) {
			if (p.equals(pits[i])) {
				return i;
			}
		}
		return 0; // what should we return here?
	}
	
	/**
	 * @return name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * get how many pebbles are in that pit
	 * and then set the num pebbles to 0 because we picked them all up
	 * then return that getpebbles (won't be 0 bec we stored in variable)
	 * @param p Pit you want to "pick up"
	 * @return number of pebbles in that pit
	 */
	public int pickUp(Pit p) {
		int pi = p.getPebbles();
		p.setPebbles(0);
		return pi;
	}
	
	/**
	 * @return number of pebbles in the Mancala
	 */
	public int getMancala() {
		return mancala.getPebbles();
	}
	
	/**
	 * @param num int to add to Mancala
	 */
	public void addMancalas(int num) {
		mancala.addPebbles(num);
	}
	
	/**
	 * Add one pebble to Mancala
	 */
	public void addMancala() {
		mancala.addPebble();
	}
}