package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DicePanel extends JPanel
{	
	DiceFrame frame;
	DieGraphic die1 = new DieGraphic(4);
	DieGraphic die2 = new DieGraphic(2);

	
	public DicePanel(DiceFrame frame)
	{
		this.frame = frame;
		
		setPreferredSize(new Dimension(1000, 500));
		setLayout(new GridLayout(0, 2));
		
		add(die1);
		add(die2);
		repaint();
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
