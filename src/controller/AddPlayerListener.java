package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(dialog);

		String id = String.valueOf(ViewModel.playerIDCounter++);
		String playerName = dialog.getUsername();
		int points = dialog.getPoints();
		int bet = dialog.getBet();

		Player newPlayer = new SimplePlayer(id, playerName, points);
		
		if (newPlayer.setBet(bet))
		{
			gameController.addPlayer(newPlayer, bet);
			dialog.setVisible(false);
		}
		
		else if (bet > points)
		{
			JOptionPane.showMessageDialog(frame, "You cannot set a bet higher than your points!");
		}
		
		else
		{
			JOptionPane.showMessageDialog(frame, "You cannot set a bet of 0!");
		}
	
	}
}