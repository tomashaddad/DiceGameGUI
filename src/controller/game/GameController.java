package controller.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import constants.Events;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.ViewModel;

/* The sole purpose of this class is to hold the listeners for event firing, and to hold 
 * references of the GameEngine and ViewModel.
 * 
 * i.e. this is equivalent to passing the frame everywhere and calling view getters (and GameEngine/ViewModel
 * getters on the frame) that eventually change the view and update the model accordingly,
 * but this way I get to use property change listeners :)
 * 
 * */

public class GameController
{
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private GameEngine engine;
	private ViewModel viewModel = new ViewModel();
	private Player selectedPlayer; // TODO: Move to view model
	
	public GameController(GameEngine engine)
	{
		this.engine = engine;
	}

	public void rollPlayer(Player player)
	{
		engine.rollPlayer(player, 100, 1000, 100, 50, 500, 50);
	}
	
	public void updatePlayerDie(Player player, Die die)
	{
		viewModel.updatePlayerDie(player, die);
		pcs.firePropertyChange(Events.PLAYER_DIE_UPDATED, null, player);
	}
	
	public void addListener(PropertyChangeListener listener)
	{
		pcs.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener)
	{
		pcs.removePropertyChangeListener(listener);
	}
	
	public void setSelectedPlayer(Player player)
	{
		selectedPlayer = player;
	}
	
	public void addNewPlayer(Player player)
	{
		engine.addPlayer(player);
		setSelectedPlayer(player);
		firePropertyChange(Events.PLAYER_ADDED, null, player);
	}
	
	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}

	public GameEngine getGameEngine()
	{
		return engine;
	}
	
	public ViewModel getViewModel()
	{
		return viewModel;
	}
	
	public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
	{
		pcs.firePropertyChange(propertyName, oldValue, newValue);
	}
}
