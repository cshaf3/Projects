/*
Author: Caroline Shafer
*/
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;

public class SHAFER_Wk6Participation_cas369
{
	public static void main(String[] args) throws IOException
	{
		//Writing:
		String filename;
		filename = JOptionPane.showInputDialog("Enter the filename.");
		
		PrintWriter outputFile = new PrintWriter(filename);
		outputFile.println("Caroline");
		outputFile.println("Sam");
		outputFile.println("Ryan");
		outputFile.close();

		//Reading:
		File file = new File("names.txt");
		Scanner inputFile = new Scanner(file);
		String friendName = inputFile.nextLine();
		System.out.println(friendName);

		friendName = inputFile.nextLine();
		System.out.println(friendName);

		friendName = inputFile.nextLine();
		System.out.println(friendName);

		//Appending:
		FileWriter fw = new FileWriter("names.txt", true);
		PrintWriter secondOutputFile = new PrintWriter(fw);
		secondOutputFile.println("Lauren");
		secondOutputFile.println("Will");
		secondOutputFile.println("Sophia");
		secondOutputFile.close();

		//Reading again:
		File secondFile = new File("names.txt");
		Scanner inputFileAgain = new Scanner(file);
		String name = inputFileAgain.nextLine();
		System.out.println(name);

		name = inputFileAgain.nextLine();
		System.out.println(name);

		name = inputFileAgain.nextLine();
		System.out.println(name);

		name = inputFileAgain.nextLine();
		System.out.println(name);

		name = inputFileAgain.nextLine();
		System.out.println(name);

		name = inputFileAgain.nextLine();
		System.out.println(name);
	}
}