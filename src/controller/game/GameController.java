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
 *  3. Updates the GameEngine */

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
		engine.addPlayer(player);
		viewModel.addPlayer(player);
		selectPlayer(player); // must be done before event firing
		pcs.firePropertyChange(Events.PLAYER_ADDED, null, player);			
	}

	public void removePlayer(Player player)
	{
		engine.removePlayer(player);
		viewModel.removePlayer(player);
		pcs.firePropertyChange(Events.PLAYER_REMOVED, player, null);
	}

	public void rollPlayer(Player player)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				engine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
			}
		}.start();
		
		viewModel.setPlayerStatus(player, Status.ROLLING);
		pcs.firePropertyChange(Events.PLAYER_ROLLING, null, player);
	}

	public void announcePlayerResult(Player player)
	{
		viewModel.setPlayerStatus(player, Status.ROLLED);
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
	
	public void selectPlayer(Player player)
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
	
	public boolean isPlayerListEmpty()
	{
		return engine.getAllPlayers().isEmpty();
	}

	public String getPlayerStatus(Player player)
	{
		return viewModel.getPlayerStatus(player);
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