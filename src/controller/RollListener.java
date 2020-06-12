package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.manager.EventManager;
import model.SimplePlayer;
import model.interfaces.GameEngine;

public class RollListener implements ActionListener
{
	private EventManager gameController;
	
	public RollListener(EventManager eventManager)
	{
		this.gameController = eventManager;
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
