package view;

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
import controller.manager.EventManager;
import model.SimplePlayer;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceToolbar extends JToolBar implements PropertyChangeListener
{
	private AbstractButton removePlayerButton;
	private EventManager eventManager;
	
	private ActionListener removeListener;
	private Player selectedPlayer;
	
	public DiceToolbar(EventManager eventManager)
	{
		super("Dice Game Toolbar");
		
		this.eventManager = eventManager;
		
		setBackground(Color.decode("#B8C4BB"));
		setFloatable(false);
		AbstractButton rollDiceButton = new JButton("Roll Dice");
		AbstractButton setBetButton = new JButton("Set bet");
		AbstractButton addPlayerButton = new JButton("Add player");
		removePlayerButton = new JButton("Remove player");
		
		rollDiceButton.addActionListener(new RollListener(eventManager));
		addPlayerButton.addActionListener(new AddPlayerDialogListener(eventManager));
		
		removeListener = new RemovePlayerListener(eventManager, selectedPlayer);
		removePlayerButton.addActionListener(removeListener);
		removePlayerButton.setEnabled(false);
		
		eventManager.addListener(this);
		
		add(rollDiceButton);
		add(addPlayerButton);
		add(removePlayerButton);
		add(setBetButton);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch(event)
		{
		
		case Events.PLAYER_ADDED:
			removePlayerButton.setEnabled(true);
		
		case Events.PLAYER_SELECTED:
			
			/* If a different player is selected, remove the old actionListener() on the [Remove player]
			 * button and replace it with an action listener that will remove the now-selected player */

			removePlayerButton.removeActionListener(removeListener);
			selectedPlayer = (SimplePlayer) evt.getNewValue();
			removeListener = new RemovePlayerListener(eventManager, selectedPlayer);
			removePlayerButton.addActionListener(removeListener);
			break;
			
		case Events.PLAYER_REMOVED:
			if (eventManager.getGameEngine().getAllPlayers().isEmpty())
			{
				removePlayerButton.setEnabled(false);
			}
			break;
		}
	}
}
