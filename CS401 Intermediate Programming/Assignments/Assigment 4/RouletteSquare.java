import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RouletteSquare extends JLabel
{
	//declaring variables
	private int val;
	private boolean alreadyChosen = false;
	private RParities parity;
	private RColors color;
	private RRanges range;

	public RouletteSquare(int n)
	{
		val = n;
		//creates easy-to-read format
		this.setText(Integer.toString(val));
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font("Serif", Font.BOLD, 30));
		//the if-else if statement determines what color the number should be
		if((this.getColor()) == (RColors.Red))
		{
			this.setForeground(Color.red);
		}
		else if((this.getColor()) == (RColors.Black))
		{
			this.setForeground(Color.black);
		}
		else if((this.getColor()) == (RColors.Green))
		{
			this.setForeground(Color.green);
		}
		setLayout(new BorderLayout());
	}
	//selects number and changes background
	public void choose()
	{
		this.setBackground(Color.cyan);
      	this.setOpaque(true);
      	alreadyChosen = true;
      	this.repaint();
	}
	//deselects number and changes background
	public void unChoose()
	{
		this.setBackground(Color.white);
      	this.setOpaque(true);
      	alreadyChosen = false;
      	this.repaint();
	}
	//boolean to determine if square is chosen
	public boolean isChosen()
	{
		return alreadyChosen;
	}
	//returns parity of spin
	public RParities getParity()
	{
		int remainder = val % 2;
		if(val == 0)
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
		return parity;
	}
	//returns color of spin
	public RColors getColor()
	{
		if(val == 0)
		{
			color = RColors.Green;
		}
		else if(this.getParity().equals(RParities.Odd))
		{
			if(((val >= 0) && (val <= 10)) || ((val >= 19) && (val <= 28)))
			{
				color = RColors.Red;
			}
			else
			{
				color = RColors.Black;
			}
		}
		else if(this.getParity().equals(RParities.Even))
		{
			if(((val >= 0) && (val <= 10)) || ((val >= 19) && (val <= 28)))
			{
				color = RColors.Black;
			}
			else
			{
				color = RColors.Red;
			}
		}
		return color;
	}
	//returns range of spin
	public RRanges getRange()
	{
		if((val >= 1) && (val <= 18))
		{
			range = RRanges.Low;
		}
		else if((val >= 19) && (val <= 36))
		{
			range = RRanges.High;
		}
		else
		{
			range = RRanges.None;
		}
		return range;
	}
	//returns value of spin
	public int getValue()
	{
		return val;
	}
}