import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * Creates the table with the teams
 */
public class Table extends JPanel
{	
	// Create a panel with the table
	JPanel positionTable = new JPanel();
		
	// Create the header of the table
	private static final String[] header = {"#", "Equipo", "PJ", "PG",
						"PE", "PP", "GF", "GC", "+/-", "Pts"};
	
	/**
	 * Creates a table
	 */
	public Table()
	{
		// Create a tentative table
		// TODO The table dimensions will be according to the teams read from the csv file
		String[][] teamStats = new String[13][13];
		
		// Fill the table with the stats of the teams
		// TODO This will be filled with the teams data
		for (int col = 0; col < 13; ++col)
		{
			for (int row = 0; row< 13; ++row)
			{
				teamStats[col][row] = "Test";
			}
		}
		
		// Create a table with the teams stats and the header
		JTable table = new JTable(teamStats, header);
		
		TableColumn column = null;
		// Set some dimensions for the table to avoid extra space
		for (int col = 0; col < 10; col++) 
		{
			column = table.getColumnModel().getColumn(col);

			if (col == 0)
				column.setPreferredWidth(20);
			else if(col == 1)
				column.setPreferredWidth(100);
			else
				column.setPreferredWidth(70);   
		}
		
		// Add the created table to the main window
		this.add(table);
	}

}