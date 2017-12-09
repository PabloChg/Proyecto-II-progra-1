import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Parses the csv file set by the user
 */
public class FileParser 
{
	// Create a component
	private Component fileComponent;
	// Create a null reference of a file
	private File file = null;
	// Create a reference of the file path
	private String filePath = "";
	// Reference of the default separator of a csv file
	private static final String DEFAULT_SEPARATOR = ",";
<<<<<<< HEAD
	
	String[] names = null;
=======
	//String array with all the teams
	private String [] teamNames ;
	//String array with all the matches 
	private String [] matches ;
	private Team[] teams = null;
>>>>>>> 73b848b9c211b722ecd183d668b649fcf25878f7
	/**
	 * Parses the read csv file
	 */
	public Team[] parse(String path, File file)
	{        
		this.filePath = path;
		this.file = file;

		
		// Try to create a scanner that reads the selected file
		try (Scanner input = new Scanner( new FileReader(filePath) )) 
		{
			// Read the teams
<<<<<<< HEAD
			String line = input.nextLine();
			// Create an array with the teams' names
			this.names = line.split(DEFAULT_SEPARATOR);

			//Create the teams with the read numbers
			Team[] teams = setupTeams(names);
=======
			line = input.nextLine();
			// Create an array with the teams
			this.teamNames = line.split(DEFAULT_SEPARATOR);
			
			//Array with all the matches data, the required space is all the total matches
			this.matches = new String [(this.teamNames.length - 1)*4];
						
			int index = 0;
			// Read each round
			while (input.hasNextLine())  
			{           
>>>>>>> 73b848b9c211b722ecd183d668b649fcf25878f7
				
			teams = assignData(teams, input);
			
			for (int team = 0; team < teams.length ; ++team)
			{
				teams[team].calculateStatistics();
			}
			
			return teams;
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Team[] assignData(Team[] teams, Scanner input)
	{
		String line = "";
		
		// Read each round      
		while(input.hasNextLine())
		{	
			line = input.nextLine();
			
			if (line.equals("STOP"))
				break;
				
			String[] text = line.split(DEFAULT_SEPARATOR); 
			
			try
			{
				int homeGoals = Integer.parseInt(text[2]);
				int visitGoals = Integer.parseInt(text[4]);
				
				// Add goals to the home team
				for (int homeTeam = 0 ; homeTeam < teams.length ; ++homeTeam)
				{	
					if(teams[homeTeam].getName().equals(text[1]))
					{
						teams[homeTeam].addGoals(homeGoals, visitGoals);
						break;
					}
				}
				
				// Add goals to the visit team
				for (int visitTeam = 0 ; visitTeam < teams.length ; ++visitTeam)
				{	
					if (teams[visitTeam].getName().equals(text[3]))
					{
						teams[visitTeam].addGoals(visitGoals, homeGoals);
						break;
					}
				}
			}
			catch (NumberFormatException exception) // If the user didn't modify the HOME_GOALS or VISIT_GOALS
			{
				System.err.println("Cannot parse goals");
			}
		}
		
		
		return teams;
	}
	
	/**
	 * 
	 * @param file
	 * @param teams
	 */
	public void writeOnCsv(File file, String[] teams)
	{
		this.file = file;

		FileWriter writer = null;

		try 
		{
			writer = new FileWriter(this.file);

			writeTeams(teams, writer);
			writeRounds(teams, writer);

			System.out.println("CSV file created");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				writer.flush();
				writer.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param teams
	 * @param writer
	 */
	public void writeTeams(String[] teams, FileWriter writer)
	{
		for(int team = 0; team < teams.length; ++team)
		{
			try 
			{
				writer.append(teams[team]);
				if (team == teams.length - 1)
				{
					writer.append("\n");
				}
				else
				{
					writer.append(",");
				}

			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param teams
	 * @param writer
	 * @throws IOException
	 */
	public void writeRounds(String[] teams, FileWriter writer) throws IOException
	{
		int round = 1;

		for(int homeTeam = 0; homeTeam < teams.length; ++homeTeam)
		{
			for(int visitTeam = 0; visitTeam < teams.length; ++visitTeam)
			{
				if (homeTeam == visitTeam)
				{
					continue;
				}

				// Write the round
				writer.append(round + ",");
				//Write home team
				writer.append(teams[homeTeam] + ",");
				// Write space for the goals
				writer.append("HOME_GOALS" + ",");
				// Write visit team
				writer.append(teams[visitTeam] + ",");
				// Write space for the goals
				writer.append("VISIT_GOALS");
				writer.append("\n");

				++round;
			}

		}

		writer.append("STOP" + "\n");
	}


	/**
	 * Creates an array with all the teams
	 * @param data an array with the name of each team
	 */
	public Team[] setupTeams()
	{
		// TODO This method will be called from another class and return the array

		// Create the array
		this.teams = new Team[this.teamNames.length];

		// Create a team with each name read from the file
		for (int index = 0; index < this.teamNames.length; ++index )
		{
			Team team = new Team(this.teamNames[index]);

			this.teams[index] = team;

			System.out.printf("%s%d: %s%n", "Team #", index + 1, this.teams[index].getName());
		}
<<<<<<< HEAD
		
		return teams;
	}

=======
		this.matchesResults();

		return this.teams;
	}
	public void matchesResults()
	{
		for (int index = 0; index < this.matches.length; index++) {
			String [] match = matches[index].split(",");
			int homeGoals = Integer.parseInt(match[2]);
			int visitGoals = Integer.parseInt(match[4]);

			this.addResults(this.matchAnalyzer(match, 1), homeGoals, visitGoals);
			this.addResults(this.matchAnalyzer(match, 3), visitGoals, homeGoals);
>>>>>>> 73b848b9c211b722ecd183d668b649fcf25878f7

		}
	}
	public int matchAnalyzer(String [] singleMatch, int namePosition)
	{
		int teamsPosition = 0;
		for (int index = 0; index < this.teamNames.length; index++) {
			if (singleMatch[namePosition].equals(this.teamNames[index])) {
				teamsPosition = index;
			}
		}
		//return the teams position in the array of teamNames, which is equal to the array of teams
		return teamsPosition;
	}
	public void addResults(int position, int goalsMaked, int goalsReceived )
	{
		this.teams[position].addResults(goalsMaked, goalsReceived);
	}
}