package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import constants.Events;
import controller.GameController;
import model.SimplePlayer;
import model.interfaces.Player;

// reference: https://stackoverflow.com/questions/38021262/how-to-dynamically-add-elements-to-a-jscrollpanel

@SuppressWarnings("serial")
public class DiceSummaryPanel extends JPanel implements PropertyChangeListener
{
	private GameController gameController;
	private JPanel playerPanels;
	
	private Player[] dummyPlayerArray = {new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100),
			new SimplePlayer("1", "Tomas", 100)};

	public DiceSummaryPanel(GameController gameController)
	{
		this.gameController = gameController;
		
		gameController.addListener(this);

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, 0));
		
		String[] names = {"Tomas", "Karl", "Ross", "Caspar"};
		
		JList<Player> list = new JList<>(dummyPlayerArray);
		
		list.setCellRenderer(new JListPlayerRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.getModel().getElementAt(0).setPoints(20);
		
		add(new JScrollPane(list), BorderLayout.CENTER);

//		add(createScrollPane(), BorderLayout.CENTER);
	}

	/*
	 * Wrapper JPanel is contained in JScrollPane as north-aligned BorderLayout.
	 * Without the wrapper, any players added to the inner panel will stretch
	 * vertically to evenly fill height
	 */
	
	private JScrollPane createScrollPane()
	{
		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		playerPanels = new JPanel(new GridLayout(0, 1, 10, 10));
		wrapperPanel.add(playerPanels, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(wrapperPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		return scrollPane;
	}

	private void addScrollPaneItem(JPanel item)
	{
		playerPanels.add(item);
		revalidate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		if(evt.getPropertyName() == Events.PLAYER_ADDED)
		{
			Player newPlayer = (SimplePlayer) evt.getNewValue();
			String name = newPlayer.getPlayerName();
			int points = newPlayer.getPoints();

			addScrollPaneItem(new SinglePlayerSummary(name, points, 300));
		}
	}
}
