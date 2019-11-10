/* 
Author: Caroline Shafer
Original Creation Date: 2/2/18
Last Modified Date: 2/9/18
Description: This is a measurement conversion program. The user enters a floating point specifying 
number of days and this program converts it into either seconds, minutes, hours, weeks, 
30 day months, years, or decades.
*/

import java.util.Scanner;
import java.io.BufferedReader;
//These are all the imports

public class P1A2_SHAFER_cas369
{
	public static void main(String[] args)
	{
		System.out.println("Please enter your name.");
		//This prints out the request for the user to enter their name

		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		//This scanner allows for user input on their name

		System.out.println("Hello, " + name + ". I am Caroline Shafer. This is a time conversion" +
			" program. The user enters a floating point specifying number of days and this program"
			+ " converts it into either seconds, minutes, hours, weeks, 30 day months, years, or" + 
			" decades.");
		//This prints out the welcome message and describes the program

		System.out.println("Please enter your number of days.");
		//This prints out the request for the user to enter their floating point value

		Scanner days = new Scanner(System.in);
		float numberDays = days.nextFloat();
		//This scanner allows for user input on number of days

		float seconds = (numberDays * 24) * 3600;
		float minutes = (numberDays * 24) * 3600;
		float hours = (numberDays * 24) * 3600;
		float months = (numberDays * 24) * 3600;
		float years = numberDays / 365;
		float decades = (numberDays / 365) / 10;
		//This declares the variables and sets the equations

		System.out.println("Seconds: " + seconds);
		System.out.println("Minutes: " + minutes);
		System.out.println("Hours: " + hours);
		System.out.println("Months: " + months);
		System.out.println("Years: " + years);
		System.out.println("Decades: " + decades);
		//This prints out all the conversions
	}
}