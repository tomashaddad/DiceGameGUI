package view.statusbar;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Events;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceStatusBar extends JPanel implements PropertyChangeListener
{
	JLabel selectedPlayer;
	JLabel playerStatus;
	JLabel gameStatus;
	
	GameController gameController;
	
	public DiceStatusBar(GameController gameController)
	{
		this.gameController = gameController;
		
		setBackground(Color.decode("#B8C4BB"));
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
		setLayout(new GridLayout(0, 3));
		
		selectedPlayer = new JLabel("Selected player: The HOUSE!", SwingConstants.CENTER);
		gameStatus = new JLabel("Game has not started!", SwingConstants.CENTER);
		playerStatus = new JLabel("No player selected!", SwingConstants.CENTER);
	
		add(selectedPlayer);
		add(gameStatus);
		add(playerStatus);
		
		gameController.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch(event)
		{
		case Events.PLAYER_SELECTED:
			Player player = gameController.getSelectedPlayer();
			selectedPlayer.setText("Selected player: " + player.getPlayerName());
			playerStatus.setText(player.getPlayerName() + " is currently " + gameController.getPlayerStatus(player));
			revalidate();
			repaint();
			break;
			
		case Events.PLAYER_ROLLING:
			Player rollingPlayer = (SimplePlayer) evt.getNewValue();
			gameStatus.setText(rollingPlayer.getPlayerName() + " just started rolling!");
			break;
			
		case Events.HOUSE_SELECTED:
			selectedPlayer.setText("Selected player: The HOUSE!");
			break;
			
		default:
			// do nothing
			break;
		}
	}
}
