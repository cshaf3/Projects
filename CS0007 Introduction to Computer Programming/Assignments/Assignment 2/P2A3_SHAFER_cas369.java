/* 
Author: Caroline Shafer
Original Creation Date: 2/22/18
Last Modified Date: 2/23/18
Description: Taking in account user's choice of conversion as well as creating a loop that allows
user the choice of entering another floating point value.
*/

//These are all the imports
import java.util.Scanner;
import java.io.BufferedReader;

public class P2A3_SHAFER_cas369
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

		//This prints out the request for the user to enter their floating point value
		System.out.println("Please enter miles per hour.");

		//This scanner allows for user input on miles per hour
		Scanner mph = new Scanner(System.in);
		float milesHour = mph.nextFloat();

		//This declares the variables and sets the equations
		float milesDay = (milesHour * 24);
		float metersDay = (milesDay * 1609.34f);
		float barleycorns = (metersDay * 117.647f);
		float furlongs = (metersDay * 0.00497096f) * 14;
		float mach = ((metersDay * 3.28084f) / 86400) / 1130;
		float speedOfLight = (metersDay / 86400) / 299792458;

		//This declares the strings
		String barleycornsConverted = "barleycorns";
		String furlongsConverted = "furlongs";
		String machConverted = "mach";
		String speedOfLightConverted = "percentage";

		//This allows the user to choose their preferred conversion
		System.out.println("Please choose which conversion you'd like: barleycorns per day "
			+ "(barleycorns), furlongs per fortnight (furlongs), mach number (mach), or percentage"
			+ " of speed of light (percentage). Note: response must be one of the abbreviatons " + 
			"given and must be all lowercase.");
		String userChoice = scanner.nextLine();

		//This if-else statement will allow the user's choice to be taken into account
		if(userChoice.equals(barleycornsConverted))
		{
			System.out.println("Barleycorns per day: " + barleycorns);
		}
		else if(userChoice.equals(furlongsConverted))
		{
			System.out.println("Furlongs per fortnight: " + furlongs);
		}
		else if(userChoice.equals(machConverted))
		{
			System.out.println("Mach Number: " + mach);
		}
		else if(userChoice.equals(speedOfLightConverted))
		{
			System.out.println("Percentage of the speed of light: " + speedOfLight);
		}
		else
		{
			System.out.println("Please choose a valid conversion option.");
		}

		//This gives the user the option to run the program again
		System.out.println("Would you like to enter another value? Answer with yes or no " +
			"(case sensitive).");
		String continuing = scanner.nextLine();

		String yes = "yes";
		String no = "no";

		//This while loop will only continue as long as the user response is "yes"
		while(continuing.equals("yes"))
		{
			//This prints out the request for the user to enter their floating point value
			System.out.println("Please enter miles per hour.");

			//This scanner allows for user input on miles per hour
			milesHour = mph.nextFloat();

			//This allows the user to choose their preferred conversion
			System.out.println("Please choose which conversion you'd like: barleycorns per day "
				+ "(barleycorns), furlongs per fortnight (furlongs), mach number (mach), or percentage"
				+ " of speed of light (percentage). Note: response must be one of the abbreviatons " + 
				"given and must be all lowercase.");
			String userChoiceAgain = scanner.nextLine();

			//This if-else statement will allow the user's choice to be taken into account
			if(userChoiceAgain.equals(barleycornsConverted))
			{
				System.out.println("Barleycorns per day: " + barleycorns);
			}
			else if(userChoiceAgain.equals(furlongsConverted))
			{
				System.out.println("Furlongs per fortnight: " + furlongs);
			}
			else if(userChoiceAgain.equals(machConverted))
			{
				System.out.println("Mach Number: " + mach);
			}
			else if(userChoiceAgain.equals(speedOfLightConverted))
			{
				System.out.println("Percentage of the speed of light: " + speedOfLight);
			}
			else
			{
				System.out.println("Please choose a valid conversion option.");
			}

			//The users response to this will either continue or end the loop
			System.out.println("Would you like to enter another value? Answer with yes or no " +
				"(case sensitive).");
			continuing = scanner.nextLine();
		}
	}
}