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
	GameController eventManager;

	public AddPlayerDialogListener(GameController eventManager)
	{
		this.eventManager = eventManager;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(button);
		
		new AddPlayerDialog(frame, eventManager).setVisible(true);
	}
}
