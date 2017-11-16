import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 *
 */
public class Table extends JPanel
{	
	JPanel positionTable = new JPanel();
	
	public void dTable() 
	{
		setLayout(new BorderLayout());
		
		positionTable.setLayout(new GridLayout(13,10));
		
		add(positionTable, BorderLayout.CENTER);
		
		addButtons("#");
		addButtons("Equipo");
		addButtons("PJ");
		addButtons("V");
		addButtons("E");
		addButtons("D");
		addButtons("GF");
		addButtons("GC");
		addButtons("DG");
		addButtons("Pts");
		
		for (int index = 1; index < 13; index++) 
		{
			addButtons("# "+ index);
			addButtons("Equipo " +index);
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");

		}
		
	}
	
	private void addButtons(String buttonName) 
	{
		JButton button = new JButton(buttonName);
		this.positionTable.add(button);
	}
	
	public Table()
	{
		String[] headers = {"#", "Equipo", "PJ", "PG",
							"PE", "PP", "GF", "GC", "+/-", "Pts"};
		
		String[][] teamStats = new String[13][13];
		
		JTable table = new JTable(teamStats, headers);
		
		TableColumn column = null;
		for (int col = 0; col < 5; col++) 
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