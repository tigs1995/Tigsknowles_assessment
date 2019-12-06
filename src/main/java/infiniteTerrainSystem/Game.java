package infiniteTerrainSystem;

import java.util.Random;
import java.util.Scanner;

public class Game implements Computers, Player {

	private boolean loop = true;
	private String direction = null;
	private String distance;
	private int distance2;
	private int xAxisComputer;
	private int yAxisComputer;
	private int yAxisPlayer = 0;
	private int xAxisPlayer = 0;
	private double distanceToGo;
	private int incorrectDirection = 0;

	
	public void randomInt() {
		do {
			this.xAxisComputer = new Random().nextInt(10);
			this.yAxisComputer = new Random().nextInt(10);
		} while (this.xAxisComputer == 0 && this.yAxisComputer == 0);
	}
	
	public void updatePlayerPosition() {
		switch (direction.toLowerCase()) {
		
		case "north":
			this.yAxisPlayer += distance2;
			break;
		case "south":
			this.yAxisPlayer -= distance2;
			break;
		case "east":
			this.xAxisPlayer += distance2;
			break;
		case "west":
			this.xAxisPlayer -= distance2;
			break;
		case "exit":
			this.loop = false;
			break;
		default:
			this.incorrectDirection = 1;
		}
		
	}
	
	public void playerInp() {
		Scanner scanDistance = new Scanner(System.in);
		
		randomInt();

		System.out.println(xAxisComputer);

		System.out.println(yAxisComputer);

		System.out.println("Welcome to the game. You are starting at point 0,0.");
		while (loop == true) {

			try {

				System.out.println("Pick a distance between 1 and 20 metres.");
				distance = scanDistance.nextLine();

				distance2 = Integer.parseInt(distance);

				System.out.println("Pick a direction - North, South, East or West.");
				direction = scanDistance.nextLine();

				updatePlayerPosition();

				if(incorrectDirection == 1) {
					System.out.println("Incorrect format. Please try again.");
				}
				else if (loop == false) {
					System.out.println("Thank you for playing. Goodbye.");
				}
				else if (xAxisPlayer == xAxisComputer && yAxisPlayer == yAxisComputer) {
					System.out.println("You have reached your goal! Congratulations you are a winner!!!");
					this.loop = false;
				} else {
					int distanceBetweenX = this.xAxisComputer - this.xAxisPlayer;
					int distanceBetweenY = this.yAxisComputer - this.yAxisPlayer;
					this.distanceToGo = Math.hypot(distanceBetweenX, distanceBetweenY);
					System.out.println("The linear line between you and the end point is " + distanceToGo + " metres.");
				}
			} catch (NumberFormatException numberexception) {
				System.out.println("Incorrect format entered. Please try again.");
			} finally {

				System.out.println();
				incorrectDirection = 0;
			}

		}
		scanDistance.close();

	}
}
