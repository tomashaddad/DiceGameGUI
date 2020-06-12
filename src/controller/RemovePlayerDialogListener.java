package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.manager.EventManager;
import model.interfaces.Player;

public class RemovePlayerDialogListener implements ActionListener
{
	EventManager eventManager;
	Player player;
	
	public RemovePlayerDialogListener(EventManager eventManager, Player player)
	{
		this.eventManager = eventManager;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		JFrame frame = (JFrame) SwingUtilities.getRoot(button);
		
//		new RemovePlayerDialog(frame, eventManager).setVisible(true);
	}
}