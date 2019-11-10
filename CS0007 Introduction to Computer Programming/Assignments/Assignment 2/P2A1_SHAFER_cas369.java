/* 
Author: Caroline Shafer
Original Creation Date: 2/16/18
Last Modified Date: 2/16/18
Description: Creating a customized welcome message and asking the end user for name.
*/

//These are all the imports
import java.util.Scanner;
import java.io.BufferedReader;

public class P2A1_SHAFER_cas369
{
	public static void main(String[] args)
	{
		//This prints out the request for the user to enter their name
		System.out.println("Please enter your name.");

		//This scanner allows for user input
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		//This prints out the welcome message and describes the program
		System.out.println("Hello, " + name + ". I am Caroline Shafer. This is a time conversion" +
			" program. The user enters a floating point specifying miles per hour and this program"
			+ " converts it into barleycorns per day, furlongs per fortnight, mach number, or " + 
			"percentage of the speed of light.");
	}
}