package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Events;
import controller.manager.EventManager;
import model.interfaces.Player;

public class RemovePlayerListener implements ActionListener
{
	private EventManager eventManager;
	private Player player;
	
	public RemovePlayerListener(EventManager eventManager, Player player)
	{
		this.eventManager = eventManager;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		eventManager.getGameEngine().removePlayer(player);
		eventManager.getListeners().firePropertyChange(Events.PLAYER_REMOVED, player, null);
	}
}
