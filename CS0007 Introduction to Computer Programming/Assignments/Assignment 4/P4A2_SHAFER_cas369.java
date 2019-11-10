/* 
Author: Caroline Shafer
Original Creation Date: 4/19/18
Last Modified Date: 4/19/18
Description: Creating a customized welcome message and asking the end user for name. This program
also allows for the end user to run the game with the option to keep playing.
*/

//These are all the imports
import java.util.*;
import java.io.*;

public class P4A2_SHAFER_cas369
{
	public static void main(String[] args) throws IOException
	{
		//This prints out the welcome message and describes the program/game
		System.out.println("Hello, I am Caroline Shafer. This program will run a game called " +
		"hangman. Hangman challenges the end user to guess a secret word. The end user can have 5 "
		+ "incorrect guesses before the game ends. The game ends if the user guesses the correct "
		+ "secret word, or if the user guesses a 6th incorrect letter.");

		//intializing variables
		Scanner scanner = new Scanner(System.in);
		char guess;
		boolean correctGuess = false;
		String playAgain;

		//intializing variables and calls from .txt file
		Random randNum = new Random();
		File file = new File("P4A2_SHAFER_cas369.txt");
		Scanner inputFile = new Scanner(file);
		//creates array for words in the .txt file
		String [] wordList = new String[7];

		//for loop adds words from .txt file to wordList array
		for(int k = 0; k < 7; k++)
		{
			wordList[k] = inputFile.next();
		}
		int numberofwords = 7;
		//do-while loop allows user to run the game again
		do
		{	
			//intializing variables and creates a blankarray for the asterisks
			int wrongGuess = 0;
			int n = 0;
			String secretWord = null;
			n = randNum.nextInt(7);
			secretWord = wordList[n];
			int lettersLeft = secretWord.length();
			char [] blankarray = new char[secretWord.length()];

			//for loop that fills the blankarray with asterisks
			for(int j = 0; j < blankarray.length; j++)
				{
					blankarray[j]='*';
				}
			System.out.println(new String (blankarray));
			System.out.println("Enter your first guess.");
			guess = scanner.next().charAt(0);
			/*while loop that runs as long as there are less than 6 wrong guesses and 
			letters left to guess in the word */
			while(wrongGuess < 6 && lettersLeft >= 1)
			{
				//for loop that runs for the length of letters in the word
				for(int i = 0; i < secretWord.length(); i++)
				{
					//compares letter guessed to every letter in secret word
					if(guess == secretWord.charAt(i))
					{
						blankarray[i]=guess;
						System.out.println("You guessed correctly! Your secret word is now " + 
						new String(blankarray));
						System.out.println("Number of wrong guesses: " + wrongGuess);
						correctGuess = true;
						lettersLeft--;
					}
				}
				//prints statement and adds to counter of wrong guesses
				if(correctGuess == false)
				{
					wrongGuess++;	
					System.out.println("Guessed letter is not in the secret word." + 
					" Number of wrong guesses: " + wrongGuess);
				}

				/*this if-else if-else statement checks whether there are any letters left in the
				word and whether they have guessed incorrectly 6 times */
				if(lettersLeft == 0)
				{
					System.out.println("You guessed the word! It was " + secretWord);
				}
				else if(wrongGuess == 6)
				{
					System.out.println("You lost. The word was " + secretWord);
				}
				else
				{
					System.out.println("Enter another guess.");
					guess = scanner.next().charAt(0);
				}
			//sets boolean to false
			correctGuess = false;
			}
			//resets game
			wrongGuess = 0;
			System.out.println("Want to play again? (y/n)");
			playAgain = scanner.next();	
			numberofwords--;
		}
		while(playAgain.equals("y"));
	}
}	