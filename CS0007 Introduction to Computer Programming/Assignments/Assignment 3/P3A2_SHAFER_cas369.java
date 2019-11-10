/* 
Author: Caroline Shafer
Original Creation Date: 3/22/18
Last Modified Date: 3/30/18
Description: Creating a customized welcome message and asking the end user for name. This program
also allows for the end user to run the game with the option to keep playing.
*/

//These are all the imports
import java.util.*;
import java.io.BufferedReader;

public class P3A2_SHAFER_cas369
{
	public static void main(String[] args)
	{
		//initializes variables
		Random generator = new Random();
		String rollAgain = "";
		int goal;

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

		//do-while allows for multiple win/lose scenarios
		do
		{
			int counter = 0;

			for(int i = 0; i < 4; i++)
			{
				int roll = generator.nextInt(6) + 1;
				counter = counter + roll;
				System.out.println("You rolled: " + roll);
			}
			System.out.println(counter);
			//win scenario
			if((counter == 6) || (counter == 12) || (counter == 18) || (counter == 24))
			{
				System.out.println("You win!");
			}
			//lose scenario
			else if((counter == 9) || (counter == 11) || (counter == 17) || (counter == 19) || (counter == 23))
			{
				System.out.println("You lose!");
			}
			//goal number scenario
			else
			{
				goal = counter;
				counter = 0;
				boolean finish = false;
				System.out.println("Your starting roll is now your goal number.");
				do
				{
					for(int i = 0; i < 4; i++)
					{
						int roll = generator.nextInt(6) + 1;
						counter = counter + roll;
						System.out.println("You rolled: " + roll);
					}
					System.out.println(counter);

					if(counter == goal)
					{
						System.out.println("You win!");
						finish = true;
					}
					else if(counter == 11)
					{
						System.out.println("You lose!");
						finish = true;
					}
					else
					{
						counter = 0;
					}
				}

				while(!finish && rollAgain.equals("y"));
			}

			System.out.println("Would you like to roll again? (y/n)");
			rollAgain = scanner.nextLine();
		}

		while (rollAgain.equals("y"));
	}
}