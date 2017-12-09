import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

		addTextFont("#");
		addTextFont("Equipos");
		addTextFont("PJ");
		addTextFont("V");
		addTextFont("E");
		addTextFont("D");
		addTextFont("GF");
		addTextFont("GC");
		addTextFont("DG");
		addTextFont("Pts");
		for (int index = 1; index < 13; index++) {
			addTextFont("# "+ index);
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
	public Table (Team [] teams, int[] position) 
	{
		this.setLayout(new GridLayout(teams.length+1,10));
		this.setBorder(new TitledBorder("Tabla de posiciones"));

		addTextFont("#");
		addTextFont("Equipos");
		addTextFont("PJ");
		addTextFont("V");
		addTextFont("E");
		addTextFont("D");
		addTextFont("GF");
		addTextFont("GC");
		addTextFont("DG");
		addTextFont("Pts");
		
		for (int index = 1; index < teams.length+1; index++) {
			addTextFont("# "+ index );
			addText(teams[position[position.length-index]].getName());
			addText(Integer.toString(teams[position[position.length-index]].getRoundsPlayed()));
			addText(Integer.toString(teams[position[position.length-index]].getRoundsWon()));
			addText(Integer.toString(teams[position[position.length-index]].getRoundsTied()));
			addText(Integer.toString(teams[position[position.length-index]].getRoundsLost()));
			addText(Integer.toString(teams[position[position.length-index]].getGoalsInFavor()));
			addText(Integer.toString(teams[position[position.length-index]].getGoalsAgainst()));
			addText(Integer.toString(teams[position[position.length-index]].getGoalsDifference()));
			addText(Integer.toString(teams[position[position.length-index]].getPoints()));
		}
	}
	private void addText(String buttonName) 
	{
		Label text = new Label(buttonName);
		this.add(text);
	}
	private void addTextFont(String text)
	{
		Label headers = new Label(text);
		headers.setFont(new Font("text",Font.BOLD, 15 ));
		this.add(headers);

	}

}