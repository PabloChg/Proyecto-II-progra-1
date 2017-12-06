import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 *
 */
public class ButtonListener implements ActionListener
{
	boolean fileChoosed = false;
	private FileParser parser = new FileParser();
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		//TODO Preguntar si cada accion se puede dividir en metodos
		// o si deben estar todos en este metodo.
		
		if (event.getActionCommand().equals("close"))
		{
			System.out.println("Close button clicked");
			// TODO Preguntar como hacer para cerrar sin el System.exit(0);
			System.exit(0);
		}
		
		if (event.getActionCommand().equals("refresh"))
		{
			System.out.println("Refresh button clicked");
			
			if (this.fileChoosed)
			{
				System.out.println("Refreshing...");
				this.parser.parse();
			}
			else
			{
				System.err.println("Cannot refresh");
				// Dialog that says it cant be refreshed because no file has been chose
			}
		}
		
		if (event.getActionCommand().equals("file"))
		{
	 		if ( parser.fileChooser() );
	 		{
	 			System.out.println("File has been chosen!");
	 	 		parser.parse();
	 		}
	 		
 	 		this.fileChoosed = true;	
		}
	}
	
}