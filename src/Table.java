import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 *
 */
public class Table extends JPanel
{	
	JPanel positionTable = new JPanel();
		
	public Table()
	{
		String[] headers = {"#", "Equipo", "PJ", "PG",
							"PE", "PP", "GF", "GC", "+/-", "Pts"};
		
		String[][] teamStats = new String[13][13];
		
		for (int col = 0; col < 13; ++col)
		{
			for (int row = 0; row< 13; ++row)
			{
				teamStats[col][row] = "MANTEL";
			}
		}
		
		JTable table = new JTable(teamStats, headers);
		
		TableColumn column = null;
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
		
		this.add(table);
	}

}