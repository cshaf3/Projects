import java.util.Scanner;
import java.io.BufferedReader;

public class WEEK11PARTICIPATION_PAYROLL_SHAFER
{
	public static void main(String args[])
	{
		System.out.println("Enter name.");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		System.out.println("Enter id.");
		int idNumber = scanner.nextInt();

		System.out.println("Enter hours.");
		int hours = scanner.nextInt();	

		System.out.println("Enter rate.");
		double rate = scanner.nextDouble();	

		Payroll p1 = new Payroll(name,idNumber);

		p1.setHours(hours);
		p1.setRate(rate);

		System.out.printf(p1.getName() + p1.getidNumber() + p1.getHours() + p1.getRate());
	}
}