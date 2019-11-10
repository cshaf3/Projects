/* 
Author: Caroline Shafer
Original Creation Date: 3/22/18
Last Modified Date: 3/30/18
Description: Creating a customized welcome message and asking the end user for name.
*/

//These are all the imports
import java.util.Scanner;
import java.io.BufferedReader;

public class P3A2_SHAFER_cas369
{
	public static void main(String[] args)
	{
		//This prints out the request for the user to enter their name
		System.out.println("Please enter your name.");

		//This scanner allows for user input
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		//This prints out the welcome message and describes the program/game
		System.out.println("Hello, " + name + ". I am Caroline Shafer. This is a dice game each roll "
		+ "uses four die = 4 dice. The rules are as follows: A new player begins with his first dice "
		+ "roll (4 die = 1 set of dice). This is known as the 'starting roll.' If the player rolls a "
		+ "6, 12, 18, or 24 the player wins. If the player rolls a 9, 11, 17, 19, or 23 the player "
		+ "loses. If the player rolls any other number, the 'starting roll' becomes the 'goal number.'"
		+ "The player must continue to roll the dice until either one of two things happens: The "
		+ "player rolls a 11, and he loses. The player rolls the 'goal number' again, and he wins.");
	}
}