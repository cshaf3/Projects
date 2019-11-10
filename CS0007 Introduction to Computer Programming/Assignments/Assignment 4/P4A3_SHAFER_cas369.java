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

public class P4A3_SHAFER_cas369
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
		String guess = null;
		int wrongGuess = 0;
		boolean correctGuess = false;
		String playAgain;
		//array list to determine which letters have already been guessed
		ArrayList<String> lettersGuessed = new ArrayList<String>();

		//intializing variables and calls from .txt file
		Random randNum = new Random();
		File file = new File("P4A3_SHAFER_cas369.txt");
		Scanner inputFile = new Scanner(file);
		//creates array for words in the .txt file
		String [] wordList = new String[7];
		String [] hints = new String[7];
		//boolean to determine if guess is full word or a singular letter
		boolean longGuess = false;
		String secretWord = null;
		
		//for loop adds words from .txt file to wordList array and hint to hints array
		for(int k = 0; k < 7; k++)
		{
			wordList[k] = inputFile.nextLine();
			hints[k] = inputFile.nextLine();
		}

		int numberofwords = 7;
		int n = 0;
		//do-while loop allows user to run the game again
		do
		{
		n = randNum.nextInt(7);
		secretWord = wordList[n];
		char [] blankarray = new char[secretWord.length()];
		//for loop that fills the blankarray with asterisks
		for(int j = 0; j < blankarray.length; j++)
		{
			blankarray[j]='*';
		}		
		System.out.println(new String (blankarray) + " " + hints[n]);
		System.out.println("Enter your first guess.");
		guess = scanner.next();
		lettersGuessed.add(new String(guess));
		//this if-else statement takes into account if the guess is a whole word
		if(guess.length() > 1)
		{
			for(int l = 0; l < secretWord.length(); l++)
			{
				if(guess.charAt(l) == secretWord.charAt(l))
				{
					longGuess = true;
				}
				else
				{
					longGuess = false;
				}
			}

			if(longGuess == true)
			{
				System.out.print("You guessed the word! ");
			}
			else
			{
				System.out.print("You guessed the word incorrectly. ");
				wrongGuess++;
			}
		}
		//if the user guesses a singular letter
		else
		{	
				int lettersLeft = secretWord.length();
				/*while loop that runs as long as there are less than 6 wrong guesses and 
				letters left to guess in the word */
				while(wrongGuess < 6 && lettersLeft >= 1)
				{
					//for loop that runs for the length of letters in the word
					for(int i = 0; i < secretWord.length(); i++)
					{
						//compares letter guessed to every letter in secret word
						if(guess.charAt(0) == secretWord.charAt(i))
						{
							blankarray[i] = guess.charAt(0);
							System.out.println("You guessed correctly! Your secret word is now " + 
							new String(blankarray));

							System.out.println("So far you've guessed: " + lettersGuessed);
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
						System.out.println("So far you've guessed: " + lettersGuessed);
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
						guess = scanner.next();
						lettersGuessed.add(new String(guess));
					}
				//sets boolean to false
				correctGuess = false;
				}
				//resets game
				wrongGuess = 0;
				lettersGuessed = new ArrayList<String>();
			}
				//resets game
				System.out.println("Want to play again? (y/n)");
				playAgain = scanner.next();	
				numberofwords--;
			} while(playAgain.equals("y"));
		
	}
}	