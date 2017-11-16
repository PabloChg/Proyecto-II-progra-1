import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;

public class MainWindow extends JFrame
{
	private JPanel positionTable;
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
		this.add(table, BorderLayout.NORTH);
	}
	public void createButtons()
	{		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,1));
		JButton fileFinder = new JButton("Choose file");
		this.add(fileFinder);
		// FileChooser
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}

	}



	

	
}
