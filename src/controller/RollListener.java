package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;

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
		eventManager.rollPlayer(eventManager.getSelectedPlayer());
	}
}
