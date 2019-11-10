import java.io.BufferedReader;
import java.util.*;

public class Lab6
{
	public static void main(String[] args)
	{
		System.out.println("How many items will be entered?");
		Scanner scanner = new Scanner(System.in);
  		int items = scanner.nextInt();

  		double[] data;
        data = new double[items];

        for (int i = 0; i < data.length; i++)
		{
			System.out.print("Enter number " + i + ": ");
			data[i] = scanner.nextDouble();
		}

		System.out.println("The maximum is: " + Lab12.max(data));
		// System.out.println("The minimum is: " + Arrays.min(data));
		// System.out.println("The sum is: " + Arrays.sum(data));
		// System.out.println("The average is: " + Arrays.ave(data));
	}
}