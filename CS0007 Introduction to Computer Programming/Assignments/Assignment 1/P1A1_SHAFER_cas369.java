/* 
Author: Caroline Shafer
Original Creation Date: 1/26/18
Last Modified Date: 2/9/18
Description: Creating a customized welcome message and asking the end user for name.
*/

import java.util.Scanner;
import java.io.BufferedReader;
//These are all the imports
public class P1A1_SHAFER_cas369
{
	public static void main(String[] args)
	{
		System.out.println("Please enter your name.");
		//This prints out the request for the user to enter their name

		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		//This scanner allows for user input
		
		System.out.println("Hello, " + name + ". I am Caroline Shafer. This is a time conversion" +
			" program. The user enters a floating point specifying number of days and this program"
			+ " converts it into either seconds, minutes, hours, weeks, 30 day months, years, or" + 
			" decades.");
		//This prints out the welcome message and describes the program
	}
}