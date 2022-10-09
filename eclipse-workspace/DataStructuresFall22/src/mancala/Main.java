package mancala;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Get user input
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Welcome to the Mancala Game! "
				+ "\nPlease enter the names of both players. Initials work best."
				+ "\nPlease enter the name of the first player: ");
		String player1 = keyboard.nextLine();
		System.out.print("Please enter the name of the second player: ");
		String player2 = keyboard.nextLine();

		// create the Players
		Player p1 = new Player(player1);
		Player p2 = new Player(player2);
		// create a mancala game
		MancalaGame mancala = new MancalaGame(p1, p2);

		Player currPlayer = p1;
		while (mancala.checkWinner() == false) {
			mancala.displayBoard(currPlayer);
			// whose turn is it
			System.out.print("\n\nWhich pit would you like to take from? Enter the number: ");
			int pit = keyboard.nextInt() - 1;
			while (pit < 0 || pit > 6) {
				System.out.print("Invalid entry. Enter the pit number: ");
				pit = keyboard.nextInt() - 1;
			}

			// current player gets switched in take turn method
			currPlayer = mancala.takeTurn(pit, currPlayer);
		}
		Player winner = mancala.findWinner();
		if (winner.equals(p1) || winner.equals(p2)) {
			System.out.println("The winner is " + winner.getName());
			System.out.println(winner.getName() + "'s Mancala: " + winner.getMancala());
			System.out.println(mancala.lost().getName() + "'s Mancala: " + mancala.lost().getMancala());
		}
		else {
			System.out.println("It's a tie!");
		}
	}
}