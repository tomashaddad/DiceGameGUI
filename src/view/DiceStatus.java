package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class DiceStatus extends JPanel
{

	public DiceStatus(GameEngine model)
	{
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
	}

}
