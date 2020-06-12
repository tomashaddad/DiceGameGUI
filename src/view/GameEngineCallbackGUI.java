package view;

import javax.swing.SwingUtilities;

import controller.manager.EventManager;
import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private EventManager eventManager;
	
	public GameEngineCallbackGUI(EventManager eventManager)
	{
		this.eventManager = eventManager;
	}
	
	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				eventManager.updatePlayerDie(player, die);
			}
		});
	}

	@Override
	public void houseDieUpdate(Die die, GameEngine gameEngine)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerResult(Player player, DicePair result, GameEngine gameEngine)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseResult(DicePair result, GameEngine gameEngine)
	{
		// TODO Auto-generated method stub
		
	}
}
