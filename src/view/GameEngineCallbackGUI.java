package view;

import javax.swing.SwingUtilities;

import model.interfaces.DicePair;
import model.interfaces.Die;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private DiceFrame frame;
	
	public GameEngineCallbackGUI(DiceFrame frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void playerDieUpdate(Player player, Die die, GameEngine gameEngine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if (die.getNumber() == 1)
				{
					frame.getDicePanel().getDie1().setValue(die.getValue());
				}
				
				if (die.getNumber() == 2)
				{
					frame.getDicePanel().getDie2().setValue(die.getValue());
				}

				frame.repaint();
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
