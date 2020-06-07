package view;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.AddPlayerDialogListener;
import controller.RollListener;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar
{
	private DiceFrame frame;

	public DiceToolbar(DiceFrame frame)
	{
		super("Dice Game Toolbar");
		
		this.frame = frame;
		
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		AbstractButton rollDiceButton = new JButton("Roll Dice");
		AbstractButton setBetButton = new JButton("Set bet");
		AbstractButton addPlayerButton = new JButton("Add player");
		
		String[] playerNames = {"Bob", "George", "Christine", "Cathy"};
		JComboBox<String> playerList = new JComboBox<>(playerNames);
		playerList.setSelectedIndex(0);
		
		rollDiceButton.addActionListener(new RollListener(frame.getGameEngine()));
		addPlayerButton.addActionListener(new AddPlayerDialogListener(frame));
		
		add(rollDiceButton);
		add(setBetButton);
		add(addPlayerButton);
		add(playerList);
	}

}
