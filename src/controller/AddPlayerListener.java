package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import view.PlayerDialog;
import view.SinglePlayerSummary;

public class AddPlayerListener implements ActionListener
{
	private PlayerDialog dialog;
	private GameController gameController;

	public AddPlayerListener(PlayerDialog dialog, GameController gameController)
	{
		this.gameController = gameController;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String playerName = dialog.getUsername();
		int points = dialog.getPoints();
		
		gameController.addPlayer(playerName, points);

		dialog.setVisible(false);
	}
}