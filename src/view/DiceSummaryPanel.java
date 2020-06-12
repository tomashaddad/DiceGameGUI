package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import constants.Events;
import controller.PlayerListListener;
import controller.manager.EventManager;
import model.SimplePlayer;
import model.interfaces.Player;

// reference: https://stackoverflow.com/questions/38021262/how-to-dynamically-add-elements-to-a-jscrollpanel

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel implements PropertyChangeListener
{
	private JList<Player> list;
	private DefaultListModel<Player> playerListModel;
	
	private final int EMPTY_LIST = -1;

	public DiceSummaryPanel(EventManager gameController)
	{
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 0));
		
		playerListModel = new DefaultListModel<>();
		list = new JList<>(playerListModel);
		list.setCellRenderer(new JListPlayerRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//		list.getModel().getElementAt(0).setPoints(30);
		
		list.addListSelectionListener(new PlayerListListener(gameController, list));
		
		gameController.addListener(this);
		add(new HouseSummary(), BorderLayout.NORTH);
		add(new JScrollPane(list), BorderLayout.CENTER);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();

		switch(event)
		{
		case Events.PLAYER_ADDED:
			playerListModel.addElement((SimplePlayer) evt.getNewValue());
			list.setSelectedValue((SimplePlayer) evt.getNewValue(), true);
			break;
		case Events.PLAYER_REMOVED:
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex > 0)
			{
				list.setSelectedIndex(selectedIndex - 1);
			}
			else if (selectedIndex == 0)
			{
				list.setSelectedIndex(selectedIndex + 1);
			}
			playerListModel.removeElement((SimplePlayer) evt.getOldValue());
			revalidate();
			break;
		}
	}
}