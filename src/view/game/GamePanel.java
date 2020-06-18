package view.game;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controller.game.GameController;
import view.statusbar.DiceStatusBar;
import view.toolbar.DiceToolbar;

/* 
 * GamePanel acts as a container for the different regions of the main game area
 * to the right of the player summary panel.
 * 
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	private DicePanel dicePanel;
	private DiceToolbar diceToolbar;
	private DiceStatusBar diceStatusBar;
	
	public GamePanel(GameController gameController)
	{
		setLayout(new BorderLayout());
		
		dicePanel = new DicePanel(gameController);
		diceToolbar = new DiceToolbar(gameController);
		diceStatusBar = new DiceStatusBar(gameController);
		
		add(diceToolbar, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.CENTER);
		add(diceStatusBar, BorderLayout.SOUTH);
	}
}