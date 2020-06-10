package controller;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import constants.Events;
import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameController
{
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private GameEngine engine;
	

	public GameController(GameEngine engine)
	{
		this.engine = engine;
	}

	public void rollPlayer(Player player)
	{
		engine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
	}
	
	public void rollHouse()
	{
		
	}
	
	public void applyWinLoss(Player player, DicePair houseResult)
	{
		
	}
	
	public void addPlayer(String playerName, int points)
	{
		Player newPlayer = new SimplePlayer("1", playerName, points);
		engine.addPlayer(new SimplePlayer("1", playerName, points));
		pcs.firePropertyChange(Events.PLAYER_ADDED, null, newPlayer);
	}
	
	public boolean removePlayer(Player player)
	{
		return true;
	}
	
	public boolean placeBet(Player player, int bet)
	{
		return true;
	}
	
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		engine.addGameEngineCallback(gameEngineCallback);
	} 

	public void addListener(PropertyChangeListener listener)
	{
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener)
	{
		this.pcs.removePropertyChangeListener(listener);
	}

	public GameEngine getGameEngine()
	{
		return engine;
	}

}
