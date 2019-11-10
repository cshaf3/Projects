public class CarMain
{
	public static void main(String[] args)
	{
		Car c1 = new Car ("BMW", 2016);
		Car c2 = new Car("Dodge", 2001);
		Car c3 = new Car("Audi", 2011);
		Car c4 = new Car(c3);

		for(int i = 0; i < 5; i++)
		{
			c2.accelerate();
		}

		boolean equal = c2.equal(c4);
	}
}