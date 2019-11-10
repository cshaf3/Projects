import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Thread;

public class RouletteWheel extends JPanel implements Runnable
{
	//initializes private variables
	private int value;
	private int remainder;
	private RParities parity;
	private RColors color;
	private RRanges range;
	private Random generator = new Random();
	private RouletteSquare [] squares = new RouletteSquare[37];
	private Activatable activate;
	private Thread runner;

	public RouletteWheel(Activatable a)
	{
		activate = a;
		//sets layout for roulette board
		setLayout(new GridLayout(5,8));
		//goes through array and adds each square to board
		for(int j = 0; j < squares.length; j++)
		{
			RouletteSquare s = new RouletteSquare(j);
			squares[j] = s;
			add(s);
		}
	}
	public RouletteResult spin()
	{
		//calls Thread runner to use the run() method
		this.runner = new Thread(this);
    	this.runner.start();
		//spinning and choosing random number 0-36
		value = generator.nextInt(37);
		//determining if even or odd
		remainder = value % 2;
		if(value == 0)
		{
			parity = RParities.None;
		}
		else if(remainder == 0)
		{
			parity = RParities.Even;
		}
		else
		{
			parity = RParities.Odd;
		}
		//setting colors
		if(value == 0)
		{
			color = RColors.Green;
		}
		else if(parity.equals(RParities.Odd))
		{
			if(((value >= 0) && (value <= 10)) || ((value >= 19) && (value <= 28)))
			{
				color = RColors.Red;
			}
			else
			{
				color = RColors.Black;
			}
		}
		else if(parity.equals(RParities.Even))
		{
			if(((value >= 0) && (value <= 10)) || ((value >= 19) && (value <= 28)))
			{
				color = RColors.Black;
			}
			else
			{
				color = RColors.Red;
			}
		}
		//determing if range is low or high
		if((value >= 1) && (value <= 18))
		{
			range = RRanges.Low;
		}
		else if((value >= 19) && (value <= 36))
		{
			range = RRanges.High;
		}
		else
		{
			range = RRanges.None;
		}
		//creates RouletteResult object
		RouletteResult output = new RouletteResult(color, range, parity, value);
		activate.activate();
		return output;
	}
	public int checkBet(RouletteBet b)
	{
		RBets betType = b.getBetType();
		String betValue = b.getBetValue();
		//determining if user's guess is equivalent to the true value
		if(betType == RBets.Value) 
		{
			if(Integer.valueOf(betValue) == value)
			{
				return 35;
			}
			else
			{
				return 0;
			}
		}
		//determining if user's guess is equivalent to the true parity
		if(betType == RBets.Parity)
		{
			if(RParities.valueOf(betValue) == parity)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		//determining if user's guess is equivalent to the true color
		if(betType == RBets.Color)
		{
			if(RColors.valueOf(betValue) == color)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		//determining if user's guess is equivalent to the true range
		if(betType == RBets.Range)
		{
			if(RRanges.valueOf(betValue) == range)
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
		return 0;
	}
	//deselects all squares
	public void set()
	{
		for(int i = 0; i < squares.length; i++)
		{
			squares[i].unChoose();
		}
	}
	//for a period of 5000 miliseconds, the square selection changes by 350 miliseconds
	public void run()
	{
		long delay = 350;
		long duration = 5000;
		long start = System.nanoTime();
		int highlightedValue = generator.nextInt(37);
		long end = System.nanoTime();
		long delta = end - start;  // this is the elapsed time
		long durNano = duration * 1000000;  // convert to nanoTime
		// Loop until the elapsed time is more than the requested duration
		while (delta <= durNano)  // keep iterating as long as the elapsed
		{						  // time is less than the duration
			highlightedValue = generator.nextInt(37);
			squares[highlightedValue].choose();
			try 
			{
				Thread.sleep(delay);  // sleep between changes
				squares[highlightedValue].unChoose();
			}
			catch (InterruptedException e)
			{  
				System.out.println("Problem with Thread!"); 
			}
					
			end = System.nanoTime();  // recalculate elapsed time
			delta = end - start;
		}
		squares[value].choose();
	}
}