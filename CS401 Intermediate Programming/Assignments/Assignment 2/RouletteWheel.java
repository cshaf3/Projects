import java.util.*;

public class RouletteWheel
{
	//initializes private variables
	private int value;
	private int remainder;
	private RParities parity;
	private RColors color;
	private RRanges range;
	private Random generator = new Random();
	
	public RouletteResult spinWheel()
	{
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
		RouletteResult output = new RouletteResult(color, range, parity, value);
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
}