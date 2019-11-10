/* 
Author: Caroline Shafer
Original Creation Date: 2/5/18
Last Modified Date: 2/9/18
Description: This is a time conversion program. The user enters a floating point specifying number 
of days and this program converts it into either seconds, minutes, hours, weeks, 30 day months, 
years, or decades. This is different than the second activity in that the user choses their
preferred method of conversion.
*/

import java.util.Scanner;
import java.io.BufferedReader;
//These are all the imports

public class P1A3_SHAFER_cas369
{
	public static void main(String[] args)
	{
		System.out.println("Please enter your name.");
		//This prints out the request for the user to enter their name

		Scanner scanner = new Scanner(System.in);
		String name= scanner.nextLine();
		//This scanner allows for user input

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

		float secondConversion = (numberDays * 24) * 3600;
		float minuteConversion = (numberDays * 24) * 60;
		float hourConversion = numberDays * 24;
		float monthConversion = numberDays / 30;
		float yearConversion = numberDays / 365;
		float decadeConversion = (numberDays / 365) / 10;
		//This decares the variables and sets equations

		String seconds = "seconds";
		String minutes = "minutes";
		String hours = "hours";
		String months = "months";
		String years = "years";
		String decades = "decades";
		//This declares the strings

		System.out.println("Please choose which conversion you'd like: seconds, minutes, hours, " +
			"months, years, or decades. Note: response is case sensitive and must be all lowercase.");
		Scanner request = new Scanner(System.in);
		String userChoice = request.nextLine();

		//This if-else statement will allow the user's choice to be taken into account
		if(userChoice.equals(seconds))
		{
			System.out.println("Seconds: " + secondConversion);
		}
		else if(userChoice.equals(minutes))
		{
			System.out.println("Minutes: " + minuteConversion);
		}
		else if(userChoice.equals(hours))
		{
			System.out.println("Hours: " + hourConversion);
		}
		else if(userChoice.equals(months))
		{
			System.out.println("Months: " + monthConversion);
		}
		else if(userChoice.equals(years))
		{
			System.out.println("Years: " + yearConversion);
		}
		else if(userChoice.equals(decades))
		{
			System.out.println("Decades: " + decadeConversion);
		}
		else
		{
			System.out.println("Please choose a valid conversion option.");
		}

		System.out.println("Program is now finished. Goodbye.");
		//If the user does not select one of the declared String options a message confirms it is
		//not a valid option. The program then ends.
	}
}