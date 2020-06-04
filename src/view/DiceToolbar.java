package view;

import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.RollListener;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar
{

	public DiceToolbar(DiceFrame frame)
	{
		super("Dice Game Toolbar");
		
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		
		AbstractButton rollDiceButton = new JButton("Roll Dice");
		AbstractButton setBetButton = new JButton("Set bet");
		AbstractButton addPlayerButton = new JButton("Add player");
		
		rollDiceButton.addActionListener(new RollListener(frame.getGameEngine()));
		
		add(rollDiceButton);
		add(setBetButton);
		add(addPlayerButton);
	}

}
