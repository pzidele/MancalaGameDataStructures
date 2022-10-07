package mancala;

import java.util.Scanner;


/**
 * This class manages 2 Players
 * provide view of board while game is in progress
 * determines winner
 * manages Player turn
 * @author P'nina Zidele
 */
/**
 * @author P'nina Zidele
 *
 */
public class MancalaGame {
	private Player player1;
	private Player player2;
	private String[] boardLabels = new String[] {"Pit1", "Pit2", "Pit3", "Pit4", "Pit5", "Pit6"};
	private boolean hasWinner;
	private Player[] players;
	private Player winner;
	private Player isEmpty;
	private int numPebbles;
	private boolean playerSide = true;

	/**
	 * @param player1 First Player
	 * @param player2 Second Player
	 */
	public MancalaGame(Player player1, Player player2) {
		// in main enter names of players, and pass it to mancala game
		this.player1 = player1;
		this.player2 = player2;
		this.players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		//this.boardLabels = boardLabels; // DO I NEED THIS?
		this.hasWinner = false;
	}

		
	/**
	 * Uses the rules:
	 * If you run into your own store, deposit one piece in it.  
	 * If the last piece you drop is in your own store, you get a free turn.
	 * If the last piece you drop is in an empty hole on your side, you capture that piece and any 
	 * pieces in the hole directly opposite, and put it into your Mancala
	 * Always place all captured pieces in your store.
	 * @param p which pit to pick up
	 * @param player current Player
	 * @return Player player who goes next
	 */
	public Player takeTurn(int p, Player player) {	
		Player p2;
		Pit[] pitsArray = player.getPits();
		Pit[] pitsArray2 = null;
		Pit pit = player.getPit(p);
		int numPebbles = player.pickUp(pit); // also sets pit to 0
		Pit currPit = pitsArray[p];
		Mancala manc = new Mancala();

		//	int currIndex = player.getIndex(currPit);
		// don't think I need it, not used in the end
		boolean go;


		if (player.equals(player1)) {
			p2 = player2;
		} else {
			p2 = player1;
		}		

		// deposit pebbles around the board starting on your side
		// have to keep track of:
		// how many pieces are still available to give out
		// what pit you're up to 
		// number of pebbles left in your hand
		while (numPebbles > 0) {
			for (int i = p+1; i < pitsArray.length; i++) {
				if (numPebbles != 0) {
					numPebbles--;
					pitsArray[i].addPebble();
					currPit = pitsArray[i];
				}
			}
			if (numPebbles != 0) {
				// deposit one in mancala
				player.addMancala();
				numPebbles--;
				currPit = manc;
			}

			// now move to other side of the board
			if (numPebbles != 0) {
				pitsArray2 = p2.getPits();
				for (int i = 0; i < pitsArray2.length; i++) {
					if (numPebbles != 0) {
						numPebbles--;
						pitsArray2[i].addPebble();
						currPit = pitsArray2[i];
						playerSide = false;
					}
				}
			}
			// now we deal with the last piece: currPit
			go = checkContinue(currPit, manc, player, pitsArray2);
			if (go == true) {
				return player; // player goes again, landed in mancala
			}

		}
		return p2; // other player goes
	}

	/**
	 * Check if it landed in mancala or not
	 * @param currPit The pit you are up to in the array
	 * @param manc Current players Mancala
	 * @param player Current Player
	 * @param pitsArray2 Array of pits of the other player
	 * @return boolean true if the current player goes again, otherwise returns false
	 */
	public boolean checkContinue(Pit currPit, Mancala manc, Player player, Pit[] pitsArray2) {
		// now we deal with the last piece: currPit
		if (currPit != manc) {
			// if on your side, the current pit without the one we just deposited is 0,
			// then you get that last piece, and also the one from the pit across
			if (currPit.getPebbles() -1 == 0 && playerSide == true) {     
				int index = player.getIndex(currPit);
				// set the one across to 0, use the index  
				player.addMancalas(pitsArray2[index].getPebbles()+1);
				currPit.setPebbles(0);
				pitsArray2[index].setPebbles(0);
				return false; // don't get another turn
			} 
			else {
				return false; // next player goes
			}
		}

		return true; // player goes again

	}

