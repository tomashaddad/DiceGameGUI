package view.game;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import constants.CasinoColour;
import constants.Events;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Die;
import model.interfaces.Player;

/* This class adopts a CardLayout, allowing the addition of multiple cards
 * which act as the different dice pair views for each player.
 * 
 * Navigation of each card is done by clicking the player name in the player
 * summary panel. */

@SuppressWarnings("serial")
public class DicePanel extends JPanel implements PropertyChangeListener
{
	private GameController gameController;	
	private CardLayout layout;
	private DicePairCard houseCard;

	private Map<Player, DicePairCard> playerDicePanels = new HashMap<>();

	public DicePanel(GameController gameController)
	{
		this.gameController = gameController;

		layout = new CardLayout();
		setLayout(layout);
		
		houseCard = new DicePairCard(CasinoColour.CASINO_RED);		
		add(houseCard);
		
		gameController.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();

		switch(event)
		{
		case Events.PLAYER_ADDED:
			addPlayerCard((SimplePlayer) evt.getNewValue());
			break;
			
		case Events.PLAYER_SELECTED:
			showPlayerCard(gameController.getSelectedPlayer());
			break;

		case Events.PLAYER_REMOVED:
			removePlayerCard((SimplePlayer) evt.getOldValue());
			break;

		case Events.PLAYER_DIE_UPDATED:
			Die die = gameController.getPlayerDie((SimplePlayer) evt.getNewValue());
			DicePairCard playerPanel = playerDicePanels.get((SimplePlayer) evt.getNewValue());
			updateCard(playerPanel, die);
			break;


		case Events.HOUSE_SELECTED:
		case Events.HOUSE_ROLLING: // switch to house automatically during house roll
			layout.first(this);
			break;				
		
		case Events.HOUSE_DIE_UPDATED:
			updateCard(houseCard, (Die) evt.getNewValue());
			break;
			
		default:
			// do nothing
			break;
		}
		
		revalidate();
		repaint();
	}
	
	private void addPlayerCard(Player player)
	{
		DicePairCard card = new DicePairCard(CasinoColour.CASINO_GREEN);
		
		playerDicePanels.put(player, card);
		add(card, player.getPlayerId());
		layout.show(this, player.getPlayerId());
	}
	
	private void showPlayerCard(Player player)
	{
		layout.show(this, player.getPlayerId());
	}
	
	private void removePlayerCard(Player player)
	{
		remove(playerDicePanels.get(player));
		playerDicePanels.remove(player);
	}
	
	private void updateCard(DicePairCard card, Die die)
	{
		if (die.getNumber() == 1)
			card.getDie1().setValue(die.getValue());
		if (die.getNumber() == 2)
			card.getDie2().setValue(die.getValue());
	}
}
