package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.game.GameController;

public class HouseSummaryListener extends MouseAdapter
{
	private GameController gameController;
	
	public HouseSummaryListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		gameController.selectHouse();
	}
}