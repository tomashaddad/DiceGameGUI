package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;

public class ResetBetListener implements ActionListener
{
	private GameController gameController;
	
	public ResetBetListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		gameController.resetBet(gameController.getSelectedPlayer());
	}
}