package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;
import view.model.ViewModel;
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
		String id = String.valueOf(ViewModel.playerIDCounter++); // player ID is a simple incrementing static variable
		String name = dialog.getUsername();
		int points = dialog.getPoints();
		int bet = dialog.getBet();

		Player player = new SimplePlayer(id, name, points);
		
		dialog.setVisible(false);
		gameController.addPlayer(player, bet);
	}
}