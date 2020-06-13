package view.toolbar;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import constants.Events;
import controller.AddPlayerDialogListener;
import controller.RemovePlayerListener;
import controller.RollListener;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar implements PropertyChangeListener
{
	private GameController gameController;
	
	private AbstractButton addPlayerButton;
	private AbstractButton removePlayerButton;
	private AbstractButton setBetButton;
	private AbstractButton rollDiceButton;
	
	private ActionListener removeListener;
	private Player selectedPlayer;
	
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
		
		removeListener = new RemovePlayerListener(gameController, selectedPlayer);
		removePlayerButton.addActionListener(removeListener);
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
		case Events.PLAYER_ADDED:
			rollDiceButton.setEnabled(true);
		
		case Events.PLAYER_SELECTED:
			removePlayerButton.setEnabled(true);
			setBetButton.setEnabled(true);
			rollDiceButton.setEnabled(true);
			
			/* If a different player is selected, remove the old actionListener() on the [Remove player]
			 * button and replace it with an action listener that will remove the now-selected player */

			Player selectedPlayer = gameController.getSelectedPlayer();
			removePlayerButton.removeActionListener(removeListener);
			removeListener = new RemovePlayerListener(gameController, selectedPlayer);
			removePlayerButton.addActionListener(removeListener);
			break;
			
		case Events.PLAYER_REMOVED:
			if (gameController.getGameEngine().getAllPlayers().isEmpty())
			{
				removePlayerButton.setEnabled(false);
				rollDiceButton.setEnabled(false);
				setBetButton.setEnabled(false);
			}
			break;
			
		case Events.HOUSE_SELECTED:
			removePlayerButton.setEnabled(false);
			setBetButton.setEnabled(false);
			rollDiceButton.setEnabled(false);
		}
	}
}