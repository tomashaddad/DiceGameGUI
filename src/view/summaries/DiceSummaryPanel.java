package view.summaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
	private JList<PlayerListItem> list;
	private DefaultListModel<PlayerListItem> playerListModel;
	
	JPanel houseSummary = new JPanel();
	
	public DiceSummaryPanel(GameController gameController)
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 0));
		
		Border houseCardBorder = CardBorder.createBorder(
				"HOUSE", CasinoColour.CASINO_RED, TitledBorder.CENTER,
				new Font("Arial", Font.BOLD, 16));
		
		playerListModel = new DefaultListModel<>();
		list = new JList<>(playerListModel);
		list.setCellRenderer(new JListPlayerRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new PlayerListListener(gameController, this));
		
		houseSummary.setBorder(houseCardBorder);
		houseSummary.setBackground(CasinoColour.HOUSE_SELECT);
		houseSummary.addMouseListener(new HouseSummaryListener(gameController));
		
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
			playerAdded((SimplePlayer) evt.getNewValue());
			break;
			 
		case Events.PLAYER_REMOVED:
			playerRemoved((SimplePlayer) evt.getOldValue());
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
			houseRolled((DicePair) evt.getNewValue());
			break;
			
		default:
			// do nothing
			break;
		}
	}
	
	private void playerAdded(Player player)
	{
		playerListModel.addElement(new PlayerListItem(player));
		list.setSelectedIndex(getIndexOfPlayer(player));
	}
	
	private void playerRemoved(Player player)
	{
		int prevSelectedIndex = list.getSelectedIndex();
		playerListModel.remove(getIndexOfPlayer(player));
		if(list.getModel().getSize() == 0)
		{
			houseSummary.setBackground(CasinoColour.HOUSE_SELECT);
			return;
		}

		int lastIndex = list.getModel().getSize() - 1;
		list.setSelectedIndex(Math.min(prevSelectedIndex, lastIndex));
		revalidate();
		repaint();
	}

	private void houseRolled(DicePair houseDicePair)
	{
		for (int i = 0; i < playerListModel.getSize(); i++)
		{
			Player player = playerListModel.getElementAt(i).getPlayer();
			int bet = player.getBet();
			DicePair playerDicePair = player.getResult();
			int compareToResult = playerDicePair.compareTo(houseDicePair);
			playerListModel.getElementAt(i).setWinnings(compareToResult < 0 ? -bet : bet);
		}
		revalidate();
		repaint();
	}

	private int getIndexOfPlayer(Player player)
	{
		for (int i = 0; i < playerListModel.getSize(); i++)
		{
			if(playerListModel.getElementAt(i).getPlayer().equals(player))
			{
				return i;
			}
		}
		return -1; // no player found that matches
	}
	
	public JList<PlayerListItem> getList()
	{
		return list;
	}
}