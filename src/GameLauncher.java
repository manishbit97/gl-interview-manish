import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameLauncher {

	public static void main(String[] args) {

		int n = 0, m = 0, i = 0;
		boolean isrunning = true, isCorrectValue = false;
		Scanner in = new Scanner(System.in);

		while (!isCorrectValue) {
			try {
				System.out.println("Enter no of players __");
				n = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Wrong input for No of players ! Retry !");
				continue;
			}
			if (n <= 0) {
				continue;
			}
			isCorrectValue = true;
		}
		isCorrectValue = false;
		while (!isCorrectValue) {
			try {
				System.out.println("Enter max score to reach -");
				m = Integer.parseInt(in.nextLine());
			} catch (Exception e) {
				System.out.println("Wrong input for max score. Please retry");
				continue;
			}
			if (m <= 0) {
				continue;
			}
			isCorrectValue = true;

		}

		Player[] playerList = new Player[n];
		List<Player> winList = new ArrayList<Player>();

		// Initializing object
		for (i = 0; i < playerList.length; i++) {
			playerList[i] = new Player(i + 1 + "", m);
		}

		Collections.shuffle(Arrays.asList(playerList));
		System.out.println("Shuffled array is =");
		displayPlayerList(playerList);

		while (isrunning) {
			// check if game is over , then set isrunning = false
			if (winList.size() == playerList.length) {
				isrunning = false;
			}
			Player currentPlayer = playerList[i++ % n];
			boolean reroll = true;
			// check if game for pl[i] is complete
			if (currentPlayer.hasWon()) {
				continue;
			}

			if (currentPlayer.hasOneOccuredTwoTimes()) {
				System.out.println(
						"\'1\' occured in last two chances ! Skipping this round for " + currentPlayer.getName());
				currentPlayer.insertInLastTwo(0);
				continue;
			}

			while (reroll == true) {
				System.out.println("Enter r to roll a dice for player named ->" + currentPlayer.getName());

				String rollInp = "x";
				try {
					rollInp = in.next();
				} catch (Exception e) {
				}

				if (rollInp.equals("r")) {
					System.out.print("Rolling the dice :::: ");
					int rolledNo = currentPlayer.roll();
					System.out.println("Player " + currentPlayer.getName() + " has got " + rolledNo);
					currentPlayer.addScoreAndAppendArray(rolledNo);

					boolean won = currentPlayer.hasWon();
					if (won) {
						currentPlayer.setWon(true);
						winList.add(currentPlayer);
						System.out.printf("\n ----> Hurray ! Player \'" + currentPlayer.getName()
								+ "\' has completed the game with RANK = %d <-----\n\n", winList.size());
						reroll = false;
						continue;

					}

					if (rolledNo == 6) {
						System.out.println(".....You got a six ! And you get a chance to Re-Roll !.......");
						reroll = true;
					} else {
						reroll = false;
					}

				} else {
					System.out.println("Try Again ! Enter r to roll a dice");
				}

			}

			System.out.println("\nDisplaying Individual scores table...\n");

			displayPlayerList(playerList);
		}

		System.out.println("\nWinning list final in order of RANKING");
		for (int j = 0; j < winList.size(); j++) {
			System.out.println("RANK " + (j + 1) + " is " + winList.get(j).toString());

		}
		System.out.println("THANKS !");
		in.close();

	}

	public static void displayPlayerList(Player[] playerList) {
		for (Player player : playerList) {
			System.out.println(player.toString());
		}
	}

}
