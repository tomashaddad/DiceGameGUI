package view.statusbar;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import constants.Events;
import constants.Status;
import controller.game.GameController;
import model.SimplePlayer;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class DiceStatusBar extends JPanel implements PropertyChangeListener
{
	private JLabel selectedPlayer;
	private JLabel playerStatus;
	
	private static final String HOUSE_SELECTED = "<html>Selected player: <b>The HOUSE!</b></html>";
	
	private GameController gameController;
	
	public DiceStatusBar(GameController gameController)
	{
		this.gameController = gameController;
		
		setLayout(new GridLayout(0, 2));
		
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
		
		Font font = new Font("Arial", Font.PLAIN, 16);
		
		selectedPlayer = new JLabel(HOUSE_SELECTED, SwingConstants.CENTER);
		playerStatus = new JLabel("Gambler's Help: 1800 858 858", SwingConstants.CENTER);
		
		selectedPlayer.setFont(font);
		playerStatus.setFont(font);
	
		add(selectedPlayer);
		add(playerStatus);
		
		gameController.addListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)
	{
		String event = evt.getPropertyName();
		
		switch (event)
		{
		case Events.PLAYER_ADDED:
			playerStatus.setText(
					((SimplePlayer) evt.getNewValue()).getPlayerName()
					+ " has joined the table!");
			break;
			
		case Events.PLAYER_REMOVED:
			if (gameController.isPlayerListEmpty())
			{
				selectedPlayer.setText(HOUSE_SELECTED);
				break;
			}
			break;
			
		case Events.PLAYER_SELECTED:
			selectedPlayer.setText("Selected player: " + gameController.getSelectedPlayer().getPlayerName());
			setPlayerStatusText(gameController.getSelectedPlayer());
			break;
			
		case Events.BET_SET:
			setPlayerStatusText(((SimplePlayer) evt.getNewValue()));
			break;
			
		case Events.BET_RESET:
			setPlayerStatusText(((SimplePlayer) evt.getNewValue()));
			break;
		
		case Events.PLAYER_ROLLING:
			playerStatus.setText(
					((SimplePlayer) evt.getNewValue()).getPlayerName()
					+ " has begun their roll!");
			break;
		
		case Events.PLAYER_ROLLED:
			playerStatus.setText(
					((SimplePlayer) evt.getNewValue()).getPlayerName()
					+ " just finished rolling!");
			break;
			
		case Events.HOUSE_SELECTED:
			selectedPlayer.setText(HOUSE_SELECTED);
			playerStatus.setText("Add and roll players so that the house can roll!");
			break;
			
		case Events.HOUSE_ROLLING:
			selectedPlayer.setText(HOUSE_SELECTED);
			playerStatus.setText("The house has begun their roll!");
			break;
		
		case Events.HOUSE_ROLLED:
			playerStatus.setText("The house has finished their roll!");
			break;

		default:
			// do nothing
			break;
		}
		revalidate();
		repaint();
	}
	
	private void setPlayerStatusText(Player player)
	{
		String status = gameController.getPlayerStatus(player);
		String playerName = player.getPlayerName();
		
		switch (status)
		{
		case Status.HAS_BET:
			playerStatus.setText(playerName + " has placed a bet and is waiting to roll!");
			break;

		case Status.NO_BET:
			playerStatus.setText(playerName + " has not yet placed a bet ...");
			break;

		case Status.ROLLING:
			playerStatus.setText(playerName + " is rolling!");
			break;

		case Status.ROLLED:
			playerStatus.setText(playerName + " has rolled, and is waiting for others ...");
			break;
			
		default:
			// do nothing
			break;
		}
	}
}
