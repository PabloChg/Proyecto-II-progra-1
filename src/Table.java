import java.awt.*;

import javax.swing.*;

/**
 *
 */
public class Table extends JPanel{
	private JPanel positionTable;
	public Table() {
		setLayout(new BorderLayout());
		
		JButton firstButton= new JButton("Loading");
		add(firstButton, BorderLayout.SOUTH);
		
		positionTable = new JPanel();
		positionTable.setLayout(new GridLayout(13,10));
		
		add(positionTable, BorderLayout.CENTER);
		
		addButtons("#");
		addButtons("Equipo");
		addButtons("PJ");
		addButtons("V");
		addButtons("E");
		addButtons("D");
		addButtons("GF");
		addButtons("GC");
		addButtons("DG");
		addButtons("Pts");
		
		for (int index = 1; index < 13; index++) {
			addButtons("# "+ index);
			addButtons("Equipo " +index);
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");
			addButtons("-");

		}
		
	}
	
	private void addButtons(String buttonName) {
		JButton button = new JButton(buttonName);
		positionTable.add(button);
	}
}