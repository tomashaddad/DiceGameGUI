package view.toolbar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JToolBar;

import constants.Events;
import controller.AddPlayerDialogListener;
import controller.RemovePlayerListener;
import controller.ResetBetListener;
import controller.RollListener;
import controller.SetBetDialogListener;
import controller.game.GameController;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar implements PropertyChangeListener
{
	private GameController gameController;
	
	private AbstractButton addPlayerButton;
	private AbstractButton removePlayerButton;
	private AbstractButton setBetButton;
	private AbstractButton resetBetButton;
	private AbstractButton rollDiceButton;
	
	public DiceToolbar(GameController gameController)
	{
		super("Dice Game Toolbar");
		
		this.gameController = gameController;
		
		setFloatable(false);
		
		addPlayerButton = new JButton("Add player");
		removePlayerButton = new JButton("Remove player");
		setBetButton = new JButton("Set bet");
		resetBetButton = new JButton("Reset bet");
		rollDiceButton = new JButton("Roll player dice");
		
		rollDiceButton.setEnabled(false);
		setBetButton.setEnabled(false);
		resetBetButton.setEnabled(false);
		removePlayerButton.setEnabled(false);

		addPlayerButton.addActionListener(new AddPlayerDialogListener(gameController));
		removePlayerButton.addActionListener(new RemovePlayerListener(gameController));
		setBetButton.addActionListener(new SetBetDialogListener(gameController));
		resetBetButton.addActionListener(new ResetBetListener(gameController));
		rollDiceButton.addActionListener(new RollListener(gameController));		
		
		gameController.addListener(this);
		
		add(addPlayerButton);
		add(removePlayerButton);
		add(setBetButton);
		add(resetBetButton);
		add(rollDiceButton);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch (event)
		{
		case Events.PLAYER_SELECTED:
			/* Once a player has rolled, disable all player functionality.
			 * If you've made the commitment, you have to stick to it! */
			enablePlayerButtons(!gameController.hasPlayerRolled(gameController.getSelectedPlayer()));
			break;
			
		case Events.PLAYER_REMOVED:
			if (gameController.isPlayerListEmpty())
			{
				enablePlayerButtons(false);
			}
			break;
			
		case Events.PLAYER_ROLLING:
			enablePlayerButtons(false);
			break;
			
		case Events.HOUSE_SELECTED:
			enablePlayerButtons(false);
			break;
			
		case Events.HOUSE_ROLLING:
			addPlayerButton.setEnabled(false);
			enablePlayerButtons(false);
			break;
			
		case Events.HOUSE_ROLLED:
			addPlayerButton.setEnabled(true);
			enablePlayerButtons(true);
			break;

		default:
			// do nothing
			break;
		}
	}
	
	public void enablePlayerButtons(boolean toggle)
	{
		// enforce requirement that one player rolls at a time
		if (gameController.isAnyPlayerRolling())
		{
			toggle = false;
		}
		
		removePlayerButton.setEnabled(toggle);
		setBetButton.setEnabled(toggle);
		resetBetButton.setEnabled(toggle);
		rollDiceButton.setEnabled(toggle);
	}
}