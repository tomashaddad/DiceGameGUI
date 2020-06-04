package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class GameMenu extends JMenuBar
{
	public GameMenu()
	{
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F); // if you hold ALT+F it opens the FILE menu
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_E); // after opening file menu, ALT+E closes window
		exit.addActionListener(e -> {System.exit(0);}); // TODO: LAMBDA!?!?!?
		file.add(exit);
		add(file);
	}
}
