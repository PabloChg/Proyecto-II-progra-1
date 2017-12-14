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
	
	String[] names = null;
	// Create a boolean to indicate if the file has a syntaxes error
	private boolean fileProperlyModified = true;
	// Boolean to identify if after Structure goals are missing to write
	private boolean golesPendientes = false;
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
			String line = input.nextLine();
			// Create an array with the teams' names
			this.names = line.split(DEFAULT_SEPARATOR);

			//Create the teams with the read numbers
			Team[] teams = setupTeams(names);
				
			teams = assignData(teams, input);
			
			for (int team = 0; team < teams.length ; ++team)
			{
				teams[team].calculateStatistics();
			}
			
			this.fileProperlyModified = true;
			return teams;
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean getGolesPendientes() {
		return this.golesPendientes;
	}
	public boolean getFileProperlyModified()
	{		
		return this.fileProperlyModified;
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
				// TODO Poner un mensaje que diga que tiene que meter los goles para que sirva
				
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
	public Team[] setupTeams(String[] data)
	{
		// TODO This method will be called from another class and return the array

		// Create the array
		Team[] teams = new Team[data.length];

		// Create a team with each name read from the file
		for (int index = 0; index < data.length; ++index )
		{
			Team team = new Team(data[index]);

			teams[index] = team;

			System.out.printf("%s%d: %s%n", "Team #", index + 1, teams[index].getName());
		}
		
		return teams;
	}


	
}
