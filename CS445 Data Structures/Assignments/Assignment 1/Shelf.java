package edu.pitt.cs.as1;

public class Shelf<T> implements ShelfInterface<T>
{
	//given variables
	private T[][][] shelf;
	private final int rows;
	private final int columns;
	private final int quantity;
	//constructor
	public Shelf(int rows, int columns, int quantity)
	{
		this.rows = rows;
		this.columns = columns;
		this.quantity = quantity;
		shelf = (T[][][])(new Object[rows][columns][quantity]);
	}
	public T get(int row, int column, int position)
	{
		return shelf[row][column][position];
	}
	public String getName(int row, int column, int position)
	{
		VendingItem test = (VendingItem) shelf[row][column][position];
		String name = test.getName();
		return name;
	}
	public void add(int row, int column, int position, T o)
	{
		//only add if o isn't null
		if(o != null)
		{
			shelf[row][column][position] = o;
		}
	}
	public void remove(int row, int column)
	{
		//shifts array left
    	for(int p = 0; p < quantity-1; p++)
    	{
    		shelf[row][column][p] = shelf[row][column][p+1];
    	}
    	shelf[row][column][quantity-1] = null;
	}
	public boolean contains(T entry)
	{
		//if entry isn't null, loop through 3d array to find entry
        if(entry != null)
        {
        	for(int i = 0; i < shelf.length; i++)
        	{
        		for(int j = 0; j < shelf[i].length; j++)
        		{
        			for(int k = 0; k < shelf[i][j].length; k++)
        			{
        				if(entry.equals(shelf[i][j][k]))
        				{
        					return true;
        				}
        			}
        		}
        	}
        }
        //if entry isn't in array, return false
        return false;
	}
	public boolean isEmpty(int row, int column, int position)
	{
		return shelf[row][column][position] == null;
	}
	public T[][][] toArray()
	{
		T[][][] arrayCopy = (T[][][]) new Object[rows][columns][quantity];
	    System.arraycopy(shelf, 0, arrayCopy, 0, quantity);
	    
		return arrayCopy;
	}
}