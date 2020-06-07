package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import view.DiceFrame;
import view.PlayerDialog;

public class AddPlayerDialogListener implements ActionListener
{
	DiceFrame frame;

	public AddPlayerDialogListener(DiceFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new PlayerDialog(frame).setVisible(true);
			}
		});
	}
}
