import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class LoginPanel extends JPanel
{
	//declaring variables
	private RoulettePlayer player;
	private RPList rp;
	private JLabel title;
	private JLabel iDwords;
	private JLabel pass;
	private JLabel outcome;
	private JButton submit;
	private JTextField infoTwo;
	private JPasswordField info;
	private ControlListener theListener;
	private String iD;
	private String vtext;
	private LoginInterface needed;
	private JPanel left, right;

	public LoginPanel(RPList pl, LoginInterface i)
	{
		theListener = new ControlListener();
		needed = i;
		rp = pl;
		//creating JLabels/JButtons
		title = new JLabel("Please log into the site", SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 30));
		iDwords = new JLabel("User ID: ");
		iDwords.setFont(new Font("Serif", Font.BOLD, 20));
		pass = new JLabel("Password: ");
		pass.setFont(new Font("Serif", Font.BOLD, 20));
		submit = new JButton("Submit");
		submit.setFont(new Font("Serif", Font.BOLD, 20));
		info = new JPasswordField(10);
		infoTwo = new JTextField(10);
		theListener = new ControlListener();
		//adding action listeenr
		submit.addActionListener(theListener);
		left = new JPanel();
		right = new JPanel();
		//using setLayout to create readable panel
		setLayout(new GridLayout(4,1));
		this.add(title);
		left.setLayout(new GridLayout(2,1));
		left.add(iDwords);
		left.add(infoTwo);
		this.add(left);
		right.setLayout(new GridLayout(2,1));
		right.add(pass);
		right.add(info);
		this.add(right);
		this.add(submit);
	}
private class ControlListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			RoulettePlayer p;
			// rp = new RPList("players.txt");
			//handles when submit button is clicked
			if(e.getSource() == submit)
			{
				String enteredUser = infoTwo.getText();
				String enteredPass = info.getText();
				boolean userExists = rp.checkId(enteredUser);
				//firsts check if user id exists in RPLits file
				if(userExists == true)
				{
					String truePass = rp.getPassword(enteredUser);
					//if passwords are the same, retrieves RoulettePlayer object
					if(truePass.equals(enteredPass))
					{
						JOptionPane.showMessageDialog(null, "Welcome " + enteredUser + "!");
						p = rp.getPlayer(enteredUser);
						JOptionPane.showMessageDialog(null, p.toString());
						needed.setPlayer(p);
					}
					//message for passwords not matching
					else
					{
						JOptionPane.showMessageDialog(null, "Password doesn't match for " + enteredUser);
					}
				}
				//message for no id present
				else
				{
					JOptionPane.showMessageDialog(null, "Id '" + enteredUser + "' was not found");
				}
			}
		}
	}
}