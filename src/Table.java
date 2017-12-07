import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;

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
		positionTable = new JPanel();
		positionTable.setLayout(new GridLayout(13,10));
		add(positionTable, BorderLayout.CENTER);

		add(positionTable, BorderLayout.CENTER);

		addText("#");
		addText("Equipo");
		addText("PJ");
		addText("V");
		addText("E");
		addText("D");
		addText("GF");
		addText("GC");
		addText("DG");
		addText("Pts");

		for (int index = 1; index < 13; index++) {
			addText("# "+ index);
			addText("Equipo " +index);
			addText("-");
			addText("-");
			addText("-");
			addText("-");
			addText("-");
			addText("-");
			addText("-");
			addText("-");

		}
	}
	private void addText(String buttonName) {
		Label label = new Label(buttonName);
		positionTable.add(label);
	}


}