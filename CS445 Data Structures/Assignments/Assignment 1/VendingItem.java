package edu.pitt.cs.as1;

public class VendingItem implements VendingItemInterface
{
	//given variables
	private final double price;
	private final String name;
	//constructor
	public VendingItem(double price, String name)
	{
		this.price = price;
		this.name = name;
	}
	//returns price
	public double getPrice()
	{
		return price;
	}
	//returns name
	public String getName()
	{
		return name;
	}
	//overrided toString method
	public String toString()
	{
		return this.name;
	}
}