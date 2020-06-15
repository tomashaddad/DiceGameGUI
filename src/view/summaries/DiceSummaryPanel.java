package view.summaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import constants.Events;
import controller.HouseSummaryListener;
import controller.PlayerListListener;
import controller.game.GameController;
import model.PlayerListItem;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.Player;

// reference: https://stackoverflow.com/questions/38021262/how-to-dynamically-add-elements-to-a-jscrollpanel

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel implements PropertyChangeListener
{
	private JList<PlayerListItem> list;
	private DefaultListModel<PlayerListItem> playerListModel;
	
	private final Color houseBackgroundColour = Color.decode("#e8dcdc");
	
	HouseSummary houseSummary;
	
	public DiceSummaryPanel(GameController gameController)
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 0));
		
		playerListModel = new DefaultListModel<>();
		list = new JList<>(playerListModel);
		list.setCellRenderer(new JListPlayerRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new PlayerListListener(gameController, this));
		
		houseSummary = new HouseSummary();
		houseSummary.setBackground(houseBackgroundColour);

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
			Player addedPlayer = (SimplePlayer) evt.getNewValue();
			playerListModel.addElement(new PlayerListItem(addedPlayer));
			list.setSelectedIndex(getIndexOfPlayer(addedPlayer));
			break;
			 
		case Events.PLAYER_REMOVED:
			Player removePlayer = (SimplePlayer) evt.getOldValue();
			int prevSelectedIndex = list.getSelectedIndex();
			playerListModel.remove(getIndexOfPlayer(removePlayer));
			if(list.getModel().getSize() == 0)
			{
				houseSummary.setBackground(houseBackgroundColour);
				break;
			}

			int lastIndex = list.getModel().getSize() - 1;
			list.setSelectedIndex(Math.min(prevSelectedIndex, lastIndex));

			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_SELECTED:
			houseSummary.setBackground(Color.WHITE);
			break;
			
		case Events.HOUSE_SELECTED:
			list.clearSelection();
			houseSummary.setBackground(houseBackgroundColour);
			break;
			
		case Events.HOUSE_ROLLED:
			DicePair houseDicePair = (DicePair) evt.getNewValue();
			
			for (int i = 0; i < playerListModel.getSize(); i++)
			{
				Player player = playerListModel.getElementAt(i).getPlayer();
				int bet = player.getBet();
				DicePair playerDicePair = player.getResult();
				int compareToResult = playerDicePair.compareTo(houseDicePair);
				playerListModel.getElementAt(i).setWinnings(compareToResult < 0 ? -bet : bet);
			}
			
			list.revalidate();
			list.repaint();
			break;
			
		default:
			// do nothing
			break;
		}
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