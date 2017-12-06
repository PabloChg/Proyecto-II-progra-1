import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame
{
	private JPanel positionTable = null;
	
	public MainWindow() 
	{		
		super("Tabla de Posiciones");
		positionTable = new JPanel();
		this.setSize(800, 600);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );
		
		this.createTable();	
		this.createButtons();	
	}
	
	public void createTable()
	{
		Table table = new Table();
		this.add(table, BorderLayout.CENTER);
	}
	
	public void createButtons()
	{		
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout() );
		
		JButton fileChooser = new JButton("ChooseFile");
		buttons.add(fileChooser, BorderLayout.WEST);
		fileChooser.addActionListener(new ButtonListener());
		fileChooser.setActionCommand("file");
		
		JButton refreshButton = new JButton("Refresh");
		buttons.add(refreshButton, BorderLayout.CENTER);
		refreshButton.addActionListener(new ButtonListener());
		refreshButton.setActionCommand("refresh");
		
		JButton closeButton = new JButton("Close");
		buttons.add(closeButton, BorderLayout.EAST);
		closeButton.addActionListener(new ButtonListener());
		closeButton.setActionCommand("close");
		
		this.add(buttons, BorderLayout.SOUTH);
	}
	
}
