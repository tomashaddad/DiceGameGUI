package client;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.DiceGameFrame;

public class DiceGameApp
{
	public static void main(String[] args)
	{
		final GameEngine model = new GameEngineImpl();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new DiceGameFrame(model);
			}
		});
	}
	// research model view view model (MVVM)
}