package view.summaries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import constants.Events;
import controller.PlayerListListener;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;

// reference: https://stackoverflow.com/questions/38021262/how-to-dynamically-add-elements-to-a-jscrollpanel

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel implements PropertyChangeListener
{
	private JList<Player> list;
	private DefaultListModel<Player> playerListModel;
	
	HouseSummary houseSummary;
	
	String instructionBody = "Instructions: <br>"
			+ "Add a new player by selecting the button above the dice pair panel"
			+ "to the right. You can navigate through the house's or each player's"
			+ "dice pairs by clicking their entries above.<br>"
			+ "Good luck!<br>"
			+ "(Gambler's Help: 1800 858 858)";
	
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
		houseSummary.setBackground(Color.decode("#e8dcdc"));

		houseSummary.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				gameController.firePropertyChange(Events.HOUSE_SELECTED, null, null);
				houseSummary.setBackground(Color.decode("#e8dcdc"));
			}
		});
		
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
			playerListModel.addElement(addedPlayer);
			list.setSelectedValue(addedPlayer, true);
			break;
			
		case Events.PLAYER_REMOVED:
			int prevSelectedIndex = list.getSelectedIndex();
			playerListModel.removeElement((SimplePlayer) evt.getOldValue());
			if(list.getModel().getSize() == 0)
			{
				houseSummary.setBackground(Color.decode("#e8dcdc"));
				break;
			}

			int lastIndex = list.getModel().getSize() - 1;
			list.setSelectedIndex(Math.min(prevSelectedIndex, lastIndex));

			revalidate();
			break;
			
		case Events.PLAYER_SELECTED:
			houseSummary.setBackground(Color.WHITE);
			break;
			
		case Events.HOUSE_SELECTED:
			list.clearSelection();
			break;
		}
	}
	
	public JList<Player> getList()
	{
		return list;
	}
	
}