package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.GameEngine;

public class RollListener implements ActionListener
{
	private GameController eventManager;
	
	public RollListener(GameController eventManager)
	{
		this.eventManager = eventManager;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				eventManager.rollPlayer(eventManager.getSelectedPlayer());
			}
		}.start();
	}
}
