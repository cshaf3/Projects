/* 
Author: Caroline Shafer
Original Creation Date: 4/13/18
Last Modified Date: 3/30/18
Description: Creating a customized welcome message and asking the end user for name. This program
also allows for the end user to run the game with the option to keep playing.
*/

//These are all the imports
import java.util.*;
import java.io.BufferedReader;

public class P4A1_SHAFER_cas369
{
	public static void main(String[] args)
	{
		//This prints out the welcome message and describes the program/game
		System.out.println("Hello, I am Caroline Shafer. This program will run a game called " +
		"hangman. Hangman challenges the end user to guess a secret word. The end user can have 5 "
		+ "incorrect guesses before the game ends. The game ends if the user guesses the correct "
		+ "secret word, or if the user guesses a 6th incorrect letter. The secret word is ****");

		String secretWord = "cats";// an array of char

		System.out.println("Enter your first guess.");
		Scanner scanner = new Scanner(System.in);
		char guess = scanner.next().charAt(0);
		int wrongGuess = 0;
		char [] blankarray = new char[secretWord.length()];
		boolean correctGuess = false;

		for(int j = 0; j < blankarray.length; j++)
			{
				blankarray[j]='*';
			}
		do
		{
			for(int i = 0; i < secretWord.length(); i++)
			{
				if(guess == secretWord.charAt(i))
				{
					blankarray[i]=guess;
					System.out.println("You guessed correctly! Your secret word is now " + 
					new String(blankarray));
					correctGuess = true;
				}
			}
			if(correctGuess == false)
			{
				System.out.println("Guessed letter is not in the secret word.");
				wrongGuess++;
			}
		System.out.println("Enter another guess.");
		guess = scanner.next().charAt(0);

		} while(wrongGuess < 6);	

		System.out.println("Enter another guess.");
		guess = scanner.next().charAt(0);
	}
}	