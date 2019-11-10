import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.Component;

public class RoulettePanel extends JPanel implements Activatable
{
	//declares private variables
	private RoulettePlayer player;
	private GameInterface gameinterface;
	private JLabel title;
	private JButton makeBet, spinButton, showInfo, quit, borrow, payBack;
	private ControlListener theListener;
	private RouletteWheel theWheel;
	private RBets type = null;
	private RouletteBet bet;
	private String userValue = "";
	private RouletteResult X;
	private int winnings;
	private double change, firstBet, debt, debtAvailable, paid;
	private NumberFormat formatter = new DecimalFormat("#0.00");
	private String sfirstBet, sdebt, spaid;
	private JPanel leftSide, rightSide;
	private RPList plist;

	public RoulettePanel(RoulettePlayer p, GameInterface g)
	{
		player = p;
		gameinterface = g;
		theListener = new ControlListener();
		//creating JLabel/JButtons
		title = new JLabel("Ready to play, " + p.getName(), SwingConstants.LEFT);
		title.setFont(new Font("Serif", Font.BOLD, 15));
		makeBet = new JButton("Make Bet");
		makeBet.setFont(new Font("Serif", Font.BOLD, 20));
		spinButton = new JButton("Spin Wheel");
		spinButton.setFont(new Font("Serif", Font.BOLD, 20));
		spinButton.setEnabled(false);
		showInfo = new JButton("Show My Info");
		showInfo.setFont(new Font("Serif", Font.BOLD, 20));
		quit = new JButton("Quit");
		borrow = new JButton("Borrow");
		borrow.setFont(new Font("Serif", Font.BOLD, 20));
		payBack = new JButton("Pay back debt");
		payBack.setFont(new Font("Serif", Font.BOLD, 20));
		quit.setFont(new Font("Serif", Font.BOLD, 20));
		//adding action listeners to all buttons
		quit.addActionListener(theListener);
		makeBet.addActionListener(theListener);
		spinButton.addActionListener(theListener);
		showInfo.addActionListener(theListener);
		borrow.addActionListener(theListener);
		payBack.addActionListener(theListener);
		theWheel = new RouletteWheel(this);
		//formatting JPanel so it is easy-to-read
		leftSide = new JPanel();
		leftSide.add(title);
		leftSide.add(makeBet);
		leftSide.add(spinButton);
		leftSide.add(showInfo);
		leftSide.add(borrow);
		leftSide.add(payBack);
		leftSide.add(quit);
		leftSide.setLayout(new GridLayout(7,1));
		this.add(leftSide);
		this.add(theWheel);
		this.setLayout(new GridLayout(1,2));
	}
	private class ControlListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//calls gameOver() method if quit button is pushed
			if(e.getSource() == quit)
			{
				gameinterface.gameOver();
			}
			//allows user to make bet
			else if(e.getSource() == makeBet)
			{
				//can't payback debt if there is no debt
				if(player.getDebt() == 0)
				{
					payBack.setEnabled(false);
				}
				//can't borrow money if they've already borrowed max
				if(player.getDebt() == 500)
				{
					borrow.setEnabled(false);
				}
				//won't allow player to bet unless they borrow				
				if(player.getMoney() == 0)
				{
					title.setText("Sorry but you are out of money.");
					spinButton.setEnabled(false);
					makeBet.setEnabled(false);
					payBack.setEnabled(false);
					borrow.setEnabled(true);
				}
				else
				{
					borrow.setEnabled(false);
					payBack.setEnabled(false);
					sfirstBet = JOptionPane.showInputDialog("How much to bet? $<=" + formatter.format(player.getMoney()));
					firstBet = Double.valueOf(sfirstBet);
					//if first bet is a negative, 0, or more money they have, JOptionPane will reappear
					while((firstBet > player.getMoney()) || (firstBet < 0) || (firstBet == 0))
					{
						sfirstBet = JOptionPane.showInputDialog("How much to bet? $<=" + formatter.format(player.getMoney()));
						firstBet = Double.valueOf(sfirstBet);
					}
					//allows user to choose bet type
					String userType = JOptionPane.showInputDialog("Please enter the type of your bet: [Value, Color, Range, Parity]");
					if(userType.equals("Color"))
			        {
			        	type = RBets.Color;
			            userValue = JOptionPane.showInputDialog("Enter your color [Red, Black]: ");
			        }
			        else if(userType.equals("Value"))
			        {
			            type = RBets.Value;
			            userValue = JOptionPane.showInputDialog("Enter your number [0-36]: ");
			        }
			        else if(userType.equals("Range"))
			        {
			            type = RBets.Range;
			            userValue = JOptionPane.showInputDialog("Enter your range [Low, High]: ");
			        }
			        else if(userType.equals("Parity"))
			        {
			            type = RBets.Parity;
			            userValue = JOptionPane.showInputDialog("Enter your parity [Even, Odd]: ");
			        }
			        //creates RouletteBet object
			        bet = new RouletteBet(type, userValue);
			        title.setText("You have bet $" + formatter.format(firstBet) + " on " + userValue);
			        //spinButton is now enabled
					spinButton.setEnabled(true);
					theWheel.set();
				}
			}
			else if(e.getSource() == spinButton)
			{
				theWheel.set();
				X = theWheel.spin();
				int res = theWheel.checkBet(bet);
				//after wheel is spun, prints results
				String result = "";
				if (res == 0)
				{
					result = "Losing bet!  Sorry";
					change = firstBet * -1;
	            	player.updateMoney(change);
				}
				else if (res == 1)
				{
					result = "Even money winner!";
					change = firstBet;
	            	player.updateMoney(change);
				}
				else if (res == 35)
				{
					result = "Big winner!";
					change = firstBet * 35;
	            	player.updateMoney(change);
				}
				JOptionPane.showMessageDialog(null, X);
				JOptionPane.showMessageDialog(null, result);
	          	//shows user their loss/win
	          	if(change > 0)
	          	{
	            	JOptionPane.showMessageDialog(null, "Even money winner gets $" + formatter.format(change));
	            	if(player.getDebt() > 0)
	            	{
	            		JOptionPane.showMessageDialog(null, "You have some debt that you may wish to pay off. You owe: $" + formatter.format(player.getDebt()));
	            		payBack.setEnabled(true);
	            	}
	          	}
	          	else if(change < 0)
	          	{
	            	JOptionPane.showMessageDialog(null, "Sorry but you lose your bet of $" + formatter.format(firstBet));
	          	}
	      		//updates user on their current funds
	          	title.setText("You now have: $" + formatter.format(player.getMoney()));
				makeBet.setEnabled(true);
				quit.setEnabled(true);
				borrow.setEnabled(true);
				payBack.setEnabled(true);
				if(player.getDebt() == 0)
				{
					payBack.setEnabled(false);
				}
				if(player.getDebt() == 500)
				{
					borrow.setEnabled(false);
				}
			}
			else if(e.getSource() == showInfo)
			{
				//displays users debt and current cash
				JOptionPane.showMessageDialog(null, player.toString());
				if(player.getDebt() == 0)
				{
					payBack.setEnabled(false);
				}
				if(player.getDebt() == 500)
				{
					borrow.setEnabled(false);
				}	
			}
			else if(e.getSource() == borrow)
			{
				//allows user to borrow from the house
				debtAvailable = 500 - player.getDebt();
				sdebt = JOptionPane.showInputDialog("Enter the amount you would like to borrow ($<=" + formatter.format(debtAvailable) + ")");
				debt = Double.valueOf(sdebt);
				while((debt > debtAvailable) || (debt < 0))
				{
					sdebt = JOptionPane.showInputDialog("Enter the amount you would like to borrow ($<=" + formatter.format(debtAvailable) + ")");
					debt = Double.valueOf(sdebt);
				}
				player.borrow(debt);
				makeBet.setEnabled(true);
				title.setText("You now have: $" + formatter.format(player.getMoney()));
				if(player.getDebt() == 0)
				{
					payBack.setEnabled(false);
				}
				if(player.getDebt() == 500)
				{
					borrow.setEnabled(false);
				}
			}
			else if(e.getSource() == payBack)
			{
				//allows user to pay back the house
           		spaid = JOptionPane.showInputDialog("How much would you like to pay back ($<=" + formatter.format(player.getDebt()) + ")");
           		paid = Double.valueOf(spaid);
           		while((paid > player.getDebt()) || (paid < 0) || (paid > player.getMoney()))
           		{
           			spaid = JOptionPane.showInputDialog("How much would you like to pay back ($<=" + formatter.format(player.getDebt()) + ")");
         			paid = Double.valueOf(spaid);
          		}
           		if((paid <= player.getDebt()) && (paid > 0) && (paid <= player.getMoney()))
          		{
           			JOptionPane.showMessageDialog(null, "Great - you are paying back $" + formatter.format(paid) + " of your debt");
           			player.payBack(paid);
           			title.setText("You now have: $" + formatter.format(player.getMoney()));
           		}
           		if(player.getDebt() == 0)
				{
					payBack.setEnabled(false);
				}
				if(player.getDebt() == 500)
				{
					borrow.setEnabled(false);
				}
			}
		}
	}
	//activate method called by RouletteWheel
	public void activate()
	{
		spinButton.setEnabled(false);
		makeBet.setEnabled(false);
		quit.setEnabled(false);
		borrow.setEnabled(false);
		payBack.setEnabled(false);
	}
}