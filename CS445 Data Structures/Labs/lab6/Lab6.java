package cs445.lab6;

import java.util.Arrays;

public class Lab6
{
	/**
	* Reverses the order of the objects in an array using
	* recursion
	*/
	static <T> void reverse(T[] a)
	{
		T[] reversed = (T[]) new Object[a.length];
		int done = a.length-1;
		int counter = 0;
		if(done != 0)
		{
			reversed[counter] = a[done];
			done--;
			counter++;
		}
		a = reversed;
	}
	/**
	* Replaces each instance of character `before` with
	* character `after` within `str`, and returns the
	* resulting string (using recursion)
	*/
	static String replace(String str, char before, char after)
	{
		String r = "r";
		return r;
	}
}