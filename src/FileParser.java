import java.awt.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 */
public class FileParser 
{
	private Component fileComponent;
	private File file = null;
	private String filePath = "";
    private static final String DEFAULT_SEPARATOR = ",";
	
   	public void parse()
	{        
        printTest();
	}
   	
   	private void printTest()
   	{
        String line = "";
        
        try (Scanner input = new Scanner(new FileReader(filePath))) 
        {
        	line = input.nextLine();
        	String[] text1 = line.split(DEFAULT_SEPARATOR);
        	
        	setupTeams(text1);
        	
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
	
	public void setupTeams(String[] data)
	{
		int rounds = Integer.parseInt(data[0]);
		
		Team[] teams = new Team[data.length];
		
		System.out.println("ROUNDS= " + rounds);
		
		for (int index = 1; index < data.length; ++index )
		{
			Team team = new Team(data[index]);
			
			teams[index] = team;
			
			System.out.printf("%s%d: %s%n", "Team #", index, teams[index].getName());
		}
	}
}
