package view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.PopulatePlayerListListener;
import controller.game.GameController;

/* Game menu populates the game with dummy data for easier testing */

@SuppressWarnings("serial")
public class GameMenu extends JMenuBar
{
	public GameMenu(GameController gameController)
	{
		JMenu options = new JMenu("Options");
		options.setMnemonic(KeyEvent.VK_F);
		JMenuItem populate = new JMenuItem("Populate");
		populate.setMnemonic(KeyEvent.VK_P);
		populate.addActionListener(new PopulatePlayerListListener(gameController));
		options.add(populate);
		add(options);
	}
}