/* 
Author: Caroline Shafer
Original Creation Date: 2/16/18
Last Modified Date: 2/23/18
Description: Converting given floating point value into barleycorns per day, furlongs per fortnight,
mach number, and percentage of speed of light.
*/

//These are all the imports
import java.util.Scanner;
import java.io.BufferedReader;

public class P2A2_SHAFER_cas369
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

		//This prints out all the conversions
		System.out.println("Barleycorns per day: " + barleycorns);
		System.out.println("Furlongs per fortnight: " + furlongs);
		System.out.println("Mach Number: " + mach);
		System.out.println("Percentage of the speed of light: " + speedOfLight);
	}
}