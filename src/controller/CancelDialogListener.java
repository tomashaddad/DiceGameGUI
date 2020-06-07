package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CancelDialogListener implements ActionListener
{
	
	private JDialog dialog;
	
	public CancelDialogListener(JDialog dialog)
	{
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		dialog.setVisible(false);
	}
}
