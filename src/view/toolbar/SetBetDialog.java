package view.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.CancelDialogListener;
import controller.SetBetListener;
import controller.game.GameController;

public class SetBetDialog extends JDialog
{
	private JSpinner betInput;
	
	public SetBetDialog(JFrame frame, GameController gameController)
	{
		super(frame, "Set player bet", true);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		int maxBet = gameController.getSelectedPlayer().getPoints();
		
		/* Minimum bet of 1 is assumed since players cannot be created with 0 or less
		 * points, and players are removed automatically when they reach 0 points. */
		SpinnerNumberModel pointModel = new SpinnerNumberModel(0, 1, maxBet, 1);
		
		betInput = new JSpinner(pointModel);
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");

		okButton.addActionListener(new SetBetListener(this, gameController));
		cancelButton.addActionListener(new CancelDialogListener(this));
		
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		
		// first row: 4-cell wide prompt informing user of available points for selected player

		gbc.gridwidth = 4;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel(gameController.getSelectedPlayer().getPlayerName()
				+ " has " + maxBet + " points to bet."), gbc);
		
		// second row: new bet input
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("New bet: ", SwingConstants.RIGHT), gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(betInput, gbc);
		
		// third row: 1-cell wide OK and cancel buttons in columns 3 and 4
		
		gbc.gridwidth = 1;
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		add(okButton, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		add(cancelButton, gbc);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(frame);
	}
	
	public int getBet()
	{
		return (int) betInput.getValue();
	}
}
