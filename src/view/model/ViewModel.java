package view.model;

import java.util.HashMap;
import java.util.Map;

import constants.PlayerStatus;
import model.interfaces.Die;
import model.interfaces.Player;

public class ViewModel
{	 
	 private Map<Player, String> playerStates = new HashMap<>();
	 private Map<Player, Die> playerDies = new HashMap<>();
	 
	 public void addPlayer(Player player)
	 {
		 playerStates.put(player, PlayerStatus.NEW_PLAYER);
		 playerDies.put(player, null);
	 }
	 
	 public void setBet(Player player)
	 {

	 }
	 
	 public void removePlayer(Player player)
	 {
		 playerStates.remove(player);
	 }
	 
	 public void updatePlayerDie(Player player, Die die)
	 {
		 playerDies.put(player, die);
	 }
	 
	 public Map<Player, Die> getPlayerDies()
	 {
		 return playerDies;
	 }
}