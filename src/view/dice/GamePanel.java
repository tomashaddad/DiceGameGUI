package view.dice;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controller.game.GameController;
import view.toolbar.DiceToolbar;

/* 
 * GamePanel acts as a container for the different regions of the main game area
 * to the right of the player summary panel. *
 * 
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	DicePanel dicePanel;
	DiceToolbar diceToolbar;
	
	public GamePanel(GameController gameController)
	{
		setLayout(new BorderLayout());
		
		dicePanel = new DicePanel(gameController);
		diceToolbar = new DiceToolbar(gameController);
		
		add(diceToolbar, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.CENTER);		
	}
}
