/* 
Author: Caroline Shafer
Original Creation Date: 9/10/18
Last Modified Date: 9/21/18
Description: Program will simulate transactions between customers and tutor.
*/

import java.util.*;
import java.io.BufferedReader;

public class Tutor
{
	public static void main(String[] args)
	{
   		System.out.println("Welcome to Elite DADA Tutoring. Please enter your name [empty string to quit].");

   		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		//allows user to input name

   		int totalCostInKnuts = 0;
		int hoursOfTutoring = 0;
		int numberOfSpells = 0;
		int reducedPrice = 0;
		int hourlyRate = 0;
		int perSpellRate = 0;
		int finalTotal = 0;
		int discountPerSpellRate = 0;
		int paid = 0;
		int paymentLeft = 0;
		//initializing all variables

   		String prices = ("Prices for hourly tutoring are as followed: \n\n" +
      	"From 0 to 4 hours of tutoring, 5 galleons (or 85 Sickles or 2465 Knuts) per hour. \n" +
      	"For every increment of 5 hours of tutoring, the hourly rate is reduced by 5 sickles. \n" +
      	"The minimum hourly rate is 60 Sickles regardless of the number of hours. \n\n" +
      	"Prices for per spell tutoring are as followed: \n\n" +
      	"1 or 2 spells = 5 Galleons (or 85 Sickles or 2465 Knuts) each. \n" +
      	"3 or 4 spells = 4 Galleons (or 68 Sickles or 1972 Knuts) each. \n" +
      	"All 5 spells = 3 Galleons (or 51 Sickles or 1479 Knuts) each. \n\n");
      	//price list

      	String options = ("Please choose an option: \n" +
    	"1) Order hourly tutoring. \n" +
    	"2) Order indiviual spell tutoring. \n" +
    	"3) Show price list. \n" +
    	"4) Check out.\n\n");
    	//option list

		if(name.equals (""))
		{
			System.exit(0);
		}
		//ends program with empty string
		
		System.out.println("Welcome " + name + "! If you have a password, please enter it now.");
		String input = scanner.nextLine();
		//weasel is secret password

		if(input.equals ("weasel"))
		{
			System.out.println(prices);
			System.out.println("Correct password!\nWizard's Chessmaster Discount: 10 percent per hour discount "
			+ "applied at checkout 4 Sickles per spell discount applied at checkout\n");
			System.out.println(options);
			int userChoice = scanner.nextInt();

			while(userChoice != 4)
			{
				//allows user to continue ordering
				if(userChoice == 1)
				{
					System.out.println("Here is your current order: " + hoursOfTutoring + " total hour(s) of tutuoring ordered.\n");
					System.out.println("How many hours of tutoring would you like?\n");
					hoursOfTutoring = scanner.nextInt();
					numberOfSpells = 0;
					reducedPrice = hoursOfTutoring / 5;
					if(hoursOfTutoring < 0)
					{
						System.out.println("Negative numbers are taken as 0\n");
						hoursOfTutoring = 0;
					}
					else if(reducedPrice <= 6)
					{
						totalCostInKnuts = hoursOfTutoring * (2465 - (reducedPrice * 145));
						hourlyRate = 2465 - (reducedPrice * 145);
					}
					else if(reducedPrice > 6)
					{
						totalCostInKnuts = hoursOfTutoring * 1740;
						hourlyRate = 1740;
					}
					//calculates total cost given hours
				}
				else if(userChoice == 2)
				{
					System.out.println("Here is your current order: " + numberOfSpells + " spell(s) ordered.\n");
					System.out.println("How many spells would you like? (1-5)\n");
					numberOfSpells = scanner.nextInt();
					hoursOfTutoring = 0;
					if(numberOfSpells > 5)
					{
						System.out.println("Number greater than 5 taken as 5.\n");
						numberOfSpells = 5;
						totalCostInKnuts = numberOfSpells * 1363;
						//discounted spell price for 5 spells
					}
					else if(numberOfSpells < 0)
					{
						System.out.println("Negative numbers are taken as 0\n");
						numberOfSpells = 0;
					}
					else if(numberOfSpells == 1 || numberOfSpells == 2)
					{
						totalCostInKnuts = numberOfSpells * 2349;
						perSpellRate = 2465;
						discountPerSpellRate = 2349;
						//discounted spell price for 1 or 2 spells
					}
					else if(numberOfSpells == 3 || numberOfSpells == 4)
					{
						totalCostInKnuts = numberOfSpells * 1856;
						perSpellRate = 1972;
						discountPerSpellRate = 1856;
						//discounted spell price for 3 or 4 spells
					}
					else if(numberOfSpells == 5)
					{
						totalCostInKnuts = numberOfSpells * 1363;
						perSpellRate = 1479;
						discountPerSpellRate = 1363;
						//discounted spell price for 5 spells
					}
					//calculates total gost given number of spells
				}	
				else if(userChoice ==3)
				{
					System.out.println(prices);
					//shows price list
					System.out.println("Correct password!\nWizard's Chessmaster Discount: 10 percent per hour discount "
					+ "applied at checkout 4 Sickles per spell discount applied at checkout\n");
				}

				System.out.println(options);
				userChoice = scanner.nextInt();
				//allows user to chose another option
			}
			if(userChoice ==4)
			{
				//checkout
				if(hoursOfTutoring != 0)
				{
					double discountKnuts = (double) totalCostInKnuts;
					double discountRate = (double) hourlyRate;
					discountRate = discountRate * .9;
					discountKnuts = hoursOfTutoring * discountRate;
					int roundedDiscountKnuts = (int) discountKnuts;
					int roundedDiscountRate = (int) discountRate;
					double tax = (double) roundedDiscountKnuts * .05;
					int roundedTax = (int) tax;
					finalTotal = roundedDiscountKnuts + roundedTax;
					//intializing the tax and rounded variables

					System.out.println(name + ", here is your bill: ");
					System.out.println("Hourly Tutoring:\n\nRegular rate: " + hourlyRate + " Knuts.\nDiscount Rate: "
					+ roundedDiscountRate + " Knuts.\nCost of Tutoring: " + roundedDiscountKnuts + " Knuts.\n"
					+ "Ministry of Magic Tax: " + roundedTax + " Knuts.\nTotal: " + finalTotal + " Knuts.");
					//prints users bill
					System.out.println("\nPlease enter a payment amount in the following format:\n\n<amount><space><currency>" +
					"\n\nWhere <amount> = an integer\nWhere <space> = a blank space\nWhere<currency> = {Knuts, "
					+ "Sickles, Galleons}\n\nYou may enter as many times as you like. Each entry will be added" +
					" to your total until sufficient funds have been obtained.\n\nRecall the currency exchange:\n"
					+ "29 Knuts = 1 Sickle\n493 Knuts = 17 Sickles = 1 Galleon");
					//allows user to pay
					int userAmount = scanner.nextInt();
					String userCurrency = scanner.next();
					int newUserAmount = 0;
					int newCurrency = 0;

					if(userCurrency.equals ("Knuts"))
					{
						paid = finalTotal - userAmount;
						newCurrency = userAmount;
						finalTotal = paid;
						newUserAmount += newCurrency;
					}
					else if(userCurrency.equals ("Sickles"))
					{
						paid = finalTotal - (userAmount * 29);
						newCurrency = userAmount * 29;
						finalTotal = paid;
						newUserAmount += newCurrency;
					}
					else if(userCurrency.equals ("Galleons"))
					{
						paid = finalTotal - (userAmount * 493);
						finalTotal = paid;
						newCurrency = userAmount * 493;
						newUserAmount += newCurrency;
					}
					//takes in users payment
					System.out.println("\nPayment: " + userAmount + " " + userCurrency + "\nYou have added " + userAmount 
					+ " " + userCurrency + " to your total\n" + "You have paid " + newUserAmount + " out of " + finalTotal 
					+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					while(paid != 0)
					{
						userAmount = scanner.nextInt();
						userCurrency = scanner.next();
						if(userCurrency.equals ("Knuts"))
						{
							paid = finalTotal - userAmount;
							finalTotal = paid;
							newCurrency = userAmount;
							newUserAmount += newCurrency;
						}
						else if(userCurrency.equals ("Sickles"))
						{
							paid = finalTotal - (userAmount * 29);
							finalTotal = paid;
							newCurrency = userAmount * 29;
							newUserAmount += newCurrency;
						}
						else if(userCurrency.equals ("Galleons"))
						{
							paid = finalTotal - (userAmount * 493);
							finalTotal = paid;
							newCurrency = userAmount * 493;
							newUserAmount += newCurrency;
						}
						System.out.println("\nPayment: " + userAmount + " " + userCurrency + "\nYou have added " + userAmount 
						+ " " + userCurrency + " to your total\n" + "You have paid " + newUserAmount + " out of " + finalTotal 
						+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					}
					//calculates what is left to pay
					if(paid < 0)
					{
						System.out.println("You have overpaid by " + (paid * -1) + " Knuts\nHere is your change "
						+ (paid * -1) + " Knuts\n Thank you for shopping!");
					}
					//gives user change
				}
				else if(numberOfSpells != 0)
				{
					double tax = (double) totalCostInKnuts * .05;
					int roundedTax = (int) tax;
					finalTotal = totalCostInKnuts + roundedTax;
					//rounds total and intiialzes tax variable
					System.out.println(name + ", here is your bill: ");
					System.out.println("Per spell tutoring: \n\n" + numberOfSpells + " spell(s) ordered\n"
					+ "Regular rate per spell: " + perSpellRate + " Knuts.\nDiscount Rate: " + discountPerSpellRate
					+ " Knuts.\nCost of Spells: " + totalCostInKnuts + " Knuts.\nMinistry of Magic Tax: "
					+ roundedTax + " Knuts.\nTotal: " + finalTotal + " Knuts.");
					//prints bill
					System.out.println("\nPlease enter a payment amount in the following format:\n\n<amount><space><currency>" +
					"\n\nWhere <amount> = an integer\nWhere <space> = a blank space\nWhere<currency> = {Knuts, "
					+ "Sickles, Galleons}\n\nYou may enter as many times as you like. Each entry will be added" +
					" to your total until sufficient funds have been obtained.\n\nRecall the currency exchange:\n"
					+ "29 Knuts = 1 Sickle\n493 Knuts = 17 Sickles = 1 Galleon");
					//shows currency exchange
					int userAmount1 = scanner.nextInt();
					String userCurrency1 = scanner.next();
					paid = 0;
					paymentLeft = 0;
					int newFinalTotal = finalTotal;
					int newUserAmount = 0;
					int newCurrency1 = 0;
					if(userCurrency1.equals ("Knuts"))
					{
						paid = finalTotal - userAmount1;
						finalTotal = paid;
						newCurrency1 = userAmount1;
						newUserAmount += newCurrency1;
					}
					else if(userCurrency1.equals ("Sickles"))
					{
						paid = finalTotal - (userAmount1 * 29);
						finalTotal = paid;
						newCurrency1 = userAmount1 * 29;
						newUserAmount += newCurrency1;
					}
					else if(userCurrency1.equals ("Galleons"))
					{
						paid = finalTotal - (userAmount1 * 493);
						finalTotal = paid;
						newCurrency1 = userAmount1 * 493;
						newUserAmount += newCurrency1;
					}
					//takes in user payment

					System.out.println("\nPayment: " + userAmount1 + " " + userCurrency1 + "\nYou have added " + userAmount1 
					+ " " + userCurrency1 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
					+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					while(paid != 0)
					{
						userAmount1 = scanner.nextInt();
						userCurrency1 = scanner.next();
						if(userCurrency1.equals ("Knuts"))
						{
							paid = finalTotal - userAmount1;
							finalTotal = paid;
							newCurrency1 = userAmount1;
							newUserAmount += newCurrency1;
						}
						else if(userCurrency1.equals ("Sickles"))
						{
							paid = finalTotal - (userAmount1 * 29);
							finalTotal = paid;
							newCurrency1 = userAmount1 * 29;
							newUserAmount += newCurrency1;
						}
						else if(userCurrency1.equals ("Galleons"))
						{
							paid = finalTotal - (userAmount1 * 493);
							finalTotal = paid;
							newCurrency1 = userAmount1 * 493;
							newUserAmount += newCurrency1;
						}
						System.out.println("\nPayment: " + userAmount1 + " " + userCurrency1 + "\nYou have added " + userAmount1 
						+ " " + userCurrency1 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
						+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");	
					}
					//allows user to continue to pay off debt
					if(paid < 0)
					{
						System.out.println("You have overpaid by " + (paid * -1) + " Knuts\nHere is your change "
						+ (paid * -1) + " Knuts\n Thank you for shopping!");
					}
					//gives user change
				}
				else if(numberOfSpells == 0 && hoursOfTutoring == 0)
				{
					System.out.println(name + ", here is your bill: ");
					System.out.println("No items purchased! Thanks anyway for stopping!");
				}
			}
		}
		else
		{
			//no password discount
			int newFinalTotal = finalTotal;
			System.out.println("Incorrect password, please enjoy our services at the regular prices. \n" + prices);
			System.out.println(options);
			int userChoice = scanner.nextInt();

			while(userChoice != 4)
			{
				if(userChoice == 1)
				{
					System.out.println("Here is your current order: " + hoursOfTutoring + " total hour(s) of tutuoring ordered.\n");
					System.out.println("How many hours of tutoring would you like?\n");
					hoursOfTutoring = scanner.nextInt();
					reducedPrice = hoursOfTutoring / 5;
					if(hoursOfTutoring < 0)
					{
						System.out.println("Negative numbers are taken as 0");
						hoursOfTutoring = 0;
					}
					else if(reducedPrice <= 6)
					{
						totalCostInKnuts = hoursOfTutoring * (2465 - (reducedPrice * 145));
					}
					else if(reducedPrice > 6)
					{
						totalCostInKnuts = hoursOfTutoring * 1740;
					}
				}
				else if(userChoice == 2)
				{
					System.out.println("Here is your current order: " + numberOfSpells + " spell(s) ordered.\n");
					System.out.println("How many spells would you like? (1-5)\n");
					numberOfSpells = scanner.nextInt();
					if(numberOfSpells > 5)
					{
						System.out.println("Number greater than 5 taken as 5.");
						numberOfSpells = 5;
						totalCostInKnuts = numberOfSpells * 1479;
						perSpellRate = 1479;
						//regular spell price for 5 spells
					}
					else if(numberOfSpells < 0)
					{
						System.out.println("Negative numbers are taken as 0");
						numberOfSpells = 0;
					}
					else if(numberOfSpells == 1 || numberOfSpells == 2)
					{
						totalCostInKnuts = numberOfSpells * 2465;
						perSpellRate = 2465;

						//regular spell price for 1 or 2 spells
					}
					else if(numberOfSpells == 3 || numberOfSpells == 4)
					{
						totalCostInKnuts = numberOfSpells * 1972;
						perSpellRate = 1972;
						//regular spell price for 3 or 4 spells
					}
					else if(numberOfSpells == 5)
					{
						totalCostInKnuts = numberOfSpells * 1479;
						perSpellRate = 1479;
						//regular spell price for 5 spells
					}
				}	
				else if(userChoice ==3)
				{
					System.out.println(prices);
				}

				System.out.println(options);
				userChoice = scanner.nextInt();
			}
			if(userChoice ==4)
			{
				if(hoursOfTutoring != 0)
				{
					double tax = (double) totalCostInKnuts * .05;
					int roundedTax = (int) tax;
					finalTotal = totalCostInKnuts + roundedTax;

					System.out.println(name + ", here is your bill: ");
					System.out.println("Hourly Tutoring:\n\nRegular rate: " + hourlyRate + "Cost of Tutoring: " + 
					totalCostInKnuts + " Knuts.\n" + "Ministry of Magic Tax: " + roundedTax + " Knuts.\nTotal: " + finalTotal + " Knuts.");
					//user bill
					System.out.println("\nPlease enter a payment amount in the following format:\n\n<amount><space><currency>" +
					"\n\nWhere <amount> = an integer\nWhere <space> = a blank space\nWhere<currency> = {Knuts, "
					+ "Sickles, Galleons}\n\nYou may enter as many times as you like. Each entry will be added" +
					" to your total until sufficient funds have been obtained.\n\nRecall the currency exchange:\n"
					+ "29 Knuts = 1 Sickle\n493 Knuts = 17 Sickles = 1 Galleon");
					int userAmount2 = scanner.nextInt();
					String userCurrency2 = scanner.next();
					paid = 0;
					paymentLeft = 0;
					int newUserAmount = 0;
					int newCurrency2 = 0;
					if(userCurrency2.equals ("knuts"))
					{
						paid = finalTotal - userAmount2;
						finalTotal = paid;
						newCurrency2 = userAmount2;
						newUserAmount += newCurrency2;
					}
					else if(userCurrency2.equals ("sickles"))
					{
						paid = finalTotal - (userAmount2 * 29);
						finalTotal = paid;
						newCurrency2 = userAmount2 * 29;
						newUserAmount += newCurrency2;
					}
					else if(userCurrency2.equals ("galleons"))
					{
						paid = finalTotal - (userAmount2 * 493);
						finalTotal = paid;
						newCurrency2 = userAmount2 * 493;
						newUserAmount += newCurrency2;
					}
					//takes in user payment
					System.out.println("\nPayment: " + userAmount2 + " " + userCurrency2 + "\nYou have added " + userAmount2 
					+ " " + userCurrency2 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
					+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					while(paid != 0)
					{
						userAmount2 = scanner.nextInt();
						userCurrency2 = scanner.next();
						if(userCurrency2.equals ("knuts"))
						{
							paid = finalTotal - userAmount2;
							finalTotal = paid;
							newCurrency2 = userAmount2;
							newUserAmount += newCurrency2;
						}
						else if(userCurrency2.equals ("sickles"))
						{
							paid = finalTotal - (userAmount2 * 29);
							finalTotal = paid;
							newCurrency2 = userAmount2 * 29;
							newUserAmount += newCurrency2;
						}
						else if(userCurrency2.equals ("galleons"))
						{
							paid = finalTotal - (userAmount2 * 493);
							finalTotal = paid;
							newCurrency2 = userAmount2 * 493;
							newUserAmount += newCurrency2;
						}
						System.out.println("\nPayment: " + userAmount2 + " " + userCurrency2 + "\nYou have added " + userAmount2 
						+ " " + userCurrency2 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
						+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					}
					//allows user to continue paying
					if(paid < 0)
					{
						System.out.println("You have overpaid by " + (paid * -1) + " Knuts\nHere is your change "
						+ (paid * -1) + " Knuts\n Thank you for shopping!");
					}
					//allows user to get remained
				}
				else if(numberOfSpells != 0)
				{
					double tax = (double) totalCostInKnuts * .05;
					int roundedTax = (int) tax;
					finalTotal = totalCostInKnuts + roundedTax;

					System.out.println(name + ", here is your bill: ");
					System.out.println("Per spell tutoring:\n\n" + numberOfSpells + " spell(s) ordered\nRegular rate per spell: " 
					+ perSpellRate + "\nCost of Spells : " + totalCostInKnuts + "\nMinistry of Magic tax: " + roundedTax
					+ "\nTotal: " + finalTotal + " Knuts.");
					System.out.println("\nPlease enter a payment amount in the following format:\n\n<amount><space><currency>" +
					"\n\nWhere <amount> = an integer\nWhere <space> = a blank space\nWhere<currency> = {Knuts, "
					+ "Sickles, Galleons}\n\nYou may enter as many times as you like. Each entry will be added" +
					" to your total until sufficient funds have been obtained.\n\nRecall the currency exchange:\n"
					+ "29 Knuts = 1 Sickle\n493 Knuts = 17 Sickles = 1 Galleon");
					int userAmount3 = scanner.nextInt();
					String userCurrency3 = scanner.next();
					paid = 0;
					paymentLeft = 0;
					int newUserAmount = 0;
					int newCurrency3 = 0;
					//initalizes new user input
					if(userCurrency3.equals ("knuts"))
					{
						paid = finalTotal - userAmount3;
						finalTotal = paid;
						newCurrency3 = userAmount3;
						newUserAmount += newCurrency3;
					}
					else if(userCurrency3.equals ("sickles"))
					{
						paid = finalTotal - (userAmount3 * 29);
						finalTotal = paid;
						newCurrency3 = userAmount3 * 29;
						newUserAmount += newCurrency3;
					}
					else if(userCurrency3.equals ("galleons"))
					{
						paid = finalTotal - (userAmount3 * 493);
						finalTotal = paid;
						newCurrency3 = userAmount3 * 493;
						newUserAmount += newCurrency3;
					}
					//takes in user payment
					System.out.println("\nPayment: " + userAmount3 + " " + userCurrency3 + "\nYou have added " + userAmount3 
					+ " " + userCurrency3 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
					+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					while(paid != 0)
					{
						userAmount3 = scanner.nextInt();
						userCurrency3 = scanner.next();
						if(userCurrency3.equals ("knuts"))
						{
							paid = finalTotal - userAmount3;
							finalTotal = paid;
							newCurrency3 = userAmount3;
							newUserAmount += newCurrency3;
						}
						else if(userCurrency3.equals ("sickles"))
						{
							paid = finalTotal - (userAmount3 * 29);
							newFinalTotal = paid;
							newCurrency3 = userAmount3 * 29;
							newUserAmount += newCurrency3;
						}
						else if(userCurrency3.equals ("galleons"))
						{
							paid = finalTotal - (userAmount3 * 493);
							finalTotal = paid;
							newCurrency3 = userAmount3 * 493;
							newUserAmount += newCurrency3;
						}
						System.out.println("\nPayment: " + userAmount3 + " " + userCurrency3 + "\nYou have added " + userAmount3 
						+ " " + userCurrency3 + " to your total\n" + "You have paid " + newUserAmount + " out of " + newFinalTotal 
						+ " Knuts\n" + name + ", you still owe " + paid + " Knuts");
					}
					//allows user to continue paying bill
					if(paid < 0)
					{
						System.out.println("You have overpaid by " + (paid * -1) + " Knuts\nHere is your change "
						+ (paid * -1) + " Knuts\n Thank you for shopping!");
					}
					//gives user change
				}
				else if(numberOfSpells == 0 && hoursOfTutoring == 0)
				{
					System.out.println(name + ", here is your bill: ");
					System.out.println("No items purchased! Thanks anyway for shopping!");
				}
			}
		}
		
	}
}