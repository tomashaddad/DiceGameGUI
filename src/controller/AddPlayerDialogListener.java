package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import view.PlayerDialog;

public class AddPlayerDialogListener implements ActionListener
{
	GameController gameController;

	public AddPlayerDialogListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(button);
		
		new PlayerDialog(frame, gameController).setVisible(true);
	}
}
