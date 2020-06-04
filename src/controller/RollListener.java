package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;

public class RollListener implements ActionListener
{
	private GameEngine model;
	
	public RollListener(GameEngine model)
	{
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				model.rollPlayer(new SimplePlayer("1", "The Roller", 5000), 100, 1000, 100, 50, 500, 50);
			}
		}.start();
	}
}
