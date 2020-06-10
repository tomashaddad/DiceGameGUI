package view.model;

import java.util.HashMap;
import java.util.Map;

import model.interfaces.Player;

public class ViewModel
{
	 static enum State { NEW, HAS_BET, IS_ROLLING, HAS_ROLLED };
	 
	 private Map<Player, State> playerStates = new HashMap<>();
	 
	 public void addPlayer(Player player)
	 {
		 playerStates.put(player, State.NEW);
	 }
	 
	 public void removePlayer(Player player)
	 {
		 playerStates.remove(player);
	 }
}