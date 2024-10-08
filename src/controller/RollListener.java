package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;

public class RollListener implements ActionListener
{
	private GameController gameController;
	
	public RollListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		gameController.rollPlayer(gameController.getSelectedPlayer());
	}
}
