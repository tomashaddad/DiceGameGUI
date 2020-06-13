package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import constants.Events;
import controller.game.GameController;
import model.interfaces.Player;
import view.summaries.DiceSummaryPanel;

public class PlayerListListener implements ListSelectionListener
{
	GameController eventManager;
	DiceSummaryPanel dsp;
	
	public PlayerListListener(GameController eventManager, DiceSummaryPanel dsp)
	{
		this.eventManager = eventManager;
		this.dsp = dsp;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e)
	{		
		Player player = dsp.getList().getSelectedValue();
		
		if(!e.getValueIsAdjusting() && player != null)
		{
			eventManager.setSelectedPlayer(player);
			eventManager.firePropertyChange(Events.PLAYER_SELECTED, null, null);
		}
	}
}