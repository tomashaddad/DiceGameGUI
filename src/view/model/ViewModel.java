package view.model;

import java.util.HashMap;
import java.util.Map;

import constants.Status;
import model.interfaces.Die;
import model.interfaces.Player;

/* Stores game state information useful to the view. */

public class ViewModel
{
	public static int playerIDCounter = 0;
	private Player selectedPlayer;
	private Map<Player, String> playerStates = new HashMap<>();
 
	/* Whenever a player die is updated, it is stored here in the ViewModel for the
	 * view to read once the event PLAYER_DIE_UPDATED is fired for a specific player */

	private Map<Player, Die> playerDies = new HashMap<>();

	public void addPlayer(Player player)
	{
		playerStates.put(player, Status.NEW_PLAYER);
		playerDies.put(player, null);
	}

	public void setBet(Player player)
	{
		// TODO: Complete this method
	}

	public void removePlayer(Player player)
	{
		playerStates.remove(player);
		playerDies.remove(player);
	}

	public void updatePlayerDie(Player player, Die die)
	{
		playerDies.put(player, die);
	}

	public Die getPlayerDie(Player player)
	{
		return playerDies.get(player);
	}

	public void setSelectedPlayer(Player player)
	{
		selectedPlayer = player;
	}

	public Player getSelectedPlayer()
	{
		return selectedPlayer;
	}

	public void setPlayerStatus(Player player, String status)
	{
		playerStates.put(player, status);
	}

	public String getPlayerStatus(Player player)
	{
		return playerStates.get(player);
	}

	public boolean haveAllPlayersRolled()
	{
		for (String status : playerStates.values())
		{
			if (!status.equals(Status.ROLLED))
			{
				return false;
			}
		}
		return true;
	}
}