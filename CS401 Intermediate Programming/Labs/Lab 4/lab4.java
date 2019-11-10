import java.util.*;
import java.io.BufferedReader;

public class lab4
{
	public static int roll()
	{
		lab4_die firstDie = new lab4_die();
		lab4_die secondDie = new lab4_die();

		firstDie.setRoll();
		secondDie.setRoll();

		int value = firstDie.getValue() + secondDie.getValue();

		System.out.println(value);
	}

	public static void main(String[] args)
	{
		Random generator = new Random();
		System.out.println("How many times would you like to roll?");
		Scanner scanner = new Scanner(System.in);
		int rolls = scanner.nextInt();
		for(int i = 0; i<rolls; i++)
		{
			
		}
	}
}