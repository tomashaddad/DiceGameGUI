package view.summaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import constants.CasinoColour;
import constants.Events;
import controller.HouseSummaryListener;
import controller.PlayerListListener;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel implements PropertyChangeListener
{
	private static final int NO_PLAYER = -1;
	
	private JList<PlayerListItem> list;
	private DefaultListModel<PlayerListItem> playerListModel;
	private JPanel houseSummary;
	
	public DiceSummaryPanel(GameController gameController)
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 0));
		
		Border houseCardBorder = CardBorder.createBorder(
				"HOUSE", CasinoColour.CASINO_RED, TitledBorder.CENTER,
				new Font("Arial", Font.BOLD, 20));
		houseSummary = new JPanel();
		houseSummary.setBorder(houseCardBorder);
		houseSummary.setBackground(CasinoColour.HOUSE_SELECT);
		houseSummary.addMouseListener(new HouseSummaryListener(gameController));
		
		JLabel houseCardPrompt = new JLabel("View House dice");
		houseCardPrompt.setFont(new Font("Arial", Font.ITALIC, 18));
		houseSummary.add(houseCardPrompt);
		
		playerListModel = new DefaultListModel<>();
		list = new JList<>(playerListModel);
		
		/* Custom cell renderer allows dynamic updating of player information
		 * contained within wrapper PlayerListItem class and stylised JPanel */
		list.setCellRenderer(new JListPlayerRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new PlayerListListener(gameController, this));
				
		gameController.addListener(this);

		add(houseSummary, BorderLayout.NORTH);
		add(new JScrollPane(list), BorderLayout.CENTER);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();

		switch(event)
		{
		case Events.PLAYER_ADDED:
			addPlayer((SimplePlayer) evt.getNewValue());
			break;

		case Events.PLAYER_REMOVED:
			removePlayer((SimplePlayer) evt.getOldValue());
			break;
			
		case Events.PLAYER_SELECTED:
			houseSummary.setBackground(Color.WHITE);
			break;

		case Events.HOUSE_SELECTED:
		case Events.HOUSE_ROLLING:		
			list.clearSelection();
			houseSummary.setBackground(CasinoColour.HOUSE_SELECT);
			break;
			
		case Events.HOUSE_ROLLED:
			updatePlayerWinLoss((DicePair) evt.getNewValue());
			break;
			
		default:
			// do nothing
			break;
		}
		revalidate();
		repaint();
	}
	
	private void addPlayer(Player player)
	{
		playerListModel.addElement(new PlayerListItem(player));
		list.setSelectedIndex(getIndexOfPlayer(player));
	}
	
	/* In addition to removing the player from the list, this method
	 * handles the selection of a new player.
	 * 
	 * After removing a player:
	 *  1. If the list is empty, select the house
	 *  2. If the list is not empty, select the player above the removed player,
	 *  	if one exists.
	 *  
	 * (2) is handled by the Math.min() method, and encapsulates the two
	 * conditions where the player being removed is first/last in the list,
	 * or is somewhere in the middle of the list. */
	
	private void removePlayer(Player player)
	{
		int prevIndex = list.getSelectedIndex();
		playerListModel.remove(getIndexOfPlayer(player));
		if (list.getModel().getSize() == 0)
		{
			houseSummary.setBackground(CasinoColour.HOUSE_SELECT);
			return;
		}

		int lastIndex = list.getModel().getSize() - 1;
		list.setSelectedIndex(Math.min(prevIndex, lastIndex));
	}

	private void updatePlayerWinLoss(DicePair houseDicePair)
	{
		// loop through each player and compute their winnings
		for (int i = 0; i < playerListModel.getSize(); i++)
		{
			Player player = playerListModel.getElementAt(i).getPlayer();
			int bet = player.getBet();
			DicePair playerDicePair = player.getResult();
			int compareToResult = playerDicePair.compareTo(houseDicePair);
			playerListModel.getElementAt(i).setWinnings(compareToResult < 0 ? -bet : bet);
		}
	}

	private int getIndexOfPlayer(Player player)
	{
		for (int i = 0; i < playerListModel.getSize(); i++)
		{
			if (playerListModel.getElementAt(i).getPlayer().equals(player))
			{
				return i;
			}
		}
		return NO_PLAYER;
	}
	
	/* Necessary to handle ListSelection event; recovering the JList from the event source
	 * is unsafe (?) due to generic type erasure */
	
	public JList<PlayerListItem> getList()
	{
		return list;
	}
}