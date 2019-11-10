import java.util.*;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class Assig3
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
    	double newMoney = 0;
    	//inititalizing variables that will read into a user's exisiting file
    	BufferedReader br = null;
    	FileReader fr = null;
    	//initializing variables
		double change = 0;
		double initialMoney = 0.0;
		int winnings = 0;
		String userValue = "";
		String password = "";
		RPList R = new RPList("players.txt");
		double firstBet;
		double borrow = 0.0;
		int incorrect = 0;
		double payBack = 0.0;
		double debt = 0.0;
		double endingMoney = 0.0;
        double endingDebt = 0.0;

		System.out.println("Welcome to Online Roulette!" + "\nPlease sign in with your id and password " + "\nIf you are a new "
		+ "player, leave your id field blank " + "\nand we will set you up with a new account" + "\nIf you would like to end " +
		"the game, enter 'Quit'");
		System.out.println("Id: ");
		Scanner scanner = new Scanner(System.in);
  		String name = scanner.nextLine();
  		if(name.equals("Quit"))
  		{
  			System.exit(0);
  		}
  		else if(name.equals(""))
  		{
  			boolean existingFile = false;
  			System.out.println("Welcome new Roulette Player\nPlease enter your information below:");
  			System.out.println("Name: ");
  			name = scanner.nextLine();
  			//if name is in use, makes player enter another
 			while(name.equals(R.getName(name)))
  			{
  				System.out.println("Sorry but this id is already used. Please try again");
  				name = scanner.nextLine();
  			}
  			System.out.println("Password: ");
  			String newPassword = scanner.nextLine();
  			System.out.println("Re-type password: ");
  			String sameNewPassword = scanner.nextLine();
  			boolean matchingPasswords = newPassword.equals(sameNewPassword);
  			//if passwords don't match, makes user enter it again
  			while(matchingPasswords == false)
  			{
  				System.out.println("Sorry but your passwords do not match.");
  				System.out.println("Password: ");
  				newPassword = scanner.nextLine();
  				System.out.println("Re-type password: ");
  				sameNewPassword = scanner.nextLine();
  				matchingPasswords = newPassword.equals(sameNewPassword);
  			}
  			System.out.println("Initial Money: ");
  			String convert = scanner.nextLine();
  			initialMoney = Double.parseDouble(convert);
  			System.out.println("You may add two security questions to your account.\nThese can be used to retrieve your profile " +
  			"if you forget your password.\nDo you wish to add these questions? (Y/N)");
  			String response = scanner.nextLine();
  			//allows user to create questions
  			if(response.equals("Y"))
  			{
  				System.out.println("Question 1: ");
  				String questionOne = scanner.nextLine();
  				System.out.println("Answer 1: ");
  				String answerOne = scanner.nextLine();
  				System.out.println("Question 2: ");
  				String questionTwo = scanner.nextLine();
  				System.out.println("Answer 2: ");
  				String answerTwo = scanner.nextLine();
  				System.out.println("Remember your answers -- they must be entered exactly as written here to allow retrieval "
  				+ "of your information.");
  			}
  			weasel = new RoulettePlayer(name, newPassword, initialMoney, 0.0);
  			password = newPassword;
  		}
  		//else statement for returning player
  		else
  		{
			System.out.println("Please enter your password: ");
  			String userPassword = scanner.nextLine();

  			if(!userPassword.equals(R.getPassword(name)))
 			{
 				incorrect = 1;
 				while(!userPassword.equals(R.getPassword(name)) && (incorrect < 2))
 				{
  					System.out.println("Sorry, your password does not match. Please try again.");
  					System.out.println("Please enter your password: ");
  					userPassword = scanner.nextLine();
  					incorrect++;
  					//if user is incorrect twice, gives them questions if they exist
  					if(incorrect >= 2)
  					{
  						System.out.println("Your password still does not match, so your sign-in has been canceled. " +
  						"You still may be able to play if you set security questions. Otherwise, you will need to " + 
  						"register as a new player.");
  						if(R.getQuestions(name).equals(null))
  						{
  							System.out.println("Sorry but your account does not have any security questions. We are not "
  							+ "able to recover your records. If you want to play you will have to sign in as a new player.");
  						}
  						else
  						{
  							System.out.println(name + " we will try to access your account using security questions. Please "
  							+ "answer these exactly how you answered when you generated them.");
  						
  							Question[] questions = new Question[2];
  							System.out.println(R.getQuestions(name)[0]);
  							String userAnswer = scanner.nextLine();
  							System.out.println(R.getQuestions(name)[1]);
  							String userAnswer1 = scanner.nextLine();

  							String questionOne = R.getQuestions(name)[0];
							String answerOne = R.getAnswers(name)[0];
							String questionTwo = R.getQuestions(name)[1];
							String answerTwo = R.getAnswers(name)[1];

							Question one = new Question(questionOne, answerOne); 
							Question two = new Question(questionTwo, answerTwo);
							questions[0] = one;
							questions[1] = two;

  							if((userAnswer.equals(questions[0].getA())) && (userAnswer1.equals(questions[1].getA())))
  							{
  								System.out.println("We have recovered your account.\nPlease reset your password\nNew password: ");
  								password = scanner.nextLine();
  								System.out.println("Password updated. You are now ready to play");
  								initialMoney = R.getMoney2(name);
  								debt = R.getDebt2(name);		
  							}
  							else
  							{
  								System.out.println("Sorry, wrong answers.");
  								System.exit(0);
  							}
  						}
  					}
  				}
  			}
  		}
  		System.out.println(name + " you may play as many rounds as you like.  If you run out of money you may borrow up " +
  		"to a total of $500.00 dollars.  Good luck!");

  		weasel = new RoulettePlayer(name, password, initialMoney, debt);
  		newMoney = initialMoney;
  		//user can keep playing even if they're out of money as long as their debt doesn't exceed $500
  		while(weasel.getDebt() < 500)
  		{
  			double debtAvailable = 500 - weasel.getDebt();
  			//allows user to borrow money if they have none
  		  if(weasel.getMoney() == 0)
  		  {
  		  	System.out.println("Sorry but you are out of money.\nHowever, you may borrow some if you wish");
  		  	System.out.println("Enter the amount you would like to borrow (<=" + debtAvailable + ")");
  		  	borrow = scanner.nextDouble();
  		  	if((borrow <= debtAvailable) && (borrow > 0))
  		  	{
  		  		weasel.borrow(borrow);
  		  	}
  		  	//asks user again if they enter an invalid amount
  		  	else
  		  	{
  		  		while((borrow > debtAvailable) || (borrow < 0))
  		  		{
  		  			System.out.println("Enter the amount you would like to borrow (<=" + debtAvailable + ")");
  		  			borrow = scanner.nextDouble();
  		  		}
  		  		weasel.borrow(borrow);
  		  	}
  		  }
          //asks user to enter a starting value for their money
          System.out.println("How much do you want to bet? (<=$" + formatter.format(weasel.getMoney()) + ")");
          System.out.println("(Enter 0 if you want to quit)");
          firstBet = scanner.nextDouble();
          //takes in account if user tries to bet more than they have or places a negative bet
          while((firstBet > weasel.getMoney()) || (firstBet < 0) )
          {
            if(firstBet < 0)
            {
              System.out.println("You cannot bet a negative number.");
              System.out.println("How much do you want to bet? (<=$" + formatter.format(weasel.getMoney()) + ")");
              System.out.println("(Enter 0 if you want to quit)");
              firstBet = scanner.nextDouble();
            }
            else if(firstBet > weasel.getMoney())
            {
              System.out.println("You cannot bet more than you have.");
              System.out.println("How much do you want to bet? (<=$" + formatter.format(weasel.getMoney()) + ")");
              System.out.println("(Enter 0 if you want to quit)");
              firstBet = scanner.nextDouble();
            }
          }   
          //ends game if user enters 0 and shows their results
          if(firstBet == 0)
          {        
            System.out.println("Here are your results: \nRounds Played: " + rounds + "\nStarting Money: $" 
            + formatter.format(initialMoney) + "\nEnding Money: $" + formatter.format(newMoney) + 
            "\nDebt to house: $" + formatter.format(weasel.getDebt()));
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
            //extra credit
            System.out.println("Here is how you compared to other players.\nYour final money: $" + formatter.format(weasel.getMoney()));
        	System.out.println("Average user money: $" + formatter.format(R.getAverage()));
        	//saves new user to list
        	endingMoney = weasel.getMoney();
        	endingDebt = weasel.getDebt();
        	weasel = new RoulettePlayer(name, password, endingMoney, endingDebt);
        	R.add(weasel);
        	R.saveList();
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
            	if(weasel.getDebt() > 0)
            	{
            		System.out.println("You now have: $" + formatter.format(weasel.getMoney()));
            		System.out.println("You have some debt that you may wish to pay off.\nYou owe: $" + formatter.format(weasel.getDebt())
           			+ " How much would you like to pay back (<=" + weasel.getDebt() + ")");
           			payBack = scanner.nextDouble();
           			while((payBack > weasel.getDebt()) || (payBack < 0) || (payBack > weasel.getMoney()))
           			{
           				System.out.println("How much would you like to pay back (<=" + weasel.getDebt() + ")");
           				payBack = scanner.nextDouble();
           			}
           			if((payBack <= weasel.getDebt()) && (payBack > 0) && (payBack <= weasel.getMoney()))
           			{
           				System.out.println("Great - you are paying back $" + formatter.format(payBack) + " of your debt");
           				weasel.payBack(payBack);
           			}
           		}
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
          weasel.getDebt();
        }
        //shows user their results
        System.out.println("Here are your results: \nRounds played: " + rounds + "\nStarting money: $" + 
        formatter.format(initialMoney) + "\nEnding money: $" + formatter.format(newMoney) + "\nDebt to the house: $"
        + formatter.format(weasel.getDebt()));
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
        //extra credit
        System.out.println("Here is how you compared to other players.\nYour final money: " + weasel.getMoney());
        System.out.println("Average user money: " + R.getAverage());
        //saves new user to list
        endingMoney = weasel.getMoney();
        endingDebt = weasel.getDebt();
        weasel = new RoulettePlayer(name, password, endingMoney, endingDebt);
        R.add(weasel);
        R.saveList();
  	}
}