package controller;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.game.GameController;
import model.interfaces.Player;
import view.summaries.DiceSummaryPanel;
import view.summaries.PlayerListItem;

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
		/* Can't cast e.getSource() to JList<Player> due to Java type erasure
		 * so must pass the list as shown ... */
		PlayerListItem listItem = dsp.getList().getSelectedValue();

		if(!e.getValueIsAdjusting() && listItem != null)
		{
			Player player = listItem.getPlayer();
			eventManager.selectPlayer(player);
		}
	}
}