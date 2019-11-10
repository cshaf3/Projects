import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Assig2
{
	public static void main(String[] args) throws java.io.IOException
	{
    //adds decimal point to the hundredths, for a more user friendly view of money
    NumberFormat formatter = new DecimalFormat("#0.00");
    //creates RouletteWheel object to eventually spin wheel
		RouletteWheel wheel = new RouletteWheel();
		//sets RBets type to null and will eventually change to [Value, Color, Range, Parity]
    RBets type = null;
    //creates RoulettePlayer object to eventually get money and update money
    RoulettePlayer weasel;
    //initializing variable that will keep track of how many rounds the user plays
    int rounds = 0;
    //initializing variables that will be the user's initial amount of money and final amount of money
    double initialMoney = 0;
    double newMoney = 0;
    //inititalizing variables that will read into a user's exisiting file
    BufferedReader br = null;
    FileReader fr = null;
    //initializing variables
		double change = 0;
		int winnings = 0;
		String userValue = "";
    //initializing variables to search for a users file
		boolean existingFile = false;
		File f = null;
		String filePath;
    //asks for user's name
		System.out.println("Please enter your name.");
		Scanner scanner = new Scanner(System.in);
  	String name = scanner.nextLine();
    //searches for users existing file (if there is one)
  	f = new File(name + ".txt");
  	existingFile = f.isFile();
  	filePath = f.getPath();
    //if there is an existing file this reads from it
  		if(existingFile == true)
  		{
        Scanner in = new Scanner(new FileReader(name + ".txt"));

        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
        sb.append(in.next());
      }
        in.close();
        String output = sb.toString();
        newMoney = Double.parseDouble(output);
        initialMoney = Double.parseDouble(output);
        System.out.println("Welcome back " + name + "\nYou have $" + output + "0 in your account.");
  		}
      //creates new file for user
  		else
  		{
  			f.createNewFile();

  			System.out.println("Welcome new player!");
        System.out.println("Please enter your information below: \nInitial money: ");
        initialMoney = scanner.nextDouble();
        newMoney = initialMoney;
     }
     //will run game as long as user has money to bet
     while(newMoney != 0)
        {
          //asks user to enter a starting value for their money
          System.out.println("How much do you want to bet? (<=$" + formatter.format(newMoney) + ")");
          System.out.println("(Enter 0 if you want to quit)");
          weasel = new RoulettePlayer(name, newMoney);
          double firstBet = scanner.nextDouble();
          //takes in account if user tries to bet more than they have or places a negative bet
          while((firstBet > newMoney) || (firstBet < 0))
          {
            if(firstBet < 0)
            {
              System.out.println("You cannot bet a negative number.");
              System.out.println("How much do you want to bet? (<=$" + formatter.format(newMoney) + ")");
              System.out.println("(Enter 0 if you want to quit)");
              weasel = new RoulettePlayer(name, newMoney);
              firstBet = scanner.nextDouble();
            }
            else if(firstBet > newMoney)
            {
              System.out.println("You cannot bet more than you have.");
              System.out.println("How much do you want to bet? (<=$" + formatter.format(newMoney) + ")");
              System.out.println("(Enter 0 if you want to quit)");
              weasel = new RoulettePlayer(name, newMoney);
              firstBet = scanner.nextDouble();
            }
          }   
          //ends game if user enters 0 and shows their results
          if(firstBet == 0)
          {        
            System.out.println("Here are your results: \nRounds Played: " + rounds + "\nStarting Money: $" 
            + formatter.format(initialMoney) + "\nEnding Money: $" + formatter.format(newMoney));
            double net = newMoney - initialMoney;
            if(net < 0)
            {
              System.out.println("Net loss: $" + formatter.format(net));
            }
            else if(net > 0)
            {
              System.out.println("Winnings: $" + formatter.format(net));
            }
            else if(net == 0)
            {
              System.out.println("You broke even!");
            }
            //adds current money to user file and ends program
            PrintWriter printWriter = new PrintWriter (name + ".txt");
            printWriter.println (newMoney);
            printWriter.close ();
            System.exit(0);
          }
          //asks user to choose a bet type
          System.out.println("Please enter the type of your bet: [Value, Color, Range, Parity]");
          String userType = scanner.next();
          System.out.println("Your bet type is: " + userType);
          //performs based on which type user chooses
          if(userType.equals("Color"))
          {
            type = RBets.Color;
            System.out.println("Enter your color [Red, Black]: ");
            userValue = scanner.next();
          }
          else if(userType.equals("Value"))
          {
            type = RBets.Value;
            System.out.println("Enter your number [0-36]: ");
            userValue = scanner.next();
          }
          else if(userType.equals("Range"))
          {
            type = RBets.Range;
            System.out.println("Enter your range [Low, High]: ");
            userValue = scanner.next();
          }
          else if(userType.equals("Parity"))
          {
            type = RBets.Parity;
            System.out.println("Enter your parity [Even, Odd]: ");
            userValue = scanner.next();
          }
          //sets user bet and spins wheel
          RouletteBet bet = new RouletteBet(type, userValue);
          RouletteResult X = wheel.spinWheel();
          //prints spin result
          System.out.println("Spin result: " + X);
          //calculates how much the user has won or lost
          winnings = wheel.checkBet(bet);
          if(winnings == 0)
          {
            change = firstBet * -1;
            weasel.updateMoney(change);
          }
          else if(winnings == 1)
          {
            change = firstBet;
            weasel.updateMoney(change);
          }
          else if(winnings == 35)
          {
            change = firstBet * 35;
            weasel.updateMoney(change);
          }
          //shows user their loss/win
          if(change > 0)
          {
            System.out.println("Even money winner gets $" + formatter.format(change));
            System.out.println("You now have: $" + formatter.format(weasel.getMoney()));
          }
          else if(change < 0)
          {
            System.out.println("Sorry but you lose your bet of $" + formatter.format(firstBet));
            System.out.println("You now have: $" + formatter.format(weasel.getMoney()));
          }
          //adds to number of rounds
          rounds++;
          //gets users new money value
          newMoney = weasel.getMoney();
        }
        //shows user their results
        System.out.println("Here are your results: \nRounds Played: " + rounds + "\nStarting Money: $" 
        + formatter.format(initialMoney) + "\nEnding Money: $" + formatter.format(newMoney));
        double net = newMoney - initialMoney;
        if(net < 0)
        {
              System.out.println("Net loss: $" + formatter.format(net));
        }
        else if(net > 0)
        {
           System.out.println("Winnings: $" + formatter.format(net));
        }
        else if(net == 0)
        {
          System.out.println("You broke even!");
        }
        //adds current money to user file and ends program
        PrintWriter printWriter = new PrintWriter (name + ".txt");
        printWriter.println (newMoney);
        printWriter.close ();
	}
}