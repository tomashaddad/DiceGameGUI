package model;

import model.interfaces.Player;

/* A wrapper object for players, which includes an instance variable denoting their winnings
 * the round before.
 * 
 * Since the player summary panel is implemented as a JList with a custom renderer, this class
 * had to be created in order to include the mutable "Win/loss last round: ________" label. */

public class PlayerListItem
{
	Player player;
	int winnings;
	
	public PlayerListItem(Player player)
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public int getWinnings()
	{
		return winnings;
	}
	
	public void setWinnings(int winnings)
	{
		this.winnings = winnings;
	}
}