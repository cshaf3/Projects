public class Car
{
	private int year;
	private String model;
	private int speed;
	private static int instanceNo;

	public Car(int y, String m)
	{
		year = y;
		model = m;
		speed = 0;
		instanceNo++;
	}

	public Car(Car object2)
	{
		year = object2.year;
		model = object2.model;
		speed = object2.speed;
		instanceNo++;
	}

	public int getYear()
	{
		return year;
	}

	public String getModel()
	{
		return model;
	}

	public int getSpeed()
	{
		return speed;
	}

	public int getInstanceNo()
	{
		return instanceNo;
	}

	public void accelerate()
	{
		speed = speed + 5;
	}

	public void brake()
	{
		speed = speed - 5;
	}

	public boolean equals(Car object2)
	{
    	boolean status;

    	if(model.equals(object2.model) && year == object2.year && speed == object2.speed)
    	{
    		status = true;
    	}
    	else
    	{
    		status = false;    		
    	}
    	return status;
	}

	public boolean faster(Car object2)
	{
		boolean status;

		if(speed > object2.speed)
		{
			status = true;
		}
		else if(speed < object2.speed)
		{
			status = false;
		}
		return status;
	}

	public String toString()
	{
		String str = "Model: " + model + "\nYear: " + year + "\nSpeed: " + speed;
		return str;
	}
}