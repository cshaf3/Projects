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

public class P3A3_SHAFER_cas369
{
	//roll method
	public static int roll()
	{
		P3A3_SHAFER_DIE_cas369 firstDie = new P3A3_SHAFER_DIE_cas369();
		P3A3_SHAFER_DIE_cas369 secondDie = new P3A3_SHAFER_DIE_cas369();
		P3A3_SHAFER_DIE_cas369 thirdDie = new P3A3_SHAFER_DIE_cas369();
		P3A3_SHAFER_DIE_cas369 fourthDie = new P3A3_SHAFER_DIE_cas369();

		firstDie.setRoll();
		secondDie.setRoll();
		thirdDie.setRoll();
		fourthDie.setRoll();

		int value = firstDie.getValue() + secondDie.getValue() + thirdDie.getValue() + fourthDie.getValue();

		return value;
	}

	public static void main(String[] args)
	{
		Random generator = new Random();
		String rollAgain = "";

		//initializes variables
		int goalVal = 0;
		int numberWin = 0;
		int numberLose = 0;
		int rounds;
		int goal = 0;

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
		+ "player rolls a 11, and he loses. The player rolls the 'goal number' again, and he wins."
		+ "If you want to play enter 1, if you want the computer to play enter 2.");

		//takes in consideration users choice
		int userChoice = scanner.nextInt();
		if(userChoice == 1)
		{
			String playAgain;

			//do-while allows for multiple win/lose scenarios
			do
				{
					goalVal = 0;
					goalVal = roll();
					System.out.println("Roll Value: " + goalVal);

					if((goalVal == 6) || (goalVal == 12) || (goalVal == 18) || (goalVal == 24))
					{
						System.out.println("You win!");
					}
					else if((goalVal == 9) || (goalVal == 11) || (goalVal == 17) || (goalVal == 19) || (goalVal == 23))
					{
						System.out.println("You lose!");
					}
					else
					{
						goal = goalVal;
						goalVal = 0;
						boolean finish = false;
						System.out.println("Your starting roll is now your goal number.");
						do
						{
							goalVal = roll();
							System.out.println("Roll Value: " + goalVal);

							if(goalVal == goal)
							{
								System.out.println("You win!");
								finish = true;
							}
							else if(goalVal == 11)
							{
								System.out.println("You lose!");
								finish = true;
							}
							else
							{
								goalVal = 0;
							}
						} while(!finish);
					}
					
				System.out.println("Would you like to play again? (y/n)");
				 playAgain = scanner.next();
				}
				while (playAgain.equals("y"));
		}
		
		//computer game code
		else if(userChoice == 2)
		{
			System.out.println("Enter how many times do you want the computer to play the game?");
			int numberOfRounds = scanner.nextInt();

			for(int i = 0; i < numberOfRounds; i++)
			{
				goalVal = roll();
				System.out.println("Roll Value: " + goalVal);

				if((goalVal == 6) || (goalVal == 12) || (goalVal == 18) || (goalVal == 24))
				{
					numberWin++;
					System.out.println("You win!");
				}
				else if((goalVal == 9) || (goalVal == 11) || (goalVal == 17) || (goalVal == 19) || (goalVal == 23))
				{
					numberLose++;
					System.out.println("You lose!");
				}
				else
				{
					goal = goalVal;
					goalVal = 0;
					boolean finish = false;
					System.out.println("Your starting roll (" + goal +") is now your goal number.");
					do
					{
						goalVal = roll();
						System.out.println("Roll Value: " + goalVal);

						if(goalVal == goal)
						{
							numberWin++;
							System.out.println("You win!");
							finish = true;
						}
						else if(goalVal == 11)
						{
							numberLose++;
							System.out.println("You lose!");
							finish = true;
						}
						else
						{
							goalVal = 0;
						}
					}

					while(!finish);	

					System.out.println("Number of wins: " + numberWin + "\nNumber of loses: " + numberLose);				
				}
			}
		}
	}
}