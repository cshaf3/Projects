import java.util.*;

public class RoulettePlayer
{
	//initializing private variables
	protected String name;
	protected double money;
	private boolean moneyLeft;
	private boolean same;
	protected String password;
	protected double debt;
	protected Question [] userQuestions;
	private String[] justQuestions = new String[2];
	private String[] justAnswers = new String[2];
	//constructor methods
	public RoulettePlayer()
	{
		name = "";
		money = 0;
		password = "";
		debt = 0;
	}
	public RoulettePlayer(String n, String p, double m, double d)
	{
		name = n;
		money = m;
		password = p;
		debt = d;
	}
	//constructor overload
	public RoulettePlayer(String n, String p)
	{
		name = n;
		password= p;
	}
	public void updateMoney(double delta) // update Player's money by amount delta (could be + or -)
	{
		money = money + delta;
	}
	public double getMoney() // return the current amount of money available to the Player
	{
		return money;
	}
	public String getName() // return the Player's name
	{
		return name;
	}
	public String getPassword()
	{
		return password;
	}
	public String toString() // return the player's information as a nicely formatted String.
	{
		StringBuilder b = new StringBuilder();
        b.append("Name: " + name + "\n");
        b.append("Cash: " + money + "\n");
        b.append("Debt: " + debt + "\n");
        return b.toString();
	}
	public boolean hasMoney() // return true if Player's money is > 0 and false otherwise.
	{
		if(money > 0)
		{
			moneyLeft = true;
		}
		else
		{
			moneyLeft = false;
		}
		return moneyLeft;
	}
	public void borrow(double addedDebt)
	{
		debt = debt + addedDebt;
		money = money + addedDebt;
	}
	public double getDebt()
	{
		return debt;
	}
	public void payBack(double paidDebt)
	{
		
		if(debt < paidDebt)
		{
			System.out.println("Amount: " + paidDebt + " is more than borrowed: " + debt);
			System.out.println("Only paying back: " + debt);
			money = money - (paidDebt - debt);
			debt = 0;
		}
		else if(money < paidDebt)
		{
			System.out.println("Amount: " + paidDebt + " is more than cash: " + money);
			System.out.println("Only paying back: " + money);
			debt = debt - money;
			money = 0;
		}
		else
		{
			debt = debt - paidDebt;
			money = money - paidDebt;
		}
	}
	public void addQuestions(Question [] questions)
	{
		userQuestions = questions;
		for(int i = 0; i < userQuestions.length; i++)
		{
			justQuestions[i] = userQuestions[i].getQ();
		}
	}
	public String [] getQuestions()
	{
		if(userQuestions.equals(null))
		{
			return null;
		}
		else
		{
			return justQuestions;
		}
	}
	public String [] getAnswers()
	{
		if(userQuestions.equals(null))
		{
			return null;
		}
		else
		{
			for(int i = 0; i < userQuestions.length; i++)
			{
				justAnswers[i] = userQuestions[i].getA();
			}
			return justAnswers;
		}
	}
	public void setPassword(String userPassword)
	{
		password = userPassword;
	}
	//prints out all known user data
	public void showAllData()
	{
		boolean hasQuestions = true;
		System.out.println("Name: " + name + "\nPassword: " + password + "\nCash: " + money + "\nDebt: " + debt);
		for(int i = 0; i < justQuestions.length; i++)
		{
			if(justQuestions[i] != null)
			{
				System.out.println("Q: " + justQuestions[i] + " A: " + userQuestions[i].getA());
			}
		
			else
			{
				hasQuestions = false;
			}
		}
		if(hasQuestions == false)
		{
			System.out.println("Questions: None");
		}
		
	}
	//compares the questions and answers
	public boolean matchQuestions(Question [] questions)
	{
		if((userQuestions[0] == null) || (userQuestions[1] == null))
		{
			return false;
		}
		else if(questions.length != 2)
		{
			return false;
		}
		else
		{
			if((questions[0].equals(userQuestions[0])) && (questions[0].equals(userQuestions[0]))
			&& (questions[1].equals(userQuestions[1])) && (questions[1].equals(userQuestions[1])))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	//creates StringBuilder and then returns
	public String saveString()
	{
		StringBuilder c = new StringBuilder();
        c.append(name + ",");
        c.append(password + ",");
        c.append(money  + ",");
        c.append(debt + ",");
        c.append(userQuestions[0].getQ() + ",");
        c.append(userQuestions[0].getA() + ",");
        c.append(userQuestions[1].getQ() + ",");
        c.append(userQuestions[1].getA() + ",");
        return c.toString();
	}
	//overloading equals method
	public boolean equals(RoulettePlayer player)
	{
		if((this.password == player.password) && (this.name == player.name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}