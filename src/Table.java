import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Creates the table with the teams
 */
public class Table extends JPanel
{	

	/**
	 * Creates a table
	 */
	public Table()
	{
		this.setLayout(new GridLayout(13,10));
		this.setBorder(new TitledBorder("Tabla de posiciones"));

		// Add header
		addTextFont("Posición", "Posición");
		addTextFont("Equipos", "Equipos");
		addTextFont("PJ", "Partidos jugados");
		addTextFont("V", "Victorias");
		addTextFont("E", "Empates");
		addTextFont("D", "Derrotas");
		addTextFont("GF", "Goles a favor");
		addTextFont("GC", "Goles en contra");
		addTextFont("+/-", "Diferencia de gol");
		addTextFont("Pts", "Puntos");
		
		for (int index = 1; index < 13; index++) 
		{
			addTextFont("# "+ index, "");
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
	
	public Table (Team[] teams) 
	{
		this.setLayout(new GridLayout(teams.length + 1, 10));
		this.setBorder(new TitledBorder("Tabla de posiciones"));

		// Add header
		addTextFont("Posición", "Posición");
		addTextFont("Equipos", "Equipos");
		addTextFont("PJ", "Partidos jugados");
		addTextFont("V", "Victorias");
		addTextFont("E", "Empates");
		addTextFont("D", "Derrotas");
		addTextFont("GF", "Goles a favor");
		addTextFont("GC", "Goles en contra");
		addTextFont("+/-", "Diferencia de gol");
		addTextFont("Pts", "Puntos");
		
		for (int index = 0 ; index < teams.length; ++index) 
		{
			System.out.println(teams[index].getFinalName());
			addTextFont("# "+ (index + 1), "" );
			addText(teams[index].getFinalName());
			addText(Integer.toString(teams[index].getRoundsPlayed()));
			addText(Integer.toString(teams[index].getRoundsWon()));
			addText(Integer.toString(teams[index].getRoundsTied()));
			addText(Integer.toString(teams[index].getRoundsLost()));
			addText(Integer.toString(teams[index].getGoalsInFavor()));
			addText(Integer.toString(teams[index].getGoalsAgainst()));
			addText(Integer.toString(teams[index].getGoalsDifference()));
			addText(Integer.toString(teams[index].getPoints()));
		}
	}
	
	private void addText(String buttonName) 
	{
		JLabel text = new JLabel(buttonName, SwingConstants.CENTER);
		text.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(text);
	}
	
	private void addTextFont(String text, String tooltip)
	{
		JLabel headers = new JLabel(text, SwingConstants.CENTER);
		headers.setToolTipText(tooltip);
		headers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		headers.setFont(new Font("text",Font.BOLD, 15 ));

		this.add(headers);

	}

}