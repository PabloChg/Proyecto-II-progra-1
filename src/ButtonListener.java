import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 *
 */
public class ButtonListener implements ActionListener
{
	boolean fileChoosed = false;
	
	private FileParser parser = null;
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//TODO Preguntar si cada accion se puede dividir en metodos
		// o si deben estar todos en este metodo.
		
		switch(event.getActionCommand()) {
		case"close": this.closeButton(); break;
		case"refresh":this.refreshButton(); break;
		case"file": this.fileButton(); break;
		case"setup": this.setupButton(); break;
		
		}
		
	}	
	/**
	 * Close button function
	 */
	public void closeButton() {
		System.out.println("Close button clicked");
		// TODO Preguntar como hacer para cerrar sin el System.exit(0);
		System.exit(0);
	}
	/**
	 * Refresh button function
	 */
	public void refreshButton() {
		System.out.println("Refresh button clicked");

		if (fileChoosed())
		{
			System.out.println("Refreshing...");
			this.parser.parse();
		}
		else
		{
			System.err.println("Cannot refresh");
			// Dialog that says it can't be refreshed because no file has been chose
		}
	}
	/**
	 * file button function
	 */
	public void fileButton() {
		parser = new FileParser();
		
 		if ( parser.fileChooser() );
 		{
 			System.out.println("File has been chosen!");
 	 		parser.parse();
 	 		fileChoosed = true;
 		}
	}
	/**
	 * setup button function
	 */
	public void setupButton() {
		
	}
		


			
		
		
		
		
		
	
	
	
	
	public boolean fileChoosed()
	{
		return this.fileChoosed;
	}
	
}