	/**
	 * The game ends when all six spaces on one side of the Mancala board are empty.
	 * The player who still has pieces on his side of the board when the game ends captures all of those pieces.
	 * @return boolean value true if there is a winner, false if there is no winner
	 */
	public boolean checkWinner() {
		int sum1 = 0;
		int sum2 = 0;
		for (int i = 0; i < player1.getPits().length; i++) {
			if (player1.getPits()[i].getPebbles() != 0) {
				sum1 += player1.getPits()[i].getPebbles();
			}
		}
		for (int i = 0; i < player2.getPits().length; i++) {
			if (player2.getPits()[i].getPebbles() != 0) {
				sum2 += player2.getPits()[i].getPebbles();
			}
		}
		if (sum1 == 0 || sum2 == 0) {
			hasWinner = true;
			if (sum1 == 0) {
				isEmpty = player1;
				collectPieces(player2);
			}
			else {
				isEmpty = player2;
				collectPieces(player1);
			}
			// player who still has pieces on his side gets all their pieces

			return true;
		}
		return false;
	}

	/**
	 * collect the pieces of the player at the end of the game!
	 * @param p Player who's pieces you are collecting
	 */
	public void collectPieces(Player p) {
		Pit[] pits = p.getPits();
		for (int i = 0; i < pits.length; i++) {
			p.addMancalas(pits[i].getPebbles());
		}
	}

	/**
	 * @param p The Player who's perspective you want to be displayed
	 */
	public void displayBoard(Player p) {
		// from 2 different perspectives: player 1, and player 2
		Pit[] pits1;
		Pit[] pits2;
		Player p2;

		if (p.equals(player1)) {
			pits1 = player1.getPits();
			pits2 = player2.getPits();
			p2 = player2;
		}
		else {
			pits1 = player2.getPits();
			pits2 = player1.getPits();
			p2 = player1;  // the other player
		}

		System.out.println();
		System.out.println(p.getName() + "'s perspective");
		// other players side: 
		System.out.print(p2.getName() + "'s Mancala    ");
		for (int i = boardLabels.length - 1; i >= 0; i--) {
			System.out.print(boardLabels[i] + "   ");
		}

		// print perspective mancala label
		System.out.print(p.getName() + "'s Mancala    ");
		System.out.println();
		// make space for the mancala label
		System.out.print("                 ");
		// array of pits from players
		for (int i = boardLabels.length - 1; i >= 0; i--) {
			System.out.print(pits2[i].getPebbles() + "      ");
		}
		// print players mancala num
		System.out.print("\n     " + p2.getMancala() + "                                                           " + p.getMancala());

		// players perspective side: 
		System.out.println();
		// make space for mancala label
		System.out.print("                 ");
		for (int i = 0; i < boardLabels.length; i++) {
			System.out.print(pits1[i].getPebbles() + "      ");
		}
		// print perspectives mancala num
		System.out.print("\n" + p2.getName() + "'s Mancala    ");

		for (int i = 0; i < boardLabels.length; i++) {
			System.out.print(boardLabels[i] + "   ");
		}
		// print perspective mancala label
		System.out.print(p.getName() + "'s Mancala    ");
	}
 
	/**
	 * Count all the pieces in each store. The winner is the player with the most pieces.
	 * @return Player returns the player who won the game
	 */
	public Player findWinner() {
		if (player1.getMancala() > player2.getMancala()) {
			winner = player1;
		}
		else if (player1.getMancala() < player2.getMancala()) {
			winner = player2;
		}
		else {
			System.out.println("It's a tie!");
		}
		return winner;
	}
	
	
	/**
	 * @return boolean value if there is a winner
	 */
	public boolean getHasWinner() {
		return hasWinner;
	}

}