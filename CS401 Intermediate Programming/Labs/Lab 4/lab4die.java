import java.util.*;
import java.io.BufferedReader;

public class lab4_die
{
	private int value;
	Random generator = new Random();

	public lab4_die()
	{
		value = 0;
	}

	public void setRoll()
	{
		value = generator.nextInt(6) + 1;
	}

	public int getValue()
	{
		return value;
	}
}