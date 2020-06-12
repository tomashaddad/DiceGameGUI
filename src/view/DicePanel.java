package view;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import constants.Events;
import controller.manager.EventManager;
import model.SimplePlayer;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DicePanel extends JPanel implements PropertyChangeListener
{
	EventManager eventManager;
	DieGraphic die1 = new DieGraphic(4);
	DieGraphic die2 = new DieGraphic(2);
	
	DieGraphic die3 = new DieGraphic(1);
	DieGraphic die4 = new DieGraphic(6);
	
	CardLayout layout;
	
	private Map<Player, PlayerDicePanel> playerDicePanels = new HashMap<>();

	public DicePanel(EventManager eventManager)
	{
		this.eventManager = eventManager;

		layout = new CardLayout();
		setLayout(layout);
		
		eventManager.addListener(this);
	}

	public DieGraphic getDie1()
	{
		return die1;
	}

	public DieGraphic getDie2()
	{
		return die2;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = (String) evt.getPropertyName();

		switch(event)
		{
		case Events.PLAYER_ADDED:
			PlayerDicePanel newPlayerPanel = new PlayerDicePanel();
			playerDicePanels.put((SimplePlayer) evt.getNewValue(), newPlayerPanel);
			add(newPlayerPanel, ((SimplePlayer) evt.getNewValue()).getPlayerId());
			layout.show(this, ((SimplePlayer) evt.getNewValue()).getPlayerId());
			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_SELECTED:
			layout.show(this, ((SimplePlayer) evt.getNewValue()).getPlayerId());
			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_REMOVED:
			remove(playerDicePanels.get((SimplePlayer) evt.getOldValue()));
			playerDicePanels.remove((SimplePlayer) evt.getOldValue());
			revalidate();
			repaint();
			break;
			
//		case Events.DIE_UPDATED:
//			Die playerDie = eventManager.getViewModel().getPlayerDies().get((SimplePlayer) evt.getNewValue());
//			PlayerDicePanel playerPanel = playerDicePanels.get((SimplePlayer) evt.getNewValue());
//			int dieValue = playerDie.getValue();
//
//			if (playerDie.getNumber() == 1)
//			{
//				playerPanel.getDie1().setValue(dieValue);
//			}
//			
//			if (playerDie.getNumber() == 2)
//			{
//				playerPanel.getDie2().setValue(dieValue);
//			}
//			break;
		}
	}
}
