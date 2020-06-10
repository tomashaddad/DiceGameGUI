package view;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.AddPlayerDialogListener;
import controller.GameController;
import controller.RollListener;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar
{
	private GameController gameController;

	public DiceToolbar(GameController gameController)
	{
		super("Dice Game Toolbar");
		
		this.gameController = gameController;
		
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		AbstractButton rollDiceButton = new JButton("Roll Dice");
		AbstractButton setBetButton = new JButton("Set bet");
		AbstractButton addPlayerButton = new JButton("Add player");
		
		String[] playerNames = {"Bob", "George", "Christine", "Cathy"};
		JComboBox<String> playerList = new JComboBox<>(playerNames);
		playerList.setSelectedIndex(0);
		
		rollDiceButton.addActionListener(new RollListener(gameController));
		addPlayerButton.addActionListener(new AddPlayerDialogListener(gameController));
		
		add(rollDiceButton);
		add(setBetButton);
		add(addPlayerButton);
		add(playerList);
	}
}
