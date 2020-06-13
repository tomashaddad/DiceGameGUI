package view.toolbar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.AddPlayerListener;
import controller.CancelDialogListener;
import controller.game.GameController;

@SuppressWarnings("serial")
public class AddPlayerDialog extends JDialog
{
	private JTextField usernameInput;
	private JSpinner pointsInput;
	private JSpinner betInput;

	public AddPlayerDialog(JFrame frame, GameController gameController)
	{
		super(frame, "Add new player", false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		SpinnerModel pointModel = new SpinnerNumberModel(0, 0, null, 1);
		SpinnerModel betModel = new SpinnerNumberModel(0, 0, null, 1);

		usernameInput = new JTextField("Ross Nye");
		pointsInput = new JSpinner(pointModel);
		betInput = new JSpinner(betModel);
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Cancel");		
		
		okButton.addActionListener(new AddPlayerListener(this, gameController));
		cancelButton.addActionListener(new CancelDialogListener(this));
		
		// 3 row, 4 column grid

		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.LINE_END;
		
		gbc.insets = new Insets(10, 10, 10, 10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(new JLabel("Player name: ", SwingConstants.RIGHT), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("Initial points: ", SwingConstants.RIGHT), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Bet: ", SwingConstants.RIGHT), gbc);

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(usernameInput, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(pointsInput, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(betInput, gbc);

		gbc.gridwidth = 1;
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		add(okButton, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		add(cancelButton, gbc);

		pack();
		setResizable(false);
		setLocationRelativeTo(frame);
	}

	public String getUsername()
	{
		return usernameInput.getText();
	}

	public int getPoints()
	{
		return (int) pointsInput.getValue();
	}

	public int getBet()
	{
		return (int) betInput.getValue();
	}
}
