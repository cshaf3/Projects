import java.util.*;
import java.io.BufferedReader;

public class Main 
{
    public static void main(String[] args) 
    {
           
        RollDice dice;
        dice = new RollDice();
        int[] occurence;
        occurence = new int[13];
        String response = "";
        do
        {
        	System.out.println("Please enter how many times you'd like to roll.");
        	Scanner scanner = new Scanner(System.in);
			int numberOfRolls = scanner.nextInt();
			for(int i=0; i<numberOfRolls; i++)
			{
				dice.roll();
				dice.getTotal();
				occurence[dice.getTotal()] +=1;				
			}
			for (int j = 2; j < occurence.length; j++)
			{
				System.out.println("Value: " + j + " Occurence: " + occurence[j] + "/" + numberOfRolls);
			}
		System.out.println("Would you like to continue?");
		response = scanner.next();
		}
        while(response.equals("yes"));
    }    
}  