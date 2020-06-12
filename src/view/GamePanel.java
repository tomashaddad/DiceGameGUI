package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import controller.manager.EventManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	DicePanel dicePanel;
	DiceToolbar diceToolbar;
	
	public GamePanel(EventManager eventManager)
	{
		setLayout(new BorderLayout());
		
		dicePanel = new DicePanel(eventManager);
		diceToolbar = new DiceToolbar(eventManager);
		
		add(diceToolbar, BorderLayout.NORTH);
		add(dicePanel, BorderLayout.CENTER);		
	}
	
	public DicePanel getDicePanel()
	{
		return dicePanel;
	}
}
