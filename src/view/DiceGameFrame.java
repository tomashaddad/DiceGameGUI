package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.interfaces.GameEngine;

public class DiceGameFrame extends JFrame
{
	public DiceGameFrame(GameEngine model)
	{
		super("Knockoff Dice Game");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(500, 500);
		setVisible(true);
	}
}