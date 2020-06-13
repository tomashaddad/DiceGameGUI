package view.dice;

import java.awt.CardLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import constants.Events;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Die;
import model.interfaces.Player;

/**
 * This class adopts a CardLayout, allowing the addition of multiple cards
 * which act as the different dice pair views for each player.
 * 
 * Navigation of each card is done by clicking the player name in the player
 * summary panel.
 * 
 * @author Tomas Haddad
 */

@SuppressWarnings("serial")
public class DicePanel extends JPanel implements PropertyChangeListener
{
	GameController gameController;	
	CardLayout layout;
	
	private Map<Player, DicePairCard> playerDicePanels = new HashMap<>();
	DicePairCard houseCard;

	public DicePanel(GameController gameController)
	{
		this.gameController = gameController;

		layout = new CardLayout();
		setLayout(layout);
		
		houseCard = new DicePairCard(DieGraphic.CASINO_RED);		
		add(houseCard);
		
		gameController.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = (String) evt.getPropertyName();

		switch(event)
		{
		case Events.PLAYER_ADDED:
			Player addedPlayer = (SimplePlayer) evt.getNewValue();
			String id = addedPlayer.getPlayerId();
			DicePairCard card = new DicePairCard(DieGraphic.CASINO_GREEN);
			
			playerDicePanels.put(addedPlayer, card);
			add(card, id);
			layout.show(this, id);
			
			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_SELECTED:
			Player selectedPlayer = gameController.getSelectedPlayer();
			String selectedPlayerID = selectedPlayer.getPlayerId();
			layout.show(this, selectedPlayerID);
			
			revalidate();
			repaint();
			break;

		case Events.HOUSE_SELECTED:
			layout.first(this);
			break;
			
		case Events.PLAYER_REMOVED:
			remove(playerDicePanels.get((SimplePlayer) evt.getOldValue()));
			playerDicePanels.remove((SimplePlayer) evt.getOldValue());
			
			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_DIE_UPDATED:
			Player updatedPlayer = (SimplePlayer) evt.getNewValue();
			Die newDie = gameController.getViewModel().getPlayerDies().get(updatedPlayer);
			DicePairCard playerPanel = playerDicePanels.get(updatedPlayer);
			int dieValue = newDie.getValue();

			if (newDie.getNumber() == 1)
			{
				playerPanel.getDie1().setValue(dieValue);
			}
			
			if (newDie.getNumber() == 2)
			{
				playerPanel.getDie2().setValue(dieValue);
			}
			
			playerPanel.revalidate();
			playerPanel.repaint();
			break;
		}
	}
}
