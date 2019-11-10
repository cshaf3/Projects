
import java.util.Scanner;
import java.io.BufferedReader;

public class LabOne
{
	public static void main(String[] args)
	{
		System.out.println("Please enter your name.");

		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		System.out.println("Hello, " + name + ".");

		System.out.println("Please enter a temperature value.");
		double temperature = scanner.nextDouble();

		boolean coldOutside;
		if(temperature < 35)
		{
			coldOutside = true;
		}
		else
		{
			coldOutside = false;
		}

		for(int i = 0; i < 5; i++)
		{
			System.out.println("I love snow.");
		}

		while(coldOutside)
		{
			System.out.println("It's cold!");
			System.out.println("Please enter a second temperature value.");
			double secondTemperature = scanner.nextDouble();
			if(secondTemperature < 35)
			{
				coldOutside = true;
			}
		}
	}
}