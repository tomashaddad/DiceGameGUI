package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import util.Rand;

@SuppressWarnings("serial")
public class PlayerDicePanel extends JPanel
{
	private DieGraphic die1 = new DieGraphic(Rand.getRandomNumberInRange(1, 6));
	private DieGraphic die2 = new DieGraphic(Rand.getRandomNumberInRange(1, 6));
	
	public PlayerDicePanel()
	{
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
