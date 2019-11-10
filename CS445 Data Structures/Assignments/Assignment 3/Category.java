package edu.pitt.cs.as4;

/**
 * A class that represents a category of products in a stock management system
 * @author Jon Rutkauskas
 * @author Brian Nixon
 * @version 1.0
 */
public class Category{
	private ListInterface<Product> products;
	private String categoryName;


	public Category(String categoryName) {
		// TODO: Complete this method
		this.categoryName = categoryName;
		products = new ArrayList<Product>();
	}

	// returns the name of this category
	public String getCategoryName() {
		// TODO: Complete this method
		return categoryName;
	}

	// adds a single product to this category
	public void addProduct(Product prod) {
		// TODO: Complete this method
		products.add(prod);
	}

	// returns a product entry given a string of the product's name
	public Product findProductByName(String productName) {
		// TODO: Complete this method
		try
		{
			int size = products.getSize();
			for(int i = 0; i < size; i++)
			{
				Product test = products.get(i);
				if(test.getItemName().equals(productName))
				{
					return test;
				}
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("null");
		}
		return null;
	}

	// removes a product entry from this category and returns it
	public Product removeProductByName(String productName) {
		// TODO: Complete this method
		try
		{
			int size = products.getSize();
			for(int i = 0; i < size; i++)
			{
				Product test = products.get(i);
				if(test.getItemName().equals(productName))
				{
					products.remove(i);
					return test;
				}
			}
		}
		catch(NullPointerException e)
		{
			System.out.println("List is empty");
		}
		return null; // or throw exception?
	}

	// returns the number of products in this category
	public int getSize() {
		// TODO: Complete this method
		return products.getSize();
	}

	// returns the total number of items stocked in this category (sum of all quantities)
	public int getTotalQuantityOfStock() {
		// TODO: Complete this method
		int total = 0;
		int size = products.getSize();
		for(int i = 0; i < size; i++)
		{
			Product test = products.get(i);
			total += test.getQuantityInStock();
		}
		return total;
	}

	// returns the value of all products in the system: Sum(Price * Quantity) for each Product in this category
	public double getTotalValue() {
		// TODO: Complete this method
		int size = products.getSize();
		double sum = 0;
		int quantity = 0;
		double value = 0;
		double price = 0;
		for(int i = 0; i < size; i++)
		{
			Product test = products.get(i);
			price = test.getPrice();
			quantity = test.getQuantityInStock();
			value = (price*quantity);
			sum += value;
		}
		return sum;
		/*
		price
		quantity
		price * quantity
		sum += (price*quantity)
		*/
	}

	// returns a new List containing all products in this category.  Do not directly return the private backing List
	public ListInterface<Product> getAllProducts() {
		// TODO: Complete this method
		ListInterface<Product> temp = new ArrayList<Product>();
		int size = products.getSize();
		for(int i = 0; i < size; i++)
		{
			Product p = products.get(i);
			temp.add(p);
		}
		return temp;
	}

	
}