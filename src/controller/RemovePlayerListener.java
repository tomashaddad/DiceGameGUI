package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Events;
import controller.game.GameController;
import model.interfaces.Player;

public class RemovePlayerListener implements ActionListener
{
	private GameController eventManager;
	private Player player;
	
	public RemovePlayerListener(GameController eventManager, Player player)
	{
		this.eventManager = eventManager;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		eventManager.getGameEngine().removePlayer(player);
		eventManager.firePropertyChange(Events.PLAYER_REMOVED, player, null);
	}
}
