import java.io.BufferedReader;
import java.util.*;

public class Lab12
{	
	// public static double max(double[] data)
	// {
	// 	double max = data[0];
	// 	for (int i = 1; i < data.length; i++)
	// 	{
	// 		if(data[i] > max)
	// 		{
	// 			max = data[i];
	// 		}
	// 	}
	// 	return max;
	// }
	public static double max(double [] data)
	{
    	return max_rec(data, 0);
	}
	public static double max(double [] data, int loc)
	{
   		// fill in this code
   		double largest = data[0];
   		while (loc < data.length-1) 
   		{
        //If current is the first element then override largest
        	if (loc == 0) 
        	{
            	largest = data[0];
        	}
        	if (largest < data[loc+1]) 
        	{
            	largest = data[loc+1];
            	System.out.println("New Largest : " + largest); //Just to track the change in largest value
        	}
        maxElement(data,loc+1,largest);
    	}
    	return largest;
	}
	// public static double min(double[] data)
	// {
	// 	double min = data[0];
	// 	for (int i = 1; i < data.length; i++)
	// 	{
	// 		if(data[i] < min)
	// 		{
	// 			min = data[i];
	// 		}
	// 	}
	// 	return min;
	// }
	// public static double sum(double[] data)
	// {
	// 	double sum = 0;
	// 	for (int i = 0; i < data.length; i++)
	// 	{
	// 		sum += data[i];
	// 	}
	// 	return sum;
	// }
	// public static double ave(double[] data)
	// {
	// 	double sum = 0;
	// 	for (int i = 0; i < data.length; i++)
	// 	{
	// 		sum += data[i];
	// 	}
	// 	double ave = sum / data.length;
	// 	return ave;
	// }
}