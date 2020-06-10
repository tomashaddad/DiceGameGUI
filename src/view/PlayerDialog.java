package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.AddPlayerListener;
import controller.CancelDialogListener;
import controller.GameController;

@SuppressWarnings("serial")
public class PlayerDialog extends JDialog
{
	private JTextField usernameInput;
	private JSpinner pointsInput;

	public PlayerDialog(JFrame frame, GameController gameController)
	{
		super(frame, "Add new player", false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		usernameInput = new JTextField();
		pointsInput = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
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
		

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 3;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(usernameInput, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(pointsInput, gbc);

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

	public String getUsername()
	{
		return usernameInput.getText();
	}

	public int getPoints()
	{
		return (int) pointsInput.getValue();
	}
}
