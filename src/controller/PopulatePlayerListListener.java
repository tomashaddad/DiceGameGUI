package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.game.GameController;
import model.SimplePlayer;
import view.model.ViewModel;

public class PopulatePlayerListListener implements ActionListener
{
	private GameController gameController;
	
	public PopulatePlayerListListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Fjord", 1000), 10);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Jester", 250), 50);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Beauregard", 300), 60);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Nott", 314), 159);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Caleb", 1234), 321);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Caduceus", 420), 100);
		gameController.addPlayer(new SimplePlayer(String.valueOf(ViewModel.playerIDCounter++), "Yasha", 10), 1);
	}
}
