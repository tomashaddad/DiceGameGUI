package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;
import model.interfaces.Player;

public class RemovePlayerListener implements ActionListener
{
	private GameController eventManager;
	
	public RemovePlayerListener(GameController eventManager)
	{
		this.eventManager = eventManager;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Player player = eventManager.getSelectedPlayer();
		eventManager.removePlayer(player);
	}
}