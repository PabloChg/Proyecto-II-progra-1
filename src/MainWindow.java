import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends JFrame implements ActionListener
{	
	JFrame inputTeams = new JFrame("Teams");

	private FileParser parser = new FileParser();
	
	private Team team = new Team(null);
	
	private JPanel information = null;

	private File file = null;

	private String filePath = "";

	private boolean fileChoosed = false;

	private Label statusText = new Label();

	private int [][] results ;
	
	
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
		Table table = new Table();

		this.add(table, BorderLayout.CENTER);
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
		JButton fileChooser = new JButton("Choose File");
		buttons.add(fileChooser);
		fileChooser.addActionListener(this);
		fileChooser.setActionCommand("file");


		JButton setUpButton = new JButton("Structure file");
		buttons.add(setUpButton);
		setUpButton.addActionListener(this);
		setUpButton.setActionCommand("structure");

		// Create the button that refreshes the table 
		JButton refreshButton = new JButton("Refresh");
		buttons.add(refreshButton);
		refreshButton.addActionListener(this);
		refreshButton.setActionCommand("refresh");

		// Create the close window button
		JButton closeButton = new JButton("Exit");
		buttons.add(closeButton);
		closeButton.addActionListener(this);
		closeButton.setActionCommand("close");


		// Add the buttons to the bottom of the main window
		information.add(buttons, BorderLayout.SOUTH);
	}

	public void createStatus() 
	{
		JPanel status = new JPanel();

		statusText.setText("No file selected.");
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
		System.out.println("Close button clicked");
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
			System.out.println("Refreshing...");
			parser.parse(this.filePath, file);
			statusText.setText("Refreshed!");
			this.getPositions();
		}
		else
		{
			statusText.setText("Cannot refresh, file not chose");
			// Dialog that says it can't be refreshed because no file has been chose
		}
	}

	/**
	 * file button function
	 */
	public void fileButton() 
	{
		if ( fileChoosed() )
		{
			parser.parse(this.filePath, file);
			statusText.setText("File: " + this.file.getName());
			this.getPositions();
			this.fileChoosed = true;
		}
	}

	/**
	 * setup button function
	 */
	public void structureButton() 
	{

		if (this.file == null)
		{
			statusText.setText("Cannot structure csv file, select one first");
		}
		else
		{
			boolean teamsSet = false;

			int teams = 0;
			String quantity = JOptionPane.showInputDialog(inputTeams, "Write the amount of teams");

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
				statusText.setText("File structured succesfully!");
			}

		}

	}

	public String[] askNames(int teams)
	{
		String[] names = new String[teams];

		for (int team = 0; team < names.length; ++team)
		{
			names[team] = JOptionPane.showInputDialog(inputTeams, "Write the name of the team " + (team+1) );
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
			statusText.setText("Canceled file selection");
			return false;
		}
	}
	public void getPositions() {
		
		team.setMatches(this.parser.getTeamMatches());
		team.setTeamNames(this.parser.getTeams());
		results = new int [this.parser.getTeams().length][7];
		team.matchesResults();
	
	}

}
