package view.dice;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import util.Rand;

/* 
 * This class defines the "cards" cycled through in DicePanel
 *  */

@SuppressWarnings("serial")
public class DicePairCard extends JPanel
{
	private DieGraphic die1;
	private DieGraphic die2;
	
	public DicePairCard(Color colour)
	{
		setLayout(new GridLayout(0, 2));
		
		die1 = new DieGraphic(Rand.getRandomNumberInRange(1, 6), colour);
		die2 = new DieGraphic(Rand.getRandomNumberInRange(1, 6), colour);
		
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
