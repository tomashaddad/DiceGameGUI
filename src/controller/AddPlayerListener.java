package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Events;
import controller.manager.EventManager;
import model.SimplePlayer;
import model.interfaces.Player;
import util.Rand;
import view.AddPlayerDialog;

public class AddPlayerListener implements ActionListener
{
	private AddPlayerDialog dialog;
	private EventManager eventManager;

	public AddPlayerListener(AddPlayerDialog dialog, EventManager eventManager)
	{
		this.eventManager = eventManager;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String playerName = dialog.getUsername();
		String ID = String.valueOf(SimplePlayer.playerIDCounter++);
		int points = dialog.getPoints();
		int bet = dialog.getBet();
		
		Player newPlayer = new SimplePlayer(ID, playerName, points);
		newPlayer.setBet(bet);
		
		eventManager.getGameEngine().addPlayer(newPlayer);
		eventManager.getListeners().firePropertyChange(Events.PLAYER_ADDED, null, newPlayer);

		dialog.setVisible(false);
	}
}