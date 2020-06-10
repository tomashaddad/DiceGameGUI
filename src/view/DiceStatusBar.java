package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.GameController;

@SuppressWarnings("serial")
public class DiceStatusBar extends JPanel
{
	GameController gameController;

	public DiceStatusBar(GameController gameController)
	{
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		this.gameController = gameController;
	}
}
