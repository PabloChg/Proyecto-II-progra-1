import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends JFrame implements ActionListener
{	
	JFrame inputTeams = new JFrame("Teams");

	private FileParser parser = new FileParser();
	
	private Team[] teams = null;
	
	private JPanel information = null;

	private File file = null;
	
	private String filePath = "";

	private boolean fileChoosed = false;

	private Label statusText = new Label();

	private Table table = new Table();
	
	/**
	 * Creates the main window of the program
	 */
	public MainWindow() 
	{		
		// Set the window title
		super("Tabla de Posiciones");	
		
		this.setIconImage(new ImageIcon("icon.png").getImage());
		
		// Set the dimensions of the window
		this.setSize(800, 600);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );

		// Create the information panel
		information = new JPanel();
		BorderLayout infoLayout = new BorderLayout();
		information.setLayout(infoLayout);
		information.setBackground(Color.WHITE);

		// Create the table, status pane and the buttons of the window
		this.createTable();
		this.createStatus();
		this.createButtons();
		

		this.add(information, BorderLayout.SOUTH);
	}

	/**
	 * Creates the table with the positions of each team
	 */
	public void createTable()
	{
		// Create the table and add it to the main window
		
		this.add(this.table, BorderLayout.CENTER);
	}

	/**
	 * Create the three buttons that will be used for the program
	 */
	public void createButtons()
	{	
		// Create a panel with the buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(0, 4));
		buttons.setBorder(new TitledBorder("Opciones"));
		buttons.setBackground(Color.WHITE);

		// Create the file chooser button
		JButton fileChooser = new JButton("Elegir archivo");
		buttons.add(fileChooser);
		fileChooser.addActionListener(this);
		fileChooser.setActionCommand("file");
		fileChooser.setToolTipText("Abre una ventana para elegir el archivo .csv a leer.");


		JButton setUpButton = new JButton("Estructurar archivo");
		buttons.add(setUpButton);
		setUpButton.addActionListener(this);
		setUpButton.setActionCommand("structure");
		setUpButton.setToolTipText("Reescribe el archivo seleccionado con el formato adecuado");

		// Create the button that refreshes the table 
		JButton refreshButton = new JButton("Refrescar");
		buttons.add(refreshButton);
		refreshButton.addActionListener(this);
		refreshButton.setActionCommand("refresh");
		refreshButton.setToolTipText("Refresca la tabla si se hicieron cambios al archivo seleccionado");

		// Create the close window button
		JButton closeButton = new JButton("Salir");
		buttons.add(closeButton);
		closeButton.addActionListener(this);
		closeButton.setActionCommand("close");
		closeButton.setToolTipText("Cierra esta ventana");


		// Add the buttons to the bottom of the main window
		information.add(buttons, BorderLayout.SOUTH);
	}

	public void createStatus() 
	{
		JPanel status = new JPanel();

		statusText.setText("No hay un archivo seleccionado");
		statusText.setPreferredSize(new Dimension(650, 20));

		status.add(statusText, BorderLayout.CENTER);
		status.setBorder(new TitledBorder("Estado"));
		status.setBackground(Color.WHITE);

		information.add(status, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent event)
	{		
		switch( event.getActionCommand() )
		{
			case "close": this.closeButton(); break;
			case "refresh":this.refreshButton(); break;
			case "file": this.fileButton(); break;
			case "structure": this.structureButton(); break;
		}
	}	

	/**
	 * Close button function
	 */
	public void closeButton() 
	{
		this.setVisible(false);
		this.dispose();
	}

	/**
	 * Refresh button function
	 */
	public void refreshButton() 
	{

		if (fileChoosed)
		{
			this.teams = parser.parse(this.filePath, this.file);

			if (this.checkSyntaxisFile() && this.checkGoalsStructure())
			{
				statusText.setText("¡Refrescado correctamente!");
				this.printPoints();
			}
		}
		else
		{
			statusText.setText("No se puede refrescar, elija un archivo primero");
			// Dialog that says it can't be refreshed because no file has been chosen
		}
	}
	/**
	 * file button function
	 */
	public void fileButton() 
	{
		if ( fileChoosed() )
		{
			this.teams = parser.parse(this.filePath, this.file);

			if ( this.checkSyntaxisFile() && this.checkGoalsStructure())
			{
				statusText.setText("File: " + this.file.getName());
				this.printPoints();
			}

			this.fileChoosed = true;
		}
	}
	
	/**
	 * Check if the file goal format is correct
	 * @return true if the format is correct
	 */
	public boolean checkGoalsStructure() 
	{
		if (!parser.getMissingGoals() )
		{
			return true;
		}
		else
		{	
			statusText.setText("Para mostrar la tabla, debe escribir los goles en todas las jornadas.");
			return false;
		}
	}
		
	/**
	 * Check if the file format is correct
	 * @return true if the format is not correct
	 */
	public boolean checkSyntaxisFile()
	{
		if ( parser.getFileProperlyModified() ) 
		{
			return true;
		} 
		else 
		{
			statusText.setText("Archivo csv con datos invalidos, estructurando. ");
			this.structureButton();
			return false;
		}
	}
	
	/**
	 * Print the final position table
	 */
	private void printPoints() 
	{
		int[] points = new int [this.teams.length];
		Team[] orderedTeams = new Team[this.teams.length];
		
		// Save an array with the points of each team
		for (int index = 0; index < this.teams.length; index++)
		{
			points[index] = this.teams[index].getPoints();
		}
		
		orderedTeams = orderByPoints(points);
		orderedTeams = orderByGoalDifference(orderedTeams);
		orderedTeams = orderByGoalsInFavor(orderedTeams);
		
		//Remove the default table
		this.remove(this.table);
		//Create the new table
		this.table = new Table(orderedTeams);
		
		this.add(table, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();	
	}
	
	/**
	 * Algorithm to order the teams based on their points
	 * @param points the points of each team
	 * @return an array of teams ordered by points
	 */
	public Team[] orderByPoints(int[] points)
	{
		Team[] orderedTeams = new Team[this.teams.length];
		
		int highest = -1;
		// Position of the current highest-points team
		int currentHigh = 0;
		

		// We need to pass at least one time in each team to store it
		for (int passade = 0; passade < teams.length; ++passade)
		{
			// We take the highest points from the array
			for (int team = 0; team < teams.length ; ++team)
			{
				if (points[team] > highest)
				{
					// Save the current highest points
					highest = points[team];
					// Save the position of the team with the current highest points
					currentHigh = team;
				} 
			}
			// Once we took the highest points, reset the variable
			highest = -1;
			// Set the highest points as -1 to ignore it and store the next one
			points[currentHigh] = -1;
			// Save the team with the current highest points until the last team is found
			orderedTeams[passade] = this.teams[currentHigh];
		}
		
		return orderedTeams;
	}
	
	/**
	 * Algorithm to order the teams by goal difference
	 * the teams were ordered by points previously
	 * @param orderedTeams an array with teams ordered by points
	 * @return an array of teams ordered by points and goal difference
	 */
	public Team[] orderByGoalDifference(Team[] orderedTeams) 
	{
		Team[] finalOrder = orderedTeams;
		
		for (int passade = 0; passade < orderedTeams.length; ++passade)
		{
			for (int team = 0; team < orderedTeams.length ; ++team)
			{
				if ( orderedTeams[passade].getPoints() == orderedTeams[team].getPoints() && passade < team)
				{
					if (orderedTeams[passade].getGoalsDifference() < orderedTeams[team].getGoalsDifference() )
					{
						Team aux = orderedTeams[passade];
						finalOrder[passade] = orderedTeams[team];
						finalOrder[team] = aux;
						
						if (passade + 2 < orderedTeams.length)
							passade++;
						else
							return finalOrder;	
					}
					else
					{
						finalOrder[passade] = orderedTeams[passade];
					}
				}
			}
		}
		return finalOrder;
	}
	
	/**
	 * Orders the teams by goals in favor, previously ordered by points and goal difference
	 * @param orderedTeams an array with teams previously ordered by points and goal difference
	 * @return an array of teams ordered by goals in favor
	 */
	public Team[] orderByGoalsInFavor(Team[] orderedTeams)
	{
		Team[] finalOrder = orderedTeams;
		
		for (int passade = 0; passade < orderedTeams.length; ++passade)
		{
			for (int team = 0; team < orderedTeams.length ; ++team)
			{
				if ( orderedTeams[passade].getPoints() == orderedTeams[team].getPoints() && passade < team)
				{
					if (orderedTeams[passade].getGoalsInFavor() < orderedTeams[team].getGoalsInFavor() )
					{
						Team aux = orderedTeams[passade];
						finalOrder[passade] = orderedTeams[team];
						finalOrder[team] = aux;
						
						if (passade + 2 < orderedTeams.length)
							passade++;
						else
							return finalOrder;	
					}
					else
					{
						finalOrder[passade] = orderedTeams[passade];
					}
				}
			}
		}
		return finalOrder;
	}
	
	/**
	 * Structures the csv file to match the parser requirements
	 */
	public void structureButton() 
	{
		if (this.file == null)
		{
			statusText.setText("No se puede estructurar, elija un archivo primero");
		}
		else
		{
			boolean teamsSet = false;

			int teams = 0;
			String quantity = JOptionPane.showInputDialog(inputTeams, "Escriba la cantidad de equipos");

			try
			{
				teams = Integer.valueOf(quantity);
				teamsSet = true;
			}
			catch (NumberFormatException e)
			{
				teamsSet = false;
			}

			if (teamsSet)
			{
				String[] names = askNames(teams);
				
				for (int name = 0; name < names.length; ++name)
				{
					if (names[name].equals(""))
						names[name] = "Sin Nombre " + (name + 1);
				}
				parser.writeOnCsv(file, names);
				statusText.setText("¡Archivo estructurado correctamente!");
			}
		}
	}
	
	/**
	 * Asks for the names of the teams
	 * @param teams the amount of teams
	 * @return an array with the name of each team
	 */
	public String[] askNames(int teams)
	{
		String[] names = new String[teams];

		for (int team = 0; team < names.length; ++team)
		{
			names[team] = JOptionPane.showInputDialog(inputTeams, "Escriba el nombre del equipo  " + (team+1) );
		}

		return names;
	}

	/**
	 * Creates and opens the window to choose the csv file
	 * @return true if a correct file was chose
	 */
	public boolean fileChoosed()
	{		
		// Create a fileChooser object on default folder
		JFileChooser fileChooser = new JFileChooser();

		// Add extension to make the chooser accept only csv files
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.CSV", "csv");

		//Set the filter as .csv
		fileChooser.setFileFilter(filter);

		int returnVal = fileChooser.showOpenDialog(this);

		if ( returnVal == JFileChooser.APPROVE_OPTION )
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
			statusText.setText("Selección de archivo cancelada");
			return false;
		}
	}


}
