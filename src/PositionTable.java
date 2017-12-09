import javax.swing.JFrame;

/**
 * Program that makes a table with the positions of a soccer tournament
 */
public class PositionTable 
{
	/**
	 * Main window of the program
	 */
	private MainWindow mainWindow = null;
	/**
	 * main method
	 */
	public static void main(String[] args)
	{
		PositionTable positionTable = new PositionTable();
		positionTable.run();		   		   
	}

	public void run()
	{   
		this.mainWindow = new MainWindow();
		this.mainWindow.setVisible(true);
		this.mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
