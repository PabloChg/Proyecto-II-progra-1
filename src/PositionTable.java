import javax.swing.JFrame;

public class PositionTable {
	 
	private MainWindow mainWindow = null;
	   /**
	    * @param args
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
