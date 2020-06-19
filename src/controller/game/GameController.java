package controller.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import constants.Events;
import constants.Status;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;
import view.model.ViewModel;

/* The purpose of this class is to centralise game and event management.
 * 	e.g. If there were multiple ways to add players, each listener does not need to repeat the
 * 	code contained within the methods of this class each time, it need only call a method in this class.
 * 
 * Each method does at least one of three things, if not all three:
 *  1. Announces a property change
 *  2. Updates the ViewModel
 *  3. Updates the GameEngine 
 */

public class GameController
{
	private final PropertyChangeSupport pcs;
	private final GameEngine engine;
	private final ViewModel viewModel;
	
	public GameController(GameEngine engine)
	{
		this.engine = engine;
		viewModel = new ViewModel();
		pcs = new PropertyChangeSupport(this);
	}

	public void addPlayer(Player player, int bet)
	{
		if (player.setBet(bet))
		{
			engine.addPlayer(player);
			viewModel.addPlayer(player);
			setSelectedPlayer(player); // must be done before event firing
			pcs.firePropertyChange(Events.PLAYER_ADDED, null, player);
		}
		else
		{
			pcs.firePropertyChange(Events.BET_FAILED, null, null);
		}
	}

	public void removePlayer(Player player)
	{
		engine.removePlayer(player);
		viewModel.removePlayer(player);
		pcs.firePropertyChange(Events.PLAYER_REMOVED, player, null);
		
		/* Necessary check so that if 2/3 players have rolled and player 3 is
		 * removed, then the house should roll. */
		
		if (viewModel.haveAllPlayersRolled())
		{
			rollHouse();
		}
	}
	
	public void placeBet(Player player, int bet)
	{
		engine.placeBet(player, bet);
		viewModel.setPlayerHasBet(player);
		pcs.firePropertyChange(Events.BET_SET, null, player);
	}
	
	public void resetBet(Player player)
	{
		player.resetBet();
		viewModel.resetBet(player);
		pcs.firePropertyChange(Events.BET_RESET, null, player);
	}
	
	/* The player passed in to this method is the player currently selected
	 * and set as the selected player in the ViewModel */

	public void rollPlayer(Player player)
	{
		if (player.getBet() > 0)
		{
			new Thread()
			{
				@Override
				public void run()
				{
					engine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
				}
			}.start();
			
			viewModel.setPlayerRolling(player);
			pcs.firePropertyChange(Events.PLAYER_ROLLING, null, player);
		}
		
		else
		{
			pcs.firePropertyChange(Events.ROLL_FAILED, null, null);
		}
	}

	public void announcePlayerResult(Player player)
	{
		viewModel.setPlayerHasRolled(player);
		pcs.firePropertyChange(Events.PLAYER_ROLLED, null, player);
		
		if (viewModel.haveAllPlayersRolled())
		{
			rollHouse();
		}
	}
	
	public void rollHouse()
	{
		new Thread()
		{
			@Override
			public void run()
			{
				engine.rollHouse(100, 1000, 100, 50, 500, 50);
			}
		}.start();
		
		pcs.firePropertyChange(Events.HOUSE_ROLLING, null, null);
	}

	public void announceHouseResult(DicePair result)
	{
		pcs.firePropertyChange(Events.HOUSE_ROLLED, null, result);
		
		for (Player player : engine.getAllPlayers())
		{
			if (player.getPoints() == 0)
			{
				removePlayer(player);
			}
		}
		
		viewModel.resetAllBets();
	}

	public void updatePlayerDie(Player player, Die die)
	{
		viewModel.updatePlayerDie(player, die);
		pcs.firePropertyChange(Events.PLAYER_DIE_UPDATED, null, player);
	}

	public void updateHouseDie(Die die)
	{
		pcs.firePropertyChange(Events.HOUSE_DIE_UPDATED, null, die);
	}
	
	public void setSelectedPlayer(Player player)
	{
		viewModel.setSelectedPlayer(player);
		pcs.firePropertyChange(Events.PLAYER_SELECTED, null, null);
	}
	
	public Player getSelectedPlayer()
	{
		return viewModel.getSelectedPlayer();
	}
	
	public void selectHouse()
	{
		pcs.firePropertyChange(Events.HOUSE_SELECTED, null, null);
	}
	
	public Die getPlayerDie(Player player)
	{
		return viewModel.getPlayerDie(player);
	}
	
	public String getPlayerStatus(Player player)
	{
		return viewModel.getPlayerStatus(player);
	}
	
	public boolean isPlayerListEmpty()
	{
		return engine.getAllPlayers().isEmpty();
	}
	
	public boolean isAnyPlayerRolling()
	{
		return viewModel.isAnyPlayerRolling();
	}
	
	public boolean hasPlayerRolled(Player player)
	{
		return getPlayerStatus(player).equals(Status.ROLLED);
	}
	
	public void addGameEngineCallback(GameEngineCallback callback)
	{
		engine.addGameEngineCallback(callback);
	}

	public void addListener(PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener)
	{
		pcs.removePropertyChangeListener(listener);
	}

}