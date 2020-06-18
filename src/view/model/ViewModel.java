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
		playerStates.put(player, Status.HAS_BET);
		playerDies.put(player, null);
	}

	public void setPlayerHasBet(Player player)
	{
		playerStates.put(player, Status.HAS_BET);
	}
	
	public void resetBet(Player player)
	{
		playerStates.put(player, Status.NO_BET);
	}
	
	public void resetAllBets()
	{
		for (Map.Entry<Player, String> entry : playerStates.entrySet())
		{
			playerStates.put(entry.getKey(), Status.NO_BET);
		}
	}

	public void setPlayerRolling(Player player)
	{
		playerStates.put(player, Status.ROLLING);
	}
	
	public void setPlayerHasRolled(Player player)
	{
		playerStates.put(player, Status.ROLLED);
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
	
	public boolean isAnyPlayerRolling()
	{
		return playerStates.containsValue(Status.ROLLING);
	}
}