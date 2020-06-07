package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DiceFrame;
import view.PlayerDialog;
import view.SinglePlayerSummary;

public class AddPlayerListener implements ActionListener
{
	private PlayerDialog playerDialog;
	private DiceFrame frame;

	public AddPlayerListener(PlayerDialog playerDialog, DiceFrame frame)
	{
		this.playerDialog = playerDialog;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		frame.getDiceSummaryPanel().getInnerPanel()
				.add(new SinglePlayerSummary(playerDialog.getUsername(), playerDialog.getPoints(), 0));
		frame.revalidate();
		frame.repaint();

		playerDialog.setVisible(false);
	}
}
