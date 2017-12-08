import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;

/**
 * Creates the table with the teams
 */
public class Table extends JPanel
{	

	// Create the header of the table
	private static final String[] header = {"#", "Equipo", "PJ", "PG",
			"PE", "PP", "GF", "GC", "+/-", "Pts"};

	/**
	 * Creates a table
	 */
	public Table()
	{
		this.setLayout(new GridLayout(13,10));
		this.setBorder(new TitledBorder("Tabla de posiciones"));

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


	private void addText(String buttonName) 
	{
		Label text = new Label(buttonName);
		this.add(text);
	}


}