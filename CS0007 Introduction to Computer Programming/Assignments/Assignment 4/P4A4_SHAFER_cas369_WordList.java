/* 
Author: Caroline Shafer
Original Creation Date: 4/19
Last Modified Date: 4/19
Description: Creating a customized welcome message and asking the end user for name.
*/

//These are all the imports
import java.io.*;
import java.util.*;

public class P4A4_SHAFER_cas369_WordList 
{

	//intializing variables
	ArrayList<String> words;
	Random randNum = new Random();
	String secretWord = null;
	File file;
	private int length;

	//constructor method
	public P4A4_SHAFER_cas369_WordList()
	{
		words = new ArrayList<String>();
		length=0;
	}
	//adds one single word to the WordList object [public]
	public void addWord(String singleWord)
	{
		words.add(singleWord);
	}
	//adds all words to the WordList object from a single text file [public]
	public void addWordsFromFile(String filename)
	{
		try
		{
		File inputs = new File(filename);
		Scanner inputFile = new Scanner(inputs);
		while(inputFile.hasNextLine())
		{
			words.add(inputFile.nextLine());
			length++;
		}
		}
		catch (IOException filenotout)
		{
			System.out.println("File not found.");
		}
	}
	//returns a single word at random from the WordList object [public]
	public String getRandomWord()
	{
		if(words!=null)
		{
		Random randNum = new Random();
		int n = randNum.nextInt(this.numWords());
		secretWord = words.get(n);
		return secretWord;
		}
		else
			return null;
	}
	//returns how many words are in the WordList object [public]
	public int numWords()
	{
		return length;
	}
}	