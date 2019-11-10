/*
Author: Caroline Shafer
*/

import javax.swing.JOptionPane;

public class SHAFER_Wk7Participation_cas369
{
	public static void main(String[] args)
	{
		welcome("Hello to You!");

		int num1;
		String input;

		input = JOptionPane.showInputDialog("Enter a number.");
		num1 = Integer.parseInt(input);

		int num2;

		input = JOptionPane.showInputDialog("Enter another number.");
		num2 = Integer.parseInt(input);

		int result = sum(num1, num2);
	}

	public static void welcome(String msg)
	{
		String message;
		JOptionPane.showMessageDialog(null, msg);
	}

	public static int sum(int num1, int num2)
	{
		result = num1 + num2;
		return result;
	}
}