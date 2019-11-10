/* 
Author: Caroline Shafer
Original Creation Date: 3/22/18
Last Modified Date: 3/30/18
Description: Creating a customized welcome message and asking the end user for name.
*/

//These are all the imports
import java.io.BufferedReader;
import java.util.*;

public class P3A3_SHAFER_DIE_cas369
{
	//initializes the variables
	private int value;
	Random generator = new Random();

	//constructor
	public P3A3_SHAFER_DIE_cas369()
	{
		value = 0;
	}
	//mutator
	public void setRoll()
	{
		value = generator.nextInt(6) + 1;
	}
	//accessor
	public int getValue()
	{
		return value;
	}
}