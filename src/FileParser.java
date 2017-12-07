import java.awt.Component;
import java.io.File;
import java.io.FileReader;
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
	
    /**
     * Parses the read csv file
     */
   	public void parse()
	{        
        printTest();
	}
   	
   	/**
   	 * Test that reads the csv file
   	 */
   	private void printTest()
   	{
        String line = "";
        
        // Try to create a scanner that reads the selected file
        try (Scanner input = new Scanner(new FileReader(filePath))) 
        {
        	// Read the teams
        	line = input.nextLine();
        	// Create an array with the teams
        	String[] text1 = line.split(DEFAULT_SEPARATOR);
        	
        	//Create the teams with the read numbers
        	setupTeams(text1);
        	
        	// Read each round
            while (input.hasNextLine())  
            {                	
            	String[] text = input.nextLine().split(","); 

                System.out.printf("%15s|%15s|%15s|%15s|%15s%n", text[0], text[1], text[2], text[3], text[4]);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
   	}
	
   	/**
   	 * Creates and opens the window to choose the csv file
   	 * @return true if a correct file was chose
   	 */
	public boolean fileChooser()
	{
		// Create a fileChooser object on default folder
		JFileChooser fileChooser = new JFileChooser();
		
		// Add extension to make the chooser accept only csv files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.CSV", "csv");
		 
		//Set the filter as .csv
		fileChooser.setFileFilter(filter);
		
		int result = fileChooser.showOpenDialog(fileComponent);
		
		
		//TODO JFileChooser is supposed to return an int depending on a certain action
		if (result == JFileChooser.APPROVE_OPTION) 
		{
		    //Set the selected file
		    this.file = fileChooser.getSelectedFile();	
		    
			// Print the selected path in case of debugging 
		    System.out.println("Selected file: " + this.file.getAbsolutePath());
		    
		    // Set the file path where to look the csv file
		    this.filePath = this.file.getAbsolutePath();
		    
		    return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Creates an array with all the teams
	 * @param data an array with the name of each team
	 */
	public void setupTeams(String[] data)
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
		
		System.out.println("ROUNDS= " + teams.length);
	}
}
