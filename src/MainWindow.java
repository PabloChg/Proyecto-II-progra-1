import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;

public class MainWindow extends JFrame implements MouseListener
{
	private JPanel positionTable;
	public MainWindow() 
	{		
		super("Tabla de Posiciones");
		positionTable = new JPanel();
		this.setSize(800, 600);

		BorderLayout mainLayout = new BorderLayout();
		this.setLayout( mainLayout );
		
		this.createIndicators();
		this.createTable();	
		this.createButtons();		
	}
	
	public void createIndicators()
	{
		JPanel indicators = new JPanel();
		indicators.setLayout(new BorderLayout() );
		
		JLabel teams = new JLabel("Teams: #");
		
		indicators.add(teams, BorderLayout.NORTH);
		
		this.add(indicators, BorderLayout.NORTH);
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
		
		JButton fileFinder = new JButton("Choose file");
		buttons.add(fileFinder, BorderLayout.WEST);
		fileFinder.addMouseListener(this);
		
		JButton refreshButton = new JButton("Refresh");
		buttons.add(refreshButton, BorderLayout.CENTER);
		refreshButton.addMouseListener(this);
		
		
		JButton closeButton = new JButton("Close");
		buttons.add(closeButton, BorderLayout.EAST);
		closeButton.addMouseListener(this);
		
		this.add(buttons, BorderLayout.SOUTH);
	}

	public void fileChooser()
	{
		// FileChooser
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) 
		{
		    File selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		System.out.println("closed");
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	

	
}
