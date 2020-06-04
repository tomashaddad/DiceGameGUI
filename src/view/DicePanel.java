package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class DicePanel extends JPanel
{
	DieGraphic die1 = new DieGraphic(4);
	DieGraphic die2 = new DieGraphic(2);
	
	public DicePanel(DiceFrame frame)
	{
		setPreferredSize(new Dimension(1000, 500));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		add(die1);
		add(die2);
	}

	public void setDie1Graphic(DieGraphic die)
	{
		die1 = die;
		revalidate();
		repaint();
	}
	
	public void setDie2Graphic(DieGraphic die)
	{
		die2 = die;
		revalidate();
		repaint();
	}
}
