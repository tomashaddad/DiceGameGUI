package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.game.GameController;
import view.toolbar.AddPlayerDialog;

public class AddPlayerDialogListener implements ActionListener
{
	private GameController gameController;

	public AddPlayerDialogListener(GameController gameController)
	{
		this.gameController = gameController;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Need frame to centre dialog with main window
		JButton button = (JButton) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(button);
		
		new AddPlayerDialog(frame, gameController).setVisible(true);
	}
}
