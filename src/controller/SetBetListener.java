package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;
import model.interfaces.Player;
import view.toolbar.SetBetDialog;

public class SetBetListener implements ActionListener
{
	private SetBetDialog dialog;
	private GameController gameController;

	public SetBetListener(SetBetDialog dialog, GameController gameController)
	{
		this.gameController = gameController;
		this.dialog = dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int newBet = dialog.getBet();
		
		Player player = gameController.getSelectedPlayer();
		
		gameController.placeBet(player, newBet);
		dialog.setVisible(false);
	}
}
