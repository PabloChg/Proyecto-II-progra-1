import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
	
	public MainWindow() 
	{		
		// Set the window title
		super("Tabla de Posiciones");	
		
		// Create a new panel
		information = new JPanel();

		// Set the dimensions of the window
		this.setSize(800, 600);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );

		BorderLayout infoLayout = new BorderLayout();
		information.setLayout(infoLayout);

		// Create the table and the buttons of the window
		this.createTable();
		this.createButtons();
		this.createStatus();

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
			this.printPoints();
			statusText.setText("¡Refrescado correctamente!");
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
			this.teams = parser.parse(this.filePath, file);
			statusText.setText("File: " + this.file.getName());
			this.printPoints();
			this.fileChoosed = true;
		}
	}
	/**
	 * Print the final position table
	 */
	private void printPoints() 
	{
		int[] points = new int [this.teams.length];
		Team[] orderedTeams = new Team[this.teams.length];
		int highest = -1;
		
		// Save an array with the points of each team
		for (int index = 0; index < this.teams.length; index++)
		{
			points[index] = this.teams[index].getPoints();
		}
		
		// Position of the current highest-points team
		int currentHigh = 0;
		
		// Algorithm to order the teams based on their points
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

		//Remove the default table
		this.remove(this.table);
		//Create the new table
		this.table = new Table(orderedTeams);
		this.add(table, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();	
	}
	

	/**
	 * setup button function
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
				parser.writeOnCsv(file, names);
				statusText.setText("¡Archivo estructurado correctamente!");
			}

		}
		
	}
	/**
	 * 
	 * @param teams
	 * @return
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
