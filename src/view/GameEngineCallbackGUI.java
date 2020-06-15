package view;

import controller.game.GameController;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private GameController gameController;
	
	public GameEngineCallbackGUI(GameController gameController)
	{
		this.gameController = gameController;
	}
	
	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
	{
		gameController.updatePlayerDie(player, die);
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine)
	{
		gameController.updateHouseDie(die);
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine)
	{
		gameController.announcePlayerResult(player);
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine)
	{
		gameController.announceHouseResult(result);
	}
}