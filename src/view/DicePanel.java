package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.GameController;

@SuppressWarnings("serial")
public class DicePanel extends JPanel
{	
	GameController gameController;
	DieGraphic die1 = new DieGraphic(4);
	DieGraphic die2 = new DieGraphic(2);

	
	public DicePanel(GameController gameController)
	{
		this.gameController = gameController;
		
		setPreferredSize(new Dimension(1000, 500));
		setLayout(new GridLayout(0, 2));
		add(die1);
		add(die2);
	}

	public DieGraphic getDie1()
	{
		return die1;
	}
	
	public DieGraphic getDie2()
	{
		return die2;
	}
}
