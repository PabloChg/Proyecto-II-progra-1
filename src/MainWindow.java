import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame
{
	private JPanel positionTable = null;
	
	private ButtonListener listener = new ButtonListener();
	
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
		this.createStatus();
	}
	
	/**
	 * Creates the table with the positions of each team
	 */
	public void createTable()
	{
		// Create the table and add it to the main window
		Table table = new Table();
		
		this.add(table, BorderLayout.NORTH);
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
		buttons.add(fileChooser, BorderLayout.WEST);
		fileChooser.addActionListener(listener);
		fileChooser.setActionCommand("file");
		
		
		JButton setUpButton = new JButton("Setup file");
		buttons.add(setUpButton, BorderLayout.EAST);
		setUpButton.addActionListener(listener);
		setUpButton.setActionCommand("setup");
		
		// Create the button that refreshes the table 
		JButton refreshButton = new JButton("Refresh");
		buttons.add(refreshButton, BorderLayout.CENTER);
		refreshButton.addActionListener(listener);
		refreshButton.setActionCommand("refresh");
		
		// Create the close window button
		JButton closeButton = new JButton("Close");
		buttons.add(closeButton, BorderLayout.EAST);
		closeButton.addActionListener(listener);
		closeButton.setActionCommand("close");
	
		
		// Add the buttons to the bottom of the main window
		this.add(buttons, BorderLayout.SOUTH);
	}
	public void createStatus() {
		JPanel status = new JPanel();
		Label text = new Label("Status");
		status.add(text);
		status.setBorder(new TitledBorder("status"));

		this.add(status, BorderLayout.CENTER);

		
	}
	
	
}
