import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame
{
	private JPanel positionTable = null;
	
	public MainWindow() 
	{		
		// Set the window title
		super("Tabla de Posiciones");
		
		// Create a new panel
		positionTable = new JPanel();
		
		// Set the dimensions of the window
		this.setSize(800, 600);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );
		
		// Create the table and the buttons of the window
		this.createTable();	
		this.createButtons();	
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
		
		
		
		// Create the file chooser button
		JButton fileChooser = new JButton("Choose File");
		buttons.add(fileChooser, BorderLayout.WEST);
		fileChooser.addActionListener(new ButtonListener());
		fileChooser.setActionCommand("file");
		
		
		JButton setUpButton = new JButton("Setup file");
		buttons.add(setUpButton, BorderLayout.EAST);
		setUpButton.addActionListener(new ButtonListener());
		setUpButton.setActionCommand("setup");
		
		// Create the button that refreshes the table 
		JButton refreshButton = new JButton("Refresh");
		buttons.add(refreshButton, BorderLayout.CENTER);
		refreshButton.addActionListener(new ButtonListener());
		refreshButton.setActionCommand("refresh");
		
		// Create the close window button
		JButton closeButton = new JButton("Close");
		buttons.add(closeButton, BorderLayout.EAST);
		closeButton.addActionListener(new ButtonListener());
		closeButton.setActionCommand("close");
	
		
		// Add the buttons to the bottom of the main window
		this.add(buttons, BorderLayout.SOUTH);
	}
	
	
}
