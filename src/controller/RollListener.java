package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;

public class RollListener implements ActionListener
{
	private GameController gameController;
	
	public RollListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				gameController.rollPlayer(new SimplePlayer("1", "The Roller", 5000));
			}
		}.start();
	}
}
