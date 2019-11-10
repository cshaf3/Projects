// CS 0401 Fall 2018
// Lab 11
// You must modify this file so that it works as described in the lab handout.
import java.util.*;
import java.io.*;
public class Lab11
{
	public static void main(String [] args)
	{
		Scanner inScan, fScan = null;
		int [] A = new int[5];
		inScan = new Scanner(System.in);
		String fName = "";
		boolean done = false;
		while(done == false)
		{
			try
			{
				System.out.println("Please enter the file to read from: ");
				fName = inScan.nextLine();
				fScan = new Scanner(new File(fName));
				done = true;
			}
			catch(FileNotFoundException e)
			{
				System.out.println("Your file is invalid -- please re-enter");
				fName = inScan.nextLine();
				done = false;
			}
		}
		String nextItem = "";
		int nextInt = 0;
		int i = 0;
		while (fScan.hasNextLine())
		{
			try
			{
				nextItem = fScan.nextLine();
				nextInt = Integer.parseInt(nextItem);
				A[i] = nextInt;
				i++;
			}
			catch(NumberFormatException e)
			{
				System.out.println(nextItem + " is not an integer -- ignored.");
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				int [] B = new int[A.length * 2];
				for(int k = 0; k < A.length; k++)
				{
					B[k] = A[k];
				}

				System.out.println("Resizing array from " + A.length + " to " + B.length);
				A = B;
				A[i] = nextInt;
				i++;
			}
		}
		System.out.println("Here are your " + i + " items:");
		for (int j = 0; j < i; j++)
		{
			System.out.println(A[j] + " ");
		}
	}
}