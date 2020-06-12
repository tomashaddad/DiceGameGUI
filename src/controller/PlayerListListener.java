package controller;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import constants.Events;
import controller.manager.EventManager;
import model.interfaces.Player;

public class PlayerListListener implements ListSelectionListener
{
	EventManager gameController;
	JList<Player> list;
	
	public PlayerListListener(EventManager eventManager, JList<Player> list)
	{
		this.gameController = eventManager;
		this.list = list;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{		
		if(!e.getValueIsAdjusting() && list.getModel().getSize() > 1)
		{
			Player player = list.getSelectedValue();
			gameController.getListeners().firePropertyChange(Events.PLAYER_SELECTED, null, player);
		}
	}
}