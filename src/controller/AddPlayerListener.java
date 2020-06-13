package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import constants.Events;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;
import util.Rand;
import view.toolbar.AddPlayerDialog;

public class AddPlayerListener implements ActionListener
{
	private AddPlayerDialog dialog;
	private GameController gameController;

	public AddPlayerListener(AddPlayerDialog dialog, GameController gameController)
	{
		this.gameController = gameController;
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
		
		gameController.addNewPlayer(newPlayer);
		
//		gameController.getGameEngine().addPlayer(newPlayer);
//		gameController.setSelectedPlayer(newPlayer);
//		
//		gameController.firePropertyChange(Events.PLAYER_ADDED, null, newPlayer);
		
		dialog.setVisible(false);
	}
}