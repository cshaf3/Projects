import java.util.*;
import java.io.*;
import java.io.BufferedWriter;

public class RPList
{
   	private RoulettePlayer [] plist;
   	private int size;
   	private String fileName;

	public RPList(String f) throws IOException
	{
		fileName = f;
		this.populateList();
	}
	//reads in file and creates array of RoulettePlayer
   	public void populateList() throws IOException
   	{
   		Scanner S = new Scanner(new FileInputStream(fileName));
   		BufferedReader br = new BufferedReader(new FileReader(fileName));
   		String line = null;
		line = br.readLine();
		size = Integer.parseInt(line);
		String name;
		String password;
		double money;
		double debt;
		Question[] questions = new Question[2];
		String st = "";
		plist = new RoulettePlayer[size];
 		String[] lines = new String[size];

  		for(int i = 0; i < size; i++)
  		{
  			st = br.readLine();
    		lines[i] = st;
    	}
    	for(int i = 0; i < lines.length; i++)
    	{
			String[] tokens = lines[i].split(",");
			name = tokens[0];
			password = tokens[1];
			money = Double.parseDouble(tokens[2]);
			debt = Double.parseDouble(tokens[3]);
			RoulettePlayer newPlayer = new RoulettePlayer(name, password, money, debt);
			if(tokens.length > 4)
			{
				String questionOne = tokens[4];
				String answerOne = tokens[5];
				String questionTwo = tokens[6];
				String answerTwo = tokens[7];

				Question one = new Question(questionOne, answerOne); 
				Question two = new Question(questionTwo, answerTwo);
				questions[0] = one;
				questions[1] = two;
				newPlayer.addQuestions(questions);
			}
			plist[i] = newPlayer;
		}
	}
	//adds RoulettePlayer to array
	public boolean add(RoulettePlayer player)
	{
		boolean full = false;
		//checks if array is full
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i] == null)
			{
				full = false;
			}
			else
			{
				full = true;
			}
		}
		//if full, resizes array
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i].equals(player))
			{
				return false;
			}
		}
		if(full == true)
		{
			RoulettePlayer[] doubled = new RoulettePlayer[2*plist.length];
        	for(int i = 0; i < plist.length; i++)
        	{
        		doubled[i] = plist[i];
        	}
        	doubled[size] = player;
        	plist = doubled;
        	size++;
    	}
    	//if not full, adds player
    	else
    	{
    		for(int i = 0;i < plist.length; i++)
    		{
    			if(plist[i] == null)
    			{
    				plist[i] = player;
    			}
    		}
    	}
    	return true;
	}
	//returns logical size
	public int getSize() throws IOException
	{
		return size;
	}
	//returns physical size
	public int getASize() throws IOException
	{
		return plist.length;
	}
	//loops through array and compares id and password
	public RoulettePlayer getPlayerPassword(String name, String password)
	{
		for(int i = 0; i < size; i++)
		{
			if((name.equals(plist[i].name)) && (password.equals(plist[i].password)))
			{
				return plist[i];
			}
		}
		return null;
	}
	//loops through plist array and quest array to compare questions
	public RoulettePlayer getPlayerQuestions(String name, Question[] quest)
	{				
		for(int i = 0; i < size; i++)
		{
			if(name.equals(plist[i].name))
			{
				if(quest == null)
				{
					return null;
				}
				else if(plist[i].userQuestions == null)
				{
					return null;
				}
				else
				{
					for(int j = 0; j < quest.length; j++)
					{
						if(quest[j].equals(plist[i].userQuestions[j]))
						{
							return plist[i];
						}
						else
						{
							return null;
						}
					}
				}
			}
		}
		return null;
	}
	//checks if id exists
	public boolean checkId(String id)
	{
		for(int i = 0; i < size; i++)
		{
			if(plist[i] == null)
			{
				System.out.println("Value is null");
			}
			else if(id.equals(plist[i].name))
			{
				return true;
			}
		}
		return false;
	}
	public String toString() // return the player's information as a nicely formatted String.
	{
		String b = "";
		for(int i = 0; i < size; i++)
		{
			b += (plist[i].toString());
		}
		return b;
	}
	//returns players questions
	public String[] getQuestions(String playerName)
	{
		for(int i = 0; i < size; i++)
		{
			if(playerName.equals(plist[i].name))
			{
				return plist[i].getQuestions();
			}
		}
		return null;
	}
	//returns players answers
	public String[] getAnswers(String playerName)
	{
		for(int i = 0; i < size; i++)
		{
			if(plist[i].name.equals(playerName))
			{
				for(int o = 0; o < 1; o++)
				{
					return plist[i].getAnswers();
				}
			}
		}
		return null;
	}
	//adds new player to file in a way it can be read in again
	public void saveList()
	{
		try
		{
			StringBuilder c = new StringBuilder();
			c.append(size + "\n");
			for(int i = 0; i < size; i++)
			{
        		c.append(plist[i].name + "," + plist[i].password + "," + plist[i].money + "," + plist[i].debt);
        		if(plist[i].userQuestions != null)
        		{
        			c.append(plist[i].userQuestions[0] + "," + plist[i].userQuestions[0].getA() + "," + plist[i].userQuestions[1]
        			+ "," + plist[i].userQuestions[1].getA());
        		}
        		c.append("\n");
			}
			PrintWriter printWriter = new PrintWriter (fileName);
        	printWriter.println(c);
        	printWriter.close ();
		}
		catch (IOException e)
		{
			System.out.println("Problem with file -- cannot save");
		}
	}
	//extra credit calculating and returing user average
	public double getAverage()
	{
		double added = 0;
		for(int i = 0; i < size; i++)
		{
			added += plist[i].money;
		}
		double average = added / size;
		return average;
	}
	//returns password
	public String getPassword(String name)
	{
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i].name.equals(name))
			{
				return plist[i].password;
			}
		}
		return null;
	}
	//returns debt
	public double getDebt2(String name)
	{
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i].name.equals(name))
			{
				return plist[i].debt;
			}
		}
		return 0.0;
	}
	//returns money
	public double getMoney2(String name)
	{
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i].name.equals(name))
			{
				return plist[i].money;
			}
		}
		return 0.0;
	}
	//returns name of player
	public String getName(String name)
	{
		for(int i = 0; i < plist.length; i++)
		{
			if(plist[i].name.equals(name))
			{
				return plist[i].name;
			}
		}
		return null;
	}
}