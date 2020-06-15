package view.toolbar;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JToolBar;

import constants.Events;
import controller.AddPlayerDialogListener;
import controller.RemovePlayerListener;
import controller.RollListener;
import controller.game.GameController;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar implements PropertyChangeListener
{
	private GameController gameController;
	
	private AbstractButton addPlayerButton;
	private AbstractButton removePlayerButton;
	private AbstractButton setBetButton;
	private AbstractButton rollDiceButton;
	
	public DiceToolbar(GameController gameController)
	{
		super("Dice Game Toolbar");
		
		this.gameController = gameController;
		
		setBackground(Color.decode("#B8C4BB"));
		setFloatable(false);
		
		addPlayerButton = new JButton("Add player");
		removePlayerButton = new JButton("Remove player");
		setBetButton = new JButton("Set bet");
		rollDiceButton = new JButton("Roll player dice");
		
		rollDiceButton.setEnabled(false);
		setBetButton.setEnabled(false);
		removePlayerButton.setEnabled(false);

		removePlayerButton.addActionListener(new RemovePlayerListener(gameController));
		rollDiceButton.addActionListener(new RollListener(gameController));
		addPlayerButton.addActionListener(new AddPlayerDialogListener(gameController));		
		
		gameController.addListener(this);
		
		add(addPlayerButton);
		add(removePlayerButton);
		add(setBetButton);
		add(rollDiceButton);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch(event)
		{
		case Events.PLAYER_SELECTED:
			togglePlayerButtons(true);
			break;
			
		case Events.PLAYER_REMOVED:
			if (gameController.isPlayerListEmpty())
				togglePlayerButtons(false);
			break;
			
		case Events.HOUSE_SELECTED:
			togglePlayerButtons(false);
			
		default:
			// do nothing
			break;
		}
	}
	
	public void togglePlayerButtons(boolean toggle)
	{
		removePlayerButton.setEnabled(toggle);
		setBetButton.setEnabled(toggle);
		rollDiceButton.setEnabled(toggle);
	}
}